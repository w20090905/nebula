package nebula.vm;

import static nebula.vm.BytecodeDefinition.INSTR_CALL;
import static nebula.vm.BytecodeDefinition.INSTR_FLOAD;
import static nebula.vm.BytecodeDefinition.INSTR_FSTORE;
import static nebula.vm.BytecodeDefinition.INSTR_IADD;
import static nebula.vm.BytecodeDefinition.INSTR_ICONST;
import static nebula.vm.BytecodeDefinition.INSTR_IMUL;
import static nebula.vm.BytecodeDefinition.INSTR_ISUB;
import static nebula.vm.BytecodeDefinition.INSTR_MOVE;
import static nebula.vm.BytecodeDefinition.INSTR_RET;
import static nebula.vm.BytecodeDefinition.INSTR_STRUCT;
import static nebula.vm.BytecodeDefinition.MASK_XX;
import static nebula.vm.BytecodeDefinition.MASK_X_;
import static nebula.vm.BytecodeDefinition.OFFSET_A_;
import static nebula.vm.BytecodeDefinition.OFFSET_BX;
import static nebula.vm.BytecodeDefinition.OFFSET_B_;
import static nebula.vm.BytecodeDefinition.OFFSET_C_;
import static nebula.vm.BytecodeDefinition.OFFSET_OP;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.TokenStream;

public class SourceCompiler extends NebulaParser {
	public static final int INITIAL_CODE_SIZE = 1024;
	public static final short UNKNOWN = 0;
	// protected Map<String, Integer> instructionOpcodeMapping = new
	// HashMap<String, Integer>();
	// protected Map<String, LabelSymbol> labels = // label scope
	// new HashMap<String, LabelSymbol>();

	protected List<Object> poolLocalK = new ArrayList<>();
	protected List<MethodSymbol> methods = new ArrayList<>();
	protected List<FieldSymbol> fields = new ArrayList<>();

	protected int ip = 0; // Instruction address pointer; used to fill code
	protected int[] codeBuffer = new int[INITIAL_CODE_SIZE]; // code memory

	public SourceCompiler(TokenStream input) {
		super(input);
	}

	protected void resolveTemp(TempVar v) {
		assert !v.applied;
		v.resolveForwardReferences(codeBuffer);
	}

	private short indexOfPool(Object o) {
		if (poolLocalK.contains(o))
			return (short) poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return (short) (poolLocalK.size() - 1);
	}

	@Override
	protected ClassSymbol resolveType(String name) {
		ClassSymbol clz = new ClassSymbol(name);
		short index = indexOfPool(clz);
		clz = (ClassSymbol) poolLocalK.get(index);
		return clz;
	};

	@Override
	protected ClassSymbol enterClass(String name, Type superType) {
		ClassSymbol v = new ClassSymbol(name);
		this.indexOfPool(v);
		return v;/* TODO add super class support */
	};

	@Override
	protected ClassSymbol exitClass(ClassSymbol clz) {
		clz.poolLocalK = this.poolLocalK.toArray();
		clz.fields = this.fields.toArray(new FieldSymbol[0]);
		clz.methods = this.methods.toArray(new MethodSymbol[0]);
		return clz;
	};

	@Override
	protected FieldSymbol defineField(ClassSymbol clz, String name, Type type) {
		FieldSymbol f = new FieldSymbol(clz, name, type);
		this.indexOfPool(f);
		this.fields.add(f);
		return f;
	};

	@Override
	protected MethodSymbol enterMethod(ClassSymbol clz, String name, Type returnType, List<Var> params) {
		MethodSymbol m = new MethodSymbol(clz, name, returnType);
		maxLocals = 0;

		for (Var v : params)
			pushLocal(v);

		Var varThis = new Var("this", clz);
		pushLocal(varThis);

		this.methods.add(m);
		this.indexOfPool(m);

		ip = 0;

		return m;
	};

	@Override
	protected MethodSymbol exitMethod(MethodSymbol method) {
		if (ip == 0 || codeBuffer[ip - 1] >>> OFFSET_OP == INSTR_RET) {
			gen(INSTR_RET, UNKNOWN);
		}

		int[] code = new int[ip];
		System.arraycopy(codeBuffer, 0, code, 0, ip);
		method.code = code;
		return method;
	};

	@Override
	protected Var add(Var a, Var b) {
		gen(INSTR_IADD, UNKNOWN, a.reg, b.reg);
		TempVar v = pick(a, b);
		v.addReference(ip, 1);
		return v;
	};

	@Override
	protected Var sub(Var a, Var b) {
		gen(INSTR_ISUB, UNKNOWN, a.reg, b.reg);
		TempVar v = pick(a, b);
		v.addReference(ip, 1);
		return v;
	};

	@Override
	protected Var mul(Var a, Var b) {
		gen(INSTR_IMUL, UNKNOWN, a.reg, b.reg);
		TempVar v = pick(a, b);
		v.addReference(ip, 1);
		return v;
	};

	@Override
	protected Var getField(Var obj, FieldSymbol field) {
		short index = indexOfPool(field);
		gen(INSTR_FLOAD, UNKNOWN, obj.reg, index);
		TempVar v = popTmp(BuiltInTypeSymbol.FLEX);
		v.addReference(ip, 1);
		return v;
	};

	@Override
	protected Var setField(Var obj, FieldSymbol field, Var v) {
		short index = indexOfPool(field);
		resolveTemp(v);
		
		gen(INSTR_FSTORE, v.reg, obj.reg, index);

		return v;
	};

	@Override
	protected Var invoke(Var obj, MethodSymbol method, List<Var> params) {
		short index = indexOfPool(method);

		gen(INSTR_CALL, (short) 0, index, params.get(0).reg);

		resolveTemp(obj);
		for (Var v : params) {
			resolveTemp(v);
		}
		TempVar v = popTmp(BuiltInTypeSymbol.FLEX);
		v.addReference(ip, 1);

		return v;
	};

	@Override
	protected Var set(Var to, Var from) {
		if (from.applied) {
			to = move(to, from);
		} else {
			TempVar tmp = (TempVar) from;
			tmp.reg = to.reg;
			tmp.resolveForwardReferences(codeBuffer);
			resolveTemp(from);
		}
		return to;
	};

	@Override
	protected Var set(String text, Type type, Var from) {
		Var to = new Var(text, type);
		pushLocal(to);

		if (from.applied) {
			pushLocal(to);
			to = move(to, from);
		} else {
			TempVar tmp = (TempVar) from;
			tmp.reg = to.reg;
			tmp.resolveForwardReferences(codeBuffer);
			resolveTemp(from);
		}
		return to;
	};

	@Override
	protected Var move(Var to, Var from) {
		gen(INSTR_MOVE, to.reg, from.reg);
		return to;
	}

	@Override
	protected Var createObject(ClassSymbol clz) {
		short index = indexOfPool(clz);
		gen(INSTR_STRUCT, UNKNOWN, index);

		TempVar tmp = popTmp(clz);
		tmp.addReference(ip, 1);
		return tmp;
	}

	@Override
	protected Var loadI(String text) {
		gen(INSTR_ICONST, UNKNOWN, Integer.parseInt(text));
		TempVar tmp = popTmp(BuiltInTypeSymbol.INT);
		tmp.addReference(ip, 1);
		return tmp;
	};

	private void gen(short op, short a) {
		gen(op, a, (short) 0, (short) 0);
	}

	private void gen(short op, short a, short b) {
		gen(op, a, b, (short) 0);
	}

	// @formatter:off
	protected void gen(short op, short a, short b, short c) {
		codeBuffer[ip++] = (op & 0xFF) << OFFSET_OP 
				| ((a & MASK_X_) << OFFSET_A_) 
				| ((b & MASK_X_) << OFFSET_B_)
				| ((c & MASK_X_) << OFFSET_C_);
	}

	protected void gen(short op, short a, int bx) {
		codeBuffer[ip++] = (op & 0xFF) << OFFSET_OP 
				| ((a & MASK_X_) << OFFSET_A_) 
				| ((bx & MASK_XX) << OFFSET_BX);
	}
	// @formatter:on
}

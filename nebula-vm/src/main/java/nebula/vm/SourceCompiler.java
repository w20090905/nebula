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

	// ************** START:members **************
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
		super.maxLocals = 0;

		initLocals();
		
		pushLocal("this", clz);

		for (Var v : params) {
			pushLocal(v.name, v.type);
		}

		m.nargs = params.size();
		this.methods.add(m);
		poolLocalK.set(indexOfPool(m), m);
		ip = 0;

		return m;
	};

	@Override
	protected MethodSymbol exitMethod(MethodSymbol method) {
		if (ip == 0 || codeBuffer[ip - 1] >>> OFFSET_OP != INSTR_RET) {
			gen(INSTR_RET, UNKNOWN);
		}

		int[] code = new int[ip];
		System.arraycopy(codeBuffer, 0, code, 0, ip);
		method.code = code;
		method.nlocals = super.maxLocals - method.nargs;
		return method;
	};

	@Override
	protected Var opIAdd(Var a, Var b) {
		if (!a.applied) resolveTemp(a);
		if (!b.applied) resolveTemp(b);
		TempVar v = popTmp(a.type);

		v.addReference(ip, 1);
		gen(INSTR_IADD, UNKNOWN, a.reg, b.reg);
		return v;
	};

	@Override
	protected Var opISub(Var a, Var b) {
		if (!a.applied) resolveTemp(a);
		if (!b.applied) resolveTemp(b);
		TempVar v = popTmp(a.type);

		v.addReference(ip, 1);
		gen(INSTR_ISUB, UNKNOWN, a.reg, b.reg);
		return v;
	};

	@Override
	protected Var opIMul(Var a, Var b) {
		if (!a.applied) resolveTemp(a);
		if (!b.applied) resolveTemp(b);
		TempVar v = popTmp(a.type);

		v.addReference(ip, 1);
		gen(INSTR_IMUL, UNKNOWN, a.reg, b.reg);
		return v;
	};

	@Override
	protected Var opFLoad(Var obj, FieldSymbol field) {
		if (!obj.applied) resolveTemp(obj);
		TempVar v = popTmp(BuiltInTypeSymbol.FLEX);

		short index = indexOfPool(field);

		v.addReference(ip, 1);
		gen(INSTR_FLOAD, UNKNOWN, obj.reg, index);

		return v;
	};

	@Override
	protected Var opFStore(Var obj, FieldSymbol field, Var v) {
		if (!obj.applied) resolveTemp(obj);
		if (!v.applied) resolveTemp(v);

		short index = indexOfPool(field);

		gen(INSTR_FSTORE, v.reg, obj.reg, index);

		return v;
	};

	@Override
	protected Var opInvoke(Var obj, MethodSymbol method, List<Var> params) {
		if (!obj.applied) resolveTemp(obj);
		for (Var vp : params) {
			if (!vp.applied) resolveTemp(vp);
		}
		TempVar v = popTmp(BuiltInTypeSymbol.FLEX);
		;
		short index = indexOfPool(method);
		if (params.size() > 0) {
			TempVar ov = (TempVar) params.get(0);
			assert v.reg == ov.reg;
			v.applied = true;
		} else {
			v.addReference(ip, 3);
		}
		gen(INSTR_CALL, obj.reg, index, v.reg);

		return v;
	};

	@Override
	protected Var opMove(Var to, Var from) {
		if (!from.applied) resolveTemp(from);

		if (to.applied) {
			gen(INSTR_MOVE, to.reg, from.reg);
		} else {
			((TempVar) to).addReference(ip, 1);
			gen(INSTR_MOVE, to.reg, from.reg);
		}

		return to;
	}

	@Override
	protected Var opNew(ClassSymbol clz) {
		TempVar v = popTmp(clz);

		v.addReference(ip, 1);
		gen(INSTR_STRUCT, UNKNOWN, indexOfPool(clz));

		return v;
	}

	@Override
	protected Var opILoad(String text) {
		TempVar v = popTmp(BuiltInTypeSymbol.INT);

		v.addReference(ip, 1);
		gen(INSTR_ICONST, UNKNOWN, Integer.parseInt(text));

		return v;
	};

	@Override
	protected Var opReturn(Var v) {
		if (!v.applied) resolveTemp(v);
		gen(INSTR_RET, v.reg);

		return v;
	}

	private short indexOfPool(Object o) {
		if (poolLocalK.contains(o)) return (short) poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return (short) (poolLocalK.size() - 1);
	}

	// ************** END :members **************

	// ************** START: Deal local **************
	@Override
	protected void resolveTemp(Var v) {
		assert !v.applied;
		((TempVar) v).resolveForwardReferences(codeBuffer);
	}

	@Override
	protected void resolveTemp(Var v, short reg) {
		assert !v.applied || v.reg == reg;
		if (!v.applied) ((TempVar) v).resolveForwardReferences(reg, codeBuffer);
	}

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
	// ************** END : Deal local **************
}

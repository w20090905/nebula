package nebula.vm;

import static nebula.vm.BytecodeDefinition.INSTR_IADD;
import static nebula.vm.BytecodeDefinition.INSTR_ICONST;
import static nebula.vm.BytecodeDefinition.INSTR_IMUL;
import static nebula.vm.BytecodeDefinition.INSTR_ISUB;
import static nebula.vm.BytecodeDefinition.INSTR_MOVE;
import static nebula.vm.BytecodeDefinition.INSTR_RET;
import static nebula.vm.BytecodeDefinition.MASK_XX;
import static nebula.vm.BytecodeDefinition.MASK_X_;
import static nebula.vm.BytecodeDefinition.OFFSET_A_;
import static nebula.vm.BytecodeDefinition.OFFSET_BX;
import static nebula.vm.BytecodeDefinition.OFFSET_B_;
import static nebula.vm.BytecodeDefinition.OFFSET_C_;
import static nebula.vm.BytecodeDefinition.OFFSET_OP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.TokenStream;

public class SourceCompiler extends NebulaParser {
	public static final int INITIAL_CODE_SIZE = 1024;
	protected Map<String, Integer> instructionOpcodeMapping = new HashMap<String, Integer>();
	protected Map<String, LabelSymbol> labels = // label scope
	new HashMap<String, LabelSymbol>();
	/**
	 * All float and string literals have unique int index in constant pool. We
	 * put FunctionSymbols in here too.
	 */
	protected List<Object> poolLocalK = new ArrayList<>();
	protected List<FunctionSymbol> functions = new ArrayList<>();
	protected List<FieldSymbol> fields = new ArrayList<>();

	protected int ip = 0; // Instruction address pointer; used to fill code
	protected int[] codeBuffer = new int[INITIAL_CODE_SIZE]; // code memory

	// protected int dataSize; // set via .globals

	protected FunctionSymbol currentFunction;
	protected ClassSymbol currentClass;

	public SourceCompiler(TokenStream input) {
		super(input);
	}

	List<Var> locals = new ArrayList<>();
	short maxLocals = 0;
	private void push(Var var) {
		locals.add(var);
		maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
	}

	@Override
	protected void enterClass(String name, Type superType) {
		this.currentClass = new ClassSymbol(name);
		toLocalConstantPoolIndex(this.currentClass);
	}

	@Override
	protected void exitClass() {
		currentClass.poolLocalK = this.poolLocalK.toArray();
		currentClass.fields = this.fields.toArray(new FieldSymbol[0]);
		currentClass.functions = this.functions.toArray(new FunctionSymbol[0]);
	};

	@Override
	protected void enterFunction(String name, Type returnType, List<Var> list) {
		if (currentFunction != null) {
			int[] code = new int[ip];
			System.arraycopy(codeBuffer, 0, code, 0, ip);
			currentFunction.code = code;
		}

		ip = 0;
		currentFunction = new FunctionSymbol(currentClass, name);
		functions.add(currentFunction);
		if (poolLocalK.contains(currentFunction))
			poolLocalK.set(poolLocalK.indexOf(currentFunction), currentFunction);
		else
			toLocalConstantPoolIndex(currentFunction); // save into constant
														// pool
	}

	@Override
	protected void exitFunction() {
		if (codeBuffer[ip - 1] >>> OFFSET_OP == INSTR_RET) {
			gen(INSTR_RET);
		}

		int[] code = new int[ip];
		System.arraycopy(codeBuffer, 0, code, 0, ip);
		this.currentFunction.code = code;
	};

	@Override
	protected void defField(String name, Type type) {
		FieldSymbol field = new FieldSymbol(this.currentClass, name);
		toLocalConstantPoolIndex(field);
		fields.add(field);
	}

	@Override
	protected Var refField(Var obj, String text) {
		return null;
	};

	@Override
	protected Var invoke(Var name, String funcName, List<Var> list) {
		return null;
	};

	@Override
	protected Var invokeStatic(String name, List<Var> list) {
		return null;
	};

	@Override
	protected Var index(Var obj, Var i) {
		return null;
	};

	@Override
	protected Var index(Var obj, List<Var> cause) {
		return null;
	};

	@Override
	protected void ret(Var a) {
		;
	};

	@Override
	protected Var resolve(String name) {
		for (int i = 0; i < locals.size(); i++) {
			if (locals.get(i).getName().equals(name)) {
				return locals.get(i);
			}
		}
		return null;
	};

	@Override
	protected Type resolveType(String name) {
		Type type = new ClassSymbol(name);
		if (poolLocalK.contains(type))
			type = (Type) poolLocalK.get(poolLocalK.indexOf(type));
		else
			toLocalConstantPoolIndex(type);
		return type;
	};

	@Override
	protected Var defInt(String value) {
		Var var = new Var(value, SymbolTable._int, ip, 1);
		System.out.println("const	" + var);
		push(var);
		gen(INSTR_ICONST, var.reg, java.lang.Integer.parseInt(value));
		return var;
	};

	@Override
	protected Var defVariable(String name, Type type) {
		Var var = new Var(name, type, (short) locals.size());
		push(var);
		System.out.println("var " + var);
		return var;
	}

	@Override
	protected Var eval(Var a) {
		if (!a.applied) {
			a.reg = (short) locals.indexOf(a);
			a.resolveForwardReferences(codeBuffer);
			locals.remove(a.reg);
		}
		System.out.println("eval 	" + a);
		return a;
	};

	@Override
	protected Var evalSet(String id, Var b) {
		Var var = resolve(id);
		if (!b.applied) {
			if (var == null) {
				b.setName(id);
				b.reg = (short) locals.indexOf(b);
				b.resolveForwardReferences(codeBuffer);
				var = b;
			} else {
				b.setName(var.getName());
				b.reg = var.reg;
				b.resolveForwardReferences(codeBuffer);
				var = b;
			}
		} else if (var == null) {
			b.setName(id);
			b.resolveForwardReferences(codeBuffer);
			var = b;
		} else {
			gen(INSTR_MOVE, var.reg, b.reg);
		}
		System.out.println("evalSet	" + var);
		return var;
	};

	public ClassSymbol finished() {

		if (currentClass == null) {//("Anonymous");
			throw new UnsupportedOperationException("currentClass");
		}
		if (functions.size() == 0) {
			throw new UnsupportedOperationException("functions");
		}

		return currentClass;
	}

	private int toLocalConstantPoolIndex(Object o) {
		if (poolLocalK.contains(o))
			return poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return poolLocalK.size() - 1;
	}

	private Var bop(Var a, Var b) {
		Var var;
		if (!a.applied) {
			var = a;
			a.addReference(ip, 1);
			a.addReference(ip, 2);
			if (!b.applied) {
				b.addReference(ip, 3);
				short i = (short) locals.indexOf(b);
				b.reg = i;
				b.resolveForwardReferences(codeBuffer);
				if (i + 1 == locals.size()) {
					locals.remove(i);
				}
				System.out.println("bop rm	" + b);
			}
		} else if (!b.applied) {
			var = b;
			a.addReference(ip, 1);
			a.addReference(ip, 3);
		} else {
			// add new temp variable
			var = new Var("Temp" + locals.size(), SymbolTable._int, ip, 1);
			push(var);
		}
		System.out.println("bop 	" + var);
		return var;
	};

	@Override
	protected Var add(Var a, Var b) {
		Var var = bop(a, b);
		gen(INSTR_IADD, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected Var sub(Var a, Var b) {
		Var var = bop(a, b);
		gen(INSTR_ISUB, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected Var mul(Var a, Var b) {
		Var var = bop(a, b);
		gen(INSTR_IMUL, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected Var load(Var a, Var b) {
		Var var = bop(a, b);
		gen(INSTR_ICONST, a.reg, java.lang.Integer.parseInt(b.getName()));
		return var;
	}

	private void gen(short op) {
		gen(op, (short) 0, (short) 0, (short) 0);
	}

	// private void gen(short op, short a) {
	// gen(op, a, (short) 0, (short) 0);
	// }

	private void gen(short op, short a, short b) {
		gen(op, a, b, (short) 0);
	}

	// @formatter:off
	protected void gen(short op, short a, short b, short c) {
		codeBuffer[ip++] = (op & 0xFF) << OFFSET_OP | ((a & MASK_X_) << OFFSET_A_) | ((b & MASK_X_) << OFFSET_B_)
				| ((c & MASK_X_) << OFFSET_C_);
	}

	protected void gen(short op, short a, int bx) {
		codeBuffer[ip++] = (op & 0xFF) << OFFSET_OP | ((a & MASK_X_) << OFFSET_A_) | ((bx & MASK_XX) << OFFSET_BX);
	}
	// @formatter:on
}

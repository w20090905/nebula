package nebula.vm;

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
	protected List<MethodSymbol> functions = new ArrayList<>();
	protected List<FieldSymbol> fields = new ArrayList<>();

	protected int ip = 0; // Instruction address pointer; used to fill code
	protected int[] codeBuffer = new int[INITIAL_CODE_SIZE]; // code memory

	// protected int dataSize; // set via .globals

	protected MethodSymbol currentFunction;
	protected ClassSymbol currentClass;

	public SourceCompiler(TokenStream input) {
		super(input);
	}

	private short toLocalConstantPoolIndex(Object o) {
		if (poolLocalK.contains(o))
			return (short) poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return (short) (poolLocalK.size() - 1);
	}

	@Override
	protected ClassSymbol enterClass(String name, Type superType) {
		this.currentClass = new ClassSymbol(name);
		toLocalConstantPoolIndex(this.currentClass);
		return this.currentClass;
	}

	@Override
	protected void exitClass() {
		currentClass.poolLocalK = this.poolLocalK.toArray();
		currentClass.fields = this.fields.toArray(new FieldSymbol[0]);
		currentClass.functions = this.functions.toArray(new MethodSymbol[0]);
	};

	@Override
	protected MethodSymbol enterFunction(ClassSymbol clz, String name, Type returnType, List<Var> list) {
		ip = 0;
		this.currentFunction = new MethodSymbol(clz, name);
		functions.add(currentFunction);
		if (poolLocalK.contains(currentFunction))
			poolLocalK.set(poolLocalK.indexOf(currentFunction), currentFunction);
		else
			toLocalConstantPoolIndex(currentFunction); // save into constant
														// pool
		locals.clear();
		return this.currentFunction;
		// locals.add(new Var("ret", BuiltInTypeSymbol.INT, NOT_APPLIED));
	}

	@Override
	protected void exitFunction() {
		if (ip == 0 || codeBuffer[ip - 1] >>> OFFSET_OP == INSTR_RET) {
			gen(INSTR_RET);
		}

		int[] code = new int[ip];
		System.arraycopy(codeBuffer, 0, code, 0, ip);
		this.currentFunction.code = code;
	};

//	@Override
//	protected void defField(String name, Type type) {
//		FieldSymbol field = new FieldSymbol(this.currentClass, name);
//		toLocalConstantPoolIndex(field);
//		fields.add(field);
//	}

	// @Override
	// protected Var refField(Var obj, String text) {
	// short index = toLocalConstantPoolIndex(new FieldSymbol((ClassSymbol)
	// obj.type, text));
	// gen(INSTR_FLOAD, top.reg, obj.reg, index);
	// return top;
	// };
	//
	// @Override
	// protected Var invoke(Var name, String funcName, List<Var> list) {
	// return null;
	// };
	//
	// @Override
	// protected Var invokeStatic(String name, List<Var> list) {
	// return null;
	// };
	//
	// @Override
	// protected Var index(Var obj, Var i) {
	// return null;
	// };
	//
	// @Override
	// protected Var index(Var obj, List<Var> cause) {
	// return null;
	// };
	//
	// @Override
	// protected void ret(Var a) {
	// ;
	// };

//	@Override
//	protected Var v(String name) {
//		for (int i = 0; i < locals.size(); i++) {
//			if (locals.get(i).getName().equals(name)) {
//				return locals.get(i);
//			}
//		}
//		return null;
//	};

	@Override
	protected Type resolveType(String name) {
		Type type = new ClassSymbol(name);
		if (poolLocalK.contains(type))
			type = (Type) poolLocalK.get(poolLocalK.indexOf(type));
		else
			toLocalConstantPoolIndex(type);
		return type;
	};

	// @Override
	// protected Var defInt(String value) {
	// Var v = new Var(value, SymbolTable._int, ip, 1);
	// System.out.println("const \t" + v.name);
	// gen(INSTR_ICONST, NOT_APPLIED, Integer.parseInt(value));
	// return v;
	// };
	//
	// @Override
	// protected Var opNew(Type type) {
	// ClassSymbol clz = (ClassSymbol)type;
	// short iCont = toLocalConstantPoolIndex(clz);
	// top.addReference(ip, 1);
	// gen(INSTR_STRUCT, top.reg, iCont);
	// return top;
	// }
	//
	// @Override
	// protected Var eval(Var b) {
	// if (!b.applied) {
	// apply(b, NOT_APPLIED);
	// }
	// return b;
	// };

//	private void apply(Var b, short reg) {
//		b.reg = reg;
//		b.resolveForwardReferences(codeBuffer);
//	}
//
//	private void apply(Var b) {
//		push(b);
//		b.resolveForwardReferences(codeBuffer);
//	}

	//
	// @Override
	// protected Var evalSet(Var vTo, Var vFrom) {
	// if (!vFrom.applied) {
	// apply(vFrom, vTo.reg);
	// } else {
	// gen(INSTR_MOVE, vTo.reg, vFrom.reg);
	// }
	// return vTo;
	// };

	public ClassSymbol finished() {
		return currentClass;
	}

	//
	// private Var bop(Var a, Var b) {
	// Var v = null;
	// if (!a.applied && !b.applied) {
	// a.addReference(ip, 2);
	// apply(b);
	// v = a;
	// } else if (!a.applied) {
	// a.addReference(ip, 2);
	// v = a;
	// } else if (!b.applied) {
	// b.addReference(ip, 3);
	// v = b;
	// } else {
	// // v = new Var("tmp", a.type, ip, 1);
	// }
	// v.addReference(ip, 1);
	// return v;
	// };
	//
	// @Override
	// protected Var add(Var a, Var b) {
	// Var var = bop(a, b);
	// gen(INSTR_IADD, var.reg, a.reg, b.reg);
	// return var;
	// }
	//
	// @Override
	// protected Var sub(Var a, Var b) {
	// Var var = bop(a, b);
	// gen(INSTR_ISUB, var.reg, a.reg, b.reg);
	// return var;
	// }
	//
	// @Override
	// protected Var mul(Var a, Var b) {
	// Var var = bop(a, b);
	// gen(INSTR_IMUL, var.reg, a.reg, b.reg);
	// return var;
	// }
	//
	// @Override
	// protected Var load(Var a, Var b) {
	// Var var = bop(a, b);
	// gen(INSTR_ICONST, a.reg, java.lang.Integer.parseInt(b.getName()));
	// return var;
	// }

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

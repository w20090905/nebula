package nebula.vm;

import static nebula.vm.BytecodeDefinition.INSTR_HALT;
import static nebula.vm.BytecodeDefinition.INSTR_IADD;
import static nebula.vm.BytecodeDefinition.INSTR_ICONST;
import static nebula.vm.BytecodeDefinition.INSTR_IMUL;
import static nebula.vm.BytecodeDefinition.INSTR_ISUB;
import static nebula.vm.BytecodeDefinition.INSTR_MOVE;
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

import org.antlr.runtime.Token;
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

	List<VariableSymbol> locals = new ArrayList<>();
	short maxLocals = 0;

	private void addLocals(VariableSymbol var) {
		locals.add(var);
		maxLocals = maxLocals > (short) locals.size() ? maxLocals : (short) locals.size();
	}

	@Override
	protected VariableSymbol resolve(String name) {
		for (int i = 0; i < locals.size(); i++) {
			if (locals.get(i).getName().equals(name)) {
				return locals.get(i);
			}
		}
		return null;
	};

	/** After parser is complete, look for unresolved labels */
	protected void checkForUnresolvedReferences() {
		for (String name : labels.keySet()) {
			LabelSymbol sym = (LabelSymbol) labels.get(name);
			if (!sym.isDefined) {
				System.err.println("unresolved reference: " + name);
			}
		}
	}

	/** Compute the code address of a label */
	protected int getLabelAddress(String id, int offset) {
		LabelSymbol sym = (LabelSymbol) labels.get(id);
		if (sym == null) {
			// assume it's a forward code reference; record opnd address
			sym = new LabelSymbol(id, ip - 1, offset, true);
			sym.isDefined = false;
			labels.put(id, sym); // add to symbol table
		} else {
			if (sym.isForwardRef) {
				// address is unknown, must simply add to forward ref list
				// record where in code memory we should patch later
				sym.addForwardReference(ip - 1, offset);
			} else {
				// all is well; it's defined--just grab address
				return sym.address;
			}
		}
		return 0; // we don't know the real address yet
	}

	@Override
	protected void enterClass(String name) {
		this.currentClass = new ClassSymbol(name);
		toLocalConstantPoolIndex(this.currentClass);
	}

	@Override
	protected void defineField(String name, Type type) {
		FieldSymbol field = new FieldSymbol(this.currentClass, name);
		toLocalConstantPoolIndex(field);
		fields.add(field);
	}

	@Override
	protected void enterFunction(String name, Type returnType) {
		if (currentFunction != null) {
			int[] code = new int[ip];
			System.arraycopy(codeBuffer, 0, code, 0, ip);
			currentFunction.code = code;
		}

		ip = 0;
		currentFunction = new FunctionSymbol(currentClass, name);
		functions.add(currentFunction);
		// if (name.equals("main")) mainFunction = f;
		// Did someone referred to this function before it was defined?
		// if so, replace element in constant pool (at same index)
		if (poolLocalK.contains(currentFunction))
			poolLocalK.set(poolLocalK.indexOf(currentFunction), currentFunction);
		else
			toLocalConstantPoolIndex(currentFunction); // save into constant
														// pool
	}

	protected void enterFunction(String name, int args, int locals) {
		if (currentFunction != null) {
			int[] code = new int[ip];
			System.arraycopy(codeBuffer, 0, code, 0, ip);
			currentFunction.code = code;
		}

		ip = 0;
		currentFunction = new FunctionSymbol(currentClass, name, args, locals, codeBuffer);
		functions.add(currentFunction);
		// if (name.equals("main")) mainFunction = f;
		// Did someone referred to this function before it was defined?
		// if so, replace element in constant pool (at same index)
		if (poolLocalK.contains(currentFunction))
			poolLocalK.set(poolLocalK.indexOf(currentFunction), currentFunction);
		else
			toLocalConstantPoolIndex(currentFunction); // save into constant
														// pool
	}

	// protected int getFunctionIndexx(String id) {
	// int i = poolLocalK.indexOf(new FunctionSymbol(id));
	// if (i >= 0) return i; // already in system; return index.
	// // must be a forward function reference
	// // create the constant pool entry; we'll fill in later
	// return toLocalConstantPoolIndex(new FunctionSymbol(id));
	// }

	// protected void defineDataSize(int n) {
	// dataSize = n;
	// }

	protected void defineLabel(Token idToken) {
		String id = idToken.getText();
		LabelSymbol sym = (LabelSymbol) labels.get(id);
		if (sym == null) {
			LabelSymbol csym = new LabelSymbol(id, ip, 0, false);
			labels.put(id, csym); // add to symbol table
		} else {
			if (sym.isForwardRef) {
				// we have found definition of forward
				sym.isDefined = true;
				sym.address = ip;
				sym.resolveForwardReferences(codeBuffer);
			} else {
				// redefinition of symbol
				System.err.println("line " + idToken.getLine() + ": redefinition of symbol " + id);
			}
		}
	}

	protected void ensureCapacity(int index) {
		if (index >= codeBuffer.length) { // expand
			int newSize = Math.max(index, codeBuffer.length) * 2;
			int[] bigger = new int[newSize];
			System.arraycopy(codeBuffer, 0, bigger, 0, codeBuffer.length);
			codeBuffer = bigger;
		}
	}

	@Override
	protected VariableSymbol defineVariable(String name) {
		return defineVariable(name, SymbolTable._int);
	};

	protected VariableSymbol defineInt(String name) {
		return defineInt(name, SymbolTable._int);
	};

	protected VariableSymbol defineVariable(String name, Type type) {
		VariableSymbol var = new VariableSymbol(name, type, (short) locals.size());
		addLocals(var);
		System.out.println("var " + var);
		return var;
	}

	protected VariableSymbol defineInt(String value, Type type) {
		VariableSymbol var = new VariableSymbol(value, type, ip, 1);
		System.out.println("const	" + var);
		addLocals(var);
		gen(INSTR_ICONST, var.reg, java.lang.Integer.parseInt(value));
		return var;
	}

	@Override
	protected VariableSymbol eval(VariableSymbol a) {
		if (!a.resolved) {
			a.reg = (short) locals.indexOf(a);
			a.resolveForwardReferences(codeBuffer);
			locals.remove(a.reg);
		}
		System.out.println("eval 	" + a);
		return a;
	};

	@Override
	protected VariableSymbol evalSet(String id, VariableSymbol b) {
		VariableSymbol var = resolve(id);
		if (!b.resolved) {
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
		} else { // var = var
			gen(INSTR_MOVE, var.reg, b.reg);
		}
		System.out.println("evalSet	" + var);
		return var;
	};

	public ClassSymbol finished() {

		if (currentClass == null) {
			currentClass = new ClassSymbol("Anonymous");
			toLocalConstantPoolIndex(this.currentClass);
		}
		if (functions.size() == 0) {
			currentFunction = new FunctionSymbol(currentClass, "main");
			currentFunction.nlocals = maxLocals;
			toLocalConstantPoolIndex(currentFunction); // save into constant
			this.functions.add(currentFunction);
			gen(INSTR_HALT);
		}

		if (currentFunction != null) {
			int[] code = new int[ip];
			System.arraycopy(codeBuffer, 0, code, 0, ip);
			this.currentFunction.code = code;
		}

		currentClass.poolLocalK = this.poolLocalK.toArray();
		currentClass.fields = this.fields.toArray(new FieldSymbol[0]);
		currentClass.functions = this.functions.toArray(new FunctionSymbol[0]);
		return currentClass;
	}

	protected int toLocalConstantPoolIndex(Object o) {
		if (poolLocalK.contains(o))
			return poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return poolLocalK.size() - 1;
	}

	private VariableSymbol bop(VariableSymbol a, VariableSymbol b) {
		VariableSymbol var;
		if (!a.resolved) {
			var = a;
			a.addReference(ip, 1);
			a.addReference(ip, 2);
			if (!b.resolved) {
				b.addReference(ip, 3);
				short i = (short) locals.indexOf(b);
				b.reg = i;
				b.resolveForwardReferences(codeBuffer);
				if (i + 1 == locals.size()) {
					locals.remove(i);
				}
				System.out.println("bop rm	" + b);
			}
		} else if (!b.resolved) {
			var = b;
			a.addReference(ip, 1);
			a.addReference(ip, 3);
		} else {
			// add new temp variable
			var = new VariableSymbol("Temp" + locals.size(), SymbolTable._int, ip, 1);
			addLocals(var);
		}
		System.out.println("bop 	" + var);
		return var;
	};

	@Override
	protected VariableSymbol add(VariableSymbol a, VariableSymbol b) {
		VariableSymbol var = bop(a, b);
		gen(INSTR_IADD, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected VariableSymbol sub(VariableSymbol a, VariableSymbol b) {
		VariableSymbol var = bop(a, b);
		gen(INSTR_ISUB, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected VariableSymbol mul(VariableSymbol a, VariableSymbol b) {
		VariableSymbol var = bop(a, b);
		gen(INSTR_IMUL, var.reg, a.reg, b.reg);
		return var;
	}

	@Override
	protected VariableSymbol load(VariableSymbol a, VariableSymbol b) {
		VariableSymbol var = bop(a, b);
		gen(INSTR_ICONST, a.reg, java.lang.Integer.parseInt(b.getName()));
		return var;
	}

	protected void gen(short op) {
		gen(op, (short) 0, (short) 0, (short) 0);
	}

	protected void gen(short op, short a) {
		gen(op, a, (short) 0, (short) 0);
	}

	protected void gen(short op, short a, short b) {
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

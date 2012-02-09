package nebula.vm;

/***
 \ * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
import static nebula.vm.BytecodeDefinition.INSTR_BR;
import static nebula.vm.BytecodeDefinition.INSTR_BRF;
import static nebula.vm.BytecodeDefinition.INSTR_BRT;
import static nebula.vm.BytecodeDefinition.INSTR_CALL;
import static nebula.vm.BytecodeDefinition.INSTR_CCONST;
import static nebula.vm.BytecodeDefinition.INSTR_FADD;
import static nebula.vm.BytecodeDefinition.INSTR_FCONST;
import static nebula.vm.BytecodeDefinition.INSTR_FEQ;
import static nebula.vm.BytecodeDefinition.INSTR_FLOAD;
import static nebula.vm.BytecodeDefinition.INSTR_FLT;
import static nebula.vm.BytecodeDefinition.INSTR_FMUL;
import static nebula.vm.BytecodeDefinition.INSTR_FSTORE;
import static nebula.vm.BytecodeDefinition.INSTR_FSUB;
import static nebula.vm.BytecodeDefinition.INSTR_GLOAD;
import static nebula.vm.BytecodeDefinition.INSTR_GSTORE;
import static nebula.vm.BytecodeDefinition.INSTR_HALT;
import static nebula.vm.BytecodeDefinition.INSTR_IADD;
import static nebula.vm.BytecodeDefinition.INSTR_ICONST;
import static nebula.vm.BytecodeDefinition.INSTR_IEQ;
import static nebula.vm.BytecodeDefinition.INSTR_ILT;
import static nebula.vm.BytecodeDefinition.INSTR_IMUL;
import static nebula.vm.BytecodeDefinition.INSTR_ISUB;
import static nebula.vm.BytecodeDefinition.INSTR_ITOF;
import static nebula.vm.BytecodeDefinition.INSTR_MOVE;
import static nebula.vm.BytecodeDefinition.INSTR_NULL;
import static nebula.vm.BytecodeDefinition.INSTR_PRINT;
import static nebula.vm.BytecodeDefinition.INSTR_RET;
import static nebula.vm.BytecodeDefinition.INSTR_SCONST;
import static nebula.vm.BytecodeDefinition.INSTR_STRUCT;
import static nebula.vm.BytecodeDefinition.MASK_XX;
import static nebula.vm.BytecodeDefinition.MASK_X_;
import static nebula.vm.BytecodeDefinition.OFFSET_AX;
import static nebula.vm.BytecodeDefinition.OFFSET_A_;
import static nebula.vm.BytecodeDefinition.OFFSET_BX;
import static nebula.vm.BytecodeDefinition.OFFSET_B_;
import static nebula.vm.BytecodeDefinition.OFFSET_C_;
import static nebula.vm.BytecodeDefinition.OFFSET_OP;
import static nebula.vm.BytecodeDefinition.FALSE;
import static nebula.vm.BytecodeDefinition.TRUE;
import static nebula.vm.BytecodeDefinition.instructions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

/** A simple stack-based interpreter */
public class Interpreter3 {
	public static final int DEFAULT_OPERAND_STACK_SIZE = 100;
	public static final int DEFAULT_CALL_STACK_SIZE = 1000;

	public static final int DEFAULT_Class_POOL_SIZE = 1000;
	public static final int DEFAULT_String_POOL_SIZE = 10000;
	public static final int DEFAULT_Decimal_POOL_SIZE = 10000;

	public static final int DEFAULT_Object_POOL_SIZE = 10000;

	public static final int DEFAULT_PREPAREED_NUMBER_STRING_RANGE = 1000;

	private DisAssembler disasm = new DisAssembler();

	@Deprecated
	int[] globals; // global variable space
	final ClassSymbol[] poolClass = new ClassSymbol[DEFAULT_Class_POOL_SIZE];
	final String[] poolString = new String[DEFAULT_String_POOL_SIZE];
	final BigDecimal[] poolDecimal = new BigDecimal[DEFAULT_Decimal_POOL_SIZE];

	final int[][] poolH = new int[DEFAULT_Object_POOL_SIZE][];
	int pPoolH = 0;

	final Map<String, Integer> mapString = new HashMap<>(DEFAULT_Decimal_POOL_SIZE * 2);
	int pPoolString = 0;

	private int indexOf(String v) {
		Integer i = mapString.get(v);
		if (i != null) return i.intValue();
		else {
			mapString.put(v, ++pPoolString);
			poolString[pPoolString] = v;
			return pPoolString;
		}
	}

	final Map<ClassSymbol, Integer> mapClass = new HashMap<>(DEFAULT_Class_POOL_SIZE * 2);
	int pPoolClass = 0;

	private int indexOf(ClassSymbol v) {
		Integer i = mapClass.get(v);
		if (i != null) return i.intValue();
		else {
			mapClass.put(v, ++pPoolClass);
			poolClass[pPoolClass] = v;
			return pPoolString;
		}
	}

	public Interpreter3() {
		this(false);
	}

	public Interpreter3(boolean trace) {
		this.trace = trace;
		for (int i = 0; i < DEFAULT_PREPAREED_NUMBER_STRING_RANGE; i++) {
			indexOf(String.valueOf(i));
		}

		poolH[0] = new int[1];
		poolH[0][0] = Integer.MAX_VALUE;
		pPoolH = 1;
	}

	private void __halt() {
		for (int i = 0; i < pPoolH; i++)
			poolH[i] = null;
		pPoolH = 0;
	}

	private boolean contain(ClassSymbol v) {
		return mapClass.containsKey(v);
	}

	/** Stack of stack frames, grows upwards */
	final StackFrame[] calls = new StackFrame[DEFAULT_CALL_STACK_SIZE];
	int fp = -1; // frame pointer register

	final private boolean trace;

	public static void main(String[] args) throws Exception {
		// PROCESS ARGS
		boolean trace = false;
		boolean disassemble = false;
		boolean dump = false;
		String filename = null;
		int i = 0;
		while (i < args.length) {
			if (args[i].equals("-trace")) {
				trace = true;
				i++;
			} else if (args[i].equals("-dis")) {
				disassemble = true;
				i++;
			} else if (args[i].equals("-dump")) {
				dump = true;
				i++;
			} else {
				filename = args[i];
				i++;
			}
		}

		InputStream input = null;
		if (filename != null) input = new FileInputStream(filename);
		else input = System.in;

		Interpreter3 interpreter = new Interpreter3(trace);
		ClassSymbol clz = load(input);
		interpreter.resolve(clz);
		interpreter.exec(interpreter.resolve(clz.getEntryPoint()));
		if (disassemble) interpreter.disassemble();
		if (dump) interpreter.coredump();
	}

	public void resolve(ClassSymbol classSymbol) {
		this.indexOf(classSymbol);
		int offset = 1;
		for (FieldSymbol f : classSymbol.fields) {
			f.defineAt(offset);
			offset += f.lenght;
		}
	}

	public ClassSymbol loadClass(String className) {
		ClassSymbol classSymbol = load(className + ".rc2");
		this.resolve(classSymbol);
		return classSymbol;
	}

	public static ClassSymbol load(String filename) {
		try {
			InputStream input = new FileInputStream(filename);
			return load(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ClassSymbol load(InputStream input) throws Exception {
		boolean hasErrors = false;
		try {
			AssemblerLexer assemblerLexer = new AssemblerLexer(new ANTLRInputStream(input));
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			BytecodeAssembler assembler = new BytecodeAssembler(tokens, instructions);
			assembler.program();
			ClassSymbol clz = assembler.finished();
			hasErrors = assembler.getNumberOfSyntaxErrors() > 0;
			if (!hasErrors) {
				return clz;
			}
			throw new RuntimeException("ERROR");
		} finally {
			input.close();
		}
	}

	/** Execute the bytecodes in code memory starting at mainAddr */
	public void exec(FunctionSymbol mainFunction) throws Exception {

		for (int i = 0; i < 1; i++) {
			fp = -1;
			StackFrame f = new StackFrame(mainFunction, 0, 0);
			calls[++fp] = f;
			cpu();
		}

		int max = 1000;
		long start = 0, end = 0;
		start = System.nanoTime();
		for (int i = 0; i < max; i++) {
			fp = -1;
			StackFrame f = new StackFrame(mainFunction, 0, 0);
			calls[++fp] = f;
			cpu();
		}
		end = System.nanoTime();
		System.out.println(mainFunction.definedClass.name + " -> (" + max + " times)" + " cost : " + (end - start) + "  \t||  "
				+ (end - start) / max + " / every time \t|| " + (max * ((float) (1000 * 1000 * 1000) / (end - start)))
				+ " times / every second");
	}

	/** Simulate the fetch-execute cycle */
	protected void cpu() {
		StackFrame currentFrame = calls[fp];
		int[] r = currentFrame.registers;
		int[] code = currentFrame.sym.code;
		Object[] poolK = currentFrame.sym.getConstPool();
		long maskObject = currentFrame.maskObject;
		int ip = 0;
		int op = code[ip++];

		int ra = 0;
		int rb = 0;

		Outter: for (;; op = code[ip++]) {
//			if (trace) trace(ip - 1);
			ra = A(op);

			switch (OP_CODE(op)) {

			//@formatter:off
			case INSTR_IADD:	r[ra] = r[B(op)] + r[C(op)];	break;
			case INSTR_ISUB:	r[ra] = r[B(op)] - r[C(op)];	break;
			case INSTR_IMUL:	r[ra] = r[B(op)] * r[C(op)];	break;
			case INSTR_ILT:		r[ra] = r[B(op)] < r[C(op)] ? TRUE : FALSE;	break;
			case INSTR_IEQ:		r[ra] = r[B(op)] == r[C(op)] ? TRUE : FALSE;	break;
			
			case INSTR_FADD:	r[ra] = addF(r[B(op)], r[C(op)]);	break;
			case INSTR_FSUB:	r[ra] = subF(r[B(op)], r[C(op)]);	break;
			case INSTR_FMUL:	r[ra] = mulF(r[B(op)], r[C(op)]);	break;
			case INSTR_FLT:		r[ra] = ltF(r[B(op)], r[C(op)]) ? TRUE : FALSE;	break;
			case INSTR_FEQ:		r[ra] = eqF(r[B(op)],r[C(op)]) ? TRUE : FALSE;	break;
			
			case INSTR_ITOF:	/*r[j] = (float) (((Integer)r[i]).intValue());*/	break;
			//@formatter:on

			case INSTR_CALL: {
				currentFrame.maskObject = maskObject;
				__call(currentFrame, B(op), C(op), ra, ip);
				ip = 0;

				currentFrame = calls[fp];
				r = currentFrame.registers;
				code = currentFrame.sym.code;
				poolK = currentFrame.sym.getConstPool();
				break;
			}

			case INSTR_RET: {
				rb = BX(op);
				StackFrame retFrame = calls[fp--]; // pop stack frame
				int[] rRet = retFrame.registers;

				if (maskObject > 0) {
					// clear ref object, don't deal args and ret param
					for (int a = retFrame.sym.nargs; a < ra; a++) {
						if ((maskObject & (1L << a)) > 0) {
							int index = rRet[a];
							if (poolH[index][0] < 2) {
								poolH[index] = null;
							} else {
								poolH[index][0]--;
							}
						}
					}
				}

				currentFrame = calls[fp];
				ip = retFrame.returnAddress;

				r = currentFrame.registers;

				int firstResult = retFrame.firstResult;

				for (int a = 0; a < rb; a++) {
					r[firstResult + a] = rRet[ra + a];
				}

				code = currentFrame.sym.code;
				poolK = currentFrame.sym.getConstPool();
				maskObject = currentFrame.maskObject;

				break;
			}

			//@formatter:off
			case INSTR_BR:	ip = BX(op);	break;
			case INSTR_BRT:	if (r[ra] == TRUE) ip = BX(op);	break;
			case INSTR_BRF:	if (r[ra] != TRUE) ip = BX(op);	break;

			case INSTR_CCONST:	r[ra] = BX(op);	break;
			case INSTR_ICONST:	r[ra] = BX(op);	break;
			case INSTR_FCONST:	r[ra] = ((Integer)poolK[B(op)]).intValue();	break; // TODO not implements
			case INSTR_SCONST:	r[ra] = ((Integer)poolK[B(op)]).intValue();	break; // TODO not implements

			case INSTR_GLOAD:	r[ra] = globals[BX(op)];	break;
			case INSTR_GSTORE:	globals[AX(op)] = r[B(op)];	break;

			case INSTR_FLOAD:	r[ra] = poolH[r[B(op)]][((FieldSymbol) poolK[C(op)]).offset];break;
			case INSTR_FSTORE:	poolH[r[ra]][((FieldSymbol) poolK[B(op)]).offset] = r[C(op)];break;
			//@formatter:on

			case INSTR_MOVE: {
				r[ra] = r[B(op)];
				break;
			}
			case INSTR_PRINT:
				// System.out.println(poolString[r[ra]]);
				break;
			case INSTR_STRUCT: {
				if ((maskObject & (1L << ra)) > 0) {
					int index = r[ra];
					if (poolH[index][0] < 2) {
						poolH[index] = null;
					} else {
						poolH[index][0]--;
					}
				}
				r[ra] = __newStruct(((ClassSymbol) poolK[B(op)]).getLength());
				maskObject |= (1L << ra);
				break;
			}

			case INSTR_NULL:
				poolH[r[ra]][0]--;
				maskObject |= (1L << ra);
				break;
			case INSTR_HALT: {
				__halt();
				break Outter;
			}
			case BytecodeDefinition.INSTR_OMOVE: {
				if ((maskObject & (1L << ra)) > 0) {
					int index = r[ra];
					if (poolH[index][0] < 2) {
						poolH[index] = null;
					} else {
						poolH[index][0]--;
					}
				}
				int index = r[B(op)];
				poolH[index][0]++;
				r[ra] = index;
				break;
			}
			default:
				throw new Error("Address : " + ip + " ;invalid opcode: " + Integer.toBinaryString(op) + " at ip="
						+ (ip - 1));
			}
		}
		// @formatter:on
	}

	/** Resolve Function Symbol */
	protected FunctionSymbol resolve(FunctionSymbol func) {
		if (func.resolved) return func;

		Object[] poolK = func.getConstPool();
		int[] code = func.code;
		int ip = 0;
		int op = code[ip++];

		for (; ip < code.length; op = code[ip++]) {
			// if (trace) trace();
			switch (OP_CODE(op)) {

			case INSTR_SCONST: {
				int index = B(op);
				String str = (String) poolK[index];
				poolK[index] = indexOf(str);
				break;
			}

			case INSTR_CALL: {
				int index = B(op);

				FunctionSymbol fs = (FunctionSymbol) poolK[index];
				ClassSymbol clzSymbol = fs.definedClass;

				if (!contain(clzSymbol)) {
					this.loadClass(clzSymbol.name);
				}

				poolK[index] = poolClass[indexOf(clzSymbol)].getFunction(fs.name);
				break;
			}

			case INSTR_FLOAD: {
				int index = C(op);

				FieldSymbol fs = (FieldSymbol) poolK[index];
				ClassSymbol clzSymbol = fs.definedClass;

				if (!contain(clzSymbol)) {
					this.loadClass(clzSymbol.name);
				}

				poolK[index] = poolClass[indexOf(clzSymbol)].getField(fs.name);
				break;
			}
			case INSTR_FSTORE: {
				int index = B(op);

				FieldSymbol fs = (FieldSymbol) poolK[index];
				ClassSymbol clzSymbol = fs.definedClass;

				if (!contain(clzSymbol)) {
					this.loadClass(clzSymbol.name);
				}

				poolK[index] = poolClass[indexOf(clzSymbol)].getField(fs.name);
				break;
			}

			case INSTR_STRUCT: {
				int index = B(op);

				ClassSymbol clzSymbol = (ClassSymbol) poolK[index];

				if (!contain(clzSymbol)) {
					this.loadClass(clzSymbol.name);
				}

				poolK[index] = poolClass[indexOf(clzSymbol)];
				break;
			}
			default:
				break;
			}

		}
		func.resolved = true;
		return func;
	}

	private int OP_CODE(int op) {
		return (op >>> OFFSET_OP);
	}

	private int A(int op) {
		return op >>> OFFSET_A_ & MASK_X_;
	}

	private int B(int op) {
		return op >>> OFFSET_B_ & MASK_X_;
	}

	private int C(int op) {
		return op >>> OFFSET_C_ & MASK_X_;
	}

	private int AX(int op) {
		return op >>> OFFSET_AX & MASK_XX;
	}

	private int BX(int op) {
		return op >>> OFFSET_BX & MASK_XX;
	}

	private int __newStruct(int size) {
		int index = 0;
		for (; index < pPoolH; index++) {
			if (poolH[index] == null) break;
		}
		poolH[index] = new int[size];
		poolH[index][0] = 1;
		pPoolH = pPoolH > index + 1 ? pPoolH : index + 1;
		return index;
	}

	private void __call(StackFrame currentFrame, int functionConstPoolIndex, int baseRegisterIndex, int firstResult,
			int ip) {
		final FunctionSymbol fs = (FunctionSymbol) currentFrame.sym.getConstPool()[functionConstPoolIndex];
		if (!fs.resolved) resolve(fs);
		final StackFrame f = new StackFrame(fs, ip, firstResult);
		final StackFrame callingFrame = calls[fp];
		calls[++fp] = f; // push new stack frame
		// move arguments from calling stack frame to new stack frame
		final int[] r = f.registers;
		final int[] rCalling = callingFrame.registers;
		for (int a = 0; a < fs.nargs; a++) { // move args, leaving room for r0
			r[a + 1] = rCalling[baseRegisterIndex + a];
		}
	}

	/**
	 * Pull off 4 bytes starting at ip and return 32-bit signed int value.
	 * Return with ip pointing *after* last byte of operand. The byte-order is
	 * high byte down to low byte, left to right.
	 */
	// protected int code[ip++] {
	// int word = BytecodeAssembler.getInt(code, ip);
	// ip += 4;
	// return word;
	// }

	// Tracing, dumping, ...
	public void disassemble() {
		for (int i = 1; i <= pPoolClass; i++) {
			ClassSymbol clz = poolClass[i];
			disasm.disassemble(clz);
		}
	}

	protected void trace(int ip) {
		StackFrame currentfFrame = calls[fp];
		if (ip == 0) {
			System.out.println("");
			System.out.println("Enter .function " + currentfFrame.sym.definedClass.name + "." + currentfFrame.sym.name);
		}
		disasm.disassembleInstruction(currentfFrame.sym.code, currentfFrame.sym.getConstPool(), ip);
		int[] r = currentfFrame.registers;
		if (r.length > 0) {
			System.out.print("\t\t" + calls[fp].sym.name + ".registers=[");
			;
			for (int i = 0; i < r.length; i++) {
				if (i == 1) System.out.print(" |");
				if (i == calls[fp].sym.nargs + 1 && i == 1) System.out.print("|");
				else if (i == calls[fp].sym.nargs + 1) System.out.print(" |");
				System.out.print(" ");
				if (r[i] == 0) System.out.print("_");
				else System.out.print(r[i]);
			}
			System.out.print(" ]");
		}
		if (fp >= 0) {
			System.out.print("  calls=[");
			for (int i = 0; i <= fp; i++) {
				System.out.print(" " + calls[i].sym.name);
			}
			System.out.print(" ]");
		}
		System.out.println();
	}

	public void coredump() {
		for (int i = 1; i <= pPoolClass; i++) {
			ClassSymbol clz = poolClass[i];

			if (clz.poolLocalK.length > 0) dumpConstantPool(clz.poolLocalK);
			// if (globals.length > 0) dumpDataMemory();
			for (FunctionSymbol f : clz.functions) {
				System.out.println("");
				System.out.println(".def " + f.name + " args=" + f.nargs + ", locals=" + f.nlocals + " ");
				dumpCodeMemory(f.code);
			}
		}
	}

	protected void dumpConstantPool(Object[] poolK) {
		System.out.println("Constant pool:");
		int addr = 0;
		for (Object o : poolK) {
			if (o instanceof String) {
				System.out.printf("%04d: \"%s\"\n", addr, o);
			} else {
				System.out.printf("%04d: %s\n", addr, o);
			}
			addr++;
		}
		System.out.println();
	}

	private boolean ltF(int b, int c) {
		return true;
	}

	private boolean eqF(int b, int c) {
		return true;
	}

	private int addF(int b, int c) {
		return 0;
	}

	private int subF(int b, int c) {
		return 0;
	}

	private int mulF(int b, int c) {
		return 0;
	}

	// private int divF(int b, int c) {
	// return 0;
	// }

	protected void dumpDataMemory() {
		System.out.println("Data memory:");
		int addr = 0;
		for (Object o : globals) {
			if (o != null) {
				System.out.printf("%04d: %s <%s>\n", addr, o, o.getClass().getSimpleName());
			} else {
				System.out.printf("%04d: <null>\n", addr);
			}
			addr++;
		}
		System.out.println();
	}

	public void dumpCodeMemory(int[] code) {
		System.out.println("Code memory:");
		for (int i = 0; code != null && i < code.length; i++) {
			if (i % 8 == 0 && i != 0) System.out.println();
			if (i % 8 == 0) System.out.printf("%04d:", i);
			System.out.printf(" %3d", (code[i] >>> OFFSET_OP) & 0xFFFFFFFF);
		}
		System.out.println();
	}
}

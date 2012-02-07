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
import static nebula.vm.BytecodeDefinition.MASK_AX;
import static nebula.vm.BytecodeDefinition.MASK_A_;
import static nebula.vm.BytecodeDefinition.MASK_BX;
import static nebula.vm.BytecodeDefinition.MASK_B_;
import static nebula.vm.BytecodeDefinition.MASK_C_;
import static nebula.vm.BytecodeDefinition.OFFSET_AX;
import static nebula.vm.BytecodeDefinition.OFFSET_A_;
import static nebula.vm.BytecodeDefinition.OFFSET_BX;
import static nebula.vm.BytecodeDefinition.OFFSET_B_;
import static nebula.vm.BytecodeDefinition.OFFSET_C_;
import static nebula.vm.BytecodeDefinition.OFOP;
import static nebula.vm.BytecodeDefinition._FALSE;
import static nebula.vm.BytecodeDefinition._TRUE;
import static nebula.vm.BytecodeDefinition.instructions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

/** A simple stack-based interpreter */
public class Interpreter3 {
	public static final int DEFAULT_OPERAND_STACK_SIZE = 100;
	public static final int DEFAULT_CALL_STACK_SIZE = 1000;

	// DisAssembler disasm;

	int ip; // instruction pointer register
	int[] code; // byte-addressable code memory.
	int codeSize;
	int[] globals; // global variable space
	protected String[] poolString;
	protected BigDecimal[] poolDecimal;
	protected ClassSymbol poolClass;
	
	protected Object[] poolK;
	/** Stack of stack frames, grows upwards */
	StackFrame[] calls = new StackFrame[DEFAULT_CALL_STACK_SIZE];
	int fp = -1; // frame pointer register
	FunctionSymbol mainFunction;

	boolean trace = false;

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

		Interpreter3 interpreter = new Interpreter3();
		load(interpreter, input);
		interpreter.trace = trace;
		interpreter.exec();
		if (disassemble) interpreter.disassemble();
		if (dump) interpreter.coredump();
	}

	public static boolean load(Interpreter3 interp, InputStream input) throws Exception {
		boolean hasErrors = false;
		try {
			AssemblerLexer assemblerLexer = new AssemblerLexer(new ANTLRInputStream(input));
			CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
			BytecodeAssembler assembler = new BytecodeAssembler(tokens, instructions);
			assembler.program();
			interp.code = assembler.getMachineCode();
			interp.codeSize = assembler.getCodeMemorySize();
			interp.poolK = assembler.getConstantPool();
			interp.mainFunction = assembler.getMainFunction();
			interp.globals = new int[assembler.getDataSize()];
			// interp.disasm = new DisAssembler(interp.code, interp.codeSize,
			// interp.constPool);
			hasErrors = assembler.getNumberOfSyntaxErrors() > 0;
		} finally {
			input.close();
		}
		return hasErrors;
	}

	/** Execute the bytecodes in code memory starting at mainAddr */
	public void exec() throws Exception {

		// SIMULATE "call main()"; set up stack as if we'd called main()
		if (mainFunction == null) {
			mainFunction = new FunctionSymbol("main", 0, 0, 0);
		}

		for (int i = 0; i < 1; i++) {
			fp = -1;
			ip = 0;
			StackFrame f = new StackFrame(mainFunction, ip);
			calls[++fp] = f;
			ip = mainFunction.address;
			cpu();
		}

		int max = 1;
		long start = 0, end = 0;
		start = System.nanoTime();
		for (int i = 0; i < max; i++) {
			fp = -1;
			ip = 0;
			StackFrame f = new StackFrame(mainFunction, ip);
			calls[++fp] = f;
			ip = mainFunction.address;
			cpu();
		}
		end = System.nanoTime();
		System.out.println(this.getClass().getName() + " : " + (end - start) / max);
	}

	/** Simulate the fetch-execute cycle */
	protected void cpu() {
		int op = code[ip++];
		int r[] = calls[fp].registers;
		Outter: for(;ip < codeSize;op = code[ip++]) {
			// if (trace) trace();
			switch ((op >>> OFOP) & 0xFFFFFFFF) {
			case INSTR_IADD:	r[A(op)] = r[B(op)] + r[C(op)];		break;
			case INSTR_ISUB:	r[A(op)] = r[B(op)] - r[C(op)];		break;
			case INSTR_IMUL:	r[A(op)] = r[B(op)] * r[C(op)];		break;
			case INSTR_ILT:		r[A(op)] = r[B(op)] < r[C(op)] ? _TRUE : _FALSE;	break;
			case INSTR_IEQ:		r[A(op)] = r[B(op)] == r[C(op)] ? _TRUE : _FALSE;	break;
			case INSTR_FADD:	r[A(op)] = addF(r[B(op)], r[C(op)]);	break;
			case INSTR_FSUB:	r[A(op)] = subF(r[B(op)], r[C(op)]);	break;
			case INSTR_FMUL:	r[A(op)] = mulF(r[B(op)], r[C(op)]);	break;
			case INSTR_FLT:		r[A(op)] = ltF(r[B(op)], r[C(op)]) ? _TRUE	: _FALSE;	break;
			case INSTR_FEQ:		r[A(op)] = r[B(op)] == r[C(op)] ? _TRUE : _FALSE;	break;
			case INSTR_ITOF:// r[j] = (float) (((Integer) r[i]).intValue());break;
			case INSTR_CALL:	call(A(op), B(op));	r = calls[fp].registers;	break;
			case INSTR_RET:
				StackFrame f = calls[fp--]; // pop stack frame
				calls[fp].registers[0] = f.registers[0];
				ip = f.returnAddress;
				r = calls[fp].registers;
				break;
			case INSTR_BR:		ip = AX(op);break;
			case INSTR_BRT:		if (r[A(op)] == _TRUE) ip = BX(op);	break;
			case INSTR_BRF:		if (r[A(op)] != _TRUE) ip = BX(op);	break;
			
			case INSTR_CCONST:	r[A(op)] = BX(op);	break;
			case INSTR_ICONST:	r[A(op)] = BX(op);	break;
			
			case INSTR_FCONST:	r[A(op)] = B(op);	break; // TODO not implements
			case INSTR_SCONST:  r[A(op)] = B(op); 	break; // TODO not implements
			
			case INSTR_GLOAD:	r[A(op)] = globals[BX(op)];	break;
			case INSTR_GSTORE:	globals[AX(op)] = r[B(op)];	break;
			
			case INSTR_FLOAD:	r[A(op)] = poolH[r[B(op)]][((FieldSymbol) poolK[C(op)]).offset];break;
			case INSTR_FSTORE:	poolH[r[A(op)]][((FieldSymbol) poolK[B(op)]).offset] = r[C(op)];break;
			
			case INSTR_MOVE:	r[A(op)] = r[B(op)];	break;
			case INSTR_PRINT:	System.out.println(r[A(op)]);	break;
			case INSTR_STRUCT:	r[A(op)] = newStruct(((ClassSymbol) poolK[B(op)]).getLength());break;
			case INSTR_NULL:	r[A(op)] = 0;	break;
			case INSTR_HALT:	break Outter;
			default:
				throw new Error("Address : " + ip + " ;invalid opcode: " + Integer.toBinaryString(op) + " at ip="
						+ (ip - 1));
			}
			
		}
	}

	static final int A(int op){
		return (op & MASK_A_) >>> OFFSET_A_;
	}
	static final int B(int op){
		return (op & MASK_B_) >>> OFFSET_B_;
	}
	static final int C(int op){
		return (op & MASK_C_) >>> OFFSET_C_;
	}	
	static final int AX(int op){
		return (op & MASK_AX) >>> OFFSET_AX;
	}
	static final int BX(int op){
		return (op & MASK_BX) >>> OFFSET_BX;
	}

	int[][] poolH = new int[100][];
	int structPoolSize = 0;

	protected int newStruct(int size) {
		poolH[structPoolSize] = new int[size];
		return structPoolSize++;
	}

	protected void call(int functionConstPoolIndex, int baseRegisterIndex) {
		FunctionSymbol fs = (FunctionSymbol) poolK[functionConstPoolIndex];
		StackFrame f = new StackFrame(fs, ip);
		StackFrame callingFrame = calls[fp];
		calls[++fp] = f; // push new stack frame
		// move arguments from calling stack frame to new stack frame
		for (int a = 0; a < fs.nargs; a++) { // move args, leaving room for r0
			f.registers[a + 1] = callingFrame.registers[baseRegisterIndex + a];
		}
		ip = fs.address; // branch to function
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
		// disasm.disassemble();
	}

	protected void trace() {
		// disasm.disassembleInstruction(ip);
		// int[] r = calls[fp].registers;
		// if (r.length > 0) {
		// System.out.print("\t" + calls[fp].sym.name + ".registers=[");
		// ;
		// for (int i = 0; i < r.length; i++) {
		// if (i == 1) System.out.print(" |");
		// if (i == calls[fp].sym.nargs + 1 && i == 1) System.out.print("|");
		// else if (i == calls[fp].sym.nargs + 1) System.out.print(" |");
		// System.out.print(" ");
		// if (r[i] == 0) System.out.print("?");
		// else System.out.print(r[i]);
		// }
		// System.out.print(" ]");
		// }
		// if (fp >= 0) {
		// System.out.print("  calls=[");
		// for (int i = 0; i <= fp; i++) {
		// System.out.print(" " + calls[i].sym.name);
		// }
		// System.out.print(" ]");
		// }
		// System.out.println();
	}

	public void coredump() {
		if (poolK.length > 0) dumpConstantPool();
		if (globals.length > 0) dumpDataMemory();
		dumpCodeMemory();
	}

	protected void dumpConstantPool() {
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

	static final boolean ltF(int b, int c) {
		return true;
	}

	static final int addF(int b, int c) {
		return 0;
	}

	static final int subF(int b, int c) {
		return 0;
	}

	static final int mulF(int b, int c) {
		return 0;
	}

	static final int float_div(int b, int c) {
		return 0;
	}

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

	public void dumpCodeMemory() {
		System.out.println("Code memory:");
		for (int i = 0; code != null && i < codeSize; i++) {
			if (i % 8 == 0 && i != 0) System.out.println();
			if (i % 8 == 0) System.out.printf("%04d:", i);
			System.out.printf(" %3d", code[i]);
		}
		System.out.println();
	}
}

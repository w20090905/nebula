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
import static nebula.vm.BytecodeDefinition.MKAX;
import static nebula.vm.BytecodeDefinition.MKA_;
import static nebula.vm.BytecodeDefinition.MKBX;
import static nebula.vm.BytecodeDefinition.MKB_;
import static nebula.vm.BytecodeDefinition.MKC_;
import static nebula.vm.BytecodeDefinition.OFAX;
import static nebula.vm.BytecodeDefinition.OFA_;
import static nebula.vm.BytecodeDefinition.OFBX;
import static nebula.vm.BytecodeDefinition.OFB_;
import static nebula.vm.BytecodeDefinition.OFC_;
import static nebula.vm.BytecodeDefinition.OFOP;
import static nebula.vm.BytecodeDefinition.instructions;

import java.io.FileInputStream;
import java.io.InputStream;

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
	protected Object[] constPool;
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
			interp.constPool = assembler.getConstantPool();
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

	static byte CONST_TRUE = 1;
	static byte CONST_FALSE = 0;

	/** Execute the bytecodes in code memory starting at mainAddr */
	public void exec() throws Exception {

		// SIMULATE "call main()"; set up stack as if we'd called main()
		if (mainFunction == null) {
			mainFunction = new FunctionSymbol("main", 0, 0, 0);
		}

		for (int i = 0; i < 5; i++) {
			fp = -1;
			ip = 0;
			StackFrame f = new StackFrame(mainFunction, ip);
			calls[++fp] = f;
			ip = mainFunction.address;
			cpu();
		}

		int max = 100;
		long start = System.nanoTime();
		for (int i = 0; i < max; i++) {
			fp = -1;
			ip = 0;
			StackFrame f = new StackFrame(mainFunction, ip);
			calls[++fp] = f;
			ip = mainFunction.address;
			cpu();
		}
		long end = System.nanoTime();
		System.out.println(this.getClass().getName() + " : " + (end - start) / max);
	}

	/** Simulate the fetch-execute cycle */
	protected void cpu() {
		int a = 0, b = 0, c = 0;
		int op = code[ip++];
		int r[] = calls[fp].registers;
		Outter: while (ip < codeSize) {
			// if (trace) trace();
			// ip++; // jump to next instruction or first byte of operand

			switch (op >> OFOP) {
			case INSTR_IADD:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] + r[(op & MKC_) >> OFC_];
				break;
			// ...
			case INSTR_ISUB:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] - r[(op & MKC_) >> OFC_];
				break;
			case INSTR_IMUL:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] * r[(op & MKC_) >> OFC_];
				break;
			case INSTR_ILT:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] < r[(op & MKC_) >> OFC_] ? CONST_TRUE : CONST_FALSE;
				break;
			case INSTR_IEQ:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] == r[(op & MKC_) >> OFC_] ? CONST_TRUE : CONST_FALSE;
				break;
			case INSTR_FADD:
				r[(op & MKA_) >> OFA_] = float_add(r[(op & MKB_) >> OFB_], r[(op & MKC_) >> OFC_]);
				break;
			case INSTR_FSUB:
				r[(op & MKA_) >> OFA_] = float_sub(r[(op & MKB_) >> OFB_], r[(op & MKC_) >> OFC_]);
				break;
			case INSTR_FMUL:
				r[(op & MKA_) >> OFA_] = float_mul(r[(op & MKB_) >> OFB_], r[(op & MKC_) >> OFC_]);
				break;
			case INSTR_FLT:
				r[(op & MKA_) >> OFA_] = float_lt(r[(op & MKB_) >> OFB_], r[(op & MKC_) >> OFC_]) ? CONST_TRUE
						: CONST_FALSE;
				break;
			case INSTR_FEQ:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_] == r[(op & MKC_) >> OFC_] ? CONST_TRUE : CONST_FALSE;
				break;
			case INSTR_ITOF:
				// i = code[ip++];
				// j = code[ip++];
				// r[j] = (float) (((Integer) r[i]).intValue());
				break;
			case INSTR_CALL:
				int funcStringIndex = (op & MKA_) >> OFA_;
				int baseRegisterIndex = (op & MKB_) >> OFB_;
				call(funcStringIndex, baseRegisterIndex);
				r = calls[fp].registers;
				break;
			case INSTR_RET:
				StackFrame f = calls[fp--]; // pop stack frame
				calls[fp].registers[0] = f.registers[0];
				ip = f.returnAddress;
				r = calls[fp].registers;
				break;
			case INSTR_BR:
				ip = (op & MKAX) >> OFAX;
				break;
			case INSTR_BRT:
				if (r[(op & MKA_) >> OFA_] == CONST_TRUE) ip = (op & MKBX) >> OFBX;
				break;
			case INSTR_BRF:
				if (r[(op & MKA_) >> OFA_] != CONST_TRUE) ip = (op & MKBX) >> OFBX;
				break;
			case INSTR_CCONST:
				r[(op & MKA_) >> OFA_] = (op & MKBX) >> OFBX;
				break;
			case INSTR_ICONST:
				r[(op & MKA_) >> OFA_] = (op & MKBX) >> OFBX;
				break;
			// case INSTR_FCONST:
			case INSTR_SCONST:
				r[(op & MKA_) >> OFA_] = (op & MKB_) >> OFB_;
				break;
			case INSTR_GLOAD:
				r[(op & MKA_) >> OFA_] = globals[(op & MKB_) >> OFB_];
				break;
			case INSTR_GSTORE:
				globals[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_];
				break;
			case INSTR_FLOAD:
				a = (op & MKA_) >> OFA_;
				b = (op & MKB_) >> OFB_;
				c = (op & MKC_) >> OFC_;
				r[a] = structPool[r[b]][((FieldSymbol) constPool[c]).offset];
				break;
			case INSTR_FSTORE:
				a = (op & MKA_) >> OFA_;
				b = (op & MKB_) >> OFB_;
				c = (op & MKC_) >> OFC_;
				structPool[r[a]][((FieldSymbol) constPool[b]).offset] = r[c];
				break;
			case INSTR_MOVE:
				r[(op & MKA_) >> OFA_] = r[(op & MKB_) >> OFB_];
				break;
			case INSTR_PRINT:
				a = (op & MKA_) >> OFA_;
//				System.out.println(r[a]);
				break;
			case INSTR_STRUCT:
				a = (op & MKA_) >> OFA_;
				b = (op & MKB_) >> OFB_;
				int nfields = ((ClassSymbol) constPool[b]).getLength();
				r[a] = newStruct(nfields);
				break;
			case INSTR_NULL:
				r[(op & MKA_) >> OFA_] = 0;
				break;
			case INSTR_HALT:
				break Outter;
			default:
				throw new Error("invalid opcode: " + Integer.toBinaryString(op) + " at ip=" + (ip - 1));
			}
			op = code[ip++];
		}
	}

	int[][] structPool = new int[100][];
	int structPoolSize = 0;

	protected int newStruct(int size) {
		structPool[structPoolSize] = new int[size];
		return structPoolSize++;
	}

	protected void call(int functionConstPoolIndex, int baseRegisterIndex) {
		FunctionSymbol fs = (FunctionSymbol) constPool[functionConstPoolIndex];
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
		if (constPool.length > 0) dumpConstantPool();
		if (globals.length > 0) dumpDataMemory();
		dumpCodeMemory();
	}

	protected void dumpConstantPool() {
		System.out.println("Constant pool:");
		int addr = 0;
		for (Object o : constPool) {
			if (o instanceof String) {
				System.out.printf("%04d: \"%s\"\n", addr, o);
			} else {
				System.out.printf("%04d: %s\n", addr, o);
			}
			addr++;
		}
		System.out.println();
	}

	static final boolean float_lt(int b, int c) {
		return true;
	}

	static final int float_add(int b, int c) {
		return 0;
	}

	static final int float_sub(int b, int c) {
		return 0;
	}

	static final int float_mul(int b, int c) {
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

package nebula.vm;

/***
 \ * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
import static nebula.vm.BytecodeDefinition.FALSE;
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
import static nebula.vm.BytecodeDefinition.INSTR_FORLOOP;
import static nebula.vm.BytecodeDefinition.INSTR_FORPREP;
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
import static nebula.vm.BytecodeDefinition.TRUE;
import static nebula.vm.BytecodeDefinition.instructions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

/** A simple stack-based interpreter */
public class VMInterpreter {
    public static final int DEFAULT_OPERAND_STACK_SIZE = 100;
    public static final int DEFAULT_CALL_STACK_SIZE = 1000;

    public static final int DEFAULT_Class_POOL_SIZE = 1000;
    public static final int DEFAULT_String_POOL_SIZE = 10000;
    public static final int DEFAULT_Decimal_POOL_SIZE = 10000;

    public static final int DEFAULT_Object_POOL_SIZE = 10000;
    public static final int DEFAULT_Stack_SIZE = 64 * 1024;

    public static final int DEFAULT_PREPAREED_NUMBER_STRING_RANGE = 1000;

    private DisAssembler disasm = new DisAssembler();

    @Deprecated
    int[] globals; // global variable space
    final ClassSymbol[] poolClass = new ClassSymbol[DEFAULT_Class_POOL_SIZE];
    final String[] poolString = new String[DEFAULT_String_POOL_SIZE];
    final BigDecimal[] poolDecimal = new BigDecimal[DEFAULT_Decimal_POOL_SIZE];

    final int[][] poolH = new int[DEFAULT_Object_POOL_SIZE][];
    int pPoolH = 0;

    /** Stack of stack frames, grows upwards */
    final MethodSymbol[] calls = new MethodSymbol[DEFAULT_CALL_STACK_SIZE];
    int fp = -1; // frame pointer register

    final int[] r = new int[DEFAULT_Stack_SIZE];

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
            return pPoolClass;
        }
    }

    public VMInterpreter() {
        this(false, false);
    }

    public VMInterpreter(boolean trace, boolean checkPerformance) {
        this.trace = trace;
        this.checkPerformance = checkPerformance;
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

    final private boolean trace;
    final private boolean checkPerformance;

    public static void main(String[] args) throws Exception {
        // PROCESS ARGS
        boolean trace = false;
        boolean disassemble = false;
        boolean checkPerformance = false;
        boolean dump = false;
        String filename = null;
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-t") || args[i].equals("-trace")) {
                trace = true;
                i++;
            } else if (args[i].equals("-d") || args[i].equals("-dis")) {
                disassemble = true;
                i++;
            } else if (args[i].equals("-p")) {
                checkPerformance = true;
                i++;
            } else if (args[i].equals("-dump")) {
                dump = true;
                i++;
            } else {
                filename = args[i];
                i++;
            }
        }

        ClassSymbol clz = null;
        if (filename != null) {
            clz = load(filename);
        } else {
            clz = loadFromSource(System.in);
        }
        VMInterpreter interpreter = new VMInterpreter(trace, checkPerformance);
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

    private ClassSymbol loadClass(String className) {
        ClassSymbol classSymbol = load(className);
        this.resolve(classSymbol);
        return classSymbol;
    }

    public static ClassSymbol load(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists() || !file.isFile()) {
                file = new File(filename + ".b");
            }
            if (!file.exists() || !file.isFile()) {
                file = new File(filename + ".n");
            }
            if (!file.exists() || !file.isFile()) {
                file = new File(filename + ".asm");
            }
            if (!file.exists() || !file.isFile()) {
                throw new RuntimeException("Can't find file " + filename);
            }
            filename = file.getName();
            String ext = filename.substring(filename.lastIndexOf('.') + 1);
            InputStream input = new FileInputStream(file);
            ClassSymbol clz = null;
            //@formatter:off
			switch (ext) {
			case "n":	clz = loadFromSource(input);	break;
			case "asm":	clz = loadFromASM(input);		break;
			default:	clz = loadFromBin(input);		break;
			}
			//@formatter:on
            return clz;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ClassSymbol loadFromASM(InputStream input) throws Exception {
        boolean hasErrors = false;
        try {
            AssemblerLexer lexer = new AssemblerLexer(new ANTLRInputStream(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AsmCompiler parser = new AsmCompiler(tokens, instructions);
            parser.program();
            ClassSymbol clz = parser.finished();
            hasErrors = parser.getNumberOfSyntaxErrors() > 0;
            if (!hasErrors) {
                return clz;
            }
            throw new RuntimeException("ERROR");
        } finally {
            input.close();
        }
    }

    private static ClassSymbol loadFromSource(InputStream input) throws Exception {
        boolean hasErrors = false;
        try {
        	NebulaRegisterLexer lexer = new NebulaRegisterLexer(new ANTLRInputStream(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SourceCompiler parser = new SourceCompiler(tokens);
            // parser.compilationUnit();
            ClassSymbol clz = parser.classDefinition();
            hasErrors = parser.getNumberOfSyntaxErrors() > 0;
            if (!hasErrors) {
                return clz;
            }
            throw new RuntimeException("ERROR");
        } finally {
            input.close();
        }
    }

    private static ClassSymbol loadFromBin(InputStream input) throws Exception {
        // boolean hasErrors = false;
        // try {
        // AssemblerLexer assemblerLexer = new AssemblerLexer(new
        // ANTLRInputStream(input));
        // CommonTokenStream tokens = new CommonTokenStream(assemblerLexer);
        // BytecodeAssembler assembler = new BytecodeAssembler(tokens,
        // instructions);
        // assembler.program();
        // ClassSymbol clz = assembler.finished();
        // hasErrors = assembler.getNumberOfSyntaxErrors() > 0;
        // if (!hasErrors) {
        // return clz;
        // }
        // throw new RuntimeException("ERROR");
        // } finally {
        // input.close();
        // }
        throw new UnsupportedOperationException("loadFromBin");
    }

    /** Execute the bytecodes in code memory starting at mainAddr */
    public int exec(MethodSymbol mainFunction) throws Exception {
        int ret = 0;
        if (!checkPerformance) {
            fp = -1;
            calls[++fp] = mainFunction;
            r[0] = __new(mainFunction.definedClass);
            ret = cpu();
            if (trace) System.out.println("return " + ret);
            return ret;
        }

        for (int i = 0; i < 0; i++) {
            fp = -1;
            calls[++fp] = mainFunction;
            ret = cpu();
            if (trace) System.out.println("return " + ret);
        }

        int max = 1;
        long start = 0, end = 0;
        start = System.nanoTime();
        for (int i = 0; i < max; i++) {
            fp = -1;
            calls[++fp] = mainFunction;
            ret = cpu();
            if (trace) System.out.println("return " + ret);
        }
        end = System.nanoTime();
        System.out.println(mainFunction.definedClass.name + " -> (" + max + " times)" + " cost : " + (end - start)
                + "  \t||  " + (end - start) / max + " / every time \t|| "
                + (max * ((float) (1000 * 1000 * 1000) / (end - start))) + " times / every second");
        return ret;
    }

    /** Simulate the fetch-execute cycle */
    protected int cpu() {
        MethodSymbol funcTo = null;
        int baseTo = 0;

        MethodSymbol func = calls[fp];
        int[] code = func.code;
        Object[] poolK = func.getConstPool();
        int maskObject = 0;
        int ip = 0;
        int op = 0;

        int base = 0;

        int ra = 0;
        int rb = 0;

        int length = code.length;

        Outter: for (; ip < length;) {
            op = code[ip++];
            if (trace) trace(ip - 1, base);
            ra = A(op);

            switch (OP_CODE(op)) {

            //@formatter:off
			case INSTR_IADD:	r[base+ra] = r[base+B(op)] + r[base+C(op)];	break;
			case INSTR_ISUB:	r[base+ra] = r[base+B(op)] - r[base+C(op)];	break;
			case INSTR_IMUL:	r[base+ra] = r[base+B(op)] * r[base+C(op)];	break;
			case INSTR_ILT:		r[base+ra] = r[base+B(op)] < r[base+C(op)] ? TRUE : FALSE;	break;
			case INSTR_IEQ:		r[base+ra] = r[base+B(op)] == r[base+C(op)] ? TRUE : FALSE;	break;
			
			case INSTR_FADD:	r[base+ra] = addF(r[base+B(op)], r[base+C(op)]);	break;
			case INSTR_FSUB:	r[base+ra] = subF(r[base+B(op)], r[base+C(op)]);	break;
			case INSTR_FMUL:	r[base+ra] = mulF(r[base+B(op)], r[base+C(op)]);	break;
			case INSTR_FLT:		r[base+ra] = ltF(r[base+B(op)], r[base+C(op)]) ? TRUE : FALSE;	break;
			case INSTR_FEQ:		r[base+ra] = eqF(r[base+B(op)],r[base+C(op)]) ? TRUE : FALSE;	break;
			
			case INSTR_ITOF:	/*r[base+j] = (float) (((Integer)r[base+i]).intValue());*/	break;
			//@formatter:on

            case INSTR_CALL: {
                // 1、backup current frame status
                baseTo = base + func.nargs + func.nlocals + 1 + 4;

                r[baseTo - 4] = base; // callling function locals start
                r[baseTo - 3] = ip; // calling function ip
                r[baseTo - 2] = C(op); // params start index
                r[baseTo - 1] = maskObject; // object

                // 2、Prepare new frame
                funcTo = (MethodSymbol) poolK[B(op)];
                if (!funcTo.resolved) resolve(funcTo);

                int cnt = funcTo.nargs;
                int from = base + C(op);
                r[baseTo] = ra; // set object
                if (cnt == 1) {
                    r[baseTo + 1] = r[from];
                } else if (cnt == 2) {
                    r[baseTo + 1] = r[from];
                    r[baseTo + 2] = r[from + 1];
                } else {
                    for (int to = 1; to <= cnt; from++, to++) {
                        r[baseTo + to] = r[from];
                    }
                }

                // 3、push new frame to stack
                calls[++fp] = funcTo;

                // 4、new function exec
                ip = 0;
                maskObject = 0;

                base = baseTo;
                code = funcTo.code;
                poolK = funcTo.getConstPool();

                func = funcTo;
                break;
            }

            case INSTR_RET: {

                // 1、pop stack frame
                fp--;
                if (fp < 0) return r[base + ra];

                // TODO need refact

                funcTo = calls[fp];

                baseTo = r[base - 4];
                // 2、clear object
                if (maskObject > 0) {
                    // clear ref object, don't deal args and ret param
                    int index = 0;
                    for (int a = func.nargs; a < ra; a++) {
                        if ((maskObject & (1L << a)) > 0) {
                            index = r[base + a];
                            if (poolH[index][0] < 2) poolH[index] = null;
                            else poolH[index][0]--;

                        }
                    }
                }

                // 2、Prepare new frame | return one object

                ip = r[base - 3];

                rb = r[base - 2];
                maskObject = r[base - 1];
                // rb = base + ra + BX(op);
                r[baseTo + rb] = r[base + ra];
                // for (int from = base + ra, to = baseTo + r[base - 2]; from <
                // rb; from++, to++) {
                // r[to] = r[from];
                // }

                // 4、return function exec

                base = baseTo;
                code = funcTo.code;
                poolK = funcTo.getConstPool();
                func = funcTo;

                break;
            }

            //@formatter:off
			case INSTR_BR:	ip = BX(op);	break;
			case INSTR_BRT:	if (r[base+ra] == TRUE) ip = BX(op);	break;
			case INSTR_BRF:	if (r[base+ra] != TRUE) ip = BX(op);	break;

			case INSTR_CCONST:	r[base+ra] = BX(op);	break;
			case INSTR_ICONST:	r[base+ra] = BX(op);	break;
			case INSTR_FCONST:	r[base+ra] = ((Integer)poolK[B(op)]).intValue();	break; // TODO not implements
			case INSTR_SCONST:	r[base+ra] = ((Integer)poolK[B(op)]).intValue();	break; // TODO not implements

			case INSTR_GLOAD:	r[base+ra] = globals[BX(op)];	break;
			case INSTR_GSTORE:	globals[AX(op)] = r[base+B(op)];	break;

			case INSTR_FLOAD:	r[base+ra] = poolH[r[base+B(op)]][((FieldSymbol) poolK[C(op)]).offset];break;
			case INSTR_FSTORE:	poolH[r[base+ra]][((FieldSymbol) poolK[B(op)]).offset] = r[base+C(op)];break;
			//@formatter:on

            case INSTR_MOVE: { // TODO object copy
                r[base + ra] = r[base + B(op)];
                break;
            }
            case INSTR_PRINT:
                System.out.println(r[base + ra]);
                break;
            case INSTR_STRUCT: {
                if ((maskObject & (1L << ra)) > 0) {
                    int index = r[base + ra];
                    if (poolH[index][0] < 2) {
                        poolH[index] = null;
                    } else {
                        poolH[index][0]--;
                    }
                }
                r[base + ra] = __new((ClassSymbol) poolK[B(op)]);
                maskObject |= (1L << ra);
                break;
            }
            case INSTR_FORPREP: {
                if (r[base + ra] >= r[base + ra + 1]) ip = BX(op);

                break;
            }
            case INSTR_FORLOOP: {
                r[base + ra] += r[base + ra + 2];
                ip = BX(op);
                break;
            }

            case INSTR_NULL:
                poolH[r[base + ra]][0]--;
                maskObject &= (~(1L << ra));
                break;
            case INSTR_HALT: {
                __halt();
                break Outter;
            }

            default:
                throw new Error("Address : " + ip + " ;invalid opcode: " + Integer.toBinaryString(op) + " at ip="
                        + (ip - 1));
            }
        }
        // @formatter:on
        return 0;
    }

    /** Resolve Function Symbol */
    public MethodSymbol resolve(MethodSymbol func) {
        if (func.resolved) return func;

        Object[] poolK = func.getConstPool();
        int[] code = func.code;
        int ip = 0;
        int op = 0;

        for (; ip < code.length;) {
            op = code[ip++];
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

                MethodSymbol fs = (MethodSymbol) poolK[index];
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

    private int __new(ClassSymbol clz) {
        int index = 0;
        for (; index < pPoolH; index++) {
            if (poolH[index] == null) break;
        }
        poolH[index] = new int[1 + clz.getLength()];
        poolH[index][0] = 1;
        pPoolH = pPoolH > index + 1 ? pPoolH : index + 1;
        return index;
    }

    //
    // private void __call(StackFrame currentFrame, int functionConstPoolIndex,
    // int baseRegisterIndex, int firstResult,
    // int ip) {
    // final FunctionSymbol fs = (FunctionSymbol)
    // currentFrame.sym.getConstPool()[functionConstPoolIndex];
    // if (!fs.resolved) resolve(fs);
    // final StackFrame f = new StackFrame(fs, ip, firstResult);
    // final StackFrame callingFrame = calls[fp];
    // calls[++fp] = f; // push new stack frame
    // // move arguments from calling stack frame to new stack frame
    // final int[] r = f.registers;
    // final int[] rCalling = callingFrame.registers;
    // for (int a = 0; a < fs.nargs; a++) { // move args, leaving room for r0
    // r[a + 1] = rCalling[baseRegisterIndex + a];
    // }
    // }

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
    private void disassemble() {
        for (int i = 1; i <= pPoolClass; i++) {
            ClassSymbol clz = poolClass[i];
            disasm.disassemble(clz);
        }
    }

    private void trace(int ip, int base) {
        MethodSymbol func = calls[fp];
        // if (ip == 0) {
        // System.out.println("");
        // System.out.println("Enter .function " + func.definedClass.name + "."
        // + func.name);
        // }
        disasm.disassembleInstruction(func.code, func.getConstPool(), ip);

        // int[] r = currentfFrame.registers;
        // if (r.length > 0) {
        System.out.print("\t[");
        int cnt = func.nargs + func.nlocals + 1;
        for (int i = 0; i < cnt; i++) {
            if (i == 1) System.out.print(" |");
            if (i == func.nargs + 1 && i == 1) System.out.print("|");
            else if (i == func.nargs + 1) System.out.print(" |");

            System.out.print(" ");

            if (r[base + i] == 0) System.out.print("_");
            else System.out.print(r[base + i]);
        }
        System.out.print(" ]\t");
        // }
        if (fp >= 0) {
            System.out.print("{");
            for (int i = 0; i <= fp; i++) {
                System.out.print(" " + calls[i].name);
            }
            System.out.print(" }");
        }
        System.out.println();
    }

    private void coredump() {
        for (int i = 1; i <= pPoolClass; i++) {
            ClassSymbol clz = poolClass[i];

            if (clz.poolLocalK.length > 0) dumpConstantPool(clz.poolLocalK);
            // if (globals.length > 0) dumpDataMemory();
            for (MethodSymbol f : clz.methods) {
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

    private void dumpCodeMemory(int[] code) {
        System.out.println("Code memory:");
        for (int i = 0; code != null && i < code.length; i++) {
            if (i % 8 == 0 && i != 0) System.out.println();
            if (i % 8 == 0) System.out.printf("%04d:", i);
            System.out.printf(" %3d", (code[i] >>> OFFSET_OP) & 0xFFFFFFFF);
        }
        System.out.println();
    }
}

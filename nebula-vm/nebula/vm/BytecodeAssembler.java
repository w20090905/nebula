package nebula.vm;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import static nebula.vm.BytecodeDefinition.*;

/**
 * Subclass the AssemblerParser to actually implement the necessary symbol table
 * management and code generation functions.
 */
public class BytecodeAssembler extends AssemblerParser {
	public static final int INITIAL_CODE_SIZE = 1024;
	protected Map<String, Integer> instructionOpcodeMapping = new HashMap<String, Integer>();
	protected Map<String, LabelSymbol> labels = // label scope
	new HashMap<String, LabelSymbol>();
	/**
	 * All float and string literals have unique int index in constant pool. We
	 * put FunctionSymbols in here too.
	 */
	protected List<Object> poolLocalK = new ArrayList<Object>();
	
	protected int ip = 0; // Instruction address pointer; used to fill code
	protected int[] code = new int[INITIAL_CODE_SIZE]; // code memory
	
	protected int dataSize; // set via .globals
	
	protected FunctionSymbol mainFunction;

	protected ClassSymbol currentClass;

	/**
	 * Create an assembler attached to a lexer and define the instruction set.
	 */
	public BytecodeAssembler(TokenStream lexer, Instruction[] instructions) {
		super(lexer);
		for (int i = 1; i < instructions.length; i++) {
			instructionOpcodeMapping.put(instructions[i].name.toLowerCase(), i);
		}
	}

	public int[] getMachineCode() {
		return code;
	}

	public int getCodeMemorySize() {
		return ip;
	}

	public int getDataSize() {
		return dataSize;
	}

	/** Return the address associated with label "main:" if defined */
	public FunctionSymbol getMainFunction() {
		return mainFunction;
	}

	/** Generate code for an instruction with no operands */
	protected void gen(Token instrToken) {
		code[ip++] = doGenOpcode(instrToken);
	}

	protected int doGenOpcode(Token instrToken) {
		// System.out.println("Gen "+instrToken);
		String instrName = instrToken.getText();
		Integer opcodeI = instructionOpcodeMapping.get(instrName);
		if (opcodeI == null) {
			System.err.println("line " + instrToken.getLine() + ": Unknown instruction: " + instrName);
			return -1;
		}
		ensureCapacity(ip + 1);
		int op = opcodeI.intValue();
		return (op & 0xFF) << OFOP;
	}

	/** Generate code for an instruction with one operand */
	protected void gen(Token instrToken, Token oTokenAX) {
		int op = doGenOpcode(instrToken);
		code[ip++] = genOperand(oTokenAX, op, OFFSET_A_);
	}

	protected void gen(Token instrToken, Token oTokenAX, Token oTokenBX) {
		int op = doGenOpcode(instrToken);
		op = genOperand(oTokenAX, op, OFFSET_A_);
		code[ip++] = genOperand(oTokenBX, op, OFFSET_B_);
	}

	protected void gen(Token instrToken, Token oTokenAX, Token oTokenBX, Token oTokenCX) {
		int op = doGenOpcode(instrToken);
		op = genOperand(oTokenAX, op, OFFSET_A_);
		op = genOperand(oTokenBX, op, OFFSET_B_);
		code[ip++] = genOperand(oTokenCX, op, OFFSET_C_);
	}

	protected int genOperand(Token operandToken, int op, int offset) {
		String text = operandToken.getText();
		int v = 0;
		switch (operandToken.getType()) { // switch on token type
		case CLASS:
			v = toLocalConstantPoolIndex(new ClassSymbol(text));
			op |= (v & MKX_) << offset;
			break;
		case FIELD:
			int i = text.indexOf('.');
			ClassSymbol c = new ClassSymbol(text.substring(1, i));
			c = (ClassSymbol) getConstantPool()[toLocalConstantPoolIndex(c)];
			v = toLocalConstantPoolIndex(new FieldSymbol(c, text.substring(i + 1)));
			op |= (v & MKX_) << offset;
			break;
		case INT:
			v = Integer.valueOf(text);
			op |= (v & MKXX) << (offset-OFT);
			break;
		case CHAR:
			v = Character.valueOf(text.charAt(1));
			op |= (v & MKXX) <<  (offset-OFT);
			break;
		case FLOAT:
			v = toLocalConstantPoolIndex(Float.valueOf(text));
			op |= (v & MKX_) << offset;
			break;
		case STRING:
			v = toLocalConstantPoolIndex(text);
			op |= (v & MKX_) << offset;
			break;
		case ID:
			v = getLabelAddress(text, offset-OFT);
			op |= (v & MKXX) << (offset-OFT);
			break;
		case FUNC:
			v = getFunctionIndex(text);
			break;
		case REG:
			v = toRegisterNumber(operandToken);
			op |= (v & MKX_) << offset;
			break;
		}
		return op;
	}

	protected int toLocalConstantPoolIndex(Object o) {
		if (poolLocalK.contains(o)) return poolLocalK.indexOf(o);
		poolLocalK.add(o);
		return poolLocalK.size() - 1;
	}

	public Object[] getConstantPool() {
		return poolLocalK.toArray();
	}

	protected int toRegisterNumber(Token rtoken) { // convert "rN" -> N
		return Integer.valueOf(rtoken.getText());
	}

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
			sym = new LabelSymbol(id, ip-1, offset, true);
			sym.isDefined = false;
			labels.put(id, sym); // add to symbol table
		} else {
			if (sym.isForwardRef) {
				// address is unknown, must simply add to forward ref list
				// record where in code memory we should patch later
				sym.addForwardReference(ip-1, offset);
			} else {
				// all is well; it's defined--just grab address
				return sym.address;
			}
		}
		return 0; // we don't know the real address yet
	}

	@Override
	protected void defineClass(Token idToken) {
		this.currentClass = new ClassSymbol(idToken.getText());
		toLocalConstantPoolIndex(this.currentClass);
	}

	@Override
	protected void defineField(Token idToken) {
		FieldSymbol field = new FieldSymbol(this.currentClass, idToken.getText());
		this.currentClass.add(field);
		toLocalConstantPoolIndex(field);
	}

	protected void defineFunction(Token idToken, int args, int locals) {
		String name = idToken.getText();
		FunctionSymbol f = new FunctionSymbol(name, args, locals, ip);
		if (name.equals("main")) mainFunction = f;
		// Did someone referred to this function before it was defined?
		// if so, replace element in constant pool (at same index)
		if (poolLocalK.contains(f)) poolLocalK.set(poolLocalK.indexOf(f), f);
		else toLocalConstantPoolIndex(f); // save into constant pool
	}

	protected int getFunctionIndex(String id) {
		int i = poolLocalK.indexOf(new FunctionSymbol(id));
		if (i >= 0) return i; // already in system; return index.
		// must be a forward function reference
		// create the constant pool entry; we'll fill in later
		return toLocalConstantPoolIndex(new FunctionSymbol(id));
	}

	protected void defineDataSize(int n) {
		dataSize = n;
	}

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
				sym.resolveForwardReferences(code);
			} else {
				// redefinition of symbol
				System.err.println("line " + idToken.getLine() + ": redefinition of symbol " + id);
			}
		}
	}

	protected void ensureCapacity(int index) {
		if (index >= code.length) { // expand
			int newSize = Math.max(index, code.length) * 2;
			int[] bigger = new int[newSize];
			System.arraycopy(code, 0, bigger, 0, code.length);
			code = bigger;
		}
	}

	// public static int getInt(byte[] memory, int index) {
	// int b1 = memory[index++] & 0xFF; // mask off sign-extended bits
	// int b2 = memory[index++] & 0xFF;
	// int b3 = memory[index++] & 0xFF;
	// int b4 = memory[index++] & 0xFF;
	// int word = b1 << (8 * 3) | b2 << (8 * 2) | b3 << (8 * 1) | b4;
	// return word;
	// }
	//
	// /**
	// * Write value at index into a byte array highest to lowest byte, left to
	// * right.
	// */
	// public static void writeInt(byte[] bytes, int index, int value) {
	// bytes[index + 0] = (byte) ((value >> (8 * 3)) & 0xFF); // get highest
	// // byte
	// bytes[index + 1] = (byte) ((value >> (8 * 2)) & 0xFF);
	// bytes[index + 2] = (byte) ((value >> (8 * 1)) & 0xFF);
	// bytes[index + 3] = (byte) (value & 0xFF);
	// }
}

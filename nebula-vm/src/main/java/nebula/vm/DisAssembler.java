package nebula.vm;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
import static nebula.vm.BytecodeDefinition.MASK_XX;
import static nebula.vm.BytecodeDefinition.MASK_X_;
import static nebula.vm.BytecodeDefinition.OFFSET_AX;
import static nebula.vm.BytecodeDefinition.OFFSET_A_;
import static nebula.vm.BytecodeDefinition.OFFSET_BX;
import static nebula.vm.BytecodeDefinition.OFFSET_B_;
import static nebula.vm.BytecodeDefinition.OFFSET_C_;
import static nebula.vm.BytecodeDefinition.OFFSET_OP;
import static nebula.vm.BytecodeDefinition.REG;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class DisAssembler {
	BytecodeDefinition def;

	Writer out = null;
	
	public DisAssembler() {
		out = new OutputStreamWriter(System.out);
	}
	
	public DisAssembler(Writer out){
		this.out = out;
	}
	
	private void println(String str){
		print(str);
		print("\n");
	}
	
	private void print(String format,Object... params){
		print(String.format(format,params));
	}
	
	private void print(String str){
		try {
			this.out.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	

	public void disassemble(ClassSymbol clz) {
		println(".class " + clz.name);
		println("");

		for (FieldSymbol f : clz.fields) {
			println(".field " + f.name);
		}
		println("");

		for (MethodSymbol f : clz.methods) {
			this.disassemble(f);
		}
	}

	public void disassemble(MethodSymbol func) {
		print(".def %s: args=%d, locals=%d\n",func.name,func.nargs,func.nlocals);
		int i = 0;
		int[] code = func.code;
		while (i < func.code.length) {
			i = disassembleInstruction(code, func.getConstPool(), i);
			println("");
		}
		println("");
	}

	private static final int OP_CODE(int op) {
		return (op >>> OFFSET_OP);
	}

	private static final int A(int op) {
		return op >>> OFFSET_A_ & MASK_X_;
	}

	private static final int B(int op) {
		return op >>> OFFSET_B_ & MASK_X_;
	}

	private static final int C(int op) {
		return op >>> OFFSET_C_ & MASK_X_;
	}

	private static final int AX(int op) {
		return op >>> OFFSET_AX & MASK_XX;
	}

	private static final int BX(int op) {
		return op >>> OFFSET_BX & MASK_XX;
	}

	public int disassembleInstruction(int[] code, Object[] constPool, int ip) {
		int op = code[ip];
		BytecodeDefinition.Instruction I = BytecodeDefinition.instructions[OP_CODE(op)];
		String instrName = I.name.toUpperCase();
		print(String.format("%04d: %-11s", ip, instrName));
		ip++;
		if (I.n == 0) {
			print("  ");
			return ip;
		}
		String[] operands = new String[I.n];
		switch (I.n) {
		case 3:
			switch (I.type[2]) {
			case REG:
				operands[2] = "r" + String.valueOf(C(op));
				break;
			case BytecodeDefinition.FUNC:
			case BytecodeDefinition.POOL:
				operands[2] = showConstPoolOperand(constPool, C(op));
				break;
			}
		case 2:
			switch (I.type[1]) {
			case REG:
				operands[1] = "r" + String.valueOf(B(op));
				break;
			case BytecodeDefinition.FUNC:
			case BytecodeDefinition.POOL:
				operands[1] = showConstPoolOperand(constPool, B(op));
				break;
			case BytecodeDefinition.INT:
				operands[1] = String.valueOf(BX(op));
				break;
			}
		case 1:
			switch (I.type[0]) {
			case REG:
				operands[0] = "r" + String.valueOf(A(op));
				break;
			case BytecodeDefinition.FUNC:
			case BytecodeDefinition.POOL:
				operands[0] = showConstPoolOperand(constPool, A(op));
				break;
			case BytecodeDefinition.INT:
				operands[0] = String.valueOf(AX(op));
				break;
			}
			break;
		}

		for (int i = 0; i < operands.length; i++) {
			String s = operands[i];
			if (i > 0) print(", ");
			print(s);
		}
		return ip;
	}

	private String showConstPoolOperand(Object[] constPool, int poolIndex) {
		StringBuilder buf = new StringBuilder();
		buf.append("#");
		buf.append(poolIndex);
		String s = constPool[poolIndex].toString();
		if (constPool[poolIndex] instanceof String) s = '"' + s + '"';
		else if (constPool[poolIndex] instanceof MethodSymbol) {
			MethodSymbol fs = (MethodSymbol) constPool[poolIndex];
			s = "@" + fs.definedClass.name + "." + fs.name + "()";
		} else if (constPool[poolIndex] instanceof FieldSymbol) {
			FieldSymbol fs = (FieldSymbol) constPool[poolIndex];
			s = "@" + fs.definedClass.name + "." + fs.name + "";
		}
		buf.append(":");
		buf.append(s);
		return buf.toString();
	}
}

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

public class DisAssembler {
	BytecodeDefinition def;

	public DisAssembler() {
	}

	public void disassemble(ClassSymbol clz) {
		System.out.println(".class " + clz.name);
		System.out.println();

		for (FieldSymbol f : clz.fields) {
			System.out.println(".field " + f.name);
		}
		System.out.println();

		for (FunctionSymbol f : clz.functions) {
			System.out.println(".def " + f.name + " args=" + f.nargs + ", locals=" + f.nlocals + " ");
			this.disassemble(f);
		}
	}

	public void disassemble(FunctionSymbol func) {
		int i = 0;
		int[] code = func.code;
		while (i < func.code.length) {
			i = disassembleInstruction(code, func.getConstPool(), i);
			System.out.println();
		}
		System.out.println();
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
		String instrName = I.name;
		System.out.printf("%04d:\t%-11s", ip, instrName);
		ip++;
		if (I.n == 0) {
			System.out.print("  ");
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
			if (i > 0) System.out.print(", ");
			System.out.print(s);
		}
		return ip;
	}

	private String showConstPoolOperand(Object[] constPool, int poolIndex) {
		StringBuilder buf = new StringBuilder();
		buf.append("#");
		buf.append(poolIndex);
		String s = constPool[poolIndex].toString();
		if (constPool[poolIndex] instanceof String) s = '"' + s + '"';
		else if (constPool[poolIndex] instanceof FunctionSymbol) {
			FunctionSymbol fs = (FunctionSymbol) constPool[poolIndex];
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

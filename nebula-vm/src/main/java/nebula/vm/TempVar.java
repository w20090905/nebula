package nebula.vm;

import java.util.Vector;

public class TempVar extends Var {
	Vector<Address> forwardReferences = null;

	public TempVar(String name, Type type) {
		super(name, BuiltInTypeSymbol.INT);
		this.applied = false;
	}

	class Address {
		int ip;
		int offset;

		Address(int ip, int offset) {
			this.ip = ip;
			this.offset = offset;
		}
	}

	public void clearReference() {
		forwardReferences.clear();
	}

	public void addReference(int ip, int offset) {
		if (forwardReferences == null) {
			forwardReferences = new Vector<Address>();
		}
		forwardReferences.addElement(new Address(ip, offset));
	}

	public void resolveForwardReferences(int[] code) {
		applied = true;
		// need to patch up all references to this symbol
		Vector<Address> opndsToPatch = forwardReferences;
		for (Address addrToPatch : opndsToPatch) {
			/*
			 * System.out.println("updating operand at addr "+
			 * addr+" to be "+getAddress());
			 */
			code[addrToPatch.ip] = code[addrToPatch.ip]
					| (reg << (BytecodeDefinition.OFFSET_X_ * (3 - addrToPatch.offset)));
			// BytecodeAssembler2.writeInt(code, addrToPatch, address);
		}
	}

	@Override
	public String toString() {
		return "[ " + name  + ":" + type + " | " + reg + "  " + applied + "]";
	}

}

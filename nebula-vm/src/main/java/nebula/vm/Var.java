package nebula.vm;

import java.util.Vector;

public class Var extends Symbol {
	public short reg;
	public boolean applied = true;
	Vector<Address> forwardReferences = null;

	public short category;
	public final static short LOCAL = 0;
	public final static short PARAM = 1;
	public final static short ICONST = 2;
	public final static short DCONST = 3;
	public final static short SCONST = 4;

	public Var(short category, String name, Type type) {
		super(name, type);
		this.reg = 0;
	}

	public Var(short category, String name, Type type, short reg) {
		super(name, type);
		this.reg = reg;
	}

	public Var(short category, String name, Type type, int ip, int offset) {
		super(name, type);
		applied = false;
		// if (forward) {
		// if forward reference, then address is address to update
		addReference(ip, offset);
		// } else {
		this.reg = 0;
		// }
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
		return "[ " + reg + " \t: " + name  + "    \tresolved=" + applied + ", type=" + type + "]";
	}

}

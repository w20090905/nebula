package nebula.vm;

import java.util.Vector;

<<<<<<< HEAD:nebula-vm/src/main/java/nebula/vm/Var.java
public class Var extends Symbol {
	public short reg;
	public boolean applied = true;
=======
/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
/** Represents a variable definition (name,type) in symbol table */
public class VariableSymbol extends Symbol {
	public short reg;
	/** Is this ref'd before def'd. */
	public boolean resolved = false;
	/** List of operands in memory we need to update after seeing def */
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9:nebula-vm/src/main/java/nebula/vm/VariableSymbol.java
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

<<<<<<< HEAD:nebula-vm/src/main/java/nebula/vm/Var.java
	public Var(short category, String name, Type type, short reg) {
=======
	public VariableSymbol(String name, Type type, short reg) {
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9:nebula-vm/src/main/java/nebula/vm/VariableSymbol.java
		super(name, type);
		this.reg = reg;
	}

<<<<<<< HEAD:nebula-vm/src/main/java/nebula/vm/Var.java
	public Var(short category, String name, Type type, int ip, int offset) {
=======
	public VariableSymbol(String name, Type type, int ip, int offset) {
>>>>>>> 4f361c897c997c9b3a550342d78c0a89882573c9:nebula-vm/src/main/java/nebula/vm/VariableSymbol.java
		super(name, type);
		resolved = false;
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
		resolved = true;
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
		return "[ " + reg + " \t: " + name  + "    \tresolved=" + resolved + ", type=" + type + "]";
	}

}

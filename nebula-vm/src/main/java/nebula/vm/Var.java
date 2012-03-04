package nebula.vm;

import java.util.Vector;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
 ***/
/** Represents a variable definition (name,type) in symbol table */
public class Var extends Symbol {
	public short reg;
	/** Is this ref'd before def'd. */
	public boolean resolved = false;
	/** List of operands in memory we need to update after seeing def */
	Vector<Address> forwardReferences = null;

	public void setName(String name) {
		this.name = name;
	}

	public void setIndex(short regIndex) {
		this.reg = regIndex;
	}

	public Var(String name, Type type, short reg) {
		super(name, type);
		this.reg = reg;
	}

	public Var(String name, Type type, int ip, int offset) {
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

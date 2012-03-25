package nebula.vm;

import static nebula.vm.BytecodeDefinition.MASK_X_;

import java.util.Vector;

public class TempVar extends Var {
	Vector<Address> forwardReferences = null;

	public TempVar(String name, short index, Type type) {
		super(name, type, index);
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
		Vector<Address> opndsToPatch = forwardReferences;
		for (Address addrToPatch : opndsToPatch) {

			code[addrToPatch.ip] = code[addrToPatch.ip]
					| ((reg & MASK_X_)  << (BytecodeDefinition.OFFSET_X_ * (3 - addrToPatch.offset)));
		}
	}

	public void resolveForwardReferences(short givenReg, int[] code) {
		applied = true;
		Vector<Address> opndsToPatch = forwardReferences;
		for (Address addrToPatch : opndsToPatch) {

			code[addrToPatch.ip] = code[addrToPatch.ip]
					| ((givenReg & MASK_X_) << (BytecodeDefinition.OFFSET_X_ * (3 - addrToPatch.offset)));
		}
	}

	@Override
	public String toString() {
		return "[ " + name + ":" + type + " | " + reg + "  " + applied + "]";
	}

}

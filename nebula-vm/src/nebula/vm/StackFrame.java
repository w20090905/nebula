package nebula.vm;

public class StackFrame {
	final FunctionSymbol sym;
	final int firstResult;
	final int returnAddress;
	final int[] registers;
	long maskObject;

	public StackFrame(FunctionSymbol sym, int returnAddress, int firstResult) {
		this.sym = sym;
		this.returnAddress = returnAddress;
		this.firstResult = firstResult;
		// Allocate space for registers; 1 extra for r0 reserved reg
		registers = new int[sym.nargs + sym.nlocals + 1];
	}
}

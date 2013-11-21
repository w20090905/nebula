package nebula.lang;


public interface Code {
	void scan(CompilerContext context);
    void compile(AsmCompiler compiler);
}

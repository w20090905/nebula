package nebula.simpletemplate;

import java.io.IOException;

public interface Action {
	// void exec(Writer out, Object root) throws IOException;
	void exec(STGroup group, CompiledST template, StringBuilder out, Object[] argv) throws IOException;
}

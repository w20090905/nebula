package nebula.simpletemplate;

import java.io.IOException;

public interface Action {
	// void exec(Writer out, Object root) throws IOException;
	void exec(StringBuilder out, Object root) throws IOException;
}

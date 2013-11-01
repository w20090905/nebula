package nebula.simpletemplate;

import java.io.IOException;
import java.io.Writer;

public interface Action {
	void exec(Writer out, Object root) throws IOException;
}

package nebula.simpletemplate;

import java.io.IOException;
import java.io.Writer;

public class TestCode implements Action {

	@Override
	public void exec(Writer out, Object value) throws IOException {
		Person root = (Person) value;
		{
			out.write("Hello");
			out.write(root.getName());
			out.write(";");
		}
	}
}

package nebula.simpletemplate;

import java.io.IOException;

public class TestCode implements Action {

	@Override
	public void exec(StringBuilder out, Object value) throws IOException {
		Person root = (Person) value;
		{
			out.append("Hello");
			out.append(root.getName());
			out.append(";");
		}
	}
}

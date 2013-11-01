package nebula.simpletemplate;

import java.io.IOException;

public class TestCodePrimary implements Action {

	@Override
	public void exec(StringBuilder out, Object value) throws IOException {
		Person root = (Person) value;
		{
			out.append("Hello");
			out.append("ddd");
			out.append(root.getName());
			out.append(value);
			out.append(1);
			out.append(1L);
			out.append(false);
			out.append(";");
		}
	}
}

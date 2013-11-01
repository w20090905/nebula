package nebula.simpletemplate;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class TestCodeMap implements Action {

	@Override
	public void exec(Writer out, Object value) throws IOException {
		Map<String, String> root = (Map<String, String>) value;
		{
			out.write("Hello");
			out.write(root.get("name"));
			out.write(";");
		}
	}
}

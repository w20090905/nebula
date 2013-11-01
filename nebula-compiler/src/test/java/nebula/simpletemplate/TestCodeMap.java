package nebula.simpletemplate;

import java.io.IOException;
import java.util.Map;

public class TestCodeMap implements Action {

	@Override
	@SuppressWarnings("unchecked")
	public void exec(StringBuilder out, Object value) throws IOException {
		Map<String, Object> root = (Map<String, Object>) value;
		{
			out.append("Hello");
			out.append(root.get("name"));
			out.append(";");
		}
	}
}

package nebula.simpletemplate;

import java.io.IOException;
import java.util.Map;

public class TestCodeMap implements Action {

	@Override
	@SuppressWarnings("unchecked")
	public void exec(StringBuilder out, Object... argv) throws IOException {
		{
			out.append("Hello");
			out.append(((Map<String, Object>) argv[0]).get("name"));
			out.append(";");
		}
	}
}

package nebula.simpletemplate;

import java.io.IOException;

public class TestCodePrimary implements Action {

	@Override
	public void exec(STGroup group, TemplateImpl template, StringBuilder out, Object[] argv) throws IOException {
		{
			out.append("Hello");
			out.append("ddd");
			out.append(((Person) argv[0]).getName());
			out.append(argv[0]);
			out.append(1);
			out.append(1L);
			out.append(false);
			out.append(";");
		}
	}
}

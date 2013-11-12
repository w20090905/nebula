package nebula.simpletemplate;

import java.io.IOException;

public class SampleCode implements Action {

	@Override
	public void exec(STGroup group, TemplateImpl template,StringBuilder out, Object[] argv) throws IOException {
		{
			out.append("Hello");
			out.append(((Person) argv[10]).getName());
			out.append(";");
			
			group.getTemplate("wangshilian").exec(argv);
			
			template.implicitlyDefinedTemplates.get(12).exec(argv);
		}
	}
}
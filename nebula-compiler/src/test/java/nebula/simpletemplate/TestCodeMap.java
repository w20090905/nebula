package nebula.simpletemplate;

import java.io.IOException;

public class TestCodeMap implements Action {

	Class<?> tempalte1LeadingClass;
	Action template1Action;

	@Override
	public void exec(STGroup group, CompiledST template, StringBuilder out, Object[] argv) throws IOException {
		{

			Object o = argv[0];
			if (o != null) {
				if (tempalte1LeadingClass == o.getClass()) {
					template1Action.exec(group, template, out, argv);
				} else {
					tempalte1LeadingClass = o.getClass();
					template1Action = template.get(o.getClass().getName(), tempalte1LeadingClass);
					template1Action.exec(group, template, out, argv);
				}
			} else {
				if (tempalte1LeadingClass == Void.class) {
					template1Action.exec(group, template, out, argv);
				} else {
					tempalte1LeadingClass = Void.class;
					template1Action = template.get(Void.class.getName(), tempalte1LeadingClass);
					template1Action.exec(group, template, out, argv);
				}
			}
			
			if(o==null){
			}
			
//			Person person=null;
//			
//			if(person!=null){
//				out.append(person.name);
//			}else{
//				
//			}
			argv[0] = 1098;
			
			if(o!=null){
				out.append(o);
			}
		}
	}
}

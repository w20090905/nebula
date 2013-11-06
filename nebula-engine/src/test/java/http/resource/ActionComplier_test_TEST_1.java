package http.resource;
import java.io.IOException;

import nebula.lang.Field;
import nebula.simpletemplate.Action;
import nebula.simpletemplate.STGroup;
import nebula.simpletemplate.TemplateImpl;

public class ActionComplier_test_TEST_1
  implements Action
{
	static ActionComplier_test_TEST_1 instance = new ActionComplier_test_TEST_1();
  public void exec(STGroup paramSTGroup, TemplateImpl paramTemplateImpl, StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
    throws IOException
  {
	    paramStringBuilder.append("{ ");
	    paramStringBuilder.append(((Field)paramArrayOfObject[0]).getName());
	    paramStringBuilder.append(" , ");
	    paramStringBuilder.append(((Field)paramArrayOfObject[0]).getType().getName());
	    paramStringBuilder.append(" } ");
  }
}
package http.resource;

import java.io.IOException;

import nebula.lang.Field;
import nebula.lang.Type;
import nebula.simpletemplate.Action;
import nebula.simpletemplate.STGroup;
import nebula.simpletemplate.TemplateImpl;

public class ActionComplier_test_TEST_0 implements Action {
	public void exec(STGroup paramSTGroup, TemplateImpl paramTemplateImpl, StringBuilder paramStringBuilder, Object[] paramArrayOfObject) throws IOException {
		Object[] obj = new Object[] { paramArrayOfObject[0] };
		for (Field f : ((Type) paramArrayOfObject[0]).getFields()) {
			obj[0] = f;
			ActionComplier_test_TEST_1.instance.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, obj);
		}
	}
}

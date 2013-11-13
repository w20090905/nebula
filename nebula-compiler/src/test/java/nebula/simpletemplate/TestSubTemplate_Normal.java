package nebula.simpletemplate;
import java.io.IOException;

public class TestSubTemplate_Normal
  implements Action
{
  public void exec(STGroup paramSTGroup, CompiledST paramTemplateImpl, StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
    throws IOException
  {
	  ActionComplier_java_lang_String_Normal a = new ActionComplier_java_lang_String_Normal();
    paramStringBuilder.append("<html>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<head>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<title>");
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    a.exec(paramSTGroup, paramTemplateImpl, paramStringBuilder, new Object[] { (String)paramArrayOfObject[0] });
    paramStringBuilder.append("</title>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<body>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<hr>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("</body>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("</html>");
  }
}
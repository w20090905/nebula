package nebula.simpletemplate;
import java.io.IOException;

public class TestSubTemplate_Inline
  implements Action
{
  public void exec(STGroup paramSTGroup, TemplateImpl paramTemplateImpl, StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
    throws IOException
  {
    paramStringBuilder.append("<html>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<head>");
    paramStringBuilder.append("\r\n");
    paramStringBuilder.append("<title>");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
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
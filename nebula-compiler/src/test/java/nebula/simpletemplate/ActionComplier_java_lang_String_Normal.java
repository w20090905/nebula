package nebula.simpletemplate;
import java.io.IOException;
import nebula.simpletemplate.Action;
import nebula.simpletemplate.STGroup;
import nebula.simpletemplate.CompiledST;

public final class ActionComplier_java_lang_String_Normal
  implements Action
{
  public void exec(STGroup paramSTGroup, CompiledST paramTemplateImpl, StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
    throws IOException
  {
    paramStringBuilder.append("print(");
    paramStringBuilder.append((String)paramArrayOfObject[0]);
    paramStringBuilder.append(")");
  }
}
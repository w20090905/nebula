package nebula.simpletemplate;

import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.compiler.STLexer;

import com.google.common.collect.Maps;

public class STGroup {
	final static Log log = LogFactory.getLog(STGroup.class);

	public Object errMgr;
	final char delimiterStartChar, delimiterStopChar;

	public STGroup() {
		this('$', '}');
	}

	public STGroup(char delimiterStartChar, char delimiterStopChar) {
		this.delimiterStartChar = delimiterStartChar;
		this.delimiterStopChar = delimiterStopChar;
	}

	public static boolean trackCreationEvents;
	public static STGroup defaultGroup = new STGroup();

	protected Map<String, TemplateImpl> knownsTemplate = Maps.newHashMap();

	public TemplateImpl parse(Object fileName, Object object, Object object2, String template, Object object3) {
		try {
			STLexer lexer = new STLexer(org.stringtemplate.v4.STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(template), null, '$', '}');
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SParser p = new SParser(tokens, this);
			return p.templateAndEOF();
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public TemplateImpl getTemplate(String name) {
		return knownsTemplate.get(name);
	}

	public void defineTemplate(String name, String template) {
		TemplateImpl temp = parse(null, null, null, template, null);
		temp.name = name;
		knownsTemplate.put(name, temp);
	}
}

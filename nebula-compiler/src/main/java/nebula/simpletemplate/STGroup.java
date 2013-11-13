package nebula.simpletemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.compiler.STLexer;
import org.stringtemplate.v4.misc.ErrorManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class STGroup {
	final static Log log = LogFactory.getLog(STGroup.class);

	public static final String DICT_KEY = "key";

	public static final String DEFAULT_KEY = "default";

	public static boolean trackCreationEvents;
	
	public static STGroup defaultGroup = new STGroup();


	public static STGroup fromGroupFile(String filename) {
		return fromGroupFile(filename, '<', '>');
	}
	public static STGroup fromGroupFile(String filename, char delimiterStartChar, char delimiterStopChar) {
		STGroup group = new STGroup(delimiterStartChar, delimiterStopChar);
		group.parseGroupFile(filename);
		return group;
	}

	public ErrorManager errMgr;
	char delimiterStartChar, delimiterStopChar;

	protected Map<String, TemplateImpl> knownsTemplate = Maps.newHashMap();

	public STGroup() {
		this('<', '>');
	}

	public STGroup(char delimiterStartChar, char delimiterStopChar) {
		this.delimiterStartChar = delimiterStartChar;
		this.delimiterStopChar = delimiterStopChar;
	}
	public void addTemplate(String name, TemplateImpl impl) {
		this.knownsTemplate.put(name, impl);
	}

	public TemplateImpl defineFileTemplate(String name, String pathname) {
		String fullyQualifiedTemplateName = name;
		// if ( verbose )
		// System.out.println("defineTemplate("+fullyQualifiedTemplateName+")");
		if (fullyQualifiedTemplateName == null || fullyQualifiedTemplateName.length() == 0) {
			throw new IllegalArgumentException("empty template name");
		}
		if (fullyQualifiedTemplateName.indexOf('.') >= 0) {
			throw new IllegalArgumentException("cannot have '.' in template names");
		}
		// template = Misc.trimOneStartingNewline(template);
		// template = Misc.trimOneTrailingNewline(template);
		// compile, passing in templateName as enclosing name for any embedded
		// regions
		TemplateImpl code = parse(name, pathname);

		code.name = fullyQualifiedTemplateName;
		rawDefineTemplate(fullyQualifiedTemplateName, code, null);
		// code.defineArgDefaultValueTemplates(this);
		// code.defineImplicitlyDefinedTemplates(this); // define any anonymous
		// subtemplates

		return code;
	}

	public void defineTemplate(String name, String template) {
		TemplateImpl temp = parse(null, null, null, template);
		temp.name = name;
		knownsTemplate.put(name, temp);
	}

	public void defineTemplate(String name, String args, String template) {
		List<FormalArgument> arges = Lists.newArrayList();
		for (String n : args.split(",")) {
			arges.add(new FormalArgument(n, null));
		}

		TemplateImpl code= this.parse(name, name, arges, template);
		knownsTemplate.put(name, code);
	}

	public TemplateImpl defineTemplate(String fullyQualifiedTemplateName, Token nameT, List<FormalArgument> args, String template, Token templateToken) {
		// if ( verbose )
		// System.out.println("defineTemplate("+fullyQualifiedTemplateName+")");
		if (fullyQualifiedTemplateName == null || fullyQualifiedTemplateName.length() == 0) {
			throw new IllegalArgumentException("empty template name");
		}
		if (fullyQualifiedTemplateName.indexOf('.') >= 0) {
			throw new IllegalArgumentException("cannot have '.' in template names");
		}
		// template = Misc.trimOneStartingNewline(template);
		// template = Misc.trimOneTrailingNewline(template);
		// compile, passing in templateName as enclosing name for any embedded
		// regions
		TemplateImpl code = parse(getFileName(), fullyQualifiedTemplateName, args, template);

		code.name = fullyQualifiedTemplateName;
		rawDefineTemplate(fullyQualifiedTemplateName, code, nameT);
		// code.defineArgDefaultValueTemplates(this);
		// code.defineImplicitlyDefinedTemplates(this); // define any anonymous
		// subtemplates

		return code;
	}

	// public void defineDictionary(String string, Map<String, Object> dict8) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	public void defineTemplateAlias(String alias, String target) {
		this.knownsTemplate.put(alias, this.getTemplate(target));
	}

	public void defineTemplateOrRegion(String fullyQualifiedTemplateName, String regionSurroundingTemplateName, Token templateToken, String template,
			Token nameToken, List<FormalArgument> args) {
		if (regionSurroundingTemplateName != null) {
			// defineRegion(regionSurroundingTemplateName, nameToken, template,
			// templateToken);
		} else {
			defineTemplate(fullyQualifiedTemplateName, nameToken, args, template, templateToken);
		}
	}

	private String getFileName() {
		// TODO Auto-generated method stub
		return "ddd";
	}

	//
	// public void importTemplates(Token sTRING1) {
	// // TODO Auto-generated method stub
	//
	// }

	// public ST createSingleton(Token templateToken) {
	// String template;
	// if ( templateToken.getType()==GroupParser.BIGSTRING ) {
	// template = Misc.strip(templateToken.getText(),2);
	// }
	// else {
	// template = Misc.strip(templateToken.getText(),1);
	// }
	//
	// return null;
	// }

	public ST getInstanceOf(String string) {
		return new ST(getTemplate(string));
	}

	//
	// public Object rawGetDictionary(String string) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// public Object rawGetTemplate(String string) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	public TemplateImpl getTemplate(String name) {
		return knownsTemplate.get(name);
	}

	public TemplateImpl parse(String name, String filepath) {
		try {
			STLexer lexer = new STLexer(org.stringtemplate.v4.STGroup.DEFAULT_ERR_MGR, new ANTLRFileStream(filepath), null, delimiterStartChar, delimiterStopChar);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SParser p = new SParser(tokens, this);
			return p.templateAndEOF();
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public TemplateImpl parse(String srcName, String name, List<FormalArgument> args, String template) {
		return this.parse(srcName, name, args, template, delimiterStartChar, delimiterStopChar);
	}

	public TemplateImpl parse(String srcName, String name, List<FormalArgument> args, String template, char delimiterStartChar, char delimiterStopChar) {
		try {
			STLexer lexer = new STLexer(org.stringtemplate.v4.STGroup.DEFAULT_ERR_MGR, new ANTLRStringStream(template), null, delimiterStartChar,
					delimiterStopChar);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SParser p = new SParser(tokens, this);
			if (args != null && args.size() > 0) {
				for (int i = 0; i < args.size(); i++) {
					FormalArgument arg = args.get(i);
					p.arg(arg.name);
				}
			}
			return p.templateAndEOF();
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	protected void parseGroupFile(String filename) {
		try {
			GroupLexer lexer = new GroupLexer(new ANTLRFileStream(filename));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GroupParser p = new GroupParser(tokens);
			p.group(this, "");
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	protected void parseGroupString(String template) {
		try {
			GroupLexer lexer = new GroupLexer(new ANTLRStringStream(template));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GroupParser p = new GroupParser(tokens);
			p.group(this, "");
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public void rawDefineTemplate(String name, TemplateImpl code, Token defT) {
		// CompiledST prev = rawGetTemplate(name);
		// if ( prev!=null ) {
		// if ( !prev.isRegion ) {
		// errMgr.compileTimeError(ErrorType.TEMPLATE_REDEFINITION, null, defT);
		// return;
		// }
		// if ( prev.isRegion ) {
		// if ( code.regionDefType!=ST.RegionType.IMPLICIT &&
		// prev.regionDefType==ST.RegionType.EMBEDDED )
		// {
		// errMgr.compileTimeError(ErrorType.EMBEDDED_REGION_REDEFINITION,
		// null,
		// defT,
		// getUnMangledTemplateName(name));
		// return;
		// }
		// else if ( code.regionDefType==ST.RegionType.IMPLICIT ||
		// prev.regionDefType==ST.RegionType.EXPLICIT )
		// {
		// errMgr.compileTimeError(ErrorType.REGION_REDEFINITION,
		// null,
		// defT,
		// getUnMangledTemplateName(name));
		// return;
		// }
		// }
		// }
		code.nativeGroup = this;
		knownsTemplate.put(name, code);
	}
}

package nebula.lang;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import nebula.lang.Flow.Step;

import org.antlr.runtime.RecognitionException;

public class TypeLoaderForFlowTest extends TypeLoader {
	public TypeLoaderForFlowTest(TypeLoader parent) {
		super(parent);
		Type flow = new TypeImp(this, TypeStandalone.Flow.name(), parent.findType(Type.ROOT_TYPE));
		this.types.add(flow);

		try {
			parent.defineNebula(new StringReader(
//@formatter:off
				"type Step {" +
				"		<NextStep> String := \"" + Step.Next + "\";" +
				"		<DoItNow> YesNo := false;" +
				"		<init>(){" +
				"		}; " +
				"		<next>(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"		<previous>(){" +
				"			this.DoItNow = true;" +
				"			this.NextStep = \"" + Step.Previous + "\";" +
				"		};" +
				"		<end>(){" +
				"			this.NextStep = \"End\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		<skip>(){" +
				"			this.NextStep = \"" + Step.Next + "\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		<terminal>(){" +
				"			this.NextStep = \"" + Step.Terminal + "\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		<cancel>(){" +
				"			this.NextStep = \"" + Step.Cancel + "\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		<submit>(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"};"
			//@formatter:on
			));

			parent.defineNebula(new StringReader("type Begin extends Step {" + "		submit | 提交(){" + "			this.next();" + "		};" + " };"));
			parent.defineNebula(new StringReader("type End extends Step { @Runtime submit(){}; };"));
			parent.defineNebula(new StringReader("type Approve extends Step {" + "		submit | 通过(){" + "			this.next();" + "		};" + "};"));

		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public Type load(String text) {
		Type type;
		try {
			type = super.defineNebula(new StringReader(text)).get(0);
		} catch (RecognitionException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
		return type;
	}

	public Type loadFromFile(String name) {
		try {
			Type type = super.defineNebula(new File(name).toURI().toURL()).get(0);
			return type;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		} catch (RecognitionException e) {
			log.error(e.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected URL loadClassData(String name) {
		return null;
	}
}

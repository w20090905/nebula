package nebula.lang;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.antlr.runtime.RecognitionException;

public class TypeLoaderForFlowTest extends TypeLoader {
	public TypeLoaderForFlowTest(TypeLoader parent) {
		super(parent);
		Type flow = new Type(this, TypeStandalone.Flow.name(), parent.findType(Type.ROOT_TYPE));
		this.types.add(flow);

		try {
			parent.defineNebula(new StringReader(
//@formatter:off
				"type Step {" +
				"		@Runtime NextStep String := \"Next\";" +
				"		@Runtime DoItNow YesNo := false;" +
				"		@Runtime init(){" +
				"		}; " +
				"		@Runtime next(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime end(){" +
				"			this.NextStep = \"End\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime skip(){" +
				"			this.NextStep = \"Next\";" +
				"			this.DoItNow = true;" +
				"		};" +
				"		@Runtime submit(){" +
				"			this.DoItNow = true;" +
				"		};" +
				"};"
			//@formatter:on
			));

			parent.defineNebula(new StringReader("type Begin extends Step { };"));
			parent.defineNebula(new StringReader("type End extends Step { @Runtime submit(){}; };"));
			parent.defineNebula(new StringReader("type Approve extends Step {};"));

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
		// TODO Auto-generated method stub
		return null;
	}
}

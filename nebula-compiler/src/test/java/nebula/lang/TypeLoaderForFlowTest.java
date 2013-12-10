package nebula.lang;

import java.io.StringReader;
import java.net.URL;

import org.antlr.runtime.RecognitionException;

public class TypeLoaderForFlowTest extends TypeLoader {
	public TypeLoaderForFlowTest(TypeLoader parent) {
		super(parent);

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

	@Override
	protected URL loadClassData(String name) {
		return null;
	}
}

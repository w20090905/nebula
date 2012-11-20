package nebula.lang;

import java.io.Reader;
import java.util.List;

import org.antlr.runtime.RecognitionException;

public class TypeLoaderForTest extends SystemTypeLoader {
	public TypeLoaderForTest() {
	}
	
	public List<Type> testDefineNebula(Reader in) {
		try {
			return super.defineNebula(in);
		} catch (RecognitionException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

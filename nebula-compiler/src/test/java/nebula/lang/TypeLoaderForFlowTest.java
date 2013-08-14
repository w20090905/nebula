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
		Type typeStep = new Type(this, "Step", parent.findType("Master"));
		Type typeBegin = new Type(this, "Begin", typeStep);
		Type typeEnd = new Type(this, "End", typeStep);
		Type typeApprove = new Type(this, "Approve", typeStep);
		this.types.add(flow);
		this.types.add(typeStep);
		this.types.add(typeBegin);
		this.types.add(typeEnd);
		this.types.add(typeApprove);

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

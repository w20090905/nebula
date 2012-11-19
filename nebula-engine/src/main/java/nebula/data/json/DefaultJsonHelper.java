package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

abstract class DefaultJsonHelper<T> implements JsonHelper<T> {
	final protected JsonFactory factory;
	
	public DefaultJsonHelper(JsonFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void stringifyTo(T d, Writer o) {
		try {
			JsonGenerator gen= this.factory.createJsonGenerator(o);
			this.write(gen, d);
			gen.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T readFrom(T d, Reader in) {
		try {
			JsonParser p = this.factory.createJsonParser(in);
			this.read(p, d);
			p.close();
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
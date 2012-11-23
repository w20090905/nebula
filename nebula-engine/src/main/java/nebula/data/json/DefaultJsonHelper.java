package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

abstract class DefaultJsonHelper<T> implements JsonHelper<T> {
	final protected JsonFactory factory;
	
	public DefaultJsonHelper(JsonFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void stringifyTo(T d, Writer o) {
		try {
			JsonGenerator gen= this.factory.createJsonGenerator(o);
			gen.writeStartObject();
			this.write(gen, d);//Write end object in write method
			gen.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T readFrom(T d, Reader in) {
		try {
			JsonParser parser = this.factory.createJsonParser(in);
			if(parser.nextToken()==JsonToken.START_OBJECT){
				this.read(parser, d);//check end object in read method
			}
			parser.close();
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.Entity;
import nebula.lang.Type;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class JsonHelperProvider {
	static JsonFactory factory = new JsonFactory();

	@SuppressWarnings("unchecked")
	public static <T> JsonHelper<T> getSerialize(Class<T> clz) {
		JsonHelper<T> s;
		if (clz == Type.class) {
			s = (JsonHelper<T>) new DefaultJsonHelper<>(factory, new TypeJsonDataDealer());
		} else {
			s = null;
		}

		return s;
	}

	public static JsonHelper<Entity> getSerialize(Type type) {
		return new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(type));
	}
}

class DefaultJsonHelper<T> implements JsonHelper<T> {
	final protected JsonFactory factory;
	final protected JsonDataDealer<T> dataDealer;

	public DefaultJsonHelper(JsonFactory factory, JsonDataDealer<T> dealer) {
		this.factory = factory;
		this.dataDealer = dealer;
	}

	@Override
	public void stringifyTo(T d, Writer o) {
		try {
			JsonGenerator gen = this.factory.createJsonGenerator(o);
			//gen.writeStartObject();
			this.dataDealer.writeTo("root", d, gen);// Write end object in write
													// method
			gen.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T readFrom(T d, Reader in) {
		try {
			JsonParser parser = this.factory.createJsonParser(in);
			JsonToken token = parser.nextToken();
			assert token == JsonToken.START_OBJECT;

			d = this.dataDealer.readFrom(parser, "root");// check end object in read
														// method
			parser.close();
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

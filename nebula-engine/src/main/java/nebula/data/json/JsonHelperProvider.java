package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.DataHolder;
import nebula.data.DataListener;
import nebula.data.DataStore;
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

//	public static JsonHelper<Entity> getHelper(Type type) {
//		return new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(type));
//	}

	public static DataHolder<JsonHelper<Entity>> getHelper(final DataHolder<DataStore<Entity>> storeHolder) {
		DataHolder<JsonHelper<Entity>> he = new DataHolder<JsonHelper<Entity>>() {
			DataStore<Entity> lastDataStore = storeHolder.get();
			JsonHelper<Entity> lastJsonHelper = new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(storeHolder
					.get().getType()));

			@Override
			public void set(JsonHelper<Entity> newData, JsonHelper<Entity> oldData) {
				throw new UnsupportedOperationException();
			}

			@Override
			public JsonHelper<Entity> get() {
				DataStore<Entity> currentDataStore = storeHolder.get();
				if (lastDataStore != currentDataStore) {
					lastJsonHelper = new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(storeHolder.get()
							.getType()));
				}
				return lastJsonHelper;
			}

			@Override
			public void addListener(DataListener<JsonHelper<Entity>> listener) {
				throw new UnsupportedOperationException();
			}
		};
		return he;
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
			// gen.writeStartObject();
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

			d = this.dataDealer.readFrom(parser, "root");// check end object in
															// read
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

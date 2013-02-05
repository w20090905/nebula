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

public class JsonHelperProvider {
	static JsonFactory factory = new JsonFactory();

	@SuppressWarnings("unchecked")
	public static <T> DataHelper<T,Reader,Writer> getSerialize(Class<T> clz) {
		DataHelper<T,Reader,Writer> s;
		if (clz == Type.class) {
			s = (DataHelper<T,Reader,Writer>)new DefaultJsonHelper<Type>(factory, new TypeSerializer());
		} else {
			s = null;
		}

		return s;
	}

//	public static JsonHelper<Entity> getHelper(Type type) {
//		return new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(type));
//	}

	public static DataHolder<DataHelper<Entity,Reader,Writer>> getHelper(final DataHolder<DataStore<Entity>> storeHolder) {
		DataHolder<DataHelper<Entity,Reader,Writer>> he = new DataHolder<DataHelper<Entity,Reader,Writer>>() {
			DataStore<Entity> lastDataStore = storeHolder.get();
			DataHelper<Entity,Reader,Writer> lastJsonHelper = new DefaultJsonHelper<Entity>(factory, new EntitySerializer(storeHolder
					.get().getType()));

			@Override
			public void set(DataHelper<Entity,Reader,Writer> newData,DataHelper<Entity,Reader,Writer> oldData) {
				throw new UnsupportedOperationException();
			}

			@Override
			public DataHelper<Entity,Reader,Writer> get() {
				DataStore<Entity> currentDataStore = storeHolder.get();
				if (lastDataStore != currentDataStore) {
					lastJsonHelper = new DefaultJsonHelper<Entity>(factory, new EntitySerializer(storeHolder.get()
							.getType()));
				}
				return lastJsonHelper;
			}

			@Override
			public void addListener(DataListener<DataHelper<Entity,Reader,Writer>> listener) {
				throw new UnsupportedOperationException();
			}
		};
		return he;
	}
}

class DefaultJsonHelper<T> implements DataHelper<T,Reader,Writer> {
	final protected JsonFactory factory;
	final protected JsonDataHelper<T> dataHelper;
	

	public DefaultJsonHelper(JsonFactory factory, JsonDataHelper<T> dataHelper) {
		this.factory = factory;
		this.dataHelper = dataHelper;
	}

	@Override
	public void stringifyTo(T d, Writer o) {
		try {
			JsonGenerator gen = this.factory.createJsonGenerator(o);
			// gen.writeStartObject();
			this.dataHelper.stringifyTo(d, gen);
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


			d = this.dataHelper.readFrom(d, parser);
			
			parser.close();
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

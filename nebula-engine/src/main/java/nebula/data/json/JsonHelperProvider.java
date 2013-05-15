package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.Holder;
import nebula.data.HolderListener;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Type;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class JsonHelperProvider {
	static JsonFactory factory = new JsonFactory();

	@SuppressWarnings("unchecked")
	public static <T> DataHelper<T, Reader, Writer> getSerialize(Class<T> clz) {
		DataHelper<T, Reader, Writer> s;
		if (clz == Type.class) {
			s = (DataHelper<T, Reader, Writer>) new DefaultJsonHelper<Type>(factory, new TypeSerializer());
		} else {
			s = null;
		}

		return s;
	}

	// public static JsonHelper<Entity> getHelper(Type type) {
	// return new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(type));
	// }

	public static Holder<DataHelper<Entity, Reader, Writer>> getHelper(final Holder<DataStore<Entity>> storeHolder,
			final Type type) {

		DataHelper<Entity, Reader, Writer> lastJsonHelper = (DataHelper<Entity, Reader, Writer>) new DefaultJsonHelper<Entity>(
				factory, new EntitySerializer(type));

		final Holder<DataHelper<Entity, Reader, Writer>> holder = Holder.of(lastJsonHelper);

		storeHolder.addListener(new HolderListener<DataStore<Entity>>() {
			@Override
			public boolean arrive(DataStore<Entity> newData, DataStore<Entity> oldData) {
				DataHelper<Entity, Reader, Writer> newJsonHelper = new DefaultJsonHelper<Entity>(factory,
						new EntitySerializer(type));
				holder.update(newJsonHelper);
				return false;
			}
		});
		return holder;
	}
}

class DefaultJsonHelper<T> implements DataHelper<T, Reader, Writer> {
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

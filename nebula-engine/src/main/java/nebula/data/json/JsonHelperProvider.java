package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.Entity;
import nebula.data.Holder;
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
	@SuppressWarnings("unchecked")
	public static <T> DataHelper<T, Reader, Writer> getSimpleSerialize(Class<T> clz) {
		DataHelper<T, Reader, Writer> s;
		if (clz == Type.class) {
			s = (DataHelper<T, Reader, Writer>) new DefaultJsonHelper<Type>(factory, new SimpleTypeSerializer());
		} else {
			s = null;
		}

		return s;
	}

	// public static JsonHelper<Entity> getHelper(Type type) {
	// return new DefaultJsonHelper<>(factory, new EntityJsonDataDealer(type));
	// }

	public static Holder<DataHelper<Entity, Reader, Writer>> getHelper(final Holder<Type> typeHolder) {
		final Type initType = typeHolder.get();
		DataHelper<Entity, Reader, Writer> lastJsonHelper = (DataHelper<Entity, Reader, Writer>) new DefaultJsonHelper<Entity>(
				factory, new EntitySerializer(typeHolder.get()));

		final Holder<DataHelper<Entity, Reader, Writer>> holder = new Holder<DataHelper<Entity, Reader, Writer>>(
				lastJsonHelper) {
			Type lastType = initType;

			@Override
			public DataHelper<Entity, Reader, Writer> get() {
				Type newType = typeHolder.get();
				if (this.lastType == newType) {
					return this.curData;
				} else {
					this.lastType = newType;
					this.curData = new DefaultJsonHelper<Entity>(factory, new EntitySerializer(newType));
					return this.curData;
				}
			}
		};
		return holder;
	}

	public static Holder<DataHelper<Entity, Reader, Writer>> getSimpleHelper(final Holder<Type> typeHolder) {
		final Type initType = typeHolder.get();
		DataHelper<Entity, Reader, Writer> lastJsonHelper = (DataHelper<Entity, Reader, Writer>) new DefaultJsonHelper<Entity>(
				factory, new SimpleEntitySerializer(typeHolder.get()));

		final Holder<DataHelper<Entity, Reader, Writer>> holder = new Holder<DataHelper<Entity, Reader, Writer>>(
				lastJsonHelper) {
			Type lastType = initType;

			@Override
			public DataHelper<Entity, Reader, Writer> get() {
				Type newType = typeHolder.get();
				if (this.lastType == newType) {
					return this.curData;
				} else {
					this.lastType = newType;
					this.curData = new DefaultJsonHelper<Entity>(factory, new SimpleEntitySerializer(newType));
					return this.curData;
				}
			}
		};
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

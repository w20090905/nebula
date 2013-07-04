package nebula.data.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import nebula.data.Broker;
import nebula.data.Entity;
import nebula.data.impl.BrokerCascade;
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

	public static Broker<DataHelper<Entity, Reader, Writer>> getHelper(final Broker<Type> typeHolder) {
		DataHelper<Entity, Reader, Writer> lastJsonHelper = (DataHelper<Entity, Reader, Writer>) new DefaultJsonHelper<Entity>(
				factory, new EntitySerializer(typeHolder.get()));

		final Broker<DataHelper<Entity, Reader, Writer>> broker = new BrokerCascade<DataHelper<Entity,Reader,Writer>, Type>(lastJsonHelper){
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				this.put(new DefaultJsonHelper<Entity>(factory, new EntitySerializer(newData)));
				return true;
			}
			
		};

		return broker;
	}

	public static Broker<DataHelper<Entity, Reader, Writer>> getSimpleHelper(final Broker<Type> typeHolder) {
		DataHelper<Entity, Reader, Writer> lastJsonHelper = (DataHelper<Entity, Reader, Writer>) new DefaultJsonHelper<Entity>(
				factory, new SimpleEntitySerializer(typeHolder.get()));

		final Broker<DataHelper<Entity, Reader, Writer>> broker = new BrokerCascade<DataHelper<Entity,Reader,Writer>, Type>(lastJsonHelper){
			@Override
			public boolean onUpdate(Type newData, Type oldData) {
				this.put(new DefaultJsonHelper<Entity>(factory, new SimpleEntitySerializer(newData)));
				return true;
			}
			
		};

		return broker;
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

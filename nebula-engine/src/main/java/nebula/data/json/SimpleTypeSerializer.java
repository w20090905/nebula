package nebula.data.json;

import nebula.data.Entity;
import nebula.lang.Aliases;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class SimpleTypeSerializer extends DefaultFieldSerializer<Type> implements JsonDataHelper<Type> {


	public SimpleTypeSerializer() {
		this(null, null);
	}

	public SimpleTypeSerializer(String fieldName, String frontName) {
		super(fieldName, frontName);
	}

	@Override
	public void input(JsonParser in, Entity parent, Type now) throws Exception {
		throw new UnsupportedOperationException(" Type readFrom(JsonParser in, String name) throws Exception");
	}

	@Override
	public void inputWithoutCheck(JsonParser in, Entity parent) throws Exception {
		throw new UnsupportedOperationException(" Type readFrom(JsonParser in, String name) throws Exception");
	}

	@Override
	public void output(JsonGenerator out, Type value) throws Exception {
		Type type = (Type) value;

		out.writeStartObject();
		out.writeStringField("Name", type.getName());
		if (type.getSuperType() != null) {
			out.writeStringField("SuperType", type.getSuperType().getName());
		}
		out.writeStringField("Standalone", type.getStandalone().name());

		Aliases n = type.getNameAlias();
		out.writeFieldName("NameAlias");
		out.writeStartObject();
		for (String k : n.alias.keySet()) {
			out.writeStringField(k, n.alias.get(k).toString());
		}
		out.writeEndObject();

		out.writeBooleanField("Mutable", type.isMutable());

		out.writeEndObject();

	}

	@Override
	public Type readFrom(Type d, JsonParser in) {
		throw new UnsupportedOperationException(" Type readFrom(JsonParser in, String name) throws Exception");
	}

	@Override
	public void stringifyTo(Type d, JsonGenerator o) {
		try {
			output(o, d);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
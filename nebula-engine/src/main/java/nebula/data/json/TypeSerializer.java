package nebula.data.json;

import java.util.List;

import nebula.data.Entity;
import nebula.lang.Aliases;
import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

import util.InheritHashMap;

public class TypeSerializer extends DefaultFieldSerializer<Type> implements JsonDataHelper<Type> {

	final DefaultTypeAdapter<List<Field>> fieldListDataDealer;

	public TypeSerializer() {
		this(null, null);
	}

	public TypeSerializer(String fieldName, String frontName) {
		super(fieldName, frontName);
		fieldListDataDealer = new ListJsonDataDealer<Field>(new FieldJsonDataDealer());
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
		out.writeFieldName("Fields");

		fieldListDataDealer.writeTo(null, type.getFields(), out);

		Aliases n = type.getNameAlias();
		out.writeFieldName("NameAlias");
		out.writeStartObject();
		for (String k : n.alias.keySet()) {
			out.writeStringField(k, n.alias.get(k).toString());
		}
		out.writeEndObject();

		out.writeFieldName("Attrs");
		out.writeStartObject();
		InheritHashMap p = type.getAttrs();
		for (String k : p.getNames()) {
			out.writeStringField(k, p.get(k).toString());
		}
		out.writeEndObject();

		out.writeBooleanField("Mutable", type.isMutable());
		out.writeStringField("Code", type.getCode());

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

class FieldJsonDataDealer extends DefaultTypeAdapter<Field> {
	public FieldJsonDataDealer() {
	}

	@Override
	public void writeTo(String xxx, Object value, JsonGenerator out) throws Exception {
		Field field = (Field) value;

		out.writeStartObject();

		out.writeStringField("Name", field.getName());
		out.writeBooleanField("Key", field.isKey());
		out.writeBooleanField("Unique", field.isKey());
		out.writeBooleanField("Core", field.isCore());
		out.writeBooleanField("Array", field.isArray());
		out.writeStringField("TypeName", field.getType().getName());
		out.writeStringField("Refer", field.getRefer().name());

		Aliases n = field.getNameAlias();
		out.writeFieldName("NameAlias");
		out.writeStartObject();
		for (String k : n.alias.keySet()) {
			out.writeStringField(k, n.alias.get(k).toString());
		}
		out.writeEndObject();

		out.writeObjectFieldStart("Attrs");
		InheritHashMap p = field.getAttrs();
		for (String k : p.getNames()) {
			out.writeStringField(k, p.get(k).toString());
		}
		out.writeEndObject();
		out.writeEndObject();
	}

	@Override
	public Field readFrom(JsonParser in, String name) throws Exception {
		return null;
	}
}

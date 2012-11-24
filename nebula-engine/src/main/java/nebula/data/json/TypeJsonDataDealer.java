package nebula.data.json;

import java.util.List;

import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

import util.InheritHashMap;

public class TypeJsonDataDealer extends DefaultJsonDataDealer<Type> {

	final JsonDataDealer<List<Field>> fieldListDataDealer;

	public TypeJsonDataDealer() {
		fieldListDataDealer = new ListJsonDataDealer<>(new FieldJsonDataDealer());
	}

	@Override
	public Type readFrom(JsonParser in, String name) throws Exception {
		throw new UnsupportedOperationException(" Type readFrom(JsonParser in, String name) throws Exception");
	}

	@Override
	public void writeTo(String name, Object value, JsonGenerator out) throws Exception {
		Type type = (Type) value;

		out.writeStartObject();
		out.writeStringField("name", type.getName());
		if (type.getSuperType() != null) {
			out.writeStringField("parent", type.getSuperType().getName());
		}
		out.writeStringField("standalone", type.getStandalone().name());
		out.writeFieldName("fields");
		fieldListDataDealer.writeTo(name, type.getFields(), out);
		
		out.writeFieldName("attrs");		
		out.writeStartObject();
		InheritHashMap p = type.getAttrs();
		for (String k : p.getNames()) {
			out.writeStringField(k, p.get(k).toString());
		}
		out.writeEndObject();

		out.writeBooleanField("mutable", type.isMutable());
		out.writeStringField("code", type.getCode());

		out.writeEndObject();
	}
}

class FieldJsonDataDealer extends DefaultJsonDataDealer<Field> {
	public FieldJsonDataDealer() {
	}

	@Override
	public void writeTo(String name, Object value, JsonGenerator out) throws Exception {
		Field field = (Field) value;

		out.writeStartObject();
		
		out.writeStringField("name", field.getName());
		out.writeBooleanField("isKey", field.isKey());
		out.writeBooleanField("isCore", field.isCore());
		out.writeBooleanField("isArray", field.isArray());
		out.writeStringField("typeName", field.getType().getName());

		out.writeObjectFieldStart("attrs");

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

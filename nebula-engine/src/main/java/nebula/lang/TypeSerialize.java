package nebula.lang;

import http.json.JsonSerialize;

import java.io.IOException;
import java.util.Map.Entry;

import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class TypeSerialize implements JsonSerialize<Type> {
	@Override
	public void write(JsonGenerator o, Type d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.name);
			if (d.superType != null) {
				o.writeStringField("parent", d.superType.name);
			}
			o.writeStringField("standalone", d.standalone.name());
			o.writeArrayFieldStart("fields");
			FieldSerialize fs = new FieldSerialize();
			for (Field f : d.getFields()) {
				fs.write(o, f);
			}
			o.writeEndArray();
			o.writeBooleanField("mutable", d.mutable);
			o.writeStringField("code", d.code);

			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Type read(JsonParser p, Type d) {
		if (d == null) {
			d = new Type(null, null);
		}
		JsonToken t;
		try {
			p.nextToken();
			while ((t = p.nextToken()) != null) {
				if (t != JsonToken.FIELD_NAME) {
					continue;
				}
				String fieldName = p.getCurrentName();
				p.nextToken();
				if ("code".equals(fieldName)) {
					d.code = p.getText();
				}
			}
			return d;
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class FieldSerialize implements JsonSerialize<Field> {

	@Override
	public void write(JsonGenerator o, Field d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.name);
			o.writeStringField("typename", d.type.name);
			
			o.writeObjectFieldStart("typeattrs");
			for(Entry<Object, Object> e:d.type.attrs.entrySet()){
				o.writeStringField(e.getKey().toString(), e.getValue().toString());
			}
			o.writeEndObject();
			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Field read(JsonParser p, Field d) {
		return null;
	}

}

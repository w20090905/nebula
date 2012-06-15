package nebula.lang;

import http.json.JsonSerialize;

import java.io.IOException;

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
	public void read(JsonParser p, Type d) {
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
			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void read(JsonParser p, Field d) {
		
	}

}
package http.json;

import java.io.IOException;

import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;

public class TypeSerialize implements JsonSerialize<Type> {
	@Override
	public void write(JsonGenerator o, Type d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.getName());
			o.writeStringField("standalone", d.getStandalone().name());
			o.writeArrayFieldStart("fields");
			FieldSerialize fs = new FieldSerialize();
			for (Field f : d.getFields()) {
				fs.write(o, f);
			}
			o.writeEndArray();
			o.writeStringField("text", d.getText());

			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void read(JsonGenerator generator, Type obj) {
		// TODO Auto-generated method stub

	}

}

class FieldSerialize implements JsonSerialize<Field> {

	@Override
	public void write(JsonGenerator o, Field d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.getName());
			o.writeStringField("typename", d.getType().getName());
			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void read(JsonGenerator i, Field d) {
	}

}

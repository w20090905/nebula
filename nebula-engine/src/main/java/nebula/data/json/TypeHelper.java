package nebula.data.json;


import java.io.IOException;

import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import util.InheritHashMap;

public class TypeHelper extends DefaultJsonHelper<Type> {
	public TypeHelper(JsonFactory factory) {
		super(factory);
	}

	@Override
	public void write(JsonGenerator o, Type d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.getName());
			if (d.getSuperType() != null) {
				o.writeStringField("parent", d.getSuperType().getName());
			}
			o.writeStringField("standalone", d.getStandalone().name());
			o.writeArrayFieldStart("fields");
			FieldSerialize fs = new FieldSerialize(super.factory);
			for (Field f : d.getFields()) {
				fs.write(o, f);
			}
			o.writeEndArray();
			
			o.writeObjectFieldStart("attrs");			
			InheritHashMap p = d.getAttrs();	
			for(String k:p.getNames()){
				o.writeStringField(k,p.get(k).toString());				
			}
			o.writeEndObject();
			
			o.writeBooleanField("mutable", d.isMutable());
			o.writeStringField("code", d.getCode());

			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Type read(JsonParser p, Type d) {
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
					String code = p.getText();
					//d.getCode() = p.getText();
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

class FieldSerialize extends DefaultJsonHelper<Field> {
	public FieldSerialize(JsonFactory factory) {
		super(factory);
	}

	@Override
	public void write(JsonGenerator o, Field d) {
		try {
			o.writeStartObject();
			o.writeStringField("name", d.getName());
			o.writeStringField("typename", d.getType().getName());
			
			o.writeObjectFieldStart("attrs");
			
			InheritHashMap p = d.getAttrs();	
			for(String k:p.getNames()){
				o.writeStringField(k,p.get(k).toString());				
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

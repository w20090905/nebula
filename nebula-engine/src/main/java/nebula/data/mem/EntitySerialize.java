package nebula.data.mem;

import http.json.JsonSerialize;

import java.io.IOException;

import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class EntitySerialize implements JsonSerialize<Entity> {
	@Override
	public void write(JsonGenerator o, Entity d) {
		try {
			o.writeStartObject();
			EntityImp v = (EntityImp) d;
			Type type = v.store.type;
			for (Field f : type.getFields()) {
				String name = f.getName();
				Object ov = (String) v.get(name);
				if(ov!=null){
					o.writeStringField(f.getName(), (String) v.get(f.getName()));					
				}else{
					
				}
			}
			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Entity read(JsonParser p, Entity d) {
		JsonToken t;
		try {
			p.nextToken();
			while ((t = p.nextToken()) != null) {
				if (t != JsonToken.FIELD_NAME) {
					continue;
				}
				String fieldName = p.getCurrentName();
				p.nextToken();
				d.put(fieldName, p.getText());
			}
			return d;
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

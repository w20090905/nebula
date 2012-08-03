package nebula.data.impl;

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
			Type type = ((EntityStore) v.store).type;
			for (Field f : type.getFields()) {
				Type rT;
				switch (f.getRefer()) {
				case ByVal:
					write(o, d, f.getName());
					break;
				case Inline:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						write(o, d, f.getName() + rf.getName());
					}
					break;
				case ByRef:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						switch (rf.getImportance()) {
						case Key:
						case Core:
							write(o, d, f.getName() + rf.getName());
							break;
						}
					}
					break;
				case Cascade:
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						switch (rf.getImportance()) {
						case Key:
						case Core:
							write(o, d, f.getName() + rf.getName());
							break;
						}
					}
					break;
				}				
			}
			o.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void write(JsonGenerator o,Entity d,String name) throws JsonGenerationException, IOException{
		Object ov = (String) d.get(name);
		if (ov != null) {
			o.writeStringField(name, (String)ov);
		} else {

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

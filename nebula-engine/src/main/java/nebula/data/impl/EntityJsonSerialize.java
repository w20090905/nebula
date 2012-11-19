package nebula.data.impl;

import http.json.JsonProvider.JsonDealer;
import http.json.PageField;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.data.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class EntityJsonSerialize implements JsonDealer<Entity> {
	private Log log = LogFactory.getLog(this.getClass());

	final List<PageField> pageFields;
	final Map<String, PageField> fieldMap;

	public EntityJsonSerialize(final List<PageField> pageFields) {
		this.pageFields = pageFields;
		fieldMap = new HashMap<String, PageField>();
		for (PageField pageField : pageFields) {
			fieldMap.put(pageField.pageName, pageField);
		}
	}

	@Override
	public void stringifyTo(Entity d, Writer o) {
		try {
			JsonFactory factory = new JsonFactory();
			JsonGenerator gen = factory.createJsonGenerator(o);
			gen.writeStartObject();

			for (PageField f : pageFields) {
				f.dataDealer.writeTo(d.get(f.name), f.pageName, gen);
			}

			gen.writeEndObject();
			gen.flush();
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Entity readFrom(Entity d, Reader in) {
		try {
			JsonFactory factory = new JsonFactory();
			JsonParser p = factory.createJsonParser(in);
			PageField f = null;

			JsonToken t;
			try {
				p.nextToken();
				while ((t = p.nextToken()) != null) {
					if (t != JsonToken.FIELD_NAME) {
						continue;
					}
					String fieldName = p.getCurrentName();
					p.nextToken();

					f = fieldMap.get(fieldName);
					if (f != null) {
						d.put(f.name, f.dataDealer.readFrom(p));
					}
				}
			} catch (JsonParseException e) {
				log.error(e);
				throw new RuntimeException(e);
			} catch (Exception e) {
				log.error(e);
				throw new RuntimeException(e);
			}
			return d;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

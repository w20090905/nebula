package nebula.data.json;

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

class EntityJsonHelper extends DefaultJsonHelper<Entity> {
	private Log log = LogFactory.getLog(this.getClass());

	final List<PageField> pageFields;
	final Map<String, PageField> fieldMap;

	public EntityJsonHelper(final JsonFactory factory, final List<PageField> pageFields) {
		super(factory);

		this.pageFields = pageFields;
		fieldMap = new HashMap<String, PageField>();
		for (PageField pageField : pageFields) {
			switch (pageField.innerType) {
			case String:
				pageField.dataDealer = new StringJsonDataDealer();
				break;
			case Boolean:
				pageField.dataDealer = new BooleanJsonDataDealer();
				break;
			case Long:
				pageField.dataDealer = new LongJsonDataDealer();
				break;
			case Decimal:
				pageField.dataDealer = new DecimalJsonDataDealer();
				break;
			case Date:
				pageField.dataDealer = new DateJsonDataDealer();
				break;
			case Time:
				pageField.dataDealer = new TimeJsonDataDealer();
				break;
			case Datetime:
				pageField.dataDealer = new DatetimeJsonDataDealer();
				break;
			case Timestamp:
				pageField.dataDealer = new TimestampJsonDataDealer();
				break;
			case Text:
				pageField.dataDealer = new TextBlockJsonDataDealer();
				break;
			default:
				throw new UnsupportedOperationException("rawType out range");
			}

			fieldMap.put(pageField.name, pageField);
		}
	}

	@Override
	public void write(JsonGenerator gen, Entity d) {
		try {
			gen.writeStartObject();
			for (PageField f : pageFields) {
				f.dataDealer.writeTo(f.name, d.get(f.name), gen);
			}
			gen.writeEndObject();
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Entity read(JsonParser parser, Entity d) {
		PageField f = null;

		JsonToken t;
		try {
			t = parser.nextToken();
			assert t == JsonToken.END_OBJECT;

			while ((t = parser.nextToken()) != null) {
				if (t != JsonToken.FIELD_NAME) {
					break;
				}
				String fieldName = parser.getCurrentName();

				parser.nextToken();

				f = fieldMap.get(fieldName);
				if (f != null) {
					d.put(f.name, f.dataDealer.readFrom(parser, fieldName));
				}
			}
			assert t == JsonToken.END_OBJECT;
		} catch (JsonParseException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}

		return d;
	}

}

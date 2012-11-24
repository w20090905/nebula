package nebula.data.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Field;
import nebula.lang.RawTypes;
import nebula.lang.Type;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

class EntityJsonDataDealer extends DefaultJsonDataDealer<Entity> {
	final List<PageField> pageFields;
	final Map<String, PageField> pageFieldsMap;

	public EntityJsonDataDealer(Type type) {
		pageFields = new CopyOnWriteArrayList<>();
		pageFieldsMap = new HashMap<>();
		init(type);
	}

	private JsonDataDealer<?> getBasicDateDealer(RawTypes rawType) {
		JsonDataDealer<?> dataDealer;
		switch (rawType) {
		case String:
			dataDealer = new StringJsonDataDealer();
			break;
		case Boolean:
			dataDealer = new BooleanJsonDataDealer();
			break;
		case Long:
			dataDealer = new LongJsonDataDealer();
			break;
		case Decimal:
			dataDealer = new DecimalJsonDataDealer();
			break;
		case Date:
			dataDealer = new DateJsonDataDealer();
			break;
		case Time:
			dataDealer = new TimeJsonDataDealer();
			break;
		case Datetime:
			dataDealer = new DatetimeJsonDataDealer();
			break;
		case Timestamp:
			dataDealer = new TimestampJsonDataDealer();
			break;
		case Text:
			dataDealer = new TextBlockJsonDataDealer();
			break;
		default:
			throw new UnsupportedOperationException("rawType out range");
		}
		return dataDealer;
	}

	private void addPageField(String name, String pageFieldName, JsonDataDealer<?> jsonDataDealer) {
		PageField pageField = new PageField(pageFieldName, name, jsonDataDealer);
		this.pageFields.add(pageField);
		this.pageFieldsMap.put(pageFieldName, pageField);
	}

	private void init(Type type) {
		for (Field f : type.getFields()) {
			Type rT;
			switch (f.getRefer()) {
			case ByVal:
				if (f.isArray()) {
					JsonDataDealer<?> dataDealer = new ListJsonDataDealer<>(
							getBasicDateDealer(f.getType().getRawType()));
					addPageField(f.getName(), f.getName(), dataDealer);
				} else {
					addPageField(f.getName(), f.getName(), getBasicDateDealer(f.getType().getRawType()));
				}
				break;
			case Inline:
				if (f.isArray()) {
					JsonDataDealer<?> dataDealer = new ListJsonDataDealer<>(new EntityJsonDataDealer(f.getType()));
					addPageField(f.getName(), f.getName(), dataDealer);
				} else {
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						addPageField(f.getName() + rf.getName(), f.getName() + rf.getName(), getBasicDateDealer(rf.getType()
								.getRawType()));
					}
				}
				break;
			case ByRef:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					switch (rf.getImportance()) {
					case Key:
					case Core:
						addPageField(f.getName(), f.getName() + rf.getName(), getBasicDateDealer(rf.getType()
								.getRawType()));
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
						addPageField(f.getName(), f.getName() + rf.getName(), getBasicDateDealer(rf.getType()
								.getRawType()));
						break;
					}
				}
				break;
			}
		}
	}

	@Override
	public void writeTo(String name, Object value, JsonGenerator out) throws Exception {
		Entity entity = (Entity) value;
		out.writeStartObject();
		for (PageField f : pageFields) {
			out.writeFieldName(f.name);
			f.dataDealer.writeTo(f.name, entity.get(f.name), out);
		}
		out.writeEndObject();
	}

	@Override
	public Entity readFrom(JsonParser in, String name) throws Exception {
		PageField f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		Entity entity = new EditableEntity();

		while ((token = in.nextToken()) != null) {
			if (token != JsonToken.FIELD_NAME) {
				break;
			}
			String fieldName = in.getCurrentName();
			
			in.nextToken();// To value

			f = pageFieldsMap.get(fieldName);
			if (f != null) {
				entity.put(f.name, f.dataDealer.readFrom(in, fieldName));
			}
		}
		assert token == JsonToken.END_OBJECT;
		return entity;
	}

}

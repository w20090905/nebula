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

class JsonEntityFieldMerger extends JsonFieldMerger<Entity> implements JsonDataHelper<Entity> {
	final List<JsonFieldMerger<?>> pageFields;
	final Map<String, JsonFieldMerger<?>> pageFieldsMap;

	public JsonEntityFieldMerger(Type type) {
		this(type, null, null);
	}

	public JsonEntityFieldMerger(Type type, String fieldName, String frontName) {
		super(fieldName, frontName);

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
		BasicTypeFieldMerger pageField = new BasicTypeFieldMerger(pageFieldName, name, jsonDataDealer);
		this.pageFields.add(pageField);
		this.pageFieldsMap.put(pageFieldName, pageField);
	}

	// private void addEntityPageField(String name, String
	// pageFieldName,JsonEntityFieldMerger entityMerger) {
	// this.pageFields.add(entityMerger);
	// this.pageFieldsMap.put(pageFieldName, entityMerger);
	// }

	private void addEntityListPageField(String name, String pageFieldName, JsonEntityFieldMerger entityMerger) {
		JsonFieldMerger<List<Entity>> pageField = new JsonEntityListFieldMerger(pageFieldName, name, entityMerger);
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
					addEntityListPageField(f.getName(), f.getName(), new JsonEntityFieldMerger(f.getType()));
				} else {
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						addPageField(f.getName() + rf.getName(), f.getName() + rf.getName(), getBasicDateDealer(rf
								.getType().getRawType()));
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

	void outputInList(JsonGenerator out, Entity current) throws Exception {
		Entity entity = current;
		out.writeStartObject();
		out.writeFieldName("_idx");
		out.writeNumber((long) current.get("_idx"));

		for (JsonFieldMerger<?> f : pageFields) {
			@SuppressWarnings("unchecked")
			JsonFieldMerger<Object> m = (JsonFieldMerger<Object>) f;
			out.writeFieldName(f.fieldName);
			m.output(out, entity.get(f.fieldName));
		}
		out.writeEndObject();
	}

	@Override
	public void output(JsonGenerator out, Entity current) throws Exception {
		Entity entity = current;
		out.writeStartObject();
		for (JsonFieldMerger<?> f : pageFields) {
			@SuppressWarnings("unchecked")
			JsonFieldMerger<Object> m = (JsonFieldMerger<Object>) f;
			out.writeFieldName(f.fieldName);
			m.output(out, entity.get(f.fieldName));
		}
		out.writeEndObject();
	}

	@Override
	public Entity readFrom(Entity d, JsonParser in) {
		try {
			
			JsonToken token = in.nextToken();
			assert token == JsonToken.START_OBJECT;
			
			if (d == null) {
				return doReadWithoutCheck(in, new EditableEntity());
			} else {
				return doReadWithoutCheck(in, d);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void stringifyTo(Entity d, JsonGenerator o) {
		try {
			output(o, d);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	Entity doReadWithoutCheck(JsonParser in, Entity current) throws Exception {
		Entity entity = current;

		JsonMerger<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		while ((token = in.nextToken()) != null) {
			if (token != JsonToken.FIELD_NAME) {
				break;
			}
			String frontName = in.getCurrentName();

			in.nextToken();// To value

			f = (JsonMerger<Object>) pageFieldsMap.get(frontName);
			if (f != null) {
				f.inputWithoutCheck(in, entity);
			}
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	@SuppressWarnings("unchecked")
	Entity doReadInList(JsonParser in, List<Entity> now) throws Exception {
		Entity entity = null;

		JsonFieldMerger<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		token = in.nextToken(); // Index
		if ("_idx".equals(in.getCurrentName())) {
			int idx = in.getIntValue();
			entity = now.get(idx);

			while ((token = in.nextToken()) != null) {
				if (token != JsonToken.FIELD_NAME) {
					break;
				}
				String frontName = in.getCurrentName();

				in.nextToken();// To value

				f = (JsonFieldMerger<Object>) pageFieldsMap.get(frontName);
				if (f != null) {
					f.input(in, entity, entity.get(f.fieldName));
				}
			}

		} else {
			in.getIntValue();
			entity = new EditableEntity();

			while ((token = in.nextToken()) != null) {
				if (token != JsonToken.FIELD_NAME) {
					break;
				}
				String frontName = in.getCurrentName();

				in.nextToken();// To value

				f = (JsonFieldMerger<Object>) pageFieldsMap.get(frontName);
				if (f != null) {
					f.inputWithoutCheck(in, entity);
				}
			}
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	@SuppressWarnings("unchecked")
	private Entity doRead(JsonParser in, Entity current) throws Exception {
		Entity entity = current;

		JsonFieldMerger<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		while ((token = in.nextToken()) != null) {
			if (token != JsonToken.FIELD_NAME) {
				break;
			}
			String frontName = in.getCurrentName();

			in.nextToken();// To value

			f = (JsonFieldMerger<Object>) pageFieldsMap.get(frontName);
			if (f != null) {
				f.input(in, entity, entity.get(f.fieldName));
			}
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	@Override
	public Entity input(JsonParser in, Entity parent, Entity current) throws Exception {
		if (current == null) {
			current = new EditableEntity();
			parent.put(fieldName, current);
		}
		Entity entity = doRead(in, current);
		return entity;
	}

	@Override
	public Entity inputWithoutCheck(JsonParser in, Entity parent) throws Exception {

		Entity current = new EditableEntity();
		parent.put(fieldName, current);

		Entity entity = doReadWithoutCheck(in, current);
		return entity;
	}

}

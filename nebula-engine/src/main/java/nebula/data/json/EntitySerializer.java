package nebula.data.json;

import java.io.IOException;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

class EntitySerializer extends DefaultFieldSerializer<Entity> implements JsonDataHelper<Entity> {
	final List<DefaultFieldSerializer<?>> pageFields;
	final Map<String, DefaultFieldSerializer<?>> pageFieldsMap;
	final boolean topLevel;

	public EntitySerializer(Type type) {
		this(type, null, null);
	}

	public EntitySerializer(Type type, String fieldName, String frontName) {
		super(fieldName, frontName);
		if (fieldName == null) {
			topLevel = true;
		} else {
			topLevel = false;
		}

		pageFields = new CopyOnWriteArrayList<DefaultFieldSerializer<?>>();
		pageFieldsMap = new HashMap<String, DefaultFieldSerializer<?>>();
		init(type);
	}

	private DefaultTypeAdapter<?> getBasicDateDealer(RawTypes rawType) {
		DefaultTypeAdapter<?> dataDealer;
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

	private void addPageField(String name, String pageFieldName, DefaultTypeAdapter<?> jsonDataDealer) {
		BasicTypeFieldSerializer pageField = new BasicTypeFieldSerializer(pageFieldName, name, jsonDataDealer);
		this.pageFields.add(pageField);
		this.pageFieldsMap.put(pageFieldName, pageField);
	}

	// private void addEntityPageField(String name, String
	// pageFieldName,JsonEntityFieldMerger entityMerger) {
	// this.pageFields.add(entityMerger);
	// this.pageFieldsMap.put(pageFieldName, entityMerger);
	// }
	private void addEntityPageField(String name, String pageFieldName, EntitySerializer entityMerger) {
		this.pageFields.add(entityMerger);
		this.pageFieldsMap.put(pageFieldName, entityMerger);
	}

	private void addEntityListPageField(String name, String pageFieldName, EntitySerializer entityMerger) {
		DefaultFieldSerializer<List<Entity>> pageField = new EntityListSerializer(pageFieldName, name, entityMerger);
		this.pageFields.add(pageField);
		this.pageFieldsMap.put(pageFieldName, pageField);
	}

	private void init(Type type) {
		for (Field of : type.getFields()) {
			if (!of.isArray()) {
				switch (of.getRefer()) {
				case ByVal: // Type A1
					addPageField(of.getName(), of.getName(), getBasicDateDealer(of.getType().getRawType()));
					break;
				case Inline: // Type A2 // TODO
					if (topLevel) {
						EntitySerializer es = new EntitySerializer(of.getType(), of.getName(), of.getName());
						addEntityPageField(of.getName(), of.getName(), es);
					} else {
						for (Field inf : of.getType().getFields()) {
							if (!inf.isArray()) {
								addPageField(inf.getName(), of.getName() + inf.getName(), getBasicDateDealer(inf
										.getType().getRawType()));
							}
						}
					}
					break;
				case ByRef: // Type A3 Type A4
				case Cascade:
					for (Field inf : of.getType().getFields()) {
						if (!inf.isArray() && (inf.isKey() || inf.isCore())) {
							addPageField(of.getName(), of.getName() + inf.getName(), getBasicDateDealer(inf.getType()
									.getRawType()));
						}
					}
					break;
				}
			} else {
				switch (of.getRefer()) {
				case ByVal: // Type A5
					@SuppressWarnings({ "unchecked", "rawtypes" })
					DefaultTypeAdapter<?> dataDealer = new ListJsonDataDealer(getBasicDateDealer(of.getType()
							.getRawType()));
					addPageField(of.getName(), of.getName(), dataDealer);
					break;
				case Inline:
					addEntityListPageField(of.getName(), of.getName(), new EntitySerializer(of.getType(), of.getName(),
							of.getName()));
					break;
				case ByRef:
				case Cascade:
					throw new UnsupportedOperationException();
				}
			}
		}
	}

	void outputInList(JsonGenerator out, Entity current) throws Exception {
		Entity entity = current;
		out.writeStartObject();
		out.writeFieldName("_idx");
		out.writeNumber((int) current.get("_idx"));

		for (DefaultFieldSerializer<?> f : pageFields) {
			@SuppressWarnings("unchecked")
			DefaultFieldSerializer<Object> m = (DefaultFieldSerializer<Object>) f;
			out.writeFieldName(f.fieldName);
			m.output(out, entity.get(f.fieldName));
		}
		out.writeEndObject();
	}

	@Override
	public void output(JsonGenerator out, Entity current) throws Exception {
		Entity entity = current;
		out.writeStartObject();
		for (DefaultFieldSerializer<?> f : pageFields) {
			@SuppressWarnings("unchecked")
			DefaultFieldSerializer<Object> m = (DefaultFieldSerializer<Object>) f;
			Object data = entity.get(f.fieldName);
			if (data != null) {
				out.writeFieldName(f.fieldName);
				m.output(out, data);
			}
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
				return doRead(in, d);
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

		DefaultFieldSerializer<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		while ((token = in.nextToken()) != null) {
			if (token != JsonToken.FIELD_NAME) {
				break;
			}
			String frontName = in.getCurrentName();

			in.nextToken();// To value

			f = (DefaultFieldSerializer<Object>) pageFieldsMap.get(frontName);
			if (f != null) {
				f.inputWithoutCheck(in, entity);
			} else {
				skip(in);
			}
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	private void skip(JsonParser in) throws JsonParseException, IOException {
		JsonToken token = in.getCurrentToken();
		switch (token) {
		case START_ARRAY:
			skipArray(in);
			break;
		case START_OBJECT:
			skipObject(in);
		default:
			break;
		}
	}

	private void skipArray(JsonParser in) throws JsonParseException, IOException {
		JsonToken token;
		OutWhile: while ((token = in.nextToken()) != null) {
			switch (token) {
			case END_ARRAY:
				break OutWhile;
			case START_OBJECT:
				skipObject(in);
			default:
				skip(in);
				break;
			}
		}
	}

	private void skipObject(JsonParser in) throws JsonParseException, IOException {
		JsonToken token;
		OutWhile: while ((token = in.nextToken()) != null) {
			switch (token) {
			case END_OBJECT:
				break OutWhile;
			case FIELD_NAME:
				token = in.nextToken();
				skip(in);
				break;
			default:
				throw new JsonParseException(fieldName, null);
			}
		}
	}

	@SuppressWarnings("unchecked")
	Entity doReadInList(JsonParser in, List<Entity> now) throws Exception {
		Entity entity = null;

		DefaultFieldSerializer<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		token = in.nextToken(); // Index
		if ("_idx".equals(in.getCurrentName())) {
			token = in.nextToken();
			int idx = in.getIntValue();
			entity = now.get(idx);

			while ((token = in.nextToken()) != null) {
				if (token != JsonToken.FIELD_NAME) {
					break;
				}
				String frontName = in.getCurrentName();

				in.nextToken();// To value

				if ("_action".equals(in.getCurrentName())) {
					if ("D".equals(in.getText())) {
						entity.put("_action", "D");
						continue;
					}
				}

				f = (DefaultFieldSerializer<Object>) pageFieldsMap.get(frontName);
				if (f != null) {
					f.input(in, entity, entity.get(f.fieldName));
				} else {
					skip(in);
				}
			}

		} else {
			// in.getIntValue();
			entity = new EditableEntity();

			do {
				if (token != JsonToken.FIELD_NAME) {
					break;
				}
				String frontName = in.getCurrentName();

				in.nextToken();// To value

				f = (DefaultFieldSerializer<Object>) pageFieldsMap.get(frontName);
				if (f != null) {
					f.inputWithoutCheck(in, entity);
				} else {
					skip(in);
				}
			} while ((token = in.nextToken()) != null);

			now.add(entity);
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	@SuppressWarnings("unchecked")
	private Entity doRead(JsonParser in, Entity current) throws Exception {
		Entity entity = current;

		DefaultFieldSerializer<Object> f = null;
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_OBJECT;

		while ((token = in.nextToken()) != null) {
			if (token != JsonToken.FIELD_NAME) {
				break;
			}
			String frontName = in.getCurrentName();

			in.nextToken();// To value

			f = (DefaultFieldSerializer<Object>) pageFieldsMap.get(frontName);
			if (f != null) {
				f.input(in, entity, entity.get(f.fieldName));
			} else {
				skip(in);
			}
		}

		assert token == JsonToken.END_OBJECT;

		return entity;
	}

	@Override
	public void input(JsonParser in, Entity parent, Entity current) throws Exception {
		if (current == null) {
			current = new EditableEntity();
			parent.put(fieldName, current);
		}
		doRead(in, current);
	}

	@Override
	public void inputWithoutCheck(JsonParser in, Entity parent) throws Exception {

		Entity current = new EditableEntity();
		parent.put(fieldName, current);

		doReadWithoutCheck(in, current);
	}

}

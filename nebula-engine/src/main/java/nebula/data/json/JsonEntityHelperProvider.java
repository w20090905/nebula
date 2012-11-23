package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Type;

import org.codehaus.jackson.JsonFactory;

public class JsonEntityHelperProvider {
	static JsonFactory factory = new JsonFactory();

	@SuppressWarnings("unchecked")
	public static <T> JsonHelper<T> getSerialize(Class<T> clz) {
		JsonHelper<T> s;
		if (clz == Type.class) {
			s = (JsonHelper<T>) new TypeJsonHelper(factory);
		} else {
			s = null;
		}

		return s;
	}

	private static void addBadicTypePageDataDealer(final List<PageField> pageFields, Field field, String name) {
		String pageName = name;
		PageField pageField = new PageField(pageName, pageName, field.getType().getRawType());
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
		pageFields.add(pageField);
	}

	public static JsonHelper<Entity> getSerialize(Type type) {
		List<PageField> pageFields = new ArrayList<>();

		for (Field f : type.getFields()) {
			Type rT;
			switch (f.getRefer()) {
			case ByVal:
				if (f.isArray()) {

				} else {
					addBadicTypePageDataDealer(pageFields, f, f.getName());
				}
				break;
			case Inline:
				if (f.isArray()) {

				} else {
					rT = f.getType();
					for (Field rf : rT.getFields()) {
						addBadicTypePageDataDealer(pageFields, rf, f.getName() + rf.getName());
					}
				}
				break;
			case ByRef:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					switch (rf.getImportance()) {
					case Key:
					case Core:
						addBadicTypePageDataDealer(pageFields, rf, f.getName() + rf.getName());
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
						addBadicTypePageDataDealer(pageFields, rf, f.getName() + rf.getName());
						break;
					}
				}
				break;
			}
		}
		return new EntityJsonHelper(factory, pageFields);
	}
}

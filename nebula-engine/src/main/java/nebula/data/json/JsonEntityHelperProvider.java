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
			s = (JsonHelper<T>) new TypeHelper(factory);
		} else {
			s = null;
		}

		return s;
	}

	private static void addPageDataDealer(final List<PageField> pageFields, Field field, String name) {
		String pageName = name;
		PageField pageField = new PageField(pageName, pageName, field.getType().getRawType());

		pageFields.add(pageField);
	}

	public static JsonHelper<Entity> getSerialize(Type type) {
		List<PageField> pageFields = new ArrayList<>();

		for (Field f : type.getFields()) {
			Type rT;
			switch (f.getRefer()) {
			case ByVal:
				addPageDataDealer(pageFields, f, f.getName());
				break;
			case Inline:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					addPageDataDealer(pageFields, rf, f.getName() + rf.getName());
				}
				break;
			case ByRef:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					switch (rf.getImportance()) {
					case Key:
					case Core:
						addPageDataDealer(pageFields, rf, f.getName() + rf.getName());
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
						addPageDataDealer(pageFields, rf, f.getName() + rf.getName());
						break;
					}
				}
				break;
			}
		}
		return new EntityJsonHelper(factory, pageFields);
	}
}

package http.resource;

import java.util.List;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Type;
import freemarker.template.Configuration;

public class AttachedTypeTemplateResouce extends TypeTemplateResouce {

	final Type attachedType;

	public AttachedTypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, DataStore<Entity> attributes, String path, String theme, String skin,
			Type attachedType, Type type, String specName, String layoutName, String actionName) {
		super(cfg, dataWareHouse, attributes, path, theme, skin, type, makeName(attachedType, type, specName, layoutName, actionName));
		this.attachedType = attachedType;
	}

	private static String makeName(Type attachedType, Type type, String specName, String layout, String actionName) {
		String attachedEntityType = (String) attachedType.getStandalone().name().toLowerCase();

		String entityType = (String) type.getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.getAttrs().get("Layout");

		String name = attachedEntityType + "_" + entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		name = specName != null ? specName + "_" + name : name;
		return name;
	}

	@Override
	protected void fillData() {
		root.put("attachedType", layout(attachedType));
		super.fillData();
	}

	private Type layout(Type type) {
		List<Field> fields = type.getFields();
		Field lastField = null;
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getAttrs().containsKey(Type.ATTACH)) {
				continue;
			}
			if (field.getAttrs().containsKey("SingleLine")) {
				if (lastField != null) {
					lastField.getAttrs().remove("HasFollowing");
				}
				lastField = null;
			} else {
				if (lastField != null) {
					if (field.getAttrs().containsKey("ShouldBeLeader")) {
						lastField.getAttrs().remove("HasFollowing");
					} else {
						lastField.getAttrs().put("HasFollowing", "HasFollowing");
					}
				}
				lastField = field;
			}
		}
		return type;
	}
}

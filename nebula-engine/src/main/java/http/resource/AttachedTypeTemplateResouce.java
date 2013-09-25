package http.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Type;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AttachedTypeTemplateResouce extends TypeTemplateResouce {

	final Broker<Type> attachedType;

	public AttachedTypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, Broker<DataStore<Entity>> attributes, String theme, String skin,
			 Broker<Type> attachedType, Broker<Type> type,String layout, String actionName) {
		super(cfg, dataWareHouse, attributes, theme, skin, type, makeName(attachedType, type, layout, actionName));
		this.attachedType = attachedType;
	}

	private static String makeName(Broker<Type> attachedType, Broker<Type> type, String layout, String actionName) {
		String attachedEntityType = (String) attachedType.get().getStandalone().name().toLowerCase();

		String entityType = (String) type.get().getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.get().getAttrs().get("Layout");

		String name = attachedEntityType + "_" + entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		return name;
	}

	@Override
	protected void fillData() {
		root.put("attachedType", layout(attachedType.get()));
		super.fillData();
	}

	private Type layout(Type type) {
		List<Field> fields = type.getFields();
		Field lastField = null;
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
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

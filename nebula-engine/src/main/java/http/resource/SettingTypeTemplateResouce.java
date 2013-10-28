package http.resource;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Type;
import freemarker.template.Configuration;

public class SettingTypeTemplateResouce extends TypeTemplateResouce {

	public SettingTypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, DataStore<Entity> attributes, String theme, String skin, Type type,
			String path, String spec, String layout, String actionName) {
		super(cfg, dataWareHouse, attributes, path, theme, skin, type, makeName(spec, type, layout, actionName));
	}

	private static String makeName(String spec, Type type, String layout, String actionName) {

		String entityType = (String) type.getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.getAttrs().get("Layout");

		String name = spec + "_" + entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		return name;
	}
}

package http.resource;

import http.resource.template.LoadDataMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.lang.Code;
import nebula.lang.Field;
import nebula.lang.Flow.Step;
import nebula.lang.JsCallCompiler;
import nebula.lang.JsCompiler;
import nebula.lang.Type;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;

public class TypeTemplateResouce extends AbstractResouce {
	final Configuration cfg;
	// private final String templateName;

	TemplateMethodModel dataWareHouseModel;

	final Map<String, Object> root = new HashMap<String, Object>();
	final DataRepos dataWareHouse;
	final DataStore<Entity> attributes;
	final String path;
	// final TypeLoader typeLoader;
	final String theme;
	final String skin;
	final Type type;
	// final String actionName;
	final String name;

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, DataStore<Entity> attributes, String path, String theme, String skin, Type type,
			String specName, String layoutName, String actionName) {
		this(cfg, dataWareHouse, attributes, path, theme, skin, type, makeName(type, specName, layoutName, actionName));
	}

	private static String makeName(Type type, String specName, String layout, String actionName) {
		String entityType = (String) type.getStandalone().name().toLowerCase();

		layout = layout != null ? layout : (String) type.getAttrs().get("Layout");

		String name = entityType + "_" + layout.toLowerCase() + "_" + actionName.toLowerCase() + ".ftl";
		name = specName != null ? specName + "_" + name : name;
		return name;
	}

	public TypeTemplateResouce(Configuration cfg, DataRepos dataWareHouse, DataStore<Entity> attributes, String path, String theme, String skin, Type type,
			String name) {
		super("text/template", 0, 0);// TODO Not realized TypeTemplateResouce
										// super("text/template", 0, 0)

		this.cfg = cfg;

		this.dataWareHouse = dataWareHouse;
		this.dataWareHouseModel = new LoadDataMethod(dataWareHouse);
		this.attributes = attributes;

		this.path = path;

		this.theme = theme;
		this.skin = skin;

		this.type = type;
		this.name = name;

		root.put("alldatas", dataWareHouseModel);
		root.put("_path", path);
		root.put("_theme", this.theme);
		root.put("_skin", this.skin);
		root.put("_spec", "admin");
	}

	protected void fillData() {
		root.put("type", layout(type));
		DataStore<Entity> attrs = attributes;
		root.put("attrs", attrs);
	}

	protected void get(HttpServletRequest req) throws IOException {
		try {
			TemplateLoader loader = cfg.getTemplateLoader();
			String templateName = null;

			if (loader.findTemplateSource(theme + "/" + skin + "/" + name) != null) {
				templateName = theme + "/" + skin + "/" + name;
			} else if (loader.findTemplateSource(theme + "/" + name) != null) {
				templateName = theme + "/" + name;
			} else if (loader.findTemplateSource(name) != null) {
				templateName = name;
			} else {
				throw new IOException(name + " can not find!");
			}

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			Writer w = new OutputStreamWriter(bout);

			fillData();

			Template template = cfg.getTemplate(templateName);
			template.process(root, w);
			w.flush();
			w.close();

			super.lastModified = System.currentTimeMillis();
			super.cache = bout.toByteArray();
		} catch (TemplateException e) {
			log.error("Template prase error", e);
			throw new IOException(e);
		}
	}

	public static final String HasFollowing = "HasFollowing";
	private Type layout(Type type) {
		List<Field> fields = type.getFields();
		Field lastField = null;
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (field.getAttrs().containsKey(Field.SingleLine)) {
				if (lastField != null) {
					lastField.getAttrs().remove(HasFollowing);
				}
				lastField = null;
			} else {
				if (lastField != null) {
					if (field.getAttrs().containsKey(Field.ShouldBeLeader)) {
						lastField.getAttrs().remove(HasFollowing);
					} else {
						lastField.getAttrs().put(HasFollowing, HasFollowing);
					}
				}
				lastField = field;
			}
			if (field.isDerived() && !field.getAttrs().containsKey(Field.ComputeBackend)) {
				field.getAttrs().put("DerivedExpression", JsCompiler.compiler(field.getCode(), "this", "data"));
			}
			if (field.isDefaultValue() && !field.getAttrs().containsKey(Field.ComputeBackend)) {
				field.getAttrs().put("DefaultExpression", JsCompiler.compiler(field.getCode(), "this", "data"));
			}
			if (field.getAttrs().containsKey(Field.DisplayOn)) {
				field.getAttrs().put(Field.DisplayOn + "Expression", JsCompiler.compiler((Code)field.getAttrs().get(Field.DisplayOn), "this", "data"));
			}
			if (field.getAttrs().containsKey(Field.RequiredOn)) {
				field.getAttrs().put(Field.RequiredOn + "Expression", JsCompiler.compiler((Code)field.getAttrs().get(Field.RequiredOn), "this", "data"));
			}
		}

//		if (!type.getAttrs().containsKey("AjaxExpressionName")) {
			StringBuilder sb = new StringBuilder();
			String ctrlName = type.getName() + "_OnChangeCtrl";
			sb.append("<script>function " + ctrlName + "($scope){var doCall = function( $scope ){");
			for (int i = 0; i < fields.size(); i++) {
				Field field = fields.get(i);
				if (field.getOnChangeCode() != null) {
					sb.append(JsCallCompiler.compiler("data." + field.getName(), field.getOnChangeCode(), "this", "data"));
				}
			}
			sb.append("};doCall( $scope.$parent );}</script>");
			type.getAttrs().put("AjaxExpressionName", ctrlName);
			type.getAttrs().put("AjaxExpression", sb.toString());
//		}
			
			List<Step> steps = type.getSteps();
			if(steps!=null){
				for (Step step : steps) {
					layout(step.getType());
				}
			}
			

		return type;
	}
}

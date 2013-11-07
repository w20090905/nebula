package nebula.simpletemplate;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ST {
	public final static String VERSION = "4.0.7-SNAPSHOT";

	/**
	 * {@code <@r()>}, {@code <@r>...<@end>}, and {@code @t.r() ::= "..."}
	 * defined manually by coder
	 */
	public static enum RegionType {
		/** {@code <@r()>} */
		IMPLICIT,
		/** {@code <@r>...<@end>} */
		EMBEDDED,
		/** {@code @t.r() ::= "..."} */
		EXPLICIT
	}

	public static final String UNKNOWN_NAME = "anonymous";
	public static final Object EMPTY_ATTR = new Object();

	/**
	 * When there are no formal args for template t and you map t across some
	 * values, t implicitly gets arg "it". E.g., "<b>$it$</b>"
	 */
	public static final String IMPLICIT_ARG_NAME = "it";

	/**
	 * The implementation for this template among all instances of same template
	 * .
	 */
	public TemplateImpl impl;

	public STGroup groupThatCreatedThisInstance;

	/** Used by group creation routine, not by users */
	protected ST() {
	}

	/**
	 * Used to make templates inline in code for simple things like SQL or log
	 * records. No formal arguments are set and there is no enclosing instance.
	 */
	public ST(String template) {
		this(STGroup.defaultGroup, template, '<', '>');
	}

	public ST(String template, char delimiterStartChar, char delimiterStopChar) {
		this(STGroup.defaultGroup, template, delimiterStartChar, delimiterStopChar);
	}

	public ST(STGroup group, String template, char delimiterStartChar, char delimiterStopChar) {
		groupThatCreatedThisInstance = group;
		impl = groupThatCreatedThisInstance.parse(null, null, null, template, delimiterStartChar, delimiterStopChar);
		impl.name = UNKNOWN_NAME;
	}

	public ST(STGroup group, String template) {
		this(group, template, '<', '>');
	}

	public ST(STGroup group, TemplateImpl template) {
		this();
		groupThatCreatedThisInstance = group;
		impl = template;
	}

	public String getName() {
		return impl.name;
	}

	public String render() {
		if (this.datas != null) {
			return impl.execNamed(datas);
		} else {
			return impl.exec();
		}
	}

	public String render(Object... argv) {
		return impl.exec(argv);
	}

	public <T> String renderNamed(Map<String, T> namedParams) {
		return impl.execNamed(namedParams);
	}

	public <T> String renderList(List<T> dataList) {
		return impl.execList(dataList);
	}

	@Override
	public String toString() {
		if (impl == null) return "bad-template()";
		String name = impl.name + "()";
		// if (this.impl.isRegion) {
		// name = "@" + STGroup.getUnMangledTemplateName(name);
		// }

		return name;
	}

	public static String format(String template, Object... attributes) {
		return format(-1, template, attributes);
	}

	public static String format(int lineWidth, String template, Object... attributes) {
		template = template.replaceAll("%([0-9]+)", "arg$1");
		ST st = new ST(template);
		// int i = 1;
		// for (Object a : attributes) {
		// st.add("arg" + i, a);
		// i++;
		// }
		return st.render(lineWidth);
	}

	private Map<String, Object> datas = null;

	public void add(String key, Object value) {
		if (datas == null) datas = Maps.newHashMap();
		datas.put(key, value);
	}
}

package nebula.simpletemplate;

import java.util.ArrayList;
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
	public CompiledST impl;

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

	public ST(CompiledST template) {
		groupThatCreatedThisInstance = template.nativeGroup;
		impl = template;
	}

	public ST(String template, char delimiterStartChar, char delimiterStopChar) {
		this(STGroup.defaultGroup, template, delimiterStartChar, delimiterStopChar);
	}

	public ST(STGroup group, String template, char delimiterStartChar, char delimiterStopChar) {
		group.delimiterStartChar = delimiterStartChar;
		group.delimiterStopChar  = delimiterStopChar;
		groupThatCreatedThisInstance = group;
		impl = groupThatCreatedThisInstance.compile(group.getFileName(), null,
				null, template, null);
		impl.name = UNKNOWN_NAME;
	}

	public ST(STGroup group, String template) {
		this(group, template, '<', '>');
	}

	public ST(STGroup group, CompiledST template) {
		this();
		groupThatCreatedThisInstance = group;
		impl = template;
	}

	public ST(ST proto) {
		this();
		groupThatCreatedThisInstance = proto.groupThatCreatedThisInstance;
		impl = proto.impl;
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
		return this.render();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ST add(String key, Object value) {
		if (value == null) return this;
		if (datas == null) datas = Maps.newHashMap();

		if (!datas.containsKey(key)) {
			if (value instanceof List) {
				CustomList<Object> list = new CustomList();
				List listValue = (List) value;
				for (Object v : listValue) {
					list.add(v);
				}
				datas.put(key, list);
			} else if (value.getClass().isArray()) {
				CustomList<Object> list = new CustomList();
				Object[] listValue = (Object[]) value;
				for (int i = 0; i < listValue.length; i++) {
					list.add(listValue[i]);
				}
				datas.put(key, list);
			} else {
				datas.put(key, value);
			}
		} else {
			Object o = datas.get(key);

			CustomList<Object> list = null;
			if (o instanceof CustomList) {
				list = (CustomList) o;
			} else {
				list = new CustomList();
				list.add(o);
				datas.put(key, list);
			}

			if (value instanceof List) {
				List listValue = (List) value;
				for (Object v : listValue) {
					list.add(v);
				}
			} else if (value.getClass().isArray()) {
				Object[] listValue = (Object[]) value;
				for (int i = 0; i < listValue.length; i++) {
					list.add(listValue[i]);
				}
			} else {
				list.add(value);
			}
		}
		return this;
	}

	static public class CustomList<T> extends ArrayList<T> {
		private static final long serialVersionUID = -5819856302643555224L;
	}
}

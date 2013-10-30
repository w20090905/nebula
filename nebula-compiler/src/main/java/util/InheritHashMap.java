package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class InheritHashMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 2504234689413219141L;
	private InheritHashMap defaults = null;

	public InheritHashMap() {
		super();
	}

	public InheritHashMap(InheritHashMap defaults) {
		super();
		this.defaults = defaults;
	}

	public void setDefaults(InheritHashMap defaults) {
		this.defaults = defaults;
	}

	@Override
	public Object get(Object key) {
		Object oval = super.get(key);
		return ((oval == null) && (defaults != null)) ? defaults.get(key) : oval;
	}

	public Object get(Object key, Object defaultValue) {
		Object val = get(key);
		return (val == null) ? defaultValue : val;
	}

	public Set<String> getNames() {
		Set<String> names = null;
		if (defaults == null) {
			names = new TreeSet<String>();
		} else {
			names = defaults.getNames();
		}

		names.addAll(super.keySet());

		return names;
	}

	@Override
	public int size() {
		return getNames().size();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty() && (defaults != null ? defaults.isEmpty() : true);
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key) || (defaults != null ? defaults.containsKey(key) : false);
	}

	@Override
	public boolean containsValue(Object value) {
		return super.containsValue(value) || (defaults != null ? defaults.containsValue(value) : false);
	}

	@Override
	public Set<String> keySet() {
		return super.keySet();
	}

	@Override
	public Collection<Object> values() {
		return super.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return super.entrySet();
	}

}

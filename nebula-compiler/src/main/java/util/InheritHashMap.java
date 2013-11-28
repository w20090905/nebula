package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InheritHashMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 2504234689413219141L;
	private InheritHashMap defaults = null;

	public InheritHashMap() {
		super();
		this.makeAll();
	}

	public InheritHashMap(InheritHashMap defaults) {
		super();
		this.defaults = defaults;
		this.makeAll();
	}

	public void setDefaults(InheritHashMap defaults) {
		this.defaults = defaults;
		this.makeAll();
	}

	@Override
	public Object get(Object key) {
		return cachedAll.get(key);
	}

	public Object get(Object key, Object defaultValue) {
		Object val = get(key);
		return (val == null) ? defaultValue : val;
	}

	public Set<String> getNames() {
		return cachedAll.keySet();
	}

	@Override
	public int size() {
		return getNames().size();
	}

	@Override
	public boolean isEmpty() {
		return cachedAll.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return cachedAll.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return cachedAll.containsValue(value);
	}

	@Override
	public Set<String> keySet() {
		return cachedAll.keySet();
	}

	@Override
	public Collection<Object> values() {
		return cachedAll.values();
	}

	Map<String, Object> cachedAll;

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return cachedAll.entrySet();
	}

	private void makeAll() {
		if (defaults != null) {
			cachedAll = new HashMap<String, Object>(defaults);
		} else {
			cachedAll = new HashMap<String, Object>();
		}
		
		for (Map.Entry<String, Object> entry : super.entrySet()) {
			cachedAll.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Object put(String key, Object value) {
		super.put(key, value);
		return cachedAll.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		super.putAll(m);
		cachedAll.putAll(m);
	}

	@Override
	public Object remove(Object key) {
		super.remove(key);
		return cachedAll.remove(key);
	}

	@Override
	public Object clone() {
		InheritHashMap clone = (InheritHashMap) super.clone();
		this.makeAll();
		return clone;
	}

}

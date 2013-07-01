package test.load.pdftxt;

public abstract class CrazyMap {
	protected final int MASK;
	protected String[] keys;
	protected String[] values;

	CrazyMap(String[] keys, String[] values) {
		this.keys = keys;
		this.values = values;
		MASK = keys.length - 1;
	}

	public CrazyMap addKey(String key) {
		int h = this.code(key);
		if (keys[h] == null) {
			keys[h] = key;
			return this;
		} else if (keys[h].equals(key)) {
			return this;
		} else {
			return this.upgrade(this);
		}
	}

	static public CrazyMap newMap() {
		return new RawCrazyMap(new String[16], new String[16]);
	}

	static public CrazyMap newMap(String... keys) {
		CrazyMap map = new RawCrazyMap(new String[16], new String[16]);
		for (String key : keys) {
			map = map.addKey(key);
		}
		return map;
	}

	protected abstract int code(String key);

	protected abstract CrazyMap upgrade(CrazyMap map);

	public void put(String key, String value) {
		values[code(key)] = value;
	}

	public String get(String key) {
		return values[code(key)];
	}

	public abstract CrazyMap shadow();

	static class RawCrazyMap extends CrazyMap {
		public RawCrazyMap(String[] keys, String[] values) {
			super(keys, values);
		}

		@Override
		public final int code(String key) {
			return (key.charAt(0) - 'a') & MASK;
		}

		@Override
		protected CrazyMap upgrade(CrazyMap map) {
			return new AdvCrazyMap(map.keys, map.values);
		}

		@Override
		public CrazyMap shadow() {
			return new RawCrazyMap(keys, new String[keys.length]);
		}
	};

	static class AdvCrazyMap extends CrazyMap {
		public AdvCrazyMap(String[] keys, String[] values) {
			super(keys, values);
		}

		@Override
		public final int code(String key) {
			return (key.charAt(0) + key.charAt(1) - 'a') & MASK;
		}

		@Override
		protected CrazyMap upgrade(CrazyMap map) {
			return new AdvCrazyMap(map.keys, map.values);
		}

		@Override
		public CrazyMap shadow() {
			return new AdvCrazyMap(keys, new String[keys.length]);
		}
	};
}

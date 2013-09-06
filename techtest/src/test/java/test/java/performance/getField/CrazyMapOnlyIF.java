package test.java.performance.getField;

public abstract class CrazyMapOnlyIF {
	public CrazyMapOnlyIF addKey(String key) {
	    String[] keys = this.getKeys();
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

	static public CrazyMapOnlyIF newMap() {
		return new RawCrazyMap(new String[16], new String[16]);
	}

	static public CrazyMapOnlyIF newMap(String... keys) {
		CrazyMapOnlyIF map = new RawCrazyMap(new String[16], new String[16]);
		for (String key : keys) {
			map = map.addKey(key);
		}
		return map;
	}

    public abstract void put(String key, String value);
    public abstract String get(String key);
    
    public abstract CrazyMapOnlyIF shadow();
    protected abstract int code(String key);
    protected abstract String[] getKeys();
    protected abstract String[] getValues();
	protected abstract CrazyMapOnlyIF upgrade(CrazyMapOnlyIF map);

	
	
	static class RawCrazyMap extends CrazyMapOnlyIF {
        @Override
        public int code(String key) {
            return (key.charAt(0) - 'a') & MASK;
        }        

        @Override
        protected CrazyMapOnlyIF upgrade(CrazyMapOnlyIF map) {
            return new AdvCrazyMap(map.getKeys(), map.getValues());
        }

        @Override
        public CrazyMapOnlyIF shadow() {
            return new RawCrazyMap(keys, new String[keys.length]);
        }
        
        
        private int MASK;
        private String[] keys;
        private String[] values;
		public RawCrazyMap(String[] keys, String[] values) {
		    this.keys = keys;
		    this.values = values;
		    this.MASK = keys.length-1;
		}


        public void put(String key, String value) {
            values[(key.charAt(0) + key.charAt(1) - 'a') & MASK] = value;
        }

        public String get(String key) {
            return values[(key.charAt(0) + key.charAt(1) - 'a') & MASK];
        }

        @Override
        protected String[] getKeys() {
            return this.keys;
        }

        @Override
        protected String[] getValues() {
            return this.values;
        }
        
        
	};

	static class AdvCrazyMap extends CrazyMapOnlyIF {

		@Override
		public final int code(String key) {
			return (key.charAt(0) + key.charAt(1) - 'a') & MASK;
		}

		@Override
		protected CrazyMapOnlyIF upgrade(CrazyMapOnlyIF map) {
			return new AdvCrazyMap(map.getKeys(), map.getValues());
		}

		@Override
		public CrazyMapOnlyIF shadow() {
			return new AdvCrazyMap(keys, new String[keys.length]);
		}
		
        protected final int MASK;
        protected final String[] keys;
        protected final String[] values;
        public AdvCrazyMap(String[] keys, String[] values) {
            this.keys = keys;
            this.values = values;
            this.MASK = keys.length-1;
        }

        public void put(String key, String value) {
            values[(key.charAt(0) + key.charAt(1) - 'a') & MASK] = value;
        }

        public String get(String key) {
            return values[(key.charAt(0) + key.charAt(1) - 'a') & MASK];
        }

        @Override
        protected String[] getKeys() {
            return this.keys;
        }

        @Override
        protected String[] getValues() {
            return this.values;
        }
	};
}

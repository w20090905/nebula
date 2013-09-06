package test.java.performance.getField;

public abstract class CrazyMapIF implements CMap{
	public CMap addKey(String key) {
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

	static public CMap newMap() {
		return new RawCrazyMap(new String[16], new String[16]);
	}

	static public CMap newMap(String... keys) {
	    CMap map = new RawCrazyMap(new String[16], new String[16]);
		for (String key : keys) {
			map = map.addKey(key);
		}
		return map;
	}

	
	
	static class RawCrazyMap extends CrazyMapIF implements CMap {
        @Override
        public int code(String key) {
            return (key.charAt(0) - 'a') & MASK;
        }        

        public CMap upgrade(CMap map) {
            return new AdvCrazyMap(map.getKeys(), map.getValues());
        }

        @Override
        public CMap shadow() {
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

        public String[] getKeys() {
            return this.keys;
        }

        public String[] getValues() {
            return this.values;
        }
        
        
	};

	static class AdvCrazyMap  extends CrazyMapIF implements CMap {

		@Override
		public final int code(String key) {
			return (key.charAt(0) + key.charAt(1) - 'a') & MASK;
		}

		@Override
		public CMap upgrade(CMap map) {
			return new AdvCrazyMap(map.getKeys(), map.getValues());
		}

		@Override
		public CrazyMapIF shadow() {
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
        public String[] getKeys() {
            return this.keys;
        }

        @Override
        public String[] getValues() {
            return this.values;
        }
	};
}

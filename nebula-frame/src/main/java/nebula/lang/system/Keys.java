package nebula.lang.system;

import nebula.lang.Vo;

public abstract class Keys {
    public abstract Object get(Vo v);

    public static Keys create(String[] keys) {
        switch (keys.length) {
        case 1:
            return new Keys1(keys[0]);
        case 2:
            return new Keys2(keys[0], keys[1]);
        case 3:
            return new Keys3(keys[0], keys[1], keys[2]);
        default:
            throw new RuntimeException("KeysColumn must less than three");
        }
    }

    static class Keys1 extends Keys {
        String key1;

        Keys1(String key) {
            this.key1 = key;
        }

        @Override
        public Object get(Vo v) {
            return v.get(key1.toString());
        }
    }

    static class Keys2 extends Keys {
        String key1;
        String key2;

        Keys2(String key1, String key2) {
            this.key1 = key1;
            this.key2 = key2;
        }

        @Override
        public Object get(Vo v) {
            return v.get(key1.toString()).toString() + "-" + v.get(key2.toString()).toString();
        }
    }

    static class Keys3 extends Keys {
        String key1;
        String key2;
        String key3;

        Keys3(String key1, String key2, String key3) {
            this.key1 = key1;
            this.key2 = key2;
            this.key3 = key3;
        }

        @Override
        public Object get(Vo v) {
            return v.get(key1.toString()).toString() + "-" + v.get(key2.toString()).toString() + "-"
                    + v.get(key2.toString()).toString();
        }
    }
}

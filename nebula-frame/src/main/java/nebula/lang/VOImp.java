package nebula.lang;

import java.util.HashMap;
import java.util.Map;

public class VOImp implements Vo {
    final VOType type;
    final Map<String, Object> items = new HashMap<String, Object>();

    boolean beModified = false;
    String indentify = null;

    VOImp(VOType type) {
        this.type = type;
    }

    VOImp(VOType type, Object... params) {
        this(type);

        for (int i = 0; i < params.length; i += 2) {
            if (params[i + 1] instanceof Object) {
                items.put((String) params[i], params[i + 1]);
            } else {
                items.put((String) params[i], params[i + 1]);
            }
        }
        
        items.put("_key", type.keys.get(this));
        
        beModified = true;
    }

    @Override
    public Object get(String name) {
        return items.get(name);
    }

    @Override
    public void put(String name, Object v) {
        items.put(name, v);
        beModified = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, Object> entry : items.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":");
            if (entry.getValue() != null) {
                sb.append("\"");
                sb.append(entry.getValue().toString());
                sb.append("\"");
            }
            sb.append(",");
        }
        if (sb.length() > 1) {
            sb.setCharAt(sb.length() - 1, '}');
        } else {
            sb.append('}');
        }
        return sb.toString();
    }

    @Override
    public Object get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}

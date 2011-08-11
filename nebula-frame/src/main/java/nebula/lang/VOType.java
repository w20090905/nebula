package nebula.lang;

import java.util.ArrayList;

import nebula.lang.system.Keys;
import nebula.lang.system.Type;

public class VOType implements ObjectProvider<Vo> {
    final Type type;
    final Keys keys;
    public VOType(Type type) {
        this.type = type;
        ArrayList<String> kes = new ArrayList<String>();

        for (Type.Field field : type.getFields()) {
            if (field.getImportance() == Type.Field.PrimaryKey) {
                kes.add(field.getName());
            }
        }
        String[] ks = kes.toArray(new String[0]);
        keys = Keys.create(ks);        
    }

    @Override
    public Vo instance() {
        return new VOImp(this);
    }
    
}

package test.asm.basic.type;

import java.io.IOException;


import org.objectweb.asm.ClassReader;


public class TypeReader {
    TypeReader() {
        Type type = new Type("system.Type");
        type.fields.add(new Field("name"));
        type.fields.add(new Field("displayName"));
        type.fields.add(new Field("importance"));
        type.fields.add(new Field("type_name"));

        type.fields.add(new Field("type"));
        type.fields.add(new Field("array"));
        type.fields.add(new Field("valueList"));
        type.fields.add(new Field("refer"));

        // String name;
        // String displayName;
        // String importance = Normal;
        // String type_name;
        // Type type;
        //
        // String array = "1";
        // String[] valueList;
        // String refer;
    }

    public static void main(String[] args) {
        try {
            String name = Field.class.getName();// "test.asm.basic.type.Field";
            ClassReader classReader = new ClassReader(name);
            classReader.accept(new TypeReaderAdapter(name), ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
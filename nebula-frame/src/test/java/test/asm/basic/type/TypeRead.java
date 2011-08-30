package test.asm.basic.type;

import java.io.IOException;
import java.util.Stack;

import nebula.lang.system.DisplayName;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.signature.SignatureReader;

import util.PrintObejct;

public class TypeRead {
    TypeRead() {
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
            classReader.accept(new AccessClassAdapter(name), ClassReader.EXPAND_FRAMES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class AccessClassAdapter extends ClassNopAdapter {
        final String className;
        Type type;

        public AccessClassAdapter(String className) {
            this.className = className;
            type = new Type(className);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String s, boolean flag) {
            final String name = s;
            return new AnnotationNopAdapter() {
                @Override
                public void visit(String s, Object obj) {
                    if (A.class.getName().equals(name)) {
                        FlexBL fb = new FlexBL((String) obj);
                        for (FlexBL.Entry e : fb) {
                            String key = e.getKey();
                            String value = e.getValue();
                        }
                    } else if (DisplayName.class.getName().equals(name)) {
                        type.displayName = (String) obj;
                    }
                }
            };
        }

        public FieldVisitor visitField(final int access, final String name, final String desc, final String signature,
                final Object value) {

            if (access == 25) {
                return null;
            }

            final Field f = new Field(name);
            f.type_name = desc;

            System.out.println("+++ Field  { access : " + access + " name:  " + name + " desc:  " + desc
                    + " signature:  " + signature + " value:  " + value + " };");

            f.type_name = desc;

            if (desc.charAt(0) == '[') {
                f.array = "n";
                f.type_name = desc.substring(1);
            }

            if (signature != null) {
                new SignatureReader(signature).accept(new SignatureNopAdapter() {
                    Stack<String> s = new Stack<String>();

                    @Override
                    public void visitClassType(String name) {
                        if (s.size() < 1) {
                            if ("java/util/List".indexOf(name) < 0) {
                                throw new UnsupportedOperationException(name + " is Unsupported");
                            }
                            f.array = "n";
                            s.push(name);
                        } else {
                            f.type_name = name;
                        }
                    }
                });
            }

            switch (f.type_name.charAt(0)) {
            case 'L':
                f.array = "1";
                f.type_name = desc.substring(0, desc.length() - 1);
                break;
            default:

            }

            return new FieldNopAdapter() {
                @Override
                public AnnotationVisitor visitAnnotation(String s, boolean flag) {
                    final String name = s;
                    return new AnnotationNopAdapter() {
                        @Override
                        public void visit(String s, Object obj) {
                            if (DisplayName.class.getName().equals(name)) {
                                f.displayName = (String) obj;
                            }
                        }
                    };
                }

                @Override
                public void visitEnd() {
                    type.fields.add(f);
                }

            };
        }

        @Override
        public void visitEnd() {
            PrintObejct.print(Type.class, type);
        }

    }
}
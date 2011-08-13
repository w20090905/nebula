package test.asm.basic.type;

import java.io.IOException;

import nebula.lang.system.DisplayName;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.FieldVisitor;

import util.PrintObejct;

public class TypeRead {

    public static void main(String[] args) {

        try {
            // ClassWriter classWriter = new
            // ClassWriter(ClassWriter.COMPUTE_MAXS);

            // ClassAdapter accessClassAdaptor = new
            // AccessClassAdapter(classWriter);

            String name = "test.asm.basic.type.Field";
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
                    if (DisplayName.class.getName().equals(name)) {
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
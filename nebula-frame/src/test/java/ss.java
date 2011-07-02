import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceMethodVisitor;

public class ss {

    public static void main(String[] args) {

        try {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter delLoginClassAdaptor = new DelLoginClassAdapter(classWriter);
            ClassAdapter accessClassAdaptor = new AccessClassAdapter(delLoginClassAdaptor);

            ClassReader classReader = new ClassReader("ss");
            classReader.accept(accessClassAdaptor, ClassReader.SKIP_DEBUG);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static class DelLoginClassAdapter extends ClassAdapter {
        public DelLoginClassAdapter(ClassVisitor cv) {
            super(cv);
        }

        public MethodVisitor visitMethod(final int access, final String name, final String desc,
                final String signature, final String[] exceptions) {
            if (name.equals("login")) {
                return null;
            }

            System.out.println(name);
            MethodVisitor mv = new TraceMethodVisitor(cv.visitMethod(access, name, desc, signature, exceptions)){
                @Override
                public void visitEnd() {
                    super.visitEnd();
                    System.out.println(text);
                }
            };
            return mv;
        }
    }

    static class AccessClassAdapter extends ClassAdapter {
        public AccessClassAdapter(ClassVisitor cv) {
            super(cv);
        }

        public FieldVisitor visitField(final int access, final String name, final String desc, final String signature,
                final Object value) {
            int privateAccess = Opcodes.ACC_PRIVATE;
            return cv.visitField(privateAccess, name, desc, signature, value);
        }
    }
}
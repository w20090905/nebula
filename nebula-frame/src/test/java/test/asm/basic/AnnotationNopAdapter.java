package test.asm.basic;

import org.objectweb.asm.AnnotationVisitor;

public class AnnotationNopAdapter implements AnnotationVisitor {
    @Override
    public void visit(String s, Object obj) {
    }

    @Override
    public void visitEnum(String s, String s1, String s2) {
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, String s1) {
        return null;
    }

    @Override
    public AnnotationVisitor visitArray(String s) {
        return null;
    }

    @Override
    public void visitEnd() {
    }

}

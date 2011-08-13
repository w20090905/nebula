package test.asm.basic.type;

import org.objectweb.asm.AnnotationVisitor;

public class AnnotationAdapter implements AnnotationVisitor {
    final AnnotationVisitor av;

    public AnnotationAdapter(AnnotationVisitor av) {
        this.av = av;
    }

    @Override
    public void visit(String s, Object obj) {
        av.visit(s, obj);
    }

    @Override
    public void visitEnum(String s, String s1, String s2) {
        av.visitEnum(s, s1, s2);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, String s1) {
        return av.visitAnnotation(s, s1);
    }

    @Override
    public AnnotationVisitor visitArray(String s) {
        return av.visitArray(s);
    }

    @Override
    public void visitEnd() {
        av.visitEnd();
    }

}

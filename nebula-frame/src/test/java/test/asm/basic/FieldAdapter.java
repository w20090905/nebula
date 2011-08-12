package test.asm.basic;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

public class FieldAdapter implements FieldVisitor {
    final FieldVisitor fv;
    public FieldAdapter(FieldVisitor fv){
        this.fv = fv;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean flag) {
        return fv.visitAnnotation(s, flag);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        fv.visitAttribute(attribute);
    }

    @Override
    public void visitEnd() {
        fv.visitEnd();
    }

}

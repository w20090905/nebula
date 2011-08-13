package test.asm.basic.type;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

public class FieldNopAdapter implements FieldVisitor {
    public FieldNopAdapter(){
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean flag) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
    }

    @Override
    public void visitEnd() {
    }

}

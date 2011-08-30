package test.asm.basic.util;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassNopAdapter implements ClassVisitor {

    @Override
    public void visit(int i, int j, String s, String s1, String s2, String[] as) {
        
    }

    @Override
    public void visitSource(String s, String s1) {
        
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
         
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean flag) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        
    }

    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
       
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object obj) {

        return null;
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] as) {
        return null;
    }

    @Override
    public void visitEnd() {
        
    }

}

package test.asm.basic.type;

import java.io.IOException;

import nebula.lang.system.DisplayName;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;

import util.PrintObejct;

public class TypeRead {

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

            if(signature!=null){
                SignatureReader sr=new SignatureReader(signature);
                sr.accept(new SignatureNopAdapter());
                
//                {
//
//                    @Override
//                    public void visitFormalTypeParameter(String name) {
//                        // TODO Auto-generated method stub
//                        super.visitFormalTypeParameter(name);
//                    }
//
//                    @Override
//                    public SignatureVisitor visitInterfaceBound() {
//                        // TODO Auto-generated method stub
//                        return super.visitInterfaceBound();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitSuperclass() {
//                        // TODO Auto-generated method stub
//                        return super.visitSuperclass();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitInterface() {
//                        // TODO Auto-generated method stub
//                        return super.visitInterface();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitParameterType() {
//                        // TODO Auto-generated method stub
//                        return super.visitParameterType();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitReturnType() {
//                        // TODO Auto-generated method stub
//                        return super.visitReturnType();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitExceptionType() {
//                        // TODO Auto-generated method stub
//                        return super.visitExceptionType();
//                    }
//
//                    @Override
//                    public void visitBaseType(char descriptor) {
//                        // TODO Auto-generated method stub
//                        super.visitBaseType(descriptor);
//                    }
//
//                    @Override
//                    public void visitTypeVariable(String name) {
//                        // TODO Auto-generated method stub
//                        super.visitTypeVariable(name);
//                    }
//
//                    @Override
//                    public SignatureVisitor visitArrayType() {
//                        // TODO Auto-generated method stub
//                        return super.visitArrayType();
//                    }
//
//                    @Override
//                    public void visitClassType(String name) {
//                        // TODO Auto-generated method stub
//                        super.visitClassType(name);
//                    }
//
//                    @Override
//                    public void visitInnerClassType(String name) {
//                        // TODO Auto-generated method stub
//                        super.visitInnerClassType(name);
//                    }
//
//                    @Override
//                    public void visitTypeArgument() {
//                        // TODO Auto-generated method stub
//                        super.visitTypeArgument();
//                    }
//
//                    @Override
//                    public SignatureVisitor visitTypeArgument(char wildcard) {
//                        // TODO Auto-generated method stub
//                        return super.visitTypeArgument(wildcard);
//                    }
//
//                    @Override
//                    public void visitEnd() {
//                        // TODO Auto-generated method stub
//                        super.visitEnd();
//                    }
//                    
//                });
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
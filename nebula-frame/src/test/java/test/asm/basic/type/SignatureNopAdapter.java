package test.asm.basic.type;

import org.objectweb.asm.signature.SignatureVisitor;

public class SignatureNopAdapter implements SignatureVisitor {

    @Override
    public void visitFormalTypeParameter(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public SignatureVisitor visitClassBound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitInterfaceBound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitSuperclass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitInterface() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitParameterType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitReturnType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SignatureVisitor visitExceptionType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitBaseType(char descriptor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visitTypeVariable(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public SignatureVisitor visitArrayType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitClassType(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visitInnerClassType(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visitTypeArgument() {
        // TODO Auto-generated method stub

    }

    @Override
    public SignatureVisitor visitTypeArgument(char wildcard) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visitEnd() {
        // TODO Auto-generated method stub

    }

}

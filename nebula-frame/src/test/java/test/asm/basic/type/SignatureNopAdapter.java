package test.asm.basic.type;

import org.objectweb.asm.signature.SignatureVisitor;

public class SignatureNopAdapter implements SignatureVisitor {

    @Override
    public void visitFormalTypeParameter(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public SignatureVisitor visitClassBound() {
        return this;
    }

    @Override
    public SignatureVisitor visitInterfaceBound() {
        return this;
    }

    @Override
    public SignatureVisitor visitSuperclass() {
        return this;
    }

    @Override
    public SignatureVisitor visitInterface() {
        return this;
    }

    @Override
    public SignatureVisitor visitParameterType() {
        return this;
    }

    @Override
    public SignatureVisitor visitReturnType() {
        return this;
    }

    @Override
    public SignatureVisitor visitExceptionType() {
        return this;
    }

    @Override
    public void visitBaseType(char descriptor) {

    }

    @Override
    public void visitTypeVariable(String name) {

    }

    @Override
    public SignatureVisitor visitArrayType() {
        return this;
    }

    @Override
    public void visitClassType(String name) {
    }

    @Override
    public void visitInnerClassType(String name) {
    }

    @Override
    public void visitTypeArgument() {
    }

    @Override
    public SignatureVisitor visitTypeArgument(char wildcard) {
        return this;
    }

    @Override
    public void visitEnd() {
    }

}

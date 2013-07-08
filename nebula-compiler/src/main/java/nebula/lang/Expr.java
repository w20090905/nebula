package nebula.lang;

import org.objectweb.asm.MethodVisitor;

public interface Expr<T> {
    void compile(MethodVisitor mv);
    Type getExprType();
    
	T exec();
}

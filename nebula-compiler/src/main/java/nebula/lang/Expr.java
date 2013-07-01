package nebula.lang;

import org.objectweb.asm.MethodVisitor;

public interface Expr {
    void compile(MethodVisitor mv);
	Object exec();
}

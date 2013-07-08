package nebula.lang;

import org.objectweb.asm.MethodVisitor;

public interface Statement {
    void compile(MethodVisitor mv);
	void exec();
}

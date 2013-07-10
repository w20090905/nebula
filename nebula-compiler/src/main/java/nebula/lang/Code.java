package nebula.lang;

import org.objectweb.asm.MethodVisitor;

public interface Code {
    void compile(MethodVisitor mv);
}

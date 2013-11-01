package nebula.simpletemplate;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface Code {
    void compile(ClassWriter cw, MethodVisitor mv, CompilerContext context);
    String toString(CompilerContext context);
}

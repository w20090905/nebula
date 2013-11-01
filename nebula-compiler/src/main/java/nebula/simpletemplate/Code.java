package nebula.simpletemplate;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public interface Code {
    Type compile(ClassWriter cw, MethodVisitor mv, CompilerContext context);
    String toString(CompilerContext context);
}

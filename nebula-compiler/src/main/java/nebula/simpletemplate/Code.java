package nebula.simpletemplate;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface Code {
	Class<?> compile(String clzName, ClassWriter cw, MethodVisitor mv, CompilerContext context);

	String toString(CompilerContext context);
}

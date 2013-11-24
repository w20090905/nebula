package nebula.lang;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import com.google.common.collect.ImmutableMap;

public class MethodAsmCompiler extends AsmCompiler {
	static Map<String, Integer> locals = new HashMap<String, Integer>(ImmutableMap.of("nop", 0, "context", 1, "repos", 2, "this", 3));

	public MethodAsmCompiler(ClassWriter cw, MethodVisitor mv) {
		super(cw, mv);
	}

	private int getLocal(String name) {
		Integer i = locals.get(name);
		if (i != null) {
			return i;
		} else {
			locals.put(name, locals.size());
			return locals.get(name);
		}
	}

	@Override
	public void varRefer(Var var) {
		super.varRefer(getLocal(var.name));
	}
}

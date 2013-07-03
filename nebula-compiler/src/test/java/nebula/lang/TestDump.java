package nebula.lang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import nebula.asm.ASMSampleDump;

import org.objectweb.asm.Opcodes;

public class TestDump extends ClassLoader implements Opcodes {

	public static void main(String[] args) {
		new TestDump().compile();
	}

	public void compile() {
		try {
			byte[] b = ASMSampleDump.dump();

			Class<?> expClass = this.defineClass("nebula.lang.ASMSample", b, 0, b.length);
			// instantiates this compiled expression class...
			@SuppressWarnings("unchecked")
			EntityExpression<Integer> expr = (EntityExpression<Integer>) expClass.newInstance();
			
			System.out.println(expr.eval(null));
		} catch (ClassFormatError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

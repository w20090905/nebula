package test.asm.basic;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifierClassVisitor;

public class ASMF {
	public static void main(String[] args) {
		try {
			ClassReader cr = new ClassReader(Sample.class.getName());

			ASMifierClassVisitor v = new ASMifierClassVisitor(new PrintWriter(System.out));
			cr.accept(v, ClassReader.SKIP_DEBUG);// | ClassReader.SKIP_FRAMES);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

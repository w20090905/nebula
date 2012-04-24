package test.asm.aop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.ASMifierClassVisitor;
import org.objectweb.asm.util.ASMifierFieldVisitor;

import test.asm.basic.ASMF;

public class AOPSubClass {

	public static void main(String[] args) {
		try {
			ClassReader cr = new ClassReader("test.asm.aop.Account");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
			cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
			byte[] data = cw.toByteArray();
			File file = new File("Account.class");
			FileOutputStream fout = new FileOutputStream(file);
			fout.write(data);
			fout.close();

			cr = new ClassReader(data);
			ASMifierClassVisitor cv = new ASMifierClassVisitor(new PrintWriter(System.out));
			cr.accept(cv, ClassReader.SKIP_DEBUG);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
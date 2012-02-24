package test.asm.aop;

import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class SecureAccountGenerator {

	private AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();

	private Class secureAccountClass;

	public Account generateSecureAccount() throws ClassFormatError, InstantiationException, IllegalAccessException,
			IOException {
		if (null == secureAccountClass) {
			ClassReader cr = new ClassReader("Account");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
			cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
			byte[] data = cw.toByteArray();
			secureAccountClass = classLoader.defineClassFromClassFile("Account$EnhancedByASM", data);
		}
		return (Account) secureAccountClass.newInstance();
	}

	private class AccountGeneratorClassLoader extends ClassLoader {
		public Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
			return defineClass("Account$EnhancedByASM", classFile, 0, classFile.length);
		}
	}
}
package nebula.data2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BrokerBuilder extends ClassLoader {
	Log log = LogFactory.getLog(getClass());
	
	public BrokerBuilder() {
	}

	public <T> T builder(Class<?> target){
		try {
			ClassReader cr = new ClassReader(target.getName());
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

			BrokerInterfaceVisitor bw = new BrokerInterfaceVisitor(Opcodes.ASM4, cw);
			cr.accept(bw, ClassReader.SKIP_CODE);
			byte[] code = cw.toByteArray();

			if (log.isDebugEnabled()) {
				String filename = "tmp/" + bw.name + ".class";
				String path = filename.substring(0, filename.lastIndexOf('/'));
				File file = new File(path);
				if (!file.exists()) {
					file.mkdir();
				}
				String[] p = filename.split("/");
				new FileOutputStream(p[p.length-1]).write(code);
			}

			Class<?> ac = this.defineClass(bw.name.replace('/', '.'), code, 0, code.length);
			@SuppressWarnings("unchecked")
			Broker<T> ba = (Broker<T>) ac.newInstance();

			// ba.actualValue = new A() {
			//
			// @Override
			// public String get(String name) {
			// return name + "____";
			// }
			// };
			//
			// A a = (A) ba;
			// System.out.println(a.get("Stest"));

			return ba.get();
		} catch (FileNotFoundException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (ClassFormatError e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			log.error(e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}

package nebula.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import nebula.data.impl.BrokerInterfaceVisitor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.Maps;

public class BrokerBuilder extends ClassLoader {
	Log log = LogFactory.getLog(getClass());

	private final Map<Class<?>, Class<?>> knownBrokeres;

	public BrokerBuilder() {
		knownBrokeres = Maps.newConcurrentMap();
	}

	public <T> T builder(Class<?> target) {

		try {
			Class<?> broker = knownBrokeres.get(target);

			if (broker != null) {
				@SuppressWarnings("unchecked")
				BrokerHandler<T> ba = (BrokerHandler<T>) broker.newInstance();
				return ba.get();
			}

			String typeName = target.getName() + "BrokerAuto";
			String innerTypeName = typeName.replace('.', '/');

			ClassReader cr = new ClassReader(target.getName());
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

			BrokerInterfaceVisitor bw = new BrokerInterfaceVisitor(Opcodes.ASM4, cw, innerTypeName);
			cr.accept(bw, ClassReader.SKIP_CODE);
			byte[] code = cw.toByteArray();

			if (log.isDebugEnabled()) {
				String filename = "tmp/" + typeName + ".class";
				String path = filename.substring(0, filename.lastIndexOf('/'));
				File file = new File(path);
				if (!file.exists()) {
					file.mkdir();
				}
				new FileOutputStream(filename).write(code);
			}

			broker = this.defineClass(typeName, code, 0, code.length);
			this.knownBrokeres.put(target, broker);

			@SuppressWarnings("unchecked")
			BrokerHandler<T> ba = (BrokerHandler<T>) broker.newInstance();
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

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(name);
	}
}

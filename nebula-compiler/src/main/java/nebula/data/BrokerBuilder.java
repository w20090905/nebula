package nebula.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import nebula.data.impl.BrokerInstanceBuilder;
import nebula.data.impl.BrokerInstanceBuilderMaker;
import nebula.data.impl.BrokerInterfaceVisitor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.Maps;

public class BrokerBuilder extends ClassLoader {
	Log log = LogFactory.getLog(getClass());

	private final Map<String, BrokerInstanceBuilder> knownBrokeres;
	final BrokerInstanceBuilderMaker instanceBuilder;

	public BrokerBuilder() {
		knownBrokeres = Maps.newConcurrentMap();
		instanceBuilder = new BrokerInstanceBuilderMaker();
	}

	public <T> T builder(Class<?> target) {

		try {
			BrokerInstanceBuilder builder = knownBrokeres.get(target.getName());

			if (builder != null) {
				Broker<T> broker = builder.build();
				return broker.get();
			}

			String typeName = target.getName() + "BrokerAuto";
			String innerTypeName = typeName.replace('.', '/');

			ClassReader cr = new ClassReader(target.getName());
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

			BrokerInterfaceVisitor bw = new BrokerInterfaceVisitor(Opcodes.ASM4, cw, innerTypeName);
			cr.accept(bw, ClassReader.SKIP_CODE);
			byte[] code = cw.toByteArray();

			if (log.isDebugEnabled()) {
				try {
					String filename = "tmp/" + typeName + ".class";
					String path = filename.substring(0, filename.lastIndexOf('/'));
					File file = new File(path);
					if (!file.exists()) {
						file.mkdir();
					}
					new FileOutputStream(filename).write(code);
				} catch (FileNotFoundException e) {
					log.error(e);
					throw new RuntimeException(e);
				}
			}

			Class<?> clzBroker = this.defineClass(typeName, code, 0, code.length);
			this.resolveClass(clzBroker);

			byte[] codeBuilder = instanceBuilder.dump(typeName);
			String builderTypeName = typeName + "Builder";

			Class<?> clzBuilder = this.defineClass(builderTypeName, codeBuilder, 0, codeBuilder.length);

			builder = (BrokerInstanceBuilder) clzBuilder.newInstance();

			this.knownBrokeres.put(target.getName(), builder);

			Broker<T> broker = builder.build();
			return broker.get();

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

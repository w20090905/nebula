package nebula.data.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import nebula.data.Broker;
import nebula.lang.NebulaClassLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.ImmutableMap;

public class BrokerBuilder {
	Log log = LogFactory.getLog(getClass());

	Map<String, BrokerInstanceBuilder> knownBrokeres;
	final BrokerInstanceBuilderClassMaker instanceBuilder;

	ReentrantLock lock = new ReentrantLock();

	public BrokerBuilder() {
		knownBrokeres = ImmutableMap.of();
		instanceBuilder = new BrokerInstanceBuilderClassMaker();
	}

	public void clear(){
		knownBrokeres = ImmutableMap.of();
	}
	static int count = 0;
	public <T> T builder(Class<?> target) {

		BrokerInstanceBuilder builder = knownBrokeres.get(target.getName());

		if (builder != null) {
			Broker<T> broker = builder.build();
			return broker.get();
		}

		lock.lock();
		try {

			builder = knownBrokeres.get(target.getName());
			if (builder != null) {
				Broker<T> broker = builder.build();
				return broker.get();
			}

			count++;
			String typeName = target.getName() + "BrokerAuto" + count;

			// 构建代理类
			{
				String innerTypeName = typeName.replace('.', '/');

				ClassReader cr = new ClassReader(target.getName());
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

				BrokerInterfaceClassVisitor bw = new BrokerInterfaceClassVisitor(Opcodes.ASM4, cw, innerTypeName);
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

				Class<?> clzBroker = NebulaClassLoader.defineClass(typeName, code);
				NebulaClassLoader.doResolveClass(clzBroker);
			}

			// 构建代理构建类，主要是为了性能，避免每次都是用Class.newInstance() 来构建代理
			{
				String builderTypeName = BrokerInstanceBuilder.class.getName() + "_" + typeName.replace('.', '_');
				byte[] codeBuilder = instanceBuilder.dump(typeName);

				if (log.isDebugEnabled()) {
					try {
						String filename = "tmp/" + builderTypeName + ".class";
						String path = filename.substring(0, filename.lastIndexOf('/'));
						File file = new File(path);
						if (!file.exists()) {
							file.mkdir();
						}
						new FileOutputStream(filename).write(codeBuilder);
					} catch (FileNotFoundException e) {
						log.error(e);
						throw new RuntimeException(e);
					}
				}

				Class<?> clzBuilder = NebulaClassLoader.defineClass(builderTypeName, codeBuilder);

				builder = (BrokerInstanceBuilder) clzBuilder.newInstance();
			}

			ImmutableMap.Builder<String, BrokerInstanceBuilder> mapBuilder = ImmutableMap.builder();

			this.knownBrokeres = mapBuilder.putAll(knownBrokeres).put(target.getName(), builder).build();
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
		} finally {
			lock.unlock();
		}
	}
}

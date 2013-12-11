package nebula.data.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import nebula.data.BrokerHandler;
import nebula.data.sample.AA;
import nebula.lang.NebulaClassLoader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BrokerClassClassVisitorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoad() throws IOException, InstantiationException, IllegalAccessException {
		Class<?> target = AA.class;

		String typeName = "nebula.data.AAAaaA";// target.getName() +
												// "_BrokerAuto";

		// 构建代理类
		String innerTypeName = typeName.replace('.', '/');

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

		ClassReader cr = new ClassReader(target.getName());
		BrokerForClassClassVisitor bw = new BrokerForClassClassVisitor(Opcodes.ASM4, cw, innerTypeName);
		cr.accept(bw, ClassReader.SKIP_CODE);

		byte[] code = cw.toByteArray();

		try {
			String filename = "tmp/" + typeName + ".class";
			String path = filename.substring(0, filename.lastIndexOf('/'));
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			new FileOutputStream(filename).write(code);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Class<?> clzBroker = NebulaClassLoader.defineClass(typeName, code);
		NebulaClassLoader.doResolveClass(clzBroker);
		@SuppressWarnings("unchecked")
		BrokerHandler<AA> aaBroker = (BrokerHandler<AA>) clzBroker.newInstance();
		AA aaDync = aaBroker.get();


//		assertNull(aaDync.getName());

		AA aa = new AA();
		aa.setName("oldvalue");
		aaBroker.setNewValue(aa);

		assertEquals("oldvalue", aaDync.getName());
		
		aa = new AA();
		aa.setName("oldvaluexxx");
		aaBroker.setNewValue(aa);
		
		assertEquals("oldvaluexxx", aaDync.getName());
	}

}

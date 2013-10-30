package nebula.data.impl;

import nebula.data.Broker;
import nebula.data.BrokerTestInputInterface;
import nebula.data.impl.BrokerBuilder;
import junit.framework.TestCase;

public class BrokerBuilderTest extends TestCase {
	BrokerBuilder builder;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testBrokerBuilder() {
		builder = new BrokerBuilder();
	}

	public final void testBuilder() {
		builder = new BrokerBuilder();
		BrokerTestInputInterface a = builder.builder(BrokerTestInputInterface.class);

		Broker.update(a, new BrokerTestInputInterface() {
			@Override
			public String get(String name) {
				return name + "____";
			}

			@Override
			public String get(String name, String name2) {
				return name + name2;
			}
		});

		assertEquals("test____", a.get("test"));
		assertEquals("testname", a.get("test", "name"));
	}

	public final void testBuilder_Performance() throws InstantiationException, IllegalAccessException {
		builder = new BrokerBuilder();
		BrokerTestInputInterface a = builder.builder(BrokerTestInputInterface.class);

		Broker.update(a, new BrokerTestInputInterface() {
			@Override
			public String get(String name) {
				return name + "____";
			}

			@Override
			public String get(String name, String name2) {
				return name + name2;
			}
		});

		assertEquals("test____", a.get("test"));
		assertEquals("testname", a.get("test", "name"));

		int MAX;
		long start, end, nanoAll, nanoEvery;

		MAX = 1000 * 1000 * 1;

		{
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				a = new BrokerTestInputInterfaceBrokerAuto();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.println("[   new             ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}
		{

			a = builder.builder(BrokerTestInputInterface.class);
			BrokerInstanceBuilder bb= builder.knownBrokeres.get(BrokerTestInputInterface.class.getName());
			
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				a = bb.build();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.println("[   builder direct  ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}

		{

			a = builder.builder(BrokerTestInputInterface.class);
			BrokerInstanceBuilder bb= builder.knownBrokeres.get(BrokerTestInputInterface.class.getName());

			Class<?> clz = BrokerTestInputInterface.class;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				bb= builder.knownBrokeres.get(clz.getName());
				a = bb.build();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.println("[   builder map     ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}

		{
			start = System.nanoTime();
			Class<?> clz = BrokerTestInputInterface.class;
			
			for (int i = 0; i < MAX; i++) {
				a = builder.builder(clz);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.println("[   builder         ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}
		{
			Class<?> clz = a.getClass();
			start = System.nanoTime();
			int max = MAX / 10;
			for (int i = 0; i < max; i++) {
				a = (BrokerTestInputInterface) clz.newInstance();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / max;

			System.out.println("[   newinstance     ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}
	}
}

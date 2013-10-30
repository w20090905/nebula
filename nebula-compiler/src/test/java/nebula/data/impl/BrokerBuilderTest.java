package nebula.data.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nebula.data.Broker;
import nebula.data.BrokerTestInputInterface;
import nebula.data.impl.BrokerBuilder;
import junit.framework.TestCase;

public class BrokerBuilderTest extends TestCase {
	Log log = LogFactory.getLog(getClass());
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

		MAX = 1000 * 1000 * 1;

		long rawNanoEvery;

		{
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				a = new BrokerTestInputInterfaceBrokerAuto();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;
			rawNanoEvery = nanoEvery;

			log.debug("[   new             ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
		}
		// {
		// long start, end, nanoAll, nanoEvery;
		//
		// a = builder.builder(BrokerTestInputInterface.class);
		// BrokerInstanceBuilder bb=
		// builder.knownBrokeres.get(BrokerTestInputInterface.class.getName());
		//
		// start = System.nanoTime();
		// for (int i = 0; i < MAX; i++) {
		// a = bb.build();
		// }
		// end = System.nanoTime();
		// nanoAll = end - start;
		// nanoEvery = nanoAll / MAX;
		//
		// log.debug("[   builder direct  ]\tAll :" + (nanoAll / (1000
		// * 1000)) + "s  every : " + nanoEvery + " nano");
		// }

		// {
		// long start, end, nanoAll, nanoEvery;
		//
		// a = builder.builder(BrokerTestInputInterface.class);
		// BrokerInstanceBuilder bb=
		// builder.knownBrokeres.get(BrokerTestInputInterface.class.getName());
		//
		// Class<?> clz = BrokerTestInputInterface.class;
		// start = System.nanoTime();
		// for (int i = 0; i < MAX; i++) {
		// bb= builder.knownBrokeres.get(clz.getName());
		// a = bb.build();
		// }
		// end = System.nanoTime();
		// nanoAll = end - start;
		// nanoEvery = nanoAll / MAX;
		//
		// log.debug("[   builder map     ]\tAll :" + (nanoAll / (1000
		// * 1000)) + "s  every : " + nanoEvery + " nano");
		// }

		{
			long start, end, nanoAll, nanoEvery;
			start = System.nanoTime();
			Class<?> clz = BrokerTestInputInterface.class;

			for (int i = 0; i < MAX; i++) {
				a = builder.builder(clz);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			log.debug("[   builder         ]\tAll :" + (nanoAll / (1000 * 1000)) + "s  every : " + nanoEvery + " nano");
			assertTrue((nanoEvery / rawNanoEvery) < 5);
		}
		// {
		// long start, end, nanoAll, nanoEvery;
		// Class<?> clz = a.getClass();
		// start = System.nanoTime();
		// int max = MAX / 10;
		// for (int i = 0; i < max; i++) {
		// a = (BrokerTestInputInterface) clz.newInstance();
		// }
		// end = System.nanoTime();
		// nanoAll = end - start;
		// nanoEvery = nanoAll / max;
		//
		// log.debug("[   newinstance     ]\tAll :" + (nanoAll / (1000
		// * 1000)) + "s  every : " + nanoEvery + " nano");
		// }
	}
}

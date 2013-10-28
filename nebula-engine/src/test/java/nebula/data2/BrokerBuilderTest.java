package nebula.data2;

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

		@SuppressWarnings("unchecked")
		Broker<BrokerTestInputInterface> ba = (Broker<BrokerTestInputInterface>) a;
		ba.actualValue = new BrokerTestInputInterface() {
			@Override
			public String get(String name) {
				return name + "____";
			}

			@Override
			public String get(String name, String name2) {
				return name + name2;
			}
		};

		assertEquals("test____", a.get("test"));
		assertEquals("testname", a.get("test", "name"));
	}

}

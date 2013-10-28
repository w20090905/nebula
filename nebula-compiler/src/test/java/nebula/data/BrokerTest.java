package nebula.data;

import junit.framework.TestCase;

public class BrokerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	class TT implements BrokerTestInputInterface {
		String prefix;

		public TT(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public String get(String name) {
			return this.prefix + name;
		}

		@Override
		public String get(String name, String name2) {
			return prefix +  name + name2;
		}
	}

	class Greeting implements BrokerTestResultInterface {
		BrokerTestInputInterface a;

		public Greeting(BrokerTestInputInterface a) {
			this.a = a;
		}

		@Override
		public String sayHello(String name) {
			return a.get(name);
		}
	}

	public final void testWatch() {
		TT tt = new TT("Hello ");
		BrokerHandler<BrokerTestInputInterface> ba = Broker.broke(BrokerTestInputInterface.class, tt);

		BrokerTestResultInterface ge = Broker.watch(ba.get(), new DataAdapter<BrokerTestInputInterface, BrokerTestResultInterface>() {
			@Override
			public BrokerTestResultInterface watch(BrokerTestInputInterface newData, BrokerTestInputInterface oldData) {
				return new Greeting(newData);
			}
		});

		assertEquals("Hello wangshilian", ge.sayHello("wangshilian"));
		tt = new TT("Goodbye ");
		ba.setNewValue(tt);
		assertEquals("Goodbye wangshilian", ge.sayHello("wangshilian"));
	}
}

package nebula.data2;

import junit.framework.TestCase;

public class BrokerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	class TT implements BrokerInputInterface {
		String prefix;

		public TT(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public String get(String name) {
			return this.prefix + name;
		}
	}

	class Greeting implements BrokerTestResultInterface {
		BrokerInputInterface a;

		public Greeting(BrokerInputInterface a) {
			this.a = a;
		}

		@Override
		public String sayHello(String name) {
			return a.get(name);
		}
	}

	public final void testWatch() {
		TT tt = new TT("Hello ");
		BrokerHandler<BrokerInputInterface> ba = Broker.broke(BrokerInputInterface.class, tt);

		BrokerTestResultInterface ge = Broker.watch(ba.get(), new Watcher<BrokerInputInterface, BrokerTestResultInterface>() {
			@Override
			public BrokerTestResultInterface watch(BrokerInputInterface newData, BrokerInputInterface oldData) {
				return new Greeting(newData);
			}
		});

		assertEquals("Hello wangshilian", ge.sayHello("wangshilian"));
		tt = new TT("Goodbye ");
		ba.setNewValue(tt);
		assertEquals("Goodbye wangshilian", ge.sayHello("wangshilian"));
	}
}

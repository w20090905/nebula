package nebula.data;

import nebula.data.sample.AA;
import nebula.data.sample.BrokerIII;
import nebula.data.sample.BrokerResultIII;
import junit.framework.TestCase;

public class BrokerTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	class TT implements BrokerIII {
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

	class Greeting implements BrokerResultIII {
		BrokerIII a;

		public Greeting(BrokerIII a) {
			this.a = a;
		}

		@Override
		public String sayHello(String name) {
			return a.get(name);
		}
	}

	public final void testInterface_Watch() {
		TT tt = new TT("Hello ");
		BrokerHandler<BrokerIII> ba = Broker.broke(BrokerIII.class, tt);

		BrokerResultIII ge = Broker.watch(ba.get(), new DataAdapter<BrokerIII, BrokerResultIII>() {
			@Override
			public BrokerResultIII watch(BrokerIII newData, BrokerIII oldData) {
				return new Greeting(newData);
			}
		});

		assertEquals("Hello wangshilian", ge.sayHello("wangshilian"));
		tt = new TT("Goodbye ");
		ba.setNewValue(tt);
		assertEquals("Goodbye wangshilian", ge.sayHello("wangshilian"));
	}
	
	public final void testBrokerClass() {
		AA aa = new AA();
		aa.setName("oldvalue");
		BrokerHandler<AA> aaBroker = Broker.broke(AA.class, aa);

		AA aaDync = aaBroker.get();
		aaBroker.setNewValue(aa);

		assertEquals("oldvalue", aaDync.getName());
		
		aa = new AA();
		aa.setName("oldvaluexxx");
		aaBroker.setNewValue(aa);
		
		assertEquals("oldvaluexxx", aaDync.getName());
		
	}
}

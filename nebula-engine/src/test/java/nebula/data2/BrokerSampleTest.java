package nebula.data2;

import junit.framework.TestCase;

public class BrokerSampleTest extends TestCase {

	public void testGet() {
		Broker<BrokerTestInputInterface> ba = new BrokerSample();
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

		BrokerTestInputInterface a = (BrokerTestInputInterface) ba;
		System.out.println(a.get("Stest"));
	}
}

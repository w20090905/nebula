package nebula.data2;

public class BrokerSample extends Broker<BrokerTestInputInterface> implements BrokerTestInputInterface {
	@Override
	public BrokerTestInputInterface get() {
		return this;
	}
	
	@Override
	public String get(String name) {
		return actualValue.get(name);
	}

	@Override
	public String get(String name, String name2) {
		return actualValue.get(name, name2);
	}

}

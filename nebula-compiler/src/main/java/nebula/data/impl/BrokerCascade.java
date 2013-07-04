package nebula.data.impl;

import nebula.data.DataWatcher;
import nebula.data.impl.Brokers.BrokerImp;

public abstract class BrokerCascade<T,P> extends BrokerImp<T> implements BrokerEx<T>,DataWatcher<P> {
	public BrokerCascade(T initData) {
		super(initData);
	}
}

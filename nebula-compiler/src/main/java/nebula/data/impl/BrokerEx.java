package nebula.data.impl;

import nebula.data.Broker;

interface BrokerEx<T> extends Broker<T> {
	public void put(T newData) ;
}

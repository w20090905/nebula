package nebula.data.impl;

import nebula.data.Broker;
import nebula.data.sample.BrokerIII;

public class BrokerTestInputInterfaceBrokerAuto extends Broker<BrokerIII>
  implements BrokerIII
{
  public BrokerIII get()
  {
    return this;
  }

  public String get(String paramString)
  {
    return ((BrokerIII)this.actualValue).get(paramString);
  }

  public String get(String paramString1, String paramString2)
  {
    return ((BrokerIII)this.actualValue).get(paramString1, paramString2);
  }
}
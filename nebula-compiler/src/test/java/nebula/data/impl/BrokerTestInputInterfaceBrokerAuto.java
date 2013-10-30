package nebula.data.impl;

import nebula.data.Broker;
import nebula.data.BrokerTestInputInterface;

public class BrokerTestInputInterfaceBrokerAuto extends Broker<BrokerTestInputInterface>
  implements BrokerTestInputInterface
{
  public BrokerTestInputInterface get()
  {
    return this;
  }

  public String get(String paramString)
  {
    return ((BrokerTestInputInterface)this.actualValue).get(paramString);
  }

  public String get(String paramString1, String paramString2)
  {
    return ((BrokerTestInputInterface)this.actualValue).get(paramString1, paramString2);
  }
}
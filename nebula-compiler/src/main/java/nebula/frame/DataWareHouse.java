package nebula.frame;

import nebula.SmartList;

public class DataWareHouse extends SmartListImp<SmartList<?>> {
	public DataWareHouse() {
		super("DataWareHouse", new Identifiable<SmartList<?>>() {
			@Override
			public String getId(SmartList<?> data) {
				return ((SmartListImp<?>)data).getName();
			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546114070997903477L;

}

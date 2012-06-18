package nebula.frame;

import java.util.Map;

import javax.inject.Inject;

import nebula.SmartList;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class DataWareHouse extends SmartListImp<SmartList<?>> {

	final TypeLoader typeLoader;

	@Inject
	public DataWareHouse(TypeLoader typeLoader) {
		super("DataWareHouse", new Identifiable<SmartList<?>>() {
			@Override
			public String getId(SmartList<?> data) {
				return ((SmartListImp<?>) data).getName();
			}
		});
		this.typeLoader = typeLoader;
		this.add(typeLoader.getList());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public SmartList<?> get(String key) {
		SmartList<?> list = super.get(key);
		if (list != null) {
			return super.get(key);
		}

		Type type = this.typeLoader.findType(key);
		if (type != null) {
			this.add(new SmartListImp<>(key, new Identifiable<Map>() {

				@Override
				public String getId(Map data) {
					return (String) data.get("id");
				}
			}));
		}

		// TODO Auto-generated method stub
		return super.get(key);
	}

	private static final long serialVersionUID = -4546114070997903477L;

}

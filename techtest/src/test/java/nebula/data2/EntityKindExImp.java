package nebula.data2;

public class EntityKindExImp extends AbstractKindExImp<Entity> {

	public EntityKindExImp(DatatstoreEx datastore) {
		super(datastore);
	}

	@Override
	public Entity getEditableEntity(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getEditableEntity(String key) {
		return datas.get(key);
	}

	@Override
	public Entity apply(Entity newV) {
		datas.put(newV.getKey(), newV);
		return newV;
	}
	//
	// @Override
	// public Entity applyAll() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void exec(nebula.data2.Kind.Callback<Entity> runner) {
	// runner.exec(this);
	// }
}

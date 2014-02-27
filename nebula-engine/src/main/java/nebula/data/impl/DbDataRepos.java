package nebula.data.impl;

import javax.inject.Inject;

import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.data.db.DbDataExecutor;
import nebula.lang.Type;

public class DbDataRepos extends DefaultDataRepos implements DataReposEx {

	final DbConfiguration dbConfig;

	@Inject
	public DbDataRepos(TypeDatastore typeKind, DbConfiguration dbConfig) {
		super(typeKind);
		this.dbConfig = dbConfig;
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected DataStore loadDataStore(String name, Type type) {
		switch (type.getStandalone()) {
		case Flow:
			return new EntityDataStore(IdMakerBuilder.getIDReader(type), this, type);
		case Transaction:
		case Relation:
			return new DbTransactionEntityDataStore(this, type, (DbDataExecutor<Entity>) dbConfig.getPersister(type,Entity.class));
		case Master:
		default:
			return new DbMasterEntityDataStore(this, type, dbConfig.getPersister(type,Entity.class));
		}
	}
}

package nebula.data.db;

public interface DbTxDataExecutor extends DbDataExecutor {
	long getCurrentMaxID();
}

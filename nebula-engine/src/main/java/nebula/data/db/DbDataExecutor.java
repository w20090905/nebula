package nebula.data.db;

import java.util.List;

public interface DbDataExecutor<T> {
	DbSqlHelper getHelper();
	
	T get(Object... keys);

	void insert(T value);

	void update(T value, Object... keys);

	void deleteAll();

	List<T> getAll();

	void delete(T value);

	void drop();

	void close();
	
	long getCurrentMaxID();

}

package nebula.data.db;

import java.util.List;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

public interface DbDataExecutor {

	EditableEntity get(Object... keys);

	void insert(Entity value);

	void update(Entity value, Object... keys);

	void deleteAll();

	List<EditableEntity> getAll();

	void delete(Entity value);

	void drop();

	void close();
	
	long getCurrentMaxID();

}

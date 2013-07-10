package nebula.lang;

import nebula.data.DataRepos;
import nebula.data.Entity;


public interface Statement extends Code {
	void exec(Entity entity, DataRepos repos);
}

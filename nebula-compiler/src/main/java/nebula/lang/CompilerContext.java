package nebula.lang;

import java.util.ArrayList;
import java.util.List;

public abstract class CompilerContext {
	public abstract Type resolveType(String name);
	boolean isTopLevelGet = true;
	List<Field> refFields = new ArrayList<Field>();
	Expr<?> from;
	Field field;
	boolean referRepos=false;
}

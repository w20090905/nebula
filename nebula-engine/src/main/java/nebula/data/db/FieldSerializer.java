package nebula.data.db;

import nebula.data.Entity;

public interface FieldSerializer<T extends Object, I, O> {
	T input(I in,int pos, Entity parent, T now) throws Exception;

	T inputWithoutCheck(I in,int pos, Entity parent) throws Exception;

	int output(O out, T value,int pos) throws Exception;
}

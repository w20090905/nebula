package nebula.data.json;

import nebula.data.Entity;

public interface FieldSerializer<T extends Object, I, O> {
	void input(I in, Entity parent, T now) throws Exception;

	void inputWithoutCheck(I in, Entity parent) throws Exception;

	void output(O out, T value) throws Exception;
}

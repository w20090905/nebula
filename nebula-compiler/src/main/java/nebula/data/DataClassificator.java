package nebula.data;

import java.util.List;

public interface DataClassificator<E> {
	void add(E v);

	void update(E oldData,E newData);

	void remove(E v);

	String[] getClassifications();

	List<E> getData(String classification);
}

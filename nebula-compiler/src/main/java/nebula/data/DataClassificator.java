package nebula.data;

import java.util.List;

import com.google.common.collect.Multiset;

public interface DataClassificator<E> {
	void add(E v);

	void update(E oldData,E newData);

	void remove(E v);

	Multiset<String> getClassifications();

	List<E> getData(String classification);
}

package nebula.data.impl;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class DataClassificator<K> {

	final ListMultimap<String, K> datas = ArrayListMultimap.create();
	final Function<K, String> indexFunction;

	DataClassificator(Function<K, String> indexFunction) {
		this.indexFunction = indexFunction;
	}

	public void add(K v) {
		String classification = indexFunction.apply(v);
		if (classification != null) this.datas.get(classification).add(v);
	}

	public void update(K oldData, K newData) {
		if (Objects.equal(oldData, newData)) {
			return;
		}
		String oldClassification = indexFunction.apply(oldData);
		String newClassification = indexFunction.apply(newData);

		if (oldClassification != null) this.datas.get(oldClassification).remove(oldData);
		if (newClassification != null) this.datas.get(newClassification).add(newData);
	}

	public void remove(K v) {
		String classification = indexFunction.apply(v);
		if (classification != null) this.datas.get(classification).remove(v);
	}

	public Set<String> getClassifications() {
		return this.datas.keySet();
	}

	public List<K> getData(String classification) {
		return this.datas.get(classification);
	}
}

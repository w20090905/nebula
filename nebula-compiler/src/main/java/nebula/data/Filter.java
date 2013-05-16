package nebula.data;

import java.util.List;

public interface Filter<V extends Timable> extends DataListener<V> {
	List<V> get();
}

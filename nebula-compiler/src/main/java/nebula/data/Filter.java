package nebula.data;

import java.util.List;

public interface Filter<V extends Timable> {
	List<V> get();
}

package nebula.data;

public interface Callback<V extends HasID> {
	void exec(Persistence<V> p);
}

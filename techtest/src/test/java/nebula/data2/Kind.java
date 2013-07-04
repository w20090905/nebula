package nebula.data2;

public interface Kind<V>{
	V get(long id);
	V get(String key);
	V getEditableEntity	(long id);
	V getEditableEntity	(String key);
	
	V put(V data);
	
//	interface Callback<V>{
//		void exec(Kind<V> kind);
//	}
//	
//	void exec(Callback<V> runner);
}

package http.startup;

public interface Configurable<T> {
	void configure(T site);
}

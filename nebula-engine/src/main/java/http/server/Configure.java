package http.server;

public interface Configure<T> {
	void configure(T site);
}

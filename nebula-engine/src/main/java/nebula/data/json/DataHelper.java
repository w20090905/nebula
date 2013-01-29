package nebula.data.json;


public interface DataHelper<T,I,O> {
	T readFrom(T d, I in);
	void stringifyTo(T d, O o);
}
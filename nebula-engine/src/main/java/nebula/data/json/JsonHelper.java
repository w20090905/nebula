package nebula.data.json;

import java.io.Reader;
import java.io.Writer;

public interface JsonHelper<T> {
	T readFrom(T d, Reader in);
	void stringifyTo(T d, Writer o);
}
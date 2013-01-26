package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

import nebula.data.TypeAdapter;

public interface JsonTypeAdapter<T> extends TypeAdapter<T,JsonParser,JsonGenerator> {

}

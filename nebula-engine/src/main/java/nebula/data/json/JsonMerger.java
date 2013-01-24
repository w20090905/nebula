package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public interface JsonMerger<T extends Object> extends Merger<T,JsonParser,JsonGenerator> {

}

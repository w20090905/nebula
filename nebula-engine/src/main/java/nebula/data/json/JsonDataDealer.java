package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

import nebula.data.DataDealer;

public interface JsonDataDealer<T> extends DataDealer<T,JsonParser,JsonGenerator> {

}

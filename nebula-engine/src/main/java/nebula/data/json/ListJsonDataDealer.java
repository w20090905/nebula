package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

class ListJsonDataDealer<T> extends DefaultJsonDataDealer<List<T>> {
	final JsonDataDealer<T> jsonFieldDealer;

	public ListJsonDataDealer(JsonDataDealer<T> jsonFieldDealer) {
		this.jsonFieldDealer = jsonFieldDealer;
	}

	public List<T> readFrom(JsonParser parser, String name) throws Exception {
		List<T> vList = new ArrayList<>();
		JsonToken token;
		token = parser.getCurrentToken();
		assert token == JsonToken.START_ARRAY;
		
		while ((token=parser.nextToken()) != JsonToken.END_ARRAY) {
			vList.add(jsonFieldDealer.readFrom(parser, name));
		}
		assert token == JsonToken.END_ARRAY;
		return vList;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {

		gen.writeStartArray();

		@SuppressWarnings("unchecked")
		List<T> entityList = (List<T>) value;
		for (T entity : entityList) {
			jsonFieldDealer.writeTo(name, entity, gen);
		}

		gen.writeEndArray();
	}
}

package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

class ListBasicJsonDataDealer<T> extends DefaultJsonFieldDealer<List<T>> {
	final DefaultJsonFieldDealer<T> jsonFieldDealer;

	public ListBasicJsonDataDealer(JsonFactory factory, DefaultJsonFieldDealer<T> jsonFieldDealer) {
		this.jsonFieldDealer = jsonFieldDealer;
	}

	public List<T> readFrom(JsonParser parser, String name) throws Exception {
		List<T> vList = new ArrayList<>();
		while (parser.getParsingContext().inArray()) {
			T v = jsonFieldDealer.readFrom(parser, name);
			vList.add(v);
		}
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

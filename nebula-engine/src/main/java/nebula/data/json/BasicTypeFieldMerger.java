package nebula.data.json;

import nebula.data.Entity;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

class BasicTypeFieldMerger extends JsonFieldMerger<Object> {
	public BasicTypeFieldMerger(String fieldName, String frontName, JsonDataDealer<?> dataDealer) {
		super(fieldName, frontName);
		this.dataDealer = dataDealer;
	}

	public JsonDataDealer<?> dataDealer;

	@Override
	public Object input(JsonParser in, Entity parent, Object now) throws Exception {
		Object newly = dataDealer.readFrom(in, frontName);
		if (newly != null && !newly.equals(now)) {
			parent.put(fieldName, newly);
		}
		return newly;
	}

	@Override
	public Object inputWithoutCheck(JsonParser in, Entity parent) throws Exception {
		Object newly = dataDealer.readFrom(in, frontName);
		parent.put(fieldName, newly);
		return newly;
	}

	@Override
	public void output(JsonGenerator out, Object value) throws Exception {
		dataDealer.writeTo(frontName, value, out);
	}

}

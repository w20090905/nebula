package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class BasicTypeListFieldSerializer extends DefaultFieldSerializer<List<Object>> {
	public BasicTypeListFieldSerializer(String fieldName, String frontName, DefaultTypeAdapter<Object> typeAdapter) {
		super(fieldName, frontName);
		this.typeAdapter = typeAdapter;
	}

	final DefaultTypeAdapter<Object> typeAdapter;

	@Override
	public void input(JsonParser in, Entity parent, List<Object> currentList) throws Exception {
		if (currentList == null) {
			inputWithoutCheck(in, parent);
		}

		List<Object> newlyList = new ArrayList<Object>();

		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_ARRAY;

		boolean dirty = false;
		int i = 0;
		while ((token = in.nextToken()) != JsonToken.END_ARRAY) {
			Object newly = typeAdapter.readFrom(in, null);

			if (!dirty && currentList.size()>i &&  currentList.get(i).equals(newly)) {
					dirty = true;
			}
			newlyList.add(newly);
		}
		assert token == JsonToken.END_ARRAY;

		if (dirty) {
			parent.put(fieldName, newlyList);
		}

	}

	@Override
	public void inputWithoutCheck(JsonParser in, Entity parent) throws Exception {
		List<Object> vList = new ArrayList<Object>();
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_ARRAY;

		while ((token = in.nextToken()) != JsonToken.END_ARRAY) {
			Object newly = typeAdapter.readFrom(in, null);
			vList.add(newly);
		}
		assert token == JsonToken.END_ARRAY;

		parent.put(fieldName, vList);
	}

	@Override
	public void output(JsonGenerator gen, List<Object> currentList) throws Exception {
		if (currentList != null) {
			gen.writeStartArray();

			for (int i = 0; i < currentList.size(); i++) {
				typeAdapter.writeTo(null, currentList.get(i), gen);
			}

			gen.writeEndArray();
		}

	}
}

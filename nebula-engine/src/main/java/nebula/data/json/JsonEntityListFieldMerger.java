package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class JsonEntityListFieldMerger extends JsonFieldMerger<List<Entity>> {
	public JsonEntityListFieldMerger(String fieldName, String frontName, JsonEntityFieldMerger entityMerger) {
		super(fieldName, frontName);
		this.entityMerger = entityMerger;
	}

	final JsonEntityFieldMerger entityMerger;

	@Override
	public List<Entity> input(JsonParser in, Entity parent, List<Entity> current) throws Exception {
		if(current==null){
			inputWithoutCheck(in, parent);
		}
		
		List<Entity> newlyList = new ArrayList<>();
		
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_ARRAY;

		boolean dirty = false;
		int i=0;
		while ((token = in.nextToken()) != JsonToken.END_ARRAY) {
			Entity newly = entityMerger.doReadInList(in, current);
			long idx = (long)newly.get("_idx");
			if(idx != i ){
				newly.put("_idx", idx);
				dirty = true;
			}
			dirty = newly.isDirty() || dirty;
			newlyList.add(newly);			
		}
		assert token == JsonToken.END_ARRAY;
		
		if(dirty){
			parent.put(fieldName, newlyList);	
			return newlyList;		
		}else{
			return current;
		}
		
	}

	@Override
	public List<Entity> inputWithoutCheck(JsonParser in, Entity parent) throws Exception {
		List<Entity> vList = new ArrayList<>();
		JsonToken token;
		token = in.getCurrentToken();
		assert token == JsonToken.START_ARRAY;

		while ((token = in.nextToken()) != JsonToken.END_ARRAY) {
			Entity newly = entityMerger.doReadWithoutCheck(in, new EditableEntity());
			vList.add(newly);
		}
		assert token == JsonToken.END_ARRAY;
		
		parent.put(fieldName, vList);
		
		return vList;
	}

	@Override
	public void output(JsonGenerator gen, List<Entity> current) throws Exception {
		if (current != null) {
			gen.writeStartArray();

			for (Entity entity : current) {
				entityMerger.output(gen, entity);
			}

			gen.writeEndArray();
		}

	}
}

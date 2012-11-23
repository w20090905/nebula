package nebula.data.json;

import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

class ListEntityJsonDataDealer extends DefaultJsonFieldDealer<List<Entity>> {
	final EntityJsonHelper entityJsonHelper;

	public ListEntityJsonDataDealer(JsonFactory factory, EntityJsonHelper entityJsonHelper) {
		this.entityJsonHelper = entityJsonHelper;
	}

	public List<Entity> readFrom(JsonParser parser, String name) throws Exception {

		List<Entity> entityList = new ArrayList<>();
		while (parser.getParsingContext().inArray()) {
			Entity entry = new EditableEntity();
			entityJsonHelper.read(parser, entry);
			entityList.add(entry);
		}
		return entityList;
	}

	public void writeTo(String name, Object value, JsonGenerator gen) throws Exception {

		gen.writeStartArray();

		@SuppressWarnings("unchecked")
		List<Entity> entityList = (List<Entity>) value;
		for (Entity entity : entityList) {
			entityJsonHelper.write(gen, entity);
		}

		gen.writeEndArray();
	}
}

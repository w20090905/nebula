package nebula.data.json;

import java.io.IOException;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.lang.Flow;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.google.common.base.Preconditions;

public class FlowEntitySerializer implements JsonDataHelper<Entity> {
	JsonDataHelper<Entity> flow;
	JsonDataHelper<Entity> step;

	public FlowEntitySerializer(JsonDataHelper<Entity> flow, JsonDataHelper<Entity> step) {
		this.flow = flow;
		this.step = step;
	}

	@Override
	public Entity readFrom(Entity entity, JsonParser in) {
		try {

			JsonToken token = in.nextToken();
			assert token == JsonToken.START_OBJECT;
			token = in.nextToken();

			Preconditions.checkArgument(token == JsonToken.FIELD_NAME);

			@SuppressWarnings("unused")
			String frontName = in.getCurrentName();// Flow
			in.nextToken();
			flow.readFrom(entity, in);
			in.nextToken();

			Preconditions.checkArgument(token == JsonToken.FIELD_NAME);
			frontName = in.getCurrentName();// Flow
			in.nextToken();
			EditableEntity stepEntity = null;
			stepEntity = entity.get(Flow.Field_CurrrentStepEntity);
			stepEntity = (EditableEntity)step.readFrom(stepEntity, in);
			entity.put(Flow.Field_CurrrentStepEntity, stepEntity);
			in.nextToken();

			assert token == JsonToken.END_OBJECT;

			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void stringifyTo(Entity entity, JsonGenerator out) {
		try {
			out.writeStartObject();

			out.writeFieldName("flow");
			flow.stringifyTo(entity, out);
			out.writeFieldName("step");
			Entity stepEntity = entity.get(Flow.Field_CurrrentStepEntity);
			step.stringifyTo(stepEntity, out);
			
			out.writeEndObject();
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

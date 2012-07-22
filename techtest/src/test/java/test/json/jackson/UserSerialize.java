package test.json.jackson;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;

public class UserSerialize {
	public final void writeMedia(JsonGenerator generator, User media) throws IOException {
		generator.writeStartObject();
		generator.writeObjectFieldStart("name");
		generator.writeStringField("first", "Joe");
		generator.writeStringField("last", "Sixpack");
		generator.writeEndObject(); // for field 'name'
		generator.writeStringField("gender", User.Gender.MALE.toString());
		generator.writeBooleanField("verified", false);
		generator.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
		generator.writeBinary(media.getUserImage());
		generator.writeEndObject();
	}
}

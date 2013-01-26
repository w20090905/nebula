package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

abstract class DefaultFieldSerializer<T extends Object> implements FieldSerializer<T,JsonParser,JsonGenerator> {
	final String fieldName;
	final String frontName;
	
	public DefaultFieldSerializer(String fieldName, String frontName) {
		this.fieldName = fieldName;
		this.frontName = frontName;
	}

}

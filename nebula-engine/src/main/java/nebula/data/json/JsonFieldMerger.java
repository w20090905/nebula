package nebula.data.json;

public abstract class JsonFieldMerger<T extends Object> implements JsonMerger<T> {
	final String fieldName;
	final String frontName;
	
	public JsonFieldMerger(String fieldName, String frontName) {
		this.fieldName = fieldName;
		this.frontName = frontName;
	}

}

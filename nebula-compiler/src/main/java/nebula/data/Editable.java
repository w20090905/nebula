package nebula.data;

public interface Editable {
	boolean isFresh();

	boolean isDirty();
	
	void apply();
}

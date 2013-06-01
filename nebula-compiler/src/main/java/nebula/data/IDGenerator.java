package nebula.data;

public interface IDGenerator {
	void init(Long initValue);
	void setSeed(Long seed);
	Long nextValue(Long seed);
	Long nextValue();
}
package nebula.data.impl;

class NativeIDGenerator implements IDGenerator {
	private long currentMaxValue;

	public NativeIDGenerator() {
		this(0L);
	}

	public NativeIDGenerator(Long value) {
		currentMaxValue = value;
	}

	@Override
	public Long nextValue() {
		return ++this.currentMaxValue;
	}

	@Override
	public void init(Long initValue) {
		this.currentMaxValue = initValue;
	}

	@Override
	public void setSeed(Long seed) {

	}

	@Override
	public Long nextValue(Long seed) {
		return this.currentMaxValue += seed;
	}
}

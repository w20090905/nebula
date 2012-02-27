public class HelloWorld<T> {
	String name;
	int age;
	double height;
	Double weight;

	public static void main(String[] args) {

		new HelloWorld<String>().getTest(0, 0, "Test");
		System.out.println("Hello World!");
	}

	@SuppressWarnings("unchecked")
	public T[] getTest(int a1, int b1, String d) {
		return (T[]) new String[a1];
	}

	int f;
	public void checkAndSetF(int f) {
		if (f >= 0) {
			this.f = f;
		} else {
			throw new IllegalArgumentException();
		}
	}
}

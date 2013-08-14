package test.math;

public class PrimeNumber {
	public static void main(String[] args) {

		long[] done = new long[Integer.MAX_VALUE / 100];
		done[0] = 2;
		done[1] = 3;
		int pos = 1;

		long start = System.nanoTime();

		outter: for (long i = 4; i < 1000 * 1000 * 10; i++) {
			for (int j = 0; j < 2; j++) {
				if (i % done[j] == 0) {
					continue outter;
				}
			}
			for (int j = 2; j < pos; j++) {
				if (i % done[j] == 0) {
					continue outter;
				}

				if (i / done[j] < done[j]) {
					break;
				}
			}

			done[++pos] = i;
			// System.out.println(i);
			// w.write(String.valueOf(i) + ",");
		}

		long end = System.nanoTime();
		System.out.println("S " + pos + "   cost " + (end - start) / 1000 / 1000 + " ms");

	}

}

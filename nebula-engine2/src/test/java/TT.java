import java.util.Random;

public class TT {
	public static void main(String[] args) {

		int F = 1;
		int B = -1;
		int f1, f2, f3, n;
		f1 = 0;
		f2 = 0;

		Random r = new Random(System.currentTimeMillis());

		int cntA = 0;
		int cntB = 0;
		
		int cntLoop =0;
		int sum =0;

		for (int j = 0; j < 100000; j++) {
			 r = new Random(System.currentTimeMillis());
			for (int i = 0; i < 10000; i++) {
				n = r.nextInt(2);
//				System.out.println(n);
				f3 = n >= 1 ? F : B;
				if(f1==F && f2 == F && f3==B){
					cntA++;
					break;
				}else if(f1==B && f2 == F  && f3== F){
					cntB++;
					sum+=i;
					cntLoop++;
					break;
				}
				f1 = f2;
				f2 = f3;
//				System.out.println(f1 + " " + f2 + " " + f3);
//				if (f1 == F && f2 == B && f3 == B) {
//					cntA++;
//					break;
//				} else if (f1 == B && f2 == B && f3 == F) {
//					cntB++;
//					break;
//				}
//				f1 = f2;
//				f2 = f3;
			}
		}
		System.out.println(cntA + "   " + (sum/cntLoop) + " " + cntB);
	}
}

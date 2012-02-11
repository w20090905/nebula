
public class Test {
	public static void main(String[] args) {

		int j=0;
		long start = System.nanoTime();	
		int max = 1000;
		for(int k=0;k<max;k++){
		for(int i =0;i<10000;i++){
			j++;
		}}
		long end = System.nanoTime();
		System.out.println(Test.class.getName() + " : " + (end - start)/max +"   " + j);
	}
}

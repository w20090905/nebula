package test.asm.basic;


public class Sample {
	
	public static void main(String[] args) {
		System.out.println("step in");
		new Sample().name("hello");
		new Sample().saySample2("hello");
	}
	
	public void saySample2(String name){
		System.out.println(name);
	}
	
	public String name(String hello){
		System.out.println(hello);
		return hello;
	}

	public void nop(){
		
	}
	public String nop2(){
		return null;
	}
}

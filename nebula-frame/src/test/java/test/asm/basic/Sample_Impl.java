package test.asm.basic;

public class Sample_Impl extends Sample {

	@Override
	public void saySample2(String name) {
		System.out.println("** - exec &name& at " + System.currentTimeMillis());
		super.saySample2(name);
	}

	@Override
	public String name(String hello) {
		System.out.println("** - exec &name& at " + System.currentTimeMillis());
		return super.name(hello);
	}
	
	public String ddd_(String name,String ll,int dd,Sample o){
		return null;
	}
	
	public String dd(String name,String ll,int dd,Sample o){
		return ddd_(name, ll, dd,o);
	}

}

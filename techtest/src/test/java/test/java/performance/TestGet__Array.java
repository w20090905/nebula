package test.java.performance;

public class TestGet__Array implements Runable {

    long max;
    String name,test;
    String[] pa;

    @Override
    public void setup() throws Exception {
        // Array
        pa = new String[] { "name", "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" , "sex" };
        max = 1000 * 1000 * 10;
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            // 1
            name = pa[0];
            test = pa[1];
            name = pa[0];
            name = pa[1];
            test = pa[0];
            test = pa[1];
            name = pa[0];
            name = pa[1];
            test = pa[0];
            test = pa[1];
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

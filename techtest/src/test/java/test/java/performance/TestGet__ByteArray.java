package test.java.performance;

public class TestGet__ByteArray implements Runable {

    long max;
    byte a,b,c;
    byte[] pa = new byte[]{1,23,23,11,13,54,127,123};

    @Override
    public void setup() throws Exception {
        // Array
        max = 1000 * 1000 * 10;
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            // 1
            a = pa[0];
            b = pa[1];
            c = pa[0];
            a = pa[0];
            b = pa[1];
            c = pa[0];
            a = pa[0];
            b = pa[1];
            c = pa[0];
            a = pa[0];
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

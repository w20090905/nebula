package test.java.performance.getField;

public class TestGet__IntBitOp implements Runable {

    long max;
    int a,b,c;
    int[] pa = new int[]{134234234,23,23,11,13,54,127,123};

    @Override
    public void setup() throws Exception {
        // Array
        max = 1000 * 1000 * 10;
    }

    @Override
    public long run() throws Exception {
        a = 3423424;
        for (int i = 0; i < max; i++) {
            // 1
            c= (byte) ((a >> (8 * 2)) & 0xFF);
            b = (byte) ((a >> (8 * 1)) & 0xFF);
            a = (byte) ((a >> (8 * 0)) & 0xFF);
            c= (byte) ((a >> (8 * 2)) & 0xFF);
            b = (byte) ((a >> (8 * 1)) & 0xFF);
            a = (byte) ((a >> (8 * 0)) & 0xFF);
            c= (byte) ((a >> (8 * 2)) & 0xFF);
            b = (byte) ((a >> (8 * 1)) & 0xFF);
            a = (byte) ((a >> (8 * 0)) & 0xFF);
            a = (byte) ((a >> (8 * 0)) & 0xFF);
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

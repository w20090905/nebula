package test.java.performance;

import java.util.ArrayList;

public class TestGet__List implements Runable {

    long max;
    String name,test;
    ArrayList<String> pa = new ArrayList<String>();

    @Override
    public void setup() throws Exception {
        // Array
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        pa.add("name");
        pa.add("sex");
        max = 1000 * 1000;
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            // 1
            name = pa.get(0);
            test = pa.get(3);
            test = pa.get(11);
            name = pa.get(1);
            test = pa.get(0);
            name = pa.get(1);
            test = pa.get(10);
            test = pa.get(1);
            name = pa.get(0);
            test = pa.get(9);
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

package test.java.performance.getField;

import java.util.HashMap;

public class TestGet__Map implements Runable {

    long max;
    String name;
    HashMap<String, String> map = new HashMap<String, String>();

    @Override
    public void setup() throws Exception {

        map.put("name", "name");
        map.put("sex", "sex");
        map.put("sex1", "sex");
        map.put("sex2", "sex");
        map.put("sex3", "sex");
        map.put("sex4", "sex");
        map.put("sex5", "sex");
        map.put("sex6", "sex");
        map.put("sex7", "sex");
        map.put("sex8", "sex");
        map.put("sex9", "sex");
        map.put("sex11", "sex");
        map.put("sex21", "sex");
        map.put("sex31", "sex");
        map.put("sex41", "sex");
        map.put("sex51", "sex");
        map.put("sex61", "sex");
        map.put("sex71", "sex");
        map.put("sex81", "sex");
        map.put("sex91", "sex");

        max = 1000 * 1000;
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            name = map.get("name");
            name = map.get("sex");
            name = map.get("name");
            name = map.get("sex3");
            name = map.get("name");
            name = map.get("sex51");
            name = map.get("name");
            name = map.get("sex");
            name = map.get("name");
            name = map.get("sex");
            
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

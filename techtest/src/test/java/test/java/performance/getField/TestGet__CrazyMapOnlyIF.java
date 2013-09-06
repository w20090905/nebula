package test.java.performance.getField;


public class TestGet__CrazyMapOnlyIF implements Runable {

    long max;
    String name;
    CrazyMapOnlyIF map = CrazyMapOnlyIF.newMap("name","sex","age");

    @Override
    public void setup() throws Exception {

        map.put("name", "name");
        map.put("sex", "sex");
        map.put("age", "sex");

        max = 1000 * 1000;
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            name = map.get("name");
            name = map.get("sex");
            name = map.get("name");
            name = map.get("age");
            name = map.get("name");
            name = map.get("age");
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

package test.java.performance.getField;

import java.util.TreeMap;

public class Performance {

    public static void main(String[] args) {

        try {
            TreeMap<String, Runable> rl = new TreeMap<String, Runable>();

            Runable nop = new Runable() {
                long max = 1000 * 1000;

                public void tearDown() throws Exception {
                }

                public void setup() throws Exception {
                }

                @Override
                public long run() throws Exception {
                    return max * 1000;
                }
            };

            long start, end;
            long times = 0;

            start = System.nanoTime();
            nop.run();
            end = System.nanoTime();
            // long nopCost = end - start;

            // rl.put("MVEL", new TestGetMVEL());
            // rl.put("Mget", new TestGetMGet());
//            rl.put("Prop", new TestGet__Properties());
//            rl.put("Field", new TestGet__Field());
//            rl.put("Array", new TestGet__Array());
//            rl.put("Reflect", new TestGet__Reflect());
//            rl.put("List", new TestGet__List());
//            rl.put("Map", new TestGet__Map());
//            rl.put("InsOF", new TestGet__InstanceOf());
//            rl.put("CMap", new TestGet__CrazyMap());
//            rl.put("CIFMap", new TestGet__CrazyMap());
//            rl.put("Nop", new TestGet__Nop());
            rl.put("IntBitOp", new TestGet__IntBitOp());
            rl.put("ByteArray", new TestGet__ByteArray());
            
            for (String k : rl.keySet()) {
                System.out.print("\t" + k);
            }

            System.out.println("");
            for (String k : rl.keySet()) {
                Runable v = rl.get(k);
                v.setup();
                start = System.nanoTime();
                times = v.run();
                end = System.nanoTime();

                System.out.print("\t" + String.format("%.2f", (double) (end - start) / times));
            }

            System.out.println("");
            for (String k : rl.keySet()) {
                Runable v = rl.get(k);
                v.setup();
                start = System.nanoTime();
                times = v.run();
                end = System.nanoTime();

                System.out.print("\t" + String.format("%.2f", (double) (end - start) / times));
            }
            
            System.out.println("");
            for (String k : rl.keySet()) {
                Runable v = rl.get(k);
                v.setup();
                start = System.nanoTime();
                times = v.run();
                end = System.nanoTime();

                System.out.print("\t" + String.format("%.2f", (double) (end - start) / times));
            }
            
            System.out.println("");
            for (String k : rl.keySet()) {
                Runable v = rl.get(k);
                v.setup();
                start = System.nanoTime();
                times = v.run();
                end = System.nanoTime();

                System.out.print("\t" + String.format("%.2f", (double) (end - start) / times));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

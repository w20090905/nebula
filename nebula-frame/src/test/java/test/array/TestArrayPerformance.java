package test.array;

public class TestArrayPerformance {
    static int max = 10000000;
    static int out = 10;

    public static void main(String[] args) {
        long start, stop;

        String[] sa = new String[5];
        sa[0] = "dsfsa";
        sa[1] = "test";
        sa[2] = "dddf";
        sa[3] = "grter";
        sa[4] = "tetwre";

        String s;

        start = System.nanoTime();
        for (int j = 0; j < out; j++) {
            for (int i = 0; i < max; i++) {
                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];

                // --
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
                s = sa[0];
                s = sa[1];
                s = sa[2];
                s = sa[3];
                s = sa[4];
            }
        }
        stop = System.nanoTime();
        System.out.println((double) (stop - start) / (max * out * 100));

        P p = new P();
        start = System.nanoTime();
        for (int j = 0; j < out; j++) {
            for (int i = 0; i < max; i++) {
                // -------- 1
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 2
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 3
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 4
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 5
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 6
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 7
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 8
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 9
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;

                // -------- 10
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
                s = p.aame;
                s = p.bp;
                s = p.cd;
                s = p.dre;
                s = p.edh;
            }
        }
        stop = System.nanoTime();
        

        s="";
        s=s+" ";

        System.out.println((double) (stop - start) / (max * out * 100));

        p.run();
    }

    static class P {
        String aame = "sdfsdf";
        String bp = "dsfdsf ";
        String cd = "dsfsaf";
        String dre = "sdfsadf";
        String edh = "safdsf";

        void run() {
            String s;

            long start, stop;
            start = System.nanoTime();
            for (int j = 0; j < out; j++) {
                for (int i = 0; i < max; i++) {
                    // -------- 1
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 2
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 3
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 4
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 5
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 6
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 7
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 8
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 9
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;

                    // -------- 10
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                    s = aame;
                    s = bp;
                    s = cd;
                    s = dre;
                    s = edh;
                }
            }
            stop = System.nanoTime();

            System.out.println((double) (stop - start) / (max * out * 100));

            s="";
            s=s+" ";
        }
    }

}

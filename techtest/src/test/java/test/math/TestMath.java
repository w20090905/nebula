package test.math;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMath {

    /**
     * @param args
     */
    public static void main(String[] args) {

        int[] l = new int[] { 
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        int[] ld =  new int[l.length];//{0,0,0,0};
        
        //Arrays.fill(ld, 0);
        long start = System.nanoTime();
        sub(l, ld, l.length);
        long cost = System.nanoTime() - start;
        System.out.println("Cost:" + (cost / 1000000) + " for " + c + " times");
    }

    static long c = 0;

    public static void sub(int[] l, int[] ld, int n) {
        for (int i = 0; i < n; i++) {
            if (ld[i] == 0) {
                int[] na = new int[l.length];
//                for (int j = 0; j < n; j++)
//                    na[j] = ld[j];
                System.arraycopy(ld, 0, na, 0, l.length);

                na[i] = l[i];
                 //------------------
                 for(int j=0;j<na.length;j++){
                 if(na[j]>0)System.out.print(na[j] + "\t");
                 }
                 System.out.println("");
                 //------------------
                c++;
                sub(l, na, i);
            }
        }
    }

}

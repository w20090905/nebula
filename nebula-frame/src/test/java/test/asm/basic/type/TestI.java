package test.asm.basic.type;

import java.util.ArrayList;
import java.util.List;

public class TestI {
    public static void main(String[] args) {
        List<Object> l = new ArrayList<Object>();
        
        System.out.println(List.class.isAssignableFrom(l.getClass()));
    }

}

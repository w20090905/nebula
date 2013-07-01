package test.language.mvel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import test.language.custome.nest.def.Method;


public class TestFunc {

    public static void main(String[] args) {

        Map<Object, Object> vars = new HashMap<Object, Object>();
        vars.put("birthday", new B());
        vars.put("age", new Integer(0));

        Method<Object, Object> f = new MvelFunc<Object, Object>("age = birthday.year");

        System.out.println(f.call(vars).toString());
    }
    
   static class B{
        private Date d;
        public B(){
            this.d=new Date();            
        }
        public B(Date d){
            this.d=d;            
        }
        
        public int getYear(){
            Calendar c =  Calendar.getInstance();
            c.setTime(d);
            return c.get(Calendar.YEAR);
        }
        
    }
}

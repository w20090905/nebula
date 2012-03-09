package test.mvel;

import java.io.Serializable;


import org.mvel2.MVEL;

import test.nest.def.Method;

public class MvelFunc<R,V> implements Method<R, V> {
    protected String expr;
    protected Serializable compiled;
    public MvelFunc(String expr){
        this.expr = expr;
        compiled = MVEL.compileExpression(expr);
    }
    @SuppressWarnings("unchecked")
    public R call(V vars) {
//        Map<String,Object> vs = new HashMap<String,Object>();
//        for(int i=0;i<vars.length;i++){
//            vs.put("$" + i, new Integer(100));            
//        }
        return (R)MVEL.executeExpression(compiled,vars);
    }
    
}

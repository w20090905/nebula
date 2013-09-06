package test.java.performance.getField;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;

public class TestGet__MVEL implements Runable {

    String expression = "foobar + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar  + foobar ";
    // Compile the expression.
    Serializable compiled = MVEL.compileExpression(expression);
    @SuppressWarnings("rawtypes")
	Map vars;
    Integer result = null;
    long max;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void setup() throws Exception {
        result = null;
        max = 1000 * 1000;
        vars = new HashMap();
        vars.put("foobar", new Integer(100));
    }

    @Override
    public long run() throws Exception {
        for (int i = 0; i < max; i++) {
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
            result = (Integer) MVEL.executeExpression(compiled, vars);
        }
        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

}

package test.asm_compile;


/**
 * An integer or boolean expression of at most two variables.
 * 
 * @author Eric Bruneton
 */
public interface Expression {

    /**
     * Evaluates this expression.
     * 
     * @param i the value of the first variable.
     * @param j the value of the second variable.
     * @return the value of this expression for the given variable values.
     */
    int eval(int i, int j);
}

package nebula.lang;

import static nebula.lang.Operator.ADD;
import static nebula.lang.Operator.DIV;
import static nebula.lang.Operator.EQ;
import static nebula.lang.Operator.GE;
import static nebula.lang.Operator.GT;
import static nebula.lang.Operator.LE;
import static nebula.lang.Operator.LT;
import static nebula.lang.Operator.MUL;
import static nebula.lang.Operator.NE;
import static nebula.lang.Operator.REM;
import static nebula.lang.Operator.SUB;

import java.util.EnumMap;

import org.objectweb.asm.Opcodes;

import com.google.common.collect.Maps;

public class LongOperator implements OperatorExpr, Opcodes {
	EnumMap<Operator, Integer> ops = Maps.newEnumMap(Operator.class);

	public LongOperator() {
		ops.put(ADD, LADD);
		ops.put(SUB, LSUB);
		ops.put(MUL, LMUL);
		ops.put(DIV, LDIV);
		ops.put(REM, LREM);

		ops.put(EQ, IFNE);// '==';
		ops.put(NE, IFEQ);// '!=';
		ops.put(GE, IFLT); // '>=';
		ops.put(GT, IFLE);// '>';
		ops.put(LE, IFGT); // '<=';
		ops.put(LT, IFGE);// '<';
	}

	public void arithmetic(AsmCompiler compiler, Operator op, Expr<Object> e1, Expr<Object> e2) {
		compiler.longArithmetic(e1, e2, ops.get(op));
	}

	@Override
	public void increment(final AsmCompiler compiler, Expr<Object> e1) {
		compiler.longIncrement(e1);
	}

	@Override
	public void decrement(final AsmCompiler compiler, Expr<Object> e1) {
		compiler.longDecrement(e1);
	}

	@Override
	public void positive(final AsmCompiler compiler, Expr<Object> e1) {
		e1.compile(compiler);
	}

	@Override
	public void negates(final AsmCompiler compiler, Expr<Object> e1) {
		compiler.longNegates(e1);
	}

	public <V> void relational(final AsmCompiler compiler, Operator op, Expr<V> e1, Expr<V> e2) {
		compiler.longRelational(compiler, e1, e2, ops.get(op));
	}

}

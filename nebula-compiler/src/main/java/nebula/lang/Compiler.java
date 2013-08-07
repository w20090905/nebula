package nebula.lang;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import nebula.data.DataRepos;
import nebula.data.Entity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.google.common.collect.Maps;

public class Compiler {
	Context context;

	static Map<String, Operator> opTypes = Maps.newHashMap();

	static Operator resolveOperator(Type type) {
		Operator op = opTypes.get(type.getName());
		if (op != null) return op;
		else if (type.getSuperType() != null) return resolveOperator(type.getSuperType());
		else throw new RuntimeException("Cannot find type" + type.name);
	}

	Compiler(Context context) {
		this.context = context;
		opTypes.put(RawTypes.Long.name(), new LongOperator());
	}

	EntityExpressionComplier exprCompiler = new EntityExpressionComplier();
	EntityActionComplier stCompiler = new EntityActionComplier();

	// EntityActionComplier

	// static class EntityExpressionAgent<T> implements EntityExpression {
	// final Expr<T> expr;
	// final Type type;
	//
	// EntityExpressionAgent(final Expr<T> expr, final Type type) {
	// this.expr = expr;
	// this.type = type;
	// }
	//
	// @Override
	// public T eval(Entity entity) {
	// return null;
	// }
	//
	// EntityExpression compile() {
	// return exprCompiler.compile(expr, type);
	// }
	// }

	public <T> EntityExpression compile(Expr<T> expr, Type type) {
		return exprCompiler.compile(expr, type, context);
	}

	public EntityAction compile(Statement statement, Type type) {
		return stCompiler.compile(statement, type, context);
	}

	public Expr<Boolean> opAnd(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new And(e1, e2);
	}

	public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	public Expr<Boolean> opOr(Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Or(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opNotEqualTo(Expr<V> e1, Expr<V> e2) {
		return new NotEqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opEqualTo(Expr<V> e1, Expr<V> e2) {
		return new EqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opGreaterThan(Expr<V> e1, Expr<V> e2) {
		return new GreaterThan<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opGreaterThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new GreaterThanOrEqualTo<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opLessThan(Expr<V> e1, Expr<V> e2) {
		return new LessThan<V>(e1, e2);
	}

	public <V extends Comparable<V>> Expr<Boolean> opLessThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
		return new LessThanOrEqualTo<V>(e1, e2);
	}


	public Expr<Object> opAdd(Expr<Object> e1, Expr<Object> e2) {
		return new Add(e1, e2);
	}

	public Expr<Object> range(Expr<Object> from, Expr<Object> to) {
		if (from == null) {
			return new Range_0_To(to);
		} else if (to == null) {
			return new Range_From_(from);
		} else {
			return new Range(from, to);
		}
	}

	public Expr<Object> index(Expr<Object> from) {
		return new Index(from);
	}

	public Expr<Object> entities(Expr<Object> repos,String string) {
		return new DatastoreGet(repos, string);
	}

	public Expr<Object> list(Expr<Object> list, List<Expr<Object>> ranges) {
		if (ranges.size() == 1) {
			return new ListGet(list, ranges.get(0));
		} else {
			return new ListRanges(list, ranges);
		}
	}

	public Expr<Object> list(Expr<Object> list, Expr<Object> clause) {
		// Lists.
		return null;
		// TODO
	}

	public Expr<Object> opUnit(Expr<Object> v, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Expr<Object> opFieldInList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Expr<Object> opSub(Expr<Object> e1, Expr<Object> e2) {
		return new Sub(e1, e2);
	}

	public Expr<Object> opMulti(Expr<Object> e1, Expr<Object> e2) {
		return new Multi(e1, e2);
	}

	public Expr<Object> opDiv(Expr<Object> e1, Expr<Object> e2) {
		return new Div(e1, e2);
	}

	public Expr<Object> opRemainder(Expr<Object> e1, Expr<Object> e2) {
		return new Remainder(e1, e2);
	}

	public Expr<Object> opIncrement(Expr<Object> e1) {
		return new Increment(e1);
	}

	public Expr<Object> opDecrement(Expr<Object> e1) {
		return new Decrement(e1);
	}

	public Expr<Object> opPositive(Expr<Object> e1) {
		return new Positive(e1);
	}

	public Expr<Object> opNegates(Expr<Object> e1) {
		return new Negates(e1);
	}

	public Expr<Long> opLongCst(String value) {
		return new LongCst(value);
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}

	public Expr<DateTime> opTimestampCst(String value) {
		return new TimestampCst(value);
	}

	public Expr<DateTime> opDatetimeCst(String value) {
		return new DatetimeCst(value);
	}

	public Expr<DateTime> opDateCst(String value) {
		return new DateCst(value);
	}

	public Expr<DateTime> opTimeCst(String value) {
		return new TimeCst(value);
	}

	public Expr<Object> opType(String text) {
		return new TypeRefer(text);
	}

	public Expr<Object> opLocal(Var var) {
		return new VarRefer(var);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		return new FieldOf(e1, name);
	}

	public Statement stPut(Expr<Object> field, Expr<Object> value) {
		return new PutField((FieldOf) field, value);
	}

	public Statement stPutVar(Var var, Expr<Object> initExpr) {
		return new PutVar(var, initExpr);
	}

	public Statement stBlock(List<Statement> statements) {
		return new Block(statements);
	}

	public Expr<Object> opMethodCall(Expr<Entity> e1, String name) {
		return new MethodCall(e1, name);
	}

	public Statement stCall(Expr<Object> e1) {
		return new CallStatment(e1);
	}

	abstract static class Expression<T> implements Opcodes, Expr<T> {
		@Override
		public T eval() {
			throw new UnsupportedOperationException();
		}
	}

	abstract static class LogicExpr extends Expression<Boolean> {
		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Boolean.name());
		}
	}

	/**
	 * A logical "and" expression.
	 */
	static class And extends LogicExpr {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public And(Expr<Boolean> e1, Expr<Boolean> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// compiles e1
			e1.compile(mv, context);
			// tests if e1 is false
			mv.visitInsn(DUP);
			Label end = new Label();
			mv.visitJumpInsn(IFEQ, end);
			// case where e1 is true : e1 && e2 is equal to e2
			mv.visitInsn(POP);
			e2.compile(mv, context);
			// if e1 is false, e1 && e2 is equal to e1:
			// we jump directly to this label, without evaluating e2
			mv.visitLabel(end);
		}

		@Override
		public Boolean eval() {
			return e1.eval() && e2.eval();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " && " + e2.toString() + ")";
		}
	}

	/**
	 * A logical "not" expression.
	 */
	static class Not extends LogicExpr {
		final Expr<Boolean> e1;

		Not(Expr<Boolean> e1) {
			this.e1 = e1;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// computes !e1 by evaluating 1 - e1
			mv.visitLdcInsn(new Integer(1));
			e1.compile(mv, context);
			mv.visitInsn(ISUB);
		}

		@Override
		public Boolean eval() {
			return !e1.eval();
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}
	}

	/**
	 * A logical "or" expression.
	 */
	static class Or extends LogicExpr {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public Or(Expr<Boolean> e1, Expr<Boolean> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// compiles e1
			e1.compile(mv, context);
			// tests if e1 is true
			mv.visitInsn(DUP);
			Label end = new Label();
			mv.visitJumpInsn(IFNE, end);
			// case where e1 is false : e1 || e2 is equal to e2
			mv.visitInsn(POP);
			e2.compile(mv, context);
			// if e1 is true, e1 || e2 is equal to e1:
			// we jump directly to this label, without evaluating e2
			mv.visitLabel(end);
		}

		@Override
		public Boolean eval() {
			return e1.eval() || e2.eval();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " || " + e2.toString() + ")";
		}
	}

	abstract static class CompareExpr<V extends Comparable<V>> extends Expression<Boolean> {
		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Boolean.name());
		}
	}

	static class NotEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		NotEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).ne(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) != 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " != " + e2.toString() + ")";
		}
	}

	static class EqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		EqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).eq(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) == 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " == " + e2.toString() + ")";
		}
	}

	/**
	 * A "greater than" expression.
	 */
	static class GreaterThan<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		GreaterThan(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).gt(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) > 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " > " + e2.toString() + ")";
		}
	}

	static class GreaterThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		GreaterThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).ge(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) >= 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " >= " + e2.toString() + ")";
		}
	}

	static class LessThan<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		LessThan(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).lt(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) < 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " < " + e2.toString() + ")";
		}
	}

	static class LessThanOrEqualTo<V extends Comparable<V>> extends CompareExpr<V> {
		final Expr<V> e1;
		final Expr<V> e2;

		LessThanOrEqualTo(Expr<V> e1, Expr<V> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).le(mv, context, e1, e2);
		}

		@Override
		public Boolean eval() {
			return e1.eval().compareTo(e2.eval()) <= 0;
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " <= " + e2.toString() + ")";
		}
	}

	abstract static class ArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;
		final Expr<Object> e2;

		ArithmeticExpr(final Expr<Object> e1, final Expr<Object> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}

		@Override
		public Type getExprType(Context context) {
			return e1.getExprType(context);
		}
	}

	/**
	 * An addition expression.
	 */
	static class Add extends ArithmeticExpr {
		Add(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).add(mv, context, e1, e2);
		}

		@Override
		public Long eval() {
			return (Long) e1.eval() + (Long) e2.eval();
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " + " + e2.toString() + ")";
		}
	}

	static class Sub extends ArithmeticExpr {
		Sub(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).sub(mv, context, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " - " + e2.toString() + ")";
		}
	}

	/**
	 * A multiplication expression.
	 */
	static class Multi extends ArithmeticExpr {
		Multi(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).multi(mv, context, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " * " + e2.toString() + ")";
		}
	}

	static class Div extends ArithmeticExpr {
		Div(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).div(mv, context, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " / " + e2.toString() + ")";
		}
	}

	static class Remainder extends ArithmeticExpr {
		Remainder(final Expr<Object> e1, final Expr<Object> e2) {
			super(e1, e2);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).remainder(mv, context, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " % " + e2.toString() + ")";
		}
	}

	abstract static class UnaryArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;

		UnaryArithmeticExpr(final Expr<Object> e1) {
			this.e1 = e1;
		}

		@Override
		public Type getExprType(Context context) {
			return e1.getExprType(context);
		}
	}

	static class Increment extends UnaryArithmeticExpr {
		Increment(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).increment(mv, context, e1);
		}

		@Override
		public String toString() {
			return "++" + e1.toString();
		}
	}

	static class Decrement extends UnaryArithmeticExpr {
		Decrement(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).decrement(mv, context, e1);
		}

		@Override
		public String toString() {
			return "--" + e1.toString();
		}
	}

	static class Positive extends UnaryArithmeticExpr {
		Positive(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).positive(mv, context, e1);
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class Negates extends UnaryArithmeticExpr {
		Negates(Expr<Object> e1) {
			super(e1);
		}

		public void compile(final MethodVisitor mv, Context context) {
			resolveOperator(e1.getExprType(context)).negates(mv, context, e1);
		}

		@Override
		public String toString() {
			return "+" + e1.toString();
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public void compile(final MethodVisitor mv, Context context) {
			mv.visitLdcInsn(value);
		}

		@Override
		public String eval() {
			return this.value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.String.name());
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public void compile(final MethodVisitor mv, Context context) {
			mv.visitTypeInsn(NEW, "java/math/BigDecimal");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(text);
			mv.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V");
		}

		@Override
		public BigDecimal eval() {
			return new BigDecimal(this.text);
		}

		@Override
		public String toString() {
			return text;
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Decimal.name());
		}
	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public void compile(final MethodVisitor mv, Context context) {
			mv.visitLdcInsn(value);
		}

		@Override
		public Long eval() {
			return this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Long.name());
		}
	}

	abstract static class Tempral<T> extends Expression<T> {
		final DateTimeFormatter formater;
		final long value;

		Tempral(String text) {
			this(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		Tempral(DateTimeFormatter formater, String text) {
			this.formater = formater;
			this.value = formater.parseDateTime(text).getMillis();
		}

		public void compile(final MethodVisitor mv, Context context) {
			mv.visitTypeInsn(NEW, "org/joda/time/DateTime");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(this.value);
			mv.visitMethodInsn(INVOKESPECIAL, "org/joda/time/DateTime", "<init>", "(J)V");
		}

		@Override
		public String toString() {
			return formater.print(value);
		}
	}

	static class TimestampCst extends Tempral<DateTime> {

		TimestampCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Timestamp.name());
		}
	}

	static class DatetimeCst extends Tempral<DateTime> {
		DatetimeCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Datetime.name());
		}
	}

	static class DateCst extends Tempral<DateTime> {
		DateCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Date.name());
		}
	}

	static class TimeCst extends Tempral<DateTime> {
		TimeCst(String text) {
			super(DateTimeFormat.forPattern("HH:mm:ss"), text);
		}

		@Override
		public DateTime eval() {
			return new DateTime(this.value);
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(RawTypes.Time.name());
		}
	}

	static class FieldOf extends Expression<Object> {
		final Expr<Object> e1;
		final String name;

		FieldOf(Expr<Object> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(final MethodVisitor mv, Context context) {
			e1.compile(mv, context);
			mv.visitLdcInsn(this.name);
			switch (this.getExprType(context).rawType) {
			case Long:
				compileLong(mv);
				break;
			case String:
				compileString(mv);
				break;
			default:
				break;
			}			
		}

		public void compileLong(final MethodVisitor mv) {
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J");
		}

		public void compileString(final MethodVisitor mv) {
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get", "(Ljava/lang/String;)Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "java/lang/String");
		}

		@Override
		public Object eval() {
			return ((Entity) e1.eval()).get(name);
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public Type getExprType(Context context) {
			Type type = e1.getExprType(context);
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					return f.getType();
				}
			}
			throw new RuntimeException("Cannot find field");
		}
	}

	// Type resolveType(Type type, String name) {
	// for (Field f : type.getFields()) {
	// if (f.name.equals(name)) {
	// return f.getType();
	// }
	// }
	// throw new RuntimeException("Cannot find field");
	// }

	// TODO Not realized MethodCall
	static class MethodCall extends Expression<Object> {
		final Expr<Entity> e1;
		final String name;

		MethodCall(Expr<Entity> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
		}

		@Override
		public Long eval() {
			return null;
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}

		@Override
		public Type getExprType(Context context) {
			Type type = e1.getExprType(context);
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					return f.getType();
				}
			}
			throw new RuntimeException("Cannot find field");
		}
	}

	static class TypeRefer extends Expression<Object> {
		final String name;

		TypeRefer(final String name) {
			this.name = name;
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
			mv.visitLdcInsn(name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get", "()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");

			//
			//
			// mv.visitVarInsn(ALOAD, 3);
			// mv.visitLdcInsn("wangshilian");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore",
			// "get", "(Ljava/lang/Object;)Lnebula/data/Timable;");
			// mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
			// mv.visitVarInsn(ASTORE, 4);
			//
			//
			// mv.visitVarInsn(ALOAD, 1);
			// mv.visitLdcInsn("Age");
			// mv.visitVarInsn(ALOAD, 4);
			// mv.visitLdcInsn("Age");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "get",
			// "(Ljava/lang/String;)Ljava/lang/Object;");
			// mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put",
			// "(Ljava/lang/String;Ljava/lang/Object;)V");

		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType("Type");
		}

		@Override
		public String toString() {
			return "$" + name;
		}
	}

	static class Index extends Expression<Object> {
		final Expr<Object> index;

		Index(final Expr<Object> index) {
			this.index = index;
		}

		@Override
		public Type getExprType(Context context) {
			return this.index.getExprType(context);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			index.compile(mv, context);
		}
	}

	static class Range_0_To extends Expression<Object> {
		final Expr<Object> to;

		Range_0_To(final Expr<Object> index) {
			this.to = index;
		}

		@Override
		public Type getExprType(Context context) {
			return this.to.getExprType(context);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			to.compile(mv, context);
		}
	}

	static class Range_From_ extends Expression<Object> {
		final Expr<Object> from;

		Range_From_(final Expr<Object> index) {
			this.from = index;
		}

		@Override
		public Type getExprType(Context context) {
			return this.from.getExprType(context);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			from.compile(mv, context);
		}
	}

	static class Range extends Expression<Object> {
		final Expr<Object> from;
		final Expr<Object> to;

		Range(final Expr<Object> from, final Expr<Object> to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public Type getExprType(Context context) {
			if (from != null) return this.from.getExprType(context);
			else return this.to.getExprType(context);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			from.compile(mv, context);
			to.compile(mv, context);
		}
	}

	static class ListGet extends Expression<Object> {
		final Expr<Object> list;
		final Expr<Object> index;

		ListGet(final Expr<Object> list, final Expr<Object> index) {
			this.list = list;
			this.index = index;
		}

		@Override
		public Type getExprType(Context context) {
			return list.getExprType(context);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			list.compile(mv, context);
			mv.visitTypeInsn(CHECKCAST, "java/util/List");
			index.compile(mv, context);
			mv.visitInsn(L2I);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
			 mv.visitTypeInsn(CHECKCAST, "nebula/data/Entity");
//			 mv.visitInsn(POP);
		}
	}

	static class DatastoreGet extends Expression<Object> {
		final Expr<Object> repos;
		final String name;

		DatastoreGet(final Expr<Object> repos, final String name) {
			this.repos = repos;
			this.name = name;
		}

		@Override
		public Type getExprType(Context context) {
			return context.resolveType(name);
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			repos.compile(mv, context);
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Ljava/lang/String;"));
			mv.visitLdcInsn(org.objectweb.asm.Type.getType("Lnebula/data/Entity;"));
			mv.visitLdcInsn(name);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataRepos", "define", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Lnebula/data/Broker;");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Broker", "get", "()Ljava/lang/Object;");
			mv.visitTypeInsn(CHECKCAST, "nebula/data/DataStore");
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/DataStore", "listAll", "()Ljava/util/List;");
		}
	}

	static class ListRanges extends Expression<Object> {
		final Expr<Object> list;
		final List<Expr<Object>> ranges;

		ListRanges(final Expr<Object> list, final List<Expr<Object>> ranges) {
			this.list = list;
			this.ranges = ranges;
		}

		@Override
		public Type getExprType(Context context) {
			return new ListType(list.getExprType(context));
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			// TODO
		}
	}

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public void compile(MethodVisitor mv, Context context) {
			mv.visitVarInsn(ALOAD, var.index);
		}

		@Override
		public Type getExprType(Context context) {
			return var.type;
		}

		@Override
		public String toString() {
			return var.name;
		}
	}

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public void compile(final MethodVisitor mv, Context context) {
			for (Statement st : statements) {
				st.compile(mv, context);
			}
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			for (Statement st : statements) {
				st.exec(entity, repos);
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("{");
			for (Statement st : statements) {
				sb.append(st);
			}
			sb.append("}");
			return sb.toString();
		}
	}

	// TODO Not realized VarDefineField
	static class PutVar implements Opcodes, Statement {
		final Var var;
		final Expr<Object> initExpr;

		PutVar(Var var, Expr<Object> initExpr) {
			this.var = var;
			this.initExpr = initExpr;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);

		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			// return (Long) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return var.name + " = " + initExpr.toString() + ";";
		}
	}

	static class PutField implements Opcodes, Statement {
		final Expr<Object> parent;
		final Expr<Object> value;
		final String name;

		PutField(FieldOf field, Expr<Object> value) {
			this.parent = field.e1;
			this.name = field.name;
			this.value = value;
		}

		private void toObject(final MethodVisitor mv, Expr<Object> expr, Context context) {
			switch (expr.getExprType(context).rawType) {
			case Boolean:
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
				break;
			case Long:
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
				break;
			default:
				break;
			}
		}


		 
		public void compile(final MethodVisitor mv, Context context) {
			parent.compile(mv, context);
			mv.visitLdcInsn(this.name);
			value.compile(mv, context);
			toObject(mv, value, context);
			mv.visitMethodInsn(INVOKEINTERFACE, "nebula/data/Entity", "put", "(Ljava/lang/String;Ljava/lang/Object;)V");
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			entity.put(name, value.eval());
		}

		@Override
		public String toString() {
			return parent.toString() + "." + name.toString() + " = " + value.toString() + ";";
		}
	}

	// TODO Not realized CallStatment
	static class CallStatment implements Opcodes, Statement {
		final Expr<Object> value;

		CallStatment(Expr<Object> value) {
			this.value = value;
		}

		public void compile(final MethodVisitor mv, Context context) {
			// compiles e1, e2, and adds an instruction to multiply the two
			// values
			// mv.visitLdcInsn(value);
		}

		@Override
		public void exec(Entity entity, DataRepos repos) {
			// return (Long) e1.exec().get(name);
		}

		@Override
		public String toString() {
			return value.toString() + ";";
		}
	}

}

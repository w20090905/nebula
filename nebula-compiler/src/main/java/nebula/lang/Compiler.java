package nebula.lang;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import nebula.data.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.objectweb.asm.Opcodes;

import com.google.common.base.Preconditions;

public class Compiler {
	static abstract class AbstractRange extends Expression<Object> {
		@Override
		public Type getType() {
			throw new UnsupportedOperationException("Index");
		}
	}

	public static class Action implements Statement {
		LinkedHashMap<String, Var> locals;
		Statement st;

		public Action(LinkedHashMap<String, Var> locals, Statement st) {
			this.locals = locals;
			this.st = st;
		}

		@Override
		public void compile(CompilerBase compiler) {
			st.compile(compiler);
		}

		@Override
		public void scan(CompilerContext context) {
			st.scan(context);
		}
	}

	static class Arithmetic extends Expression<Object> {
		final Expr<Object> e1;
		final Expr<Object> e2;
		final Operator op;

		Arithmetic(final Operator op, final Expr<Object> e1, final Expr<Object> e2) {
			this.e1 = e1;
			this.e2 = e2;
			this.op = op;
		}

		public void compile(CompilerBase compiler) {
			compiler.arithmetic(op, e1, e2);
		}

		@Override
		public Long eval() {
			return (Long) e1.eval() + (Long) e2.eval();
		}

		@Override
		public Type getType() {
			return e1.getType();
		}

		@Override
		public void scan(CompilerContext context) {
			e1.scan(context);
			e2.scan(context);
			Type type1 = e1.getType();
			Type type2 = e2.getType();
			Preconditions.checkArgument(type1.getRawType() == type2.getRawType());
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}
	}

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public void compile(CompilerBase compiler) {
			compiler.block(statements);
		}

		@Override
		public void scan(CompilerContext context) {
			for (Statement st : statements) {
				st.scan(context);
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

	// TODO Not realized CallStatment
	static class CallStatment implements Opcodes, Statement {
		final Expr<Object> value;

		CallStatment(Expr<Object> value) {
			this.value = value;
		}

		public void compile(CompilerBase compiler) {
			compiler.call(value);
		}

		@Override
		public void scan(CompilerContext context) {
			value.scan(context);
		}

		@Override
		public String toString() {
			return value.toString() + ";";
		}
	}

	/**
	 * A logical "and" expression.
	 */
	static class Conditional extends LogicExpr {
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;
		final Operator op;

		public Conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(CompilerBase compiler) {
			compiler.conditional(op, e1, e2);
		}

		@Override
		public void scan(CompilerContext context) {
			e1.scan(context);
			e2.scan(context);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}
	}

	static class DatastoreGet extends Expression<Object> {
		final String name;
		final Expr<Object> repos;
		Type type;

		DatastoreGet(final Expr<Object> repos, final String name) {
			this.repos = repos;
			this.name = name;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.datastoreGet(repos, name);
		}

		@Override
		public Type getType() {
			return this.type;
		}

		@Override
		public void scan(CompilerContext context) {
			type = new ListType(context.resolveType(name));
		}
	}

	static class DateCst extends Tempral<DateTime> {
		DateCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd"), text);
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.constDate(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DATE;
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	static class DatetimeCst extends Tempral<DateTime> {
		DatetimeCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"), text);
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.constDatetime(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DATETIME;
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public void compile(CompilerBase compiler) {
			compiler.constDecimal(text);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DECIMAL;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return text;
		}
	}

	static class Decrement extends UnaryArithmeticExpr {
		Decrement(Expr<Object> e1) {
			super(e1);
		}

		public void compile(CompilerBase compiler) {
			compiler.decrement(e1);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return "(--" + e1.toString() + ")";
		}
	}

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
	// return exprreturn compiler.compile(expr, type);
	// }
	// }

	abstract static class Expression<T> implements Opcodes, Expr<T> {
		@Override
		public T eval() {
			throw new UnsupportedOperationException();
		}
	}

	static class FieldOf extends Expression<Object> {
		final Expr<Object> entity;
		Type fieldType;
		final String name;

		FieldOf(Expr<Object> entity, String name) {
			this.entity = entity;
			this.name = name;
		}

		public void compile(CompilerBase compiler) {
			compiler.getField(entity, name, fieldType);
		}

		@Override
		public Type getType() {
			return fieldType;
		}

		@Override
		public void scan(CompilerContext context) {
			if (context.isTopLevelGet) {
				context.isTopLevelGet = false;
				entity.scan(context);
				Type type = entity.getType();
				for (Field f : type.getFields()) {
					if (f.name.equals(name)) {
						this.fieldType = f.getType();
						context.field = f;
						break;
					}
				}
				context.refFields.add(context.field);
				context.isTopLevelGet = true;
			} else {
				entity.scan(context);
				Type type = entity.getType();
				for (Field f : type.getFields()) {
					if (f.name.equals(name)) {
						this.fieldType = f.getType();
						context.field = f;
						break;
					}
				}
			}
		}

		@Override
		public String toString() {
			return entity.toString() + "." + name.toString();
		}
	}

	static class Increment extends UnaryArithmeticExpr {
		Increment(Expr<Object> e1) {
			super(e1);
		}

		public void compile(CompilerBase compiler) {
			compiler.increment(e1);
		}

		@Override
		public void scan(CompilerContext context) {

		}

		@Override
		public String toString() {
			return "(++" + e1.toString() + ")";
		}
	}

	static class ListFilterByClause extends Expression<Object> {
		final Expr<Object> clause;
		final Expr<Object> list;
		final List<Expr<Object>> params;

		Type type;

		ListFilterByClause(final Expr<Object> list, final Expr<Object> clause, final List<Expr<Object>> params) {
			this.list = list;
			this.clause = clause;
			this.params = params;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.listFilterByClause(list, clause, params);
		}

		@Override
		public Type getType() {
			return list.getType();
		}

		@Override
		public void scan(CompilerContext context) {
			list.scan(context);
			clause.scan(context);
			for (Expr<Object> param : params) {
				param.scan(context);
			}
		}
	}

	static class ListFilterByRanger extends Expression<Object> {
		final Expr<Object> list;
		final List<Expr<Object>> ranges;

		ListFilterByRanger(final Expr<Object> list, final List<Expr<Object>> ranges) {
			this.list = list;
			this.ranges = ranges;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.listFilter(list, ranges);
		}

		@Override
		public Type getType() {
			return list.getType();
		}

		@Override
		public void scan(CompilerContext context) {
			list.scan(context);
			for (Expr<Object> expr : ranges) {
				expr.scan(context);
			}
			Preconditions.checkArgument(list.getType() instanceof ListType);
		}
	}

	static class ListThis extends Expression<Object> {
		final Expr<Object> list;
		Type type;

		ListThis(final Expr<Object> list) {
			this.list = list;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.varRefer(new Var("this", type));
		}

		@Override
		public Type getType() {
			return this.type;
		}

		@Override
		public void scan(CompilerContext context) {
			Type listType = list.getType();
			Preconditions.checkArgument(listType instanceof ListType);
			type = ((ListType) list.getType()).getUnderlyType();
		}

		@Override
		public String toString() {
			return "this";
		}
	}

	static class ListGetItemByIndexExpr extends Expression<Object> {
		final RangeSingle index;
		final Expr<Object> list;
		Type type;

		ListGetItemByIndexExpr(final Expr<Object> list, final RangeSingle index) {
			this.list = list;
			this.index = index;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.get(list, index.value);
		}

		@Override
		public Type getType() {
			return this.type;
		}

		@Override
		public void scan(CompilerContext context) {
			list.scan(context);
			Type listType = list.getType();
			Preconditions.checkArgument(listType instanceof ListType);
			type = ((ListType) list.getType()).getUnderlyType();

			Type indexType = index.value.getType();
			Preconditions.checkArgument(indexType.getRawType() == RawTypes.Long);
		}
	}

	abstract static class LogicExpr extends Expression<Boolean> {
		@Override
		public Type getType() {
			return BootstrapTypeLoader.BOOLEAN;
		}
	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public void compile(CompilerBase compiler) {
			compiler.constLong(value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.LONG;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	// TODO Not realized MethodCall
	static class MethodCall extends Expression<Object> implements Statement {
		final Expr<Entity> e1;
		final String name;

		MethodCall(Expr<Entity> e1, String name) {
			this.e1 = e1;
			this.name = name;
		}

		public void compile(CompilerBase compiler) {
			compiler.callMethod(e1, name);
		}

		@Override
		public Type getType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void scan(CompilerContext context) {
			// Type type = e1.scan(context);
			// for (Field f : type.getFields()) {
			// if (f.name.equals(name)) {
			// return f.getType();
			// }
			// }
			// throw new RuntimeException("Cannot find field");
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
		}
	}

	static class Negates extends UnaryArithmeticExpr {
		Negates(Expr<Object> e1) {
			super(e1);
		}

		public void compile(CompilerBase compiler) {
			compiler.negates(e1);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return "(-" + e1.toString() + ")";
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

		public void compile(CompilerBase compiler) {
			compiler.not(e1);
		}

		@Override
		public void scan(CompilerContext context) {
			Preconditions.checkArgument(e1.getType() == BootstrapTypeLoader.BOOLEAN);
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}
	}

	static class ParamsRefer extends Expression<Object> {
		final Expr<Object> in;
		final int index;
		final int params;

		ParamsRefer(Expr<Object> in, int params, int index) {
			this.in = in;
			this.params = params;
			this.index = index;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.paramsRefer(in, params, index);
		}

		@Override
		public Type getType() {
			return in.getType();
		}

		@Override
		public void scan(CompilerContext context) {
			in.scan(context);
		}

		@Override
		public String toString() {
			return "params[" + String.valueOf(index) + "]";
		}
	}

	static class Positive extends UnaryArithmeticExpr {
		Positive(Expr<Object> e1) {
			super(e1);
		}

		public void compile(CompilerBase compiler) {
			compiler.positive(e1);
		}

		@Override
		public void scan(CompilerContext context) {

		}

		@Override
		public String toString() {
			return "(+" + e1.toString() + ")";
		}
	}

	static class PutField extends Expression<Object> implements Opcodes, Statement {
		final Expr<Object> entity;
		Type fieldType;
		final String name;
		final Expr<Object> value;

		PutField(FieldOf field, Expr<Object> value) {
			this.entity = field.entity;
			this.name = field.name;
			this.value = value;
		}

		public void compile(CompilerBase compiler) {
			compiler.setField(entity, name, fieldType, value);
		}

		@Override
		public Type getType() {
			return fieldType;
		}

		@Override
		public void scan(CompilerContext context) {
			if (context.isTopLevelGet) {
				context.isTopLevelGet = false;

				entity.scan(context);
				Type type = entity.getType();
				for (Field f : type.getFields()) {
					if (f.name.equals(name)) {
						this.fieldType = f.getType();
						context.field = f;
					}
				}
				context.isTopLevelGet = true;

				value.scan(context);

			} else {
				entity.scan(context);
				Type type = entity.getType();
				for (Field f : type.getFields()) {
					if (f.name.equals(name)) {
						this.fieldType = f.getType();
					}
				}
				value.scan(context);
			}
		}

		@Override
		public String toString() {
			return entity.toString() + "." + name.toString() + " = " + value.toString() + ";";
		}
	}

	// TODO Not realized VarDefineField
	static class PutVar implements Opcodes, Statement {
		final Expr<Object> initExpr;
		final Var var;

		PutVar(Var var, Expr<Object> initExpr) {
			this.var = var;
			this.initExpr = initExpr;
		}

		public void compile(CompilerBase compiler) {
			compiler.putVar(var, initExpr);
		}

		@Override
		public void scan(CompilerContext context) {
			initExpr.scan(context);
		}

		@Override
		public String toString() {
			return var.name + " = " + initExpr.toString() + ";";
		}
	}

	static class Range extends AbstractRange {
		final Expr<Object> from;
		final Expr<Object> to;

		Range(final Expr<Object> from, final Expr<Object> to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.makeRange_From_To(from, to);
		}

		@Override
		public void scan(CompilerContext context) {
			from.scan(context);
			Preconditions.checkArgument(from.getType().getRawType() == RawTypes.Long);

			to.scan(context);
			Preconditions.checkArgument(to.getType().getRawType() == RawTypes.Long);
		}
	}

	static class Range_0_To extends AbstractRange {
		final Expr<Object> to;

		Range_0_To(final Expr<Object> index) {
			this.to = index;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.makeRange_0_To(to);
		}

		@Override
		public void scan(CompilerContext context) {
			to.scan(context);
		}
	}

	static class Range_From_ extends AbstractRange {
		final Expr<Object> from;

		Range_From_(final Expr<Object> index) {
			this.from = index;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.makeRange_From(from);
		}

		@Override
		public void scan(CompilerContext context) {
			from.scan(context);
			Preconditions.checkArgument(from.getType().getRawType() == RawTypes.Long);
		}
	}

	static class RangeSingle extends AbstractRange {
		final Expr<Object> value;

		RangeSingle(final Expr<Object> index) {
			this.value = index;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.makeRangeIndex(value);
		}

		@Override
		public void scan(CompilerContext context) {
			this.value.scan(context);
		}

	}

	/**
	 * An addition expression.
	 */
	static class Relational<V extends Comparable<V>> extends Expression<Boolean> implements Expr<Boolean> {
		final Expr<V> e1;
		final Expr<V> e2;
		final Operator op;

		Relational(final Operator op, Expr<V> e1, Expr<V> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.relational(op, e1, e2);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.BOOLEAN;
		}

		@Override
		public void scan(CompilerContext context) {
			e1.scan(context);
			e2.scan(context);
			// Preconditions.checkArgument(e1.getType() ==
			// BootstrapTypeLoader.BOOLEAN);
			// Preconditions.checkArgument(e2.getType() ==
			// BootstrapTypeLoader.BOOLEAN);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public void compile(CompilerBase compiler) {
			compiler.constString(value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.STRING;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return value;
		}
	}

	abstract static class Tempral<T> extends Expression<T> {
		final DateTimeFormatter formater;
		final long value;

		Tempral(DateTimeFormatter formater, String text) {
			this.formater = formater;
			this.value = formater.parseDateTime(text).getMillis();
		}

		Tempral(String text) {
			this(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		@Override
		public String toString() {
			return formater.print(value);
		}
	}

	static class TimeCst extends Tempral<DateTime> {
		TimeCst(String text) {
			super(DateTimeFormat.forPattern("HH:mm:ss"), text);
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.constTime(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.TIME;
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	static class TimestampCst extends Tempral<DateTime> {

		TimestampCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"), text);
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.constTimestamp(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.TIMESTAMP;
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	abstract static class UnaryArithmeticExpr extends Expression<Object> {
		final Expr<Object> e1;

		UnaryArithmeticExpr(final Expr<Object> e1) {
			this.e1 = e1;
		}

		@Override
		public Type getType() {
			return e1.getType();
		}
	}

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public void compile(CompilerBase compiler) {
			compiler.varRefer(var);
		}

		@Override
		public Type getType() {
			return var.type;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return var.name;
		}
	}

	static class YesnoCst extends Expression<Integer> {
		final int value;

		YesnoCst(boolean v) {
			if (v) this.value = 1;
			else this.value = 0;
		}

		public void compile(CompilerBase compiler) {
			compiler.constYesno(value);
		}

		@Override
		public Type getType() {
			// TODO Auto-generated method stub
			return BootstrapTypeLoader.BOOLEAN;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return value == 1 ? "Yes" : "No";
		}
	}

//	public final static int CONTEXT = 1;
//
	public final static int PARAMS = 4;
//
//	public final static int REPOS = 2;
//
//	public final static int SYSTEM_THIS = 0;
//
//	public final static int THIS = 3;

	CompilerContext context;

	EntityExpressionComplier exprCompiler = EntityExpressionComplier.DEFAULT;

	Log log = LogFactory.getLog(getClass());

	EntityActionComplier stCompiler = EntityActionComplier.DEFAULT;

	Compiler(CompilerContext context) {
		this.context = context;

	}

	public Action action(LinkedHashMap<String, Var> locals, Statement st) {
		return new Action(locals, st);
	}

	public <T> EntityExpression compile(Type type, String name, Expr<?> expr) {
		expr.scan(context);
		return exprCompiler.compile(type, name, expr);
	}

	public EntityAction compileToJavaBytecode(Type type, String name, Action action) {
		action.scan(context);
		return stCompiler.compile(context, type, name, action);
	}

	public Expr<Object> entities(Expr<Object> repos, String string) {
		return new DatastoreGet(repos, string);
	}

	public Expr<Object> index(Expr<Object> from) {
		return new RangeSingle(from);
	}

	public Expr<Object> list(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {
		return new ListFilterByClause(list, clause, params);
	}

	public Expr<Object> list(Expr<Object> list, List<Expr<Object>> ranges) {
		if (ranges.size() == 1 && ranges.get(0) instanceof RangeSingle) {
			return new ListGetItemByIndexExpr(list, (RangeSingle) ranges.get(0));
		} else {
			return new ListFilterByRanger(list, ranges);
		}
	}

	public Expr<Object> opArithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		return new Arithmetic(op, e1, e2);
	}

	public Expr<Boolean> opConditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Conditional(op, e1, e2);
	}

	public Expr<DateTime> opDateCst(String value) {
		return new DateCst(value);
	}

	public Expr<DateTime> opDatetimeCst(String value) {
		return new DatetimeCst(value);
	}

	public Expr<BigDecimal> opDecimalCst(String value) {
		return new DecimalCst(value);
	}

	public Expr<Object> opDecrement(Expr<Object> e1) {
		return new Decrement(e1);
	}

	public Expr<Object> opFieldInList(Expr<Object> list, String name) {
		return new FieldOf(new ListThis(list), name);
	}

	public Expr<Object> opFieldOf(Expr<Object> e1, String name) {
		return new FieldOf(e1, name);
	}

	public Expr<Object> opIncrement(Expr<Object> e1) {
		return new Increment(e1);
	}

	// Type resolveType(Type type, String name) {
	// for (Field f : type.getFields()) {
	// if (f.name.equals(name)) {
	// return f.getType();
	// }
	// }
	// throw new RuntimeException("Cannot find field");
	// }

	public Expr<Object> opLocal(Var var) {
		return new VarRefer(var);
	}

	public Expr<Long> opLongCst(String value) {
		return new LongCst(value);
	}

	public Expr<Object> opMethodCall(Expr<Entity> e1, String name) {
		return new MethodCall(e1, name);
	}

	public Expr<Object> opNegates(Expr<Object> e1) {
		return new Negates(e1);
	}

	public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	public Expr<Object> opParamsl(Expr<Object> e1, int i) {
		return new ParamsRefer(e1, PARAMS, i);
	}

	public Expr<Object> opPositive(Expr<Object> e1) {
		return new Positive(e1);
	}

	public <V extends Comparable<V>> Expr<Boolean> opRelational(Operator op, Expr<V> e1, Expr<V> e2) {
		return new Relational<V>(op, e1, e2);
	}

	public Expr<String> opStringCst(String value) {
		return new StringCst(value);
	}

	public Expr<DateTime> opTimeCst(String value) {
		return new TimeCst(value);
	}

	public Expr<DateTime> opTimestampCst(String value) {
		return new TimestampCst(value);
	}

	public Expr<Object> opUnit(Expr<Object> v, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Expr<Integer> opYesnoCst(boolean b) {
		return new YesnoCst(b);
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

	public Statement stBlock(List<Statement> statements) {
		return new Block(statements);
	}

	public Statement stCall(Expr<Object> e1) {
		return new CallStatment(e1);
	}

	public Statement stPut(Expr<Object> field, Expr<Object> value) {
		return new PutField((FieldOf) field, value);
	}

	public Statement stPutVar(Var var, Expr<Object> initExpr) {
		return new PutVar(var, initExpr);
	}

}

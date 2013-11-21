package nebula.lang;

import java.math.BigDecimal;
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
	Log log = LogFactory.getLog(getClass());
	public final static int SYSTEM_THIS = 0;
	public final static int CONTEXT = 1;
	public final static int REPOS = 2;
	public final static int THIS = 3;
	public final static int PARAMS = 4;

	CompilerContext context;

	Compiler(CompilerContext context) {
		this.context = context;

	}

	EntityExpressionComplier exprCompiler = EntityExpressionComplier.DEFAULT;
	EntityActionComplier stCompiler = EntityActionComplier.DEFAULT;

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

	public <T> EntityExpression compile(Type type, String name, Expr<T> expr) {
		expr.scan(context);
		return exprCompiler.compile(type, name, expr);
	}

	public EntityAction compile(Type type, String name, Statement statement) {
		statement.scan(context);
		return stCompiler.compile(context, type, name, statement);
	}

	public Expr<Boolean> opConditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
		return new Conditional(op, e1, e2);
	}

	public Expr<Boolean> opNot(Expr<Boolean> e1) {
		return new Not(e1);
	}

	public <V extends Comparable<V>> Expr<Boolean> opRelational(Operator op, Expr<V> e1, Expr<V> e2) {
		return new Relational<V>(op, e1, e2);
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
		return new RangeSingle(from);
	}

	public Expr<Object> entities(Expr<Object> repos, String string) {
		return new DatastoreGet(repos, string);
	}

	public Expr<Object> list(Expr<Object> list, List<Expr<Object>> ranges) {
		if (ranges.size() == 1 && ranges.get(0) instanceof RangeSingle) {
			return new ListGetItemByIndexExpr(list, (RangeSingle) ranges.get(0));
		} else {
			return new ListFilterByRanger(list, ranges);
		}
	}

	public Expr<Object> list(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {
		return new ListFilterByClause(list, clause, params);
	}

	public Expr<Object> opUnit(Expr<Object> v, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Expr<Object> opFieldInList(Expr<Object> list, String name) {
		return new FieldOf(new ListGetItemByIndex(list, THIS), name);
	}

	public Expr<Object> opArithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		return new Arithmetic(op, e1, e2);
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

	public Expr<Integer> opYesnoCst(boolean b) {
		return new YesnoCst(b);
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

	public Expr<Object> opParamsl(Expr<Object> e1, int i) {
		return new ParamsRefer(e1, PARAMS, i);
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
		public Type getType() {
			return BootstrapTypeLoader.BOOLEAN;
		}
	}

	/**
	 * A logical "and" expression.
	 */
	static class Conditional extends LogicExpr {
		final Operator op;
		final Expr<Boolean> e1;
		final Expr<Boolean> e2;

		public Conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		public void compile(AsmCompiler compiler) {
			compiler.conditional(op, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {
			e1.scan(context);
			e2.scan(context);
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

		public void compile(AsmCompiler compiler) {
			compiler.not(e1);
		}

		@Override
		public String toString() {
			return "(!" + e1.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {
			Preconditions.checkArgument(e1.getType() == BootstrapTypeLoader.BOOLEAN);
		}
	}

	/**
	 * An addition expression.
	 */
	static class Relational<V extends Comparable<V>> extends Expression<Boolean> implements Expr<Boolean> {
		final Operator op;
		final Expr<V> e1;
		final Expr<V> e2;

		Relational(final Operator op, Expr<V> e1, Expr<V> e2) {
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.relational(op, e1, e2);
		}

		@Override
		public String toString() {
			return "(" + e1.toString() + " " + op.getSign() + " " + e2.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {
			e1.scan(context);
			e2.scan(context);
//			Preconditions.checkArgument(e1.getType() == BootstrapTypeLoader.BOOLEAN);
//			Preconditions.checkArgument(e2.getType() == BootstrapTypeLoader.BOOLEAN);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.BOOLEAN;
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

		public void compile(AsmCompiler compiler) {
			compiler.arithmetic(op, e1, e2);
		}

		@Override
		public Long eval() {
			return (Long) e1.eval() + (Long) e2.eval();
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

		@Override
		public Type getType() {
			return e1.getType();
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

	static class Increment extends UnaryArithmeticExpr {
		Increment(Expr<Object> e1) {
			super(e1);
		}

		public void compile(AsmCompiler compiler) {
			compiler.increment(e1);
		}

		@Override
		public String toString() {
			return "(++" + e1.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {

		}
	}

	static class Decrement extends UnaryArithmeticExpr {
		Decrement(Expr<Object> e1) {
			super(e1);
		}

		public void compile(AsmCompiler compiler) {
			compiler.decrement(e1);
		}

		@Override
		public String toString() {
			return "(--" + e1.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	static class Positive extends UnaryArithmeticExpr {
		Positive(Expr<Object> e1) {
			super(e1);
		}

		public void compile(AsmCompiler compiler) {
			compiler.positive(e1);
		}

		@Override
		public String toString() {
			return "(+" + e1.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {

		}
	}

	static class Negates extends UnaryArithmeticExpr {
		Negates(Expr<Object> e1) {
			super(e1);
		}

		public void compile(AsmCompiler compiler) {
			compiler.negates(e1);
		}

		@Override
		public String toString() {
			return "(-" + e1.toString() + ")";
		}

		@Override
		public void scan(CompilerContext context) {
		}
	}

	static class StringCst extends Expression<String> {
		final String value;

		StringCst(String value) {
			this.value = value;
		}

		public void compile(AsmCompiler compiler) {
			compiler.constString(value);
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.STRING;
		}
	}

	static class DecimalCst extends Expression<BigDecimal> {
		final String text;

		DecimalCst(String text) {
			this.text = text;
		}

		public void compile(AsmCompiler compiler) {
			compiler.constDecimal(text);
		}

		@Override
		public String toString() {
			return text;
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DECIMAL;
		}
	}

	static class YesnoCst extends Expression<Integer> {
		final int value;

		YesnoCst(boolean v) {
			if (v) this.value = 1;
			else this.value = 0;
		}

		public void compile(AsmCompiler compiler) {
			compiler.constYesno(value);
		}

		@Override
		public String toString() {
			return value == 1 ? "Yes" : "No";
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			// TODO Auto-generated method stub
			return BootstrapTypeLoader.BOOLEAN;
		}
	}

	static class LongCst extends Expression<Long> {
		final Long value;

		LongCst(String text) {
			this.value = Long.parseLong(text);
		}

		public void compile(AsmCompiler compiler) {
			compiler.constLong(value);
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.LONG;
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
		public void scan(CompilerContext context) {
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.constTimestamp(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.TIMESTAMP;
		}
	}

	static class DatetimeCst extends Tempral<DateTime> {
		DatetimeCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"), text);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.constDatetime(super.value);
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DATETIME;
		}
	}

	static class DateCst extends Tempral<DateTime> {
		DateCst(String text) {
			super(DateTimeFormat.forPattern("yyyy-MM-dd"), text);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.constDate(super.value);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.DATE;
		}
	}

	static class TimeCst extends Tempral<DateTime> {
		TimeCst(String text) {
			super(DateTimeFormat.forPattern("HH:mm:ss"), text);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.constTime(super.value);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public Type getType() {
			return BootstrapTypeLoader.TIME;
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

		public void compile(AsmCompiler compiler) {
			compiler.callMethod(e1, name);
		}

		@Override
		public String toString() {
			return e1.toString() + "." + name.toString();
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
		public Type getType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	static class TypeRefer extends Expression<Object> {
		final String name;
		Type type;

		TypeRefer(final String name) {
			this.name = name;
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.typeRefer(name);
		}

		@Override
		public void scan(CompilerContext context) {
			type = context.resolveType("Type");
		}

		@Override
		public String toString() {
			return "$" + name;
		}

		@Override
		public Type getType() {
			return this.type;
		}
	}

	static abstract class AbstractRange extends Expression<Object> {
		@Override
		public Type getType() {
			throw new UnsupportedOperationException("Index");
		}
	}

	static class RangeSingle extends AbstractRange {
		final Expr<Object> value;

		RangeSingle(final Expr<Object> index) {
			this.value = index;
		}

		@Override
		public void scan(CompilerContext context) {
			this.value.scan(context);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.makeRangeIndex(value);
		}

	}

	static class Range_0_To extends AbstractRange {
		final Expr<Object> to;

		Range_0_To(final Expr<Object> index) {
			this.to = index;
		}

		@Override
		public void scan(CompilerContext context) {
			to.scan(context);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.makeRange_0_To(to);
		}
	}

	static class Range_From_ extends AbstractRange {
		final Expr<Object> from;

		Range_From_(final Expr<Object> index) {
			this.from = index;
		}

		@Override
		public void scan(CompilerContext context) {
			from.scan(context);
			Preconditions.checkArgument(from.getType().getRawType() == RawTypes.Long);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.makeRange_From(from);
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
		public void scan(CompilerContext context) {
			from.scan(context);
			Preconditions.checkArgument(from.getType().getRawType() == RawTypes.Long);

			to.scan(context);
			Preconditions.checkArgument(to.getType().getRawType() == RawTypes.Long);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.makeRange_From_To(from, to);
		}
	}

	static class ListFilterByClause extends Expression<Object> {
		final Expr<Object> list;
		final Expr<Object> clause;
		final List<Expr<Object>> params;

		Type type;

		ListFilterByClause(final Expr<Object> list, final Expr<Object> clause, final List<Expr<Object>> params) {
			this.list = list;
			this.clause = clause;
			this.params = params;
		}

		@Override
		public void scan(CompilerContext context) {
			list.scan(context);
			clause.scan(context);
			for (Expr<Object> param : params) {
				param.scan(context);
			}
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.listFilterByClause(list, clause, params);
		}

		@Override
		public Type getType() {
			return list.getType();
		}
	}

	static class ListGetItemByIndexExpr extends Expression<Object> {
		final Expr<Object> list;
		final RangeSingle index;
		Type type;

		ListGetItemByIndexExpr(final Expr<Object> list, final RangeSingle index) {
			this.list = list;
			this.index = index;
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

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.get(list, index.value);
		}

		@Override
		public Type getType() {
			return this.type;
		}
	}

	static class DatastoreGet extends Expression<Object> {
		final Expr<Object> repos;
		final String name;
		Type type;

		DatastoreGet(final Expr<Object> repos, final String name) {
			this.repos = repos;
			this.name = name;
		}

		@Override
		public void scan(CompilerContext context) {
			type = new ListType(context.resolveType(name));
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.datastoreGet(repos, name);
		}

		@Override
		public Type getType() {
			return this.type;
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
		public void scan(CompilerContext context) {
			list.scan(context);
			for (Expr<Object> expr : ranges) {
				expr.scan(context);
			}
			Preconditions.checkArgument(list.getType() instanceof ListType);
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.listFilter(list, ranges);
		}

		@Override
		public Type getType() {
			return list.getType();
		}
	}

	static class VarRefer extends Expression<Object> {
		final Var var;

		VarRefer(final Var var) {
			this.var = var;
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.varRefer(var.index);
		}

		@Override
		public void scan(CompilerContext context) {
		}

		@Override
		public String toString() {
			return var.name;
		}

		@Override
		public Type getType() {
			return var.type;
		}
	}

	static class ParamsRefer extends Expression<Object> {
		final Expr<Object> in;
		final int params;
		final int index;

		ParamsRefer(Expr<Object> in, int params, int index) {
			this.in = in;
			this.params = params;
			this.index = index;
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.paramsRefer(in, params, index);
		}

		@Override
		public void scan(CompilerContext context) {
			in.scan(context);
		}

		@Override
		public String toString() {
			return "params[" + String.valueOf(index) + "]";
		}

		@Override
		public Type getType() {
			return in.getType();
		}
	}

	static class ListGetItemByIndex extends Expression<Object> {
		final Expr<Object> list;
		final int index;
		Type type;

		ListGetItemByIndex(final Expr<Object> list, int index) {
			this.list = list;
			this.index = index;
		}

		@Override
		public void compile(AsmCompiler compiler) {
			compiler.listGetItem(list, index);
		}

		@Override
		public void scan(CompilerContext context) {
			Type listType = list.getType();
			Preconditions.checkArgument(listType instanceof ListType);
			type = ((ListType) list.getType()).getUnderlyType();
		}

		@Override
		public String toString() {
			return "this[" + index + "]";
		}

		@Override
		public Type getType() {
			return this.type;
		}
	}

	static class Block implements Opcodes, Statement {
		final List<Statement> statements;

		Block(List<Statement> statements) {
			this.statements = statements;
		}

		public void compile(AsmCompiler compiler) {
			compiler.block(statements);
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

		@Override
		public void scan(CompilerContext context) {
			for (Statement st : statements) {
				st.scan(context);
			}			
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

		public void compile(AsmCompiler compiler) {
			compiler.putVar(var, initExpr);
		}

		@Override
		public String toString() {
			return var.name + " = " + initExpr.toString() + ";";
		}

		@Override
		public void scan(CompilerContext context) {
			initExpr.scan(context);			
		}
	}

	static class FieldOf extends Expression<Object> {
		final Expr<Object> entity;
		final String name;
		Type fieldType;

		FieldOf(Expr<Object> entity, String name) {
			this.entity = entity;
			this.name = name;
		}

		public void compile(AsmCompiler compiler) {
			compiler.getField(entity, name, fieldType);
		}

		@Override
		public String toString() {
			return entity.toString() + "." + name.toString();
		}

		@Override
		public void scan(CompilerContext context) {
			entity.scan(context);
			Type type = entity.getType();
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					this.fieldType = f.getType();
				}
			}
		}

		@Override
		public Type getType() {
			return fieldType;
		}
	}

	static class PutField extends Expression<Object> implements Opcodes, Statement {
		final Expr<Object> entity;
		final Expr<Object> value;
		final String name;
		Type fieldType;

		PutField(FieldOf field, Expr<Object> value) {
			this.entity = field.entity;
			this.name = field.name;
			this.value = value;
		}

		public void compile(AsmCompiler compiler) {
			compiler.setField(entity, name, fieldType, value);
		}

		@Override
		public void scan(CompilerContext context) {
			entity.scan(context);
			Type type = entity.getType();
			for (Field f : type.getFields()) {
				if (f.name.equals(name)) {
					this.fieldType = f.getType();
				}
			}
			value.scan(context);
		}

		@Override
		public String toString() {
			return entity.toString() + "." + name.toString() + " = " + value.toString() + ";";
		}

		@Override
		public Type getType() {
			return fieldType;
		}
	}

	// TODO Not realized CallStatment
	static class CallStatment implements Opcodes, Statement {
		final Expr<Object> value;

		CallStatment(Expr<Object> value) {
			this.value = value;
		}

		public void compile(AsmCompiler compiler) {
			compiler.call(value);
		}

		@Override
		public String toString() {
			return value.toString() + ";";
		}

		@Override
		public void scan(CompilerContext context) {
			value.scan(context);			
		}
	}

}

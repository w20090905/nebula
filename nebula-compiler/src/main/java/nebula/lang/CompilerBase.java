package nebula.lang;

import java.util.List;

import nebula.data.Entity;

public interface CompilerBase {
	public void arithmetic(Operator op, Expr<Object> e1, Expr<Object> e2);

	public void block(List<Statement> statements);

	public <V> void relational(final Operator op, Expr<V> e1, Expr<V> e2);

	public void call(Expr<Object> value);

	public void callMethod(Expr<Entity> e1, String name);

	public void conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2);

	public void constDate(long value);

	public void constDatetime(long value);

	public void constDecimal(String text);

	public void constLong(Long value);

	public void constString(String value);

	public void constTime(long value);

	public void constTimestamp(long value);

	public void constYesno(int value);

	public void datastoreGet(Expr<Object> repos, String name);

	public void decrement(Expr<Object> e1);

	public void get(Expr<Object> list, Expr<Object> index);

	public void getField(Expr<Object> entity, String name, Type fieldType);

	public void increment(Expr<Object> e1);

	public void listFilter(Expr<Object> list, List<Expr<Object>> ranges);

	public void listFilterByClause(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params);

	public void listGetItem(Expr<Object> list, int index);

	public void makeRange_0_To(Expr<Object> to);

	public void makeRange_From(Expr<Object> from);

	public void makeRange_From_To(Expr<Object> from, Expr<Object> to);

	public void makeRangeIndex(Expr<Object> index);

	public void negates(Expr<Object> e1);

	public void not(Expr<Boolean> e1);

	public void paramsRefer(Expr<Object> in, int params, int index);

	public void positive(Expr<Object> e1);

	public void putVar(Var var, Expr<Object> initExpr);

	public void setField(Expr<Object> parent, String name, Type fieldType, Expr<Object> value);

	public void typeRefer(String name);

	public void varRefer(Var var);
}

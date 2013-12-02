package nebula.lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.data.Entity;

public class JsCompiler implements CompilerBase {

	StringBuilder sb;
	Map<String, String> locals;

	public JsCompiler(String... names) {
		sb = new StringBuilder();
		locals = new HashMap<String, String>();
		for (int i = 0; i < names.length; i += 2) {
			locals.put(names[i], names[i + 1]);
		}
	}

	public static String compiler(Code code, String... names) {
		JsCompiler c = new JsCompiler(names);
		code.compile(c);
		return c.sb.toString();
	}

	@Override
	public void arithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		sb.append('(');
		e1.compile(this);
		sb.append(op.getSign());
		e2.compile(this);
		sb.append(')');
	}

	@Override
	public void block(List<Statement> statements) {
		for (Statement statement : statements) {
			statement.compile(this);
		}
	}

	@Override
	public void call(Expr<Object> value) {

	}

	@Override
	public void callMethod(Expr<Entity> e1, String name) {

	}

	@Override
	public void conditional(Operator op, Expr<Boolean> e1, Expr<Boolean> e2) {
		sb.append('(');
		e1.compile(this);
		sb.append(op.getSign());
		e2.compile(this);
		sb.append(')');
	}

	@Override
	public void constDate(long value) {
	}

	@Override
	public void constDatetime(long value) {
	}

	@Override
	public void constDecimal(String text) {

	}

	@Override
	public void constLong(Long value) {
		sb.append(value);
	}

	@Override
	public void constString(String value) {
		sb.append("\"");
		sb.append(value);
		sb.append("\"");
	}

	@Override
	public void constTime(long value) {
	}

	@Override
	public void constTimestamp(long value) {
	}

	@Override
	public void constYesno(int value) {
		if (value == 0) {
			sb.append("false");
		} else {
			sb.append("true");
		}
	}

	@Override
	public void datastoreGet(Expr<Object> repos, String name) {
		sb.append("$p(");
		sb.append('"');
		sb.append(name);
		sb.append('"');
		sb.append(')');
	}

	@Override
	public void decrement(Expr<Object> e1) {
		sb.append('(');
		sb.append("--");
		e1.compile(this);
		sb.append(')');
	}

	@Override
	public void get(Expr<Object> list, Expr<Object> index) {
		list.compile(this);
		sb.append("[");
		index.compile(this);
		sb.append("]");
	}

	@Override
	public void getField(Expr<Object> entity, String name, Type fieldType) {
		entity.compile(this);
		sb.append('.');
		sb.append(name);
	}

	@Override
	public void increment(Expr<Object> e1) {
		sb.append('(');
		sb.append("++");
		e1.compile(this);
		sb.append(')');
	}

	@Override
	public void listFilter(Expr<Object> list, List<Expr<Object>> ranges) {
		sb.append("nebula.filter(");
		list.compile(this);
		for (int i = 0; i < ranges.size(); i++) {
			sb.append(",");
			ranges.get(i).compile(this);
		}
		sb.append(')');
	}

	@Override
	public void listFilterByClause(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {
		sb.append("nebula.filter(");
		list.compile(this);
		sb.append(",");
		clause.compile(this);
		for (int i = 0; i < params.size(); i++) {
			sb.append(",");
			params.get(i).compile(this);
		}
		sb.append(')');
	}

	@Override
	public void listGetItem(Expr<Object> list, int index) {
		list.compile(this);
		sb.append("[");
		sb.append(index);
		sb.append("]");
	}

	@Override
	public void makeRange_0_To(Expr<Object> to) {
		// TODO Auto-generated method stub
	}

	@Override
	public void makeRange_From(Expr<Object> from) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRange_From_To(Expr<Object> from, Expr<Object> to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRangeIndex(Expr<Object> index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void negates(Expr<Object> e1) {
		sb.append('(');
		sb.append('-');
		e1.compile(this);
		sb.append(')');
	}

	@Override
	public void not(Expr<Boolean> e1) {
		sb.append('(');
		sb.append('!');
		e1.compile(this);
		sb.append(')');
	}

	@Override
	public void paramsRefer(Expr<Object> in, int params, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void positive(Expr<Object> e1) {
		e1.compile(this);
	}

	@Override
	public void putVar(Var var, Expr<Object> initExpr) {
		// TODO
	}

	@Override
	public <V> void relational(Operator op, Expr<V> e1, Expr<V> e2) {
		sb.append('(');
		e1.compile(this);
		sb.append(op.getSign());
		e2.compile(this);
		sb.append(')');
	}

	@Override
	public void setField(Expr<Object> parent, String name, Type fieldType, Expr<Object> value) {
		parent.compile(this);
		sb.append('.');
		sb.append(name);
	}

	@Override
	public void varRefer(Var var) {
		sb.append(locals.get(var.name));
	}

	@Override
	public String toString() {
		return sb.toString();
	}

	@Override
	public void stIf(Expr<Object> expr, Statement stIf, Statement stElse) {
		sb.append("if(");
		expr.compile(this);
		sb.append(')');
		stIf.compile(this);
		sb.append("else");
		stElse.compile(this);
	}

	@Override
	public void stIf(Expr<Object> expr, Statement stIf) {
		sb.append("if(");
		expr.compile(this);
		sb.append(')');
		stIf.compile(this);
	}
}

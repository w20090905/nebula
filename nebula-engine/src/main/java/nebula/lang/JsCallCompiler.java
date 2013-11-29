package nebula.lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nebula.data.Entity;

public class JsCallCompiler extends JsCompiler {
	StringBuilder sbPut;
	Map<String, String> localsPutResult;
	Map<String, String> localsPutSet;
	Map<String, String> localsGetName;
	Map<String, String> localsGetValue;

	public JsCallCompiler(String... names) {
		sb = new StringBuilder();
		sbPut = new StringBuilder();
		locals = new HashMap<String, String>();
		for (int i = 0; i < names.length; i += 2) {
			locals.put(names[i], names[i + 1]);
		}
		localsPutResult = new HashMap<String, String>();
		localsPutResult.put("this", "result");
		localsPutSet = new HashMap<String, String>();
		localsPutSet.put("this", "$scope.data");

		localsGetName = new HashMap<String, String>();
		localsGetName.put("this", "data");
		localsGetValue = new HashMap<String, String>();
		localsGetValue.put("this", "$scope.data");
	}

	public static String compiler(String name, Code code, String... names) {
		JsCallCompiler c = new JsCallCompiler(names);
		return c.compile(name, code);
	}

	@Override
	public void setField(Expr<Object> parent, String name, Type fieldType, Expr<Object> value) {
		Map<String, String> localsBackup = locals;
		StringBuilder sbBackup = sb;
		sb = sbPut;
		isTopLevel = false;
		{
			locals = localsPutSet;
			{
				parent.compile(this);
				sb.append('.');
				sb.append(name);
			}
			sb.append('=');
			
			locals = localsPutResult;
			{
				parent.compile(this);
				sb.append('.');
				sb.append(name);
				sb.append(';');
			}
		}
		isTopLevel = true;
		sb = sbBackup;
		locals = localsBackup;
		
		value.compile(this);
	}

	@Override
	public void typeRefer(String name) {
	}

	@Override
	public void varRefer(Var var) {
		sb.append(locals.get(var.name));
	}

	boolean isTopLevel = true;

	@Override
	public void getField(Expr<Object> entity, String name, Type fieldType) {
		if (isTopLevel) {
			isTopLevel = false;

			Map<String, String> localsBackup = locals;
			locals = localsGetName;
			{
				sb.append('\'');
				entity.compile(this);
				sb.append('.');
				sb.append(name);
				sb.append('\'');
			}
			sb.append(':');
			locals = localsGetValue;
			{
				entity.compile(this);
				sb.append('.');
				sb.append(name);
			}
			sb.append(',');
			isTopLevel = true;
			locals = localsBackup;
		} else {
			entity.compile(this);
			sb.append('.');
			sb.append(name);
		}
	}

	@Override
	public void arithmetic(Operator op, Expr<Object> e1, Expr<Object> e2) {
		e1.compile(this);
		e2.compile(this);
	}

	@Override
	public void block(List<Statement> statements) {
		for (Statement s : statements) {
			s.compile(this);
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
		e1.compile(this);
		e2.compile(this);
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
	}

	@Override
	public void constString(String value) {
	}

	@Override
	public void constTime(long value) {
	}

	@Override
	public void constTimestamp(long value) {
	}

	@Override
	public void constYesno(int value) {
	}

	@Override
	public void datastoreGet(Expr<Object> repos, String name) {
	}

	@Override
	public void decrement(Expr<Object> e1) {
		e1.compile(this);
	}

	@Override
	public void get(Expr<Object> list, Expr<Object> index) {
	}

	@Override
	public void increment(Expr<Object> e1) {
		e1.compile(this);
	}

	@Override
	public void listFilter(Expr<Object> list, List<Expr<Object>> ranges) {

	}

	@Override
	public void listFilterByClause(Expr<Object> list, Expr<Object> clause, List<Expr<Object>> params) {

	}

	@Override
	public void listGetItem(Expr<Object> list, int index) {
	}

	@Override
	public void makeRange_0_To(Expr<Object> to) {
	}

	@Override
	public void makeRange_From(Expr<Object> from) {
	}

	@Override
	public void makeRange_From_To(Expr<Object> from, Expr<Object> to) {

	}

	@Override
	public void makeRangeIndex(Expr<Object> index) {
	}

	@Override
	public void negates(Expr<Object> e1) {
		e1.compile(this);
	}

	@Override
	public void not(Expr<Boolean> e1) {
		e1.compile(this);
	}

	@Override
	public void paramsRefer(Expr<Object> in, int params, int index) {

	}

	@Override
	public void positive(Expr<Object> e1) {
		e1.compile(this);
	}

	@Override
	public void putVar(Var var, Expr<Object> initExpr) {
		initExpr.compile(this);
	}

	@Override
	public <V> void relational(Operator op, Expr<V> e1, Expr<V> e2) {
		e1.compile(this);
		e2.compile(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String compile(String name, Code expr) {
		expr.compile(this);

		StringBuilder call = new StringBuilder();
		/*
		 * "$scope.$parent.$ajaxCall(" + "'$action' : 'data.Age'," +
		 * "{'data.Age':$scope.$parent.data.Age}," + "function($scope,result){"
		 * + "$scope.data.AgeDerived=result.AgeDerived;" + "});" + "}" + "});",
		 */
		call.append("$scope.$watch('" + name + "', function(newValue, oldValue) {\n");
		{
			call.append("if(newValue){");
			{
				call.append("$scope.$ajaxCall({");
				{
					call.append("'$action' : '" + name + "',");
					call.append(sb);
					call.setCharAt(call.length() - 1, '}');
					call.append(",function($scope,result){");
					call.append(sbPut);
				}
				call.append("});");
			}
			call.append("}");
		}
		call.append("\n});");
		return call.toString();
	}
}

package nebula.expr;

public abstract class QuickExprBuilder {
	protected Exp exp;

	protected abstract String getStringByName(String name);

	protected abstract Integer getIntegerByName(String name);

	public V<String> Fs(String name) {
		return new Field_String(name);
	}

	public V_Integer Fint(String name) {
		return new Field_Integer(name);
	}

	public V_String C(String value) {
		return new Cst_String(value);
	}

	public V<Integer> C(Integer value) {
		return new Cst_Integer(value);
	}

	public LogicExp Nop() {
		return new True();
	}

	class ExpImp implements Exp {
		@Override
		final public void finish() {
			QuickExprBuilder.this.exp = this;
		}
	}

	abstract class LogicExpImp extends ExpImp implements LogicExp {
		public LogicExp and(LogicExp v) {
			return new And(this, v);
		}

		public LogicExp or(LogicExp v) {
			return new Or(this, v);
		}

		public LogicExp not() {
			return new Not(this);
		}
	}

	class Cst_String extends V_StringImp {

		String value;

		Cst_String(final String value) {
			this.value = value;
		}

		public String exec() {
			return value;
		}
	}

	class Cst_Integer extends V_Integer {

		int value;

		Cst_Integer(final Integer value) {
			this.value = value;
		}

		public Integer exec() {
			return value;
		}
	}

	abstract class V_StringImp extends ExpImp implements V<String>, V_String {
		@Override
		public LogicExp gt(V<String> v) {
			return new Gt_String(this, v);
		}

		@Override
		public LogicExp ge(V<String> v) {
			return new Ge_String(this, v);
		}

		@Override
		public LogicExp lt(V<String> v) {
			return new Lt_String(this, v);
		}

		@Override
		public LogicExp le(V<String> v) {
			return new Le_String(this, v);
		}

		@Override
		public LogicExp eq(V<String> v) {
			return new Eq_String(this, v);
		}

		@Override
		public LogicExp ne(V<String> v) {
			return new Ne_String(this, v);
		}

		@Override
		public LogicExp gtF(String name) {
			return new Gt_String(this, new Field_String(name));
		}

		@Override
		public LogicExp geF(String name) {
			return new Ge_String(this, new Field_String(name));
		}

		@Override
		public LogicExp ltF(String name) {
			return new Lt_String(this, new Field_String(name));
		}

		@Override
		public LogicExp leF(String name) {
			return new Le_String(this, new Field_String(name));
		}

		@Override
		public LogicExp eqF(String name) {
			return new Eq_String(this, new Field_String(name));
		}

		@Override
		public LogicExp neF(String name) {
			return new Ne_String(this, new Field_String(name));
		}

		@Override
		public LogicExp gt(String v) {
			return new Gt_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp ge(String v) {
			return new Ge_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp lt(String v) {
			return new Lt_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp le(String v) {
			return new Le_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp eq(String v) {
			return new Eq_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp ne(String v) {
			return new Ne_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp inF(String name) {
			return new In_String(this, new Field_String(name));
		}

		@Override
		public LogicExp in(V<String> v) {
			return new In_String(this, v);
		}

		@Override
		public LogicExp in(String v) {
			return new In_String(this, new Cst_String(v));
		}
	}

	abstract class V_Integer extends ExpImp implements V<Integer> {
		@Override
		public LogicExp gt(V<Integer> v) {
			return new Gt_Integer(this, v);
		}

		@Override
		public LogicExp ge(V<Integer> v) {
			return new Ge_Integer(this, v);
		}

		@Override
		public LogicExp lt(V<Integer> v) {
			return new Lt_Integer(this, v);
		}

		@Override
		public LogicExp le(V<Integer> v) {
			return new Le_Integer(this, v);
		}

		@Override
		public LogicExp eq(V<Integer> v) {
			return new Eq_Integer(this, v);
		}

		@Override
		public LogicExp ne(V<Integer> v) {
			return new Ne_Integer(this, v);
		}

		@Override
		public LogicExp gtF(String name) {
			return new Gt_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp geF(String name) {
			return new Ge_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp ltF(String name) {
			return new Lt_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp leF(String name) {
			return new Le_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp eqF(String name) {
			return new Eq_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp neF(String name) {
			return new Ne_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp gt(Integer v) {
			return new Gt_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp ge(Integer v) {
			return new Ge_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp lt(Integer v) {
			return new Lt_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp le(Integer v) {
			return new Le_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp eq(Integer v) {
			return new Eq_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp ne(Integer v) {
			return new Ne_Integer(this, new Cst_Integer(v));
		}
	}

	private class Field_String extends V_StringImp implements Field<String> {

		String name;

		Field_String(final String name) {
			this.name = name;
		}

		@Override
		public String exec() {
			return QuickExprBuilder.this.getStringByName(name);
		}
	}

	private class Field_Integer extends V_Integer implements Field<Integer> {

		String name;

		Field_Integer(final String name) {
			this.name = name;
		}

		@Override
		public Integer exec() {
			return QuickExprBuilder.this.getIntegerByName(name);
		}
	}

	abstract class BinaryLogicCompareExp<T> extends LogicExpImp {
		V<T> e1;
		V<T> e2;

		BinaryLogicCompareExp(final V<T> e1, final V<T> e2) {
			this.e1 = e1;
			this.e2 = e2;
		}
	}

	abstract class BinaryLogicComputeExp extends LogicExpImp {

		LogicExp e1;

		LogicExp e2;

		BinaryLogicComputeExp(final LogicExp e1, final LogicExp e2) {
			this.e1 = e1;
			this.e2 = e2;
		}
	}

	class Gt_String extends BinaryLogicCompareExp<String> {

		Gt_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) > 0);
		}
	}

	class Ge_String extends BinaryLogicCompareExp<String> {

		Ge_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) >= 0);
		}
	}

	class Lt_String extends BinaryLogicCompareExp<String> {

		Lt_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) < 0);
		}
	}

	class Le_String extends BinaryLogicCompareExp<String> {

		Le_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) <= 0);
		}
	}

	class Eq_String extends BinaryLogicCompareExp<String> {

		Eq_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 != null && s2 != null && s1.compareTo(s2) == 0;
		}
	}

	class Ne_String extends BinaryLogicCompareExp<String> {

		Ne_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 != null && s2 != null && s1.compareTo(s2) != 0;
		}
	}

	class In_String extends BinaryLogicCompareExp<String> {
		In_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 != null && s2 != null && s2.indexOf(s1) >= 0;
		}
	}

	class Gt_Integer extends BinaryLogicCompareExp<Integer> {

		Gt_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() > e2.exec();
		}
	}

	class Ge_Integer extends BinaryLogicCompareExp<Integer> {

		Ge_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() >= e2.exec();
		}
	}

	class Lt_Integer extends BinaryLogicCompareExp<Integer> {

		Lt_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() < e2.exec();
		}
	}

	class Le_Integer extends BinaryLogicCompareExp<Integer> {

		Le_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() <= e2.exec();
		}
	}

	class Eq_Integer extends BinaryLogicCompareExp<Integer> {

		Eq_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() == e2.exec();
		}
	}

	class Ne_Integer extends BinaryLogicCompareExp<Integer> {

		Ne_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() != e2.exec();
		}
	}

	/**
	 * A logical "or" expression.
	 */
	class Or extends BinaryLogicComputeExp {

		Or(final LogicExp e1, final LogicExp e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() || e2.exec();
		}
	}

	class And extends BinaryLogicComputeExp {

		And(final LogicExp e1, final LogicExp e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() && e2.exec();
		}
	}

	class Not extends LogicExpImp {

		LogicExp e;

		Not(final LogicExp e) {
			this.e = e;
		}

		@Override
		public boolean exec() {
			return !e.exec();
		}

	}

	class True extends LogicExpImp {
		@Override
		public boolean exec() {
			return true;
		}

	}
}

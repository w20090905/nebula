package nebula.expr;

public abstract class QuickExprBilder {
	protected Exp exp;

	protected abstract String getStringByName(String name);

	protected abstract Integer getIntegerByName(String name);

	public V<String> Fs(String name) {
		return new Field_String(name);
	}

	public V<Integer> Fint(String name) {
		return new Field_Integer(name);
	}

	public V<String> C(String value) {
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
			QuickExprBilder.this.exp = this;
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

	class Cst_String extends V_String {

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

	abstract class V_String extends ExpImp implements V<String> {
		@Override
		public LogicExp gt(V<String> v) {
			return new Gt_String(this, v);
		}

		@Override
		public LogicExp ls(V<String> v) {
			return new Ls_String(this, v);
		}

		@Override
		public LogicExp eq(V<String> v) {
			return new Eq_String(this, v);
		}

		@Override
		public LogicExp gtF(String name) {
			return new Gt_String(this, new Field_String(name));
		}

		@Override
		public LogicExp lsF(String name) {
			return new Ls_String(this, new Field_String(name));
		}

		@Override
		public LogicExp eqF(String name) {
			return new Eq_String(this, new Field_String(name));
		}

		@Override
		public LogicExp gt(String v) {
			return new Gt_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp ls(String v) {
			return new Ls_String(this, new Cst_String(v));
		}

		@Override
		public LogicExp eq(String v) {
			return new Eq_String(this, new Cst_String(v));
		}
	}

	abstract class V_Integer extends ExpImp implements V<Integer> {
		@Override
		public LogicExp gt(V<Integer> v) {
			return new Gt_Integer(this, v);
		}

		@Override
		public LogicExp ls(V<Integer> v) {
			return new Ls_Integer(this, v);
		}

		@Override
		public LogicExp eq(V<Integer> v) {
			return new Eq_Integer(this, v);
		}

		@Override
		public LogicExp gtF(String name) {
			return new Gt_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp lsF(String name) {
			return new Ls_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp eqF(String name) {
			return new Eq_Integer(this, new Field_Integer(name));
		}

		@Override
		public LogicExp gt(Integer v) {
			return new Gt_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp ls(Integer v) {
			return new Ls_Integer(this, new Cst_Integer(v));
		}

		@Override
		public LogicExp eq(Integer v) {
			return new Eq_Integer(this, new Cst_Integer(v));
		}

	}

	private class Field_String extends V_String implements Field<String> {

		String name;

		Field_String(final String name) {
			this.name = name;
		}

		@Override
		public String exec() {
			return QuickExprBilder.this.getStringByName(name);
		}
	}

	private class Field_Integer extends V_Integer implements Field<Integer> {

		String name;

		Field_Integer(final String name) {
			this.name = name;
		}

		@Override
		public Integer exec() {
			return QuickExprBilder.this.getIntegerByName(name);
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

	/**
	 * A "greater than" expression.
	 */
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

	class Ls_String extends BinaryLogicCompareExp<String> {

		Ls_String(final V<String> e1, final V<String> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			String s1 = e1.exec();
			String s2 = e2.exec();
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) < 0);
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
			return s1 == s2 || (s1 != null && s2 != null && s1.compareTo(s2) == 0);
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

	class Ls_Integer extends BinaryLogicCompareExp<Integer> {

		Ls_Integer(final V<Integer> e1, final V<Integer> e2) {
			super(e1, e2);
		}

		@Override
		public boolean exec() {
			return e1.exec() < e2.exec();
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

package nebula.lang;

import junit.framework.TestCase;

public class EntityActionComplierTest extends TestCase {
	TypeLoaderForTest loader;
	EntityActionComplier funcCmp;
	Compiler cp;
	Context context;

	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest(new SystemTypeLoader());
		funcCmp = new EntityActionComplier();
		context = new Context() {

			@Override
			public Type resolveType(String name) {
				return loader.findType(name);
			}
		};

		cp = new Compiler(context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDoCompile() {

		Code code = cp.opRelational(Operator.EQ, cp.opLongCst("10"), cp.opLongCst("100"));

		EntityAction action = funcCmp.compile(code, null, context);

	}
}

package nebula.lang;

import junit.framework.TestCase;
import nebula.data.Entity;

public class EntityFuncitonComplierTest extends TestCase {
	TypeLoaderForTest loader;
	EntityClauseComplier funcCmp;
	Compiler cp;
	CompilerContext context;

	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest(new SystemTypeLoader());
		funcCmp = EntityClauseComplier.DEFAULT;
		context = new CompilerContext() {

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

		Expr<Boolean> code = cp.opRelational(Operator.EQ, cp.opLongCst("10"), cp.opLongCst("100"));
		Type type = new TypeImp(loader, "test");
		code.scan(context);
		String name = funcCmp.compile(type, code);
		try {
			Class<?> clz = NebulaClassLoader.getInstance().loadClass(name);

			@SuppressWarnings("unchecked")
			Clause<Entity> func = (Clause<Entity>) clz.newInstance();
			assertFalse(func.apply(null, null, null));
			
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}


}

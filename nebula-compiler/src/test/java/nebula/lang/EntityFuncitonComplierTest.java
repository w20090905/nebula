package nebula.lang;

import nebula.data.Entity;

import com.google.common.base.Function;

import junit.framework.TestCase;

public class EntityFuncitonComplierTest extends TestCase {
	TypeLoaderForTest loader;
	EntityClauseComplier funcCmp;
	Compiler cp;
	Context context;

	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest(new SystemTypeLoader());
		funcCmp = new EntityClauseComplier();
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

		String name = funcCmp.compile(code, context);
		try {
			Class<?> clz = NebulaClassLoader.getInstance().loadClass(name);

			@SuppressWarnings("unchecked")
			Clause<Entity> func = (Clause<Entity>) clz.newInstance();
			assertFalse(func.apply(null));
			
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

//	public final void testCompile() {
//		fail("Not yet implemented"); // TODO
//	}

}

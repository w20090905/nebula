package nebula.simpletemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class testMethodCallPerfomance extends TestCase {
	public void testMethodCall() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		p.setName("wangshilian");
		Method m = p.getClass().getMethod("getName");

		String name = (String) m.invoke(p);
		assertEquals("wangshilian", name);
		int MAX = 1000 * 100;
		{
			String desc = "invoke";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				name = (String) m.invoke(p);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "get";
			// setUp

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				name = p.getName();
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "call";
			// setUp

			Map<String, Call> calls = new HashMap<String, testMethodCallPerfomance.Call>();
			Call call = new Call() {
				@Override
				public Object call(Object obj) {
					return ((Person) obj).getName();
				}
			};

			calls.put("name", call);
			calls.put("dsf", call);
			calls.put("sadfdsfsa", call);
			calls.put("dd", call);
			calls.put("asdf", call);
			calls.put("xcs", call);
			calls.put("sadsad", call);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				name = (String) call.call(p);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "map get";
			// setUp

			Map<String, Call> calls = new HashMap<String, testMethodCallPerfomance.Call>();
			Call call = new Call() {
				@Override
				public Object call(Object obj) {
					return ((Person) obj).getName();
				}
			};

			calls.put("name", call);
			calls.put("dsf", call);
			calls.put("sadfdsfsa", call);
			calls.put("dd", call);
			calls.put("asdf", call);
			calls.put("xcs", call);
			calls.put("sadsad", call);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				call = calls.get("name");
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
		{
			String desc = "map call";
			// setUp

			Map<String, Call> calls = new HashMap<String, testMethodCallPerfomance.Call>();
			Call call = new Call() {
				@Override
				public Object call(Object obj) {
					return ((Person) obj).getName();
				}
			};

			calls.put("name", call);
			calls.put("dsf", call);
			calls.put("sadfdsfsa", call);
			calls.put("dd", call);
			calls.put("asdf", call);
			calls.put("xcs", call);
			calls.put("sadsad", call);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				name = (String) calls.get("name").call(p);
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}

		{
			String desc = "nop";
			// setUp

			Map<String, Call> calls = new HashMap<String, testMethodCallPerfomance.Call>();
			Call call = new Call() {
				@Override
				public final Object call(Object obj) {
					return ((Person) obj).getName();
				}
			};

			calls.put("name", call);
			calls.put("dsf", call);
			calls.put("sadfdsfsa", call);
			calls.put("dd", call);
			calls.put("asdf", call);
			calls.put("xcs", call);
			calls.put("sadsad", call);

			// prepare
			long start, end, nanoAll, nanoEvery;

			start = System.nanoTime();
			for (int i = 0; i < MAX; i++) {
				i = i + 1;
				i = i - 1;
			}
			end = System.nanoTime();
			nanoAll = end - start;
			nanoEvery = nanoAll / MAX;

			System.out.printf("[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n", desc, (nanoAll / (1000 * 1000)), +nanoEvery,
					1000 * 1000 * 1000 / nanoEvery);
		}
	}

	interface Call {
		Object call(Object obj);
	}
}

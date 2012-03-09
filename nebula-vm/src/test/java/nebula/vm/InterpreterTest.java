package nebula.vm;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InterpreterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInterpreter() {
		fail("Not yet implemented");
	}

	@Test
	public void testInterpreterBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain_loop_asm() throws Exception {
		Interpreter.main(new String[] { "code/loop.asm" });
	}

	@Test
	public void testMain_T_asm() throws Exception {
		Interpreter.main(new String[] { "code/T.asm" });
	}

	@Test
	public void testMain_input_n() throws Exception {
		Interpreter.main(new String[] { "code/input.n" });
	}

	@Test
	public void testResolveClassSymbol() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	public void testExec() {
		fail("Not yet implemented");
	}

	@Test
	public void testResolveFunctionSymbol() {
		fail("Not yet implemented");
	}

}

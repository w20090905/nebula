package nebula.data.db;

import nebula.lang.RawTypes;
import junit.framework.TestCase;

public class DbColumnTest extends TestCase {
	DbColumn c;
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDbColumn() {
		c = new DbColumn(getName(), false, false, false, RawTypes.Boolean, 0, 0, 0, 0, getName());
	}

}

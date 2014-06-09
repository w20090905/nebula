package nebula.data.db;

import junit.framework.TestCase;
import nebula.lang.RawTypes;

public class RealDBTypeDefineBuilderTest extends TestCase {
	RealDBTypeDefineBuilder b;

	protected void setUp() throws Exception {
		b = new RealDBTypeDefineBuilder();
	}

	protected void tearDown() throws Exception {
		b = null;
	}

	public final void testBuildRawTypes() {
		b.register(RawTypes.Boolean, "smallint");// .BIGINT
		b.register(RawTypes.Long, "bigint");// .BIGINT
		b.register(RawTypes.Date, "date");
		b.register(RawTypes.Time, "time");
		b.register(RawTypes.Datetime, "timestamp");
		b.register(RawTypes.Timestamp, "timestamp");

		assertEquals("smallint", b.build(RawTypes.Boolean));
		assertEquals("bigint", b.build(RawTypes.Long));
		assertEquals("date", b.build(RawTypes.Date));
		assertEquals("time", b.build(RawTypes.Time));
		assertEquals("timestamp", b.build(RawTypes.Datetime));
		assertEquals("timestamp", b.build(RawTypes.Timestamp));
	}

	public final void testBuildRawTypesLongIntInt() {
		b.register(RawTypes.Decimal, "numeric($p,$s)");
		b.register(RawTypes.String, "varchar($l)");
		b.register(RawTypes.Text, "varchar($l)");
		
		assertEquals("numeric(10,2)", b.build(RawTypes.Decimal, 1, 10, 2));
		assertEquals("varchar(100)", b.build(RawTypes.String, 100, 0, 0));
		assertEquals("varchar(1000)", b.build(RawTypes.Text, 1000, 0, 0));
	}

//	public final void testRegisterRawTypesLongString() {
//		b.register(RawTypes.String, 100,"char");
//		b.register(RawTypes.String,4000,"varchar($l)");
//		b.register(RawTypes.String,"blob($l)");
//
//		assertEquals("char", b.build(RawTypes.String, 100, 0, 0));
//		assertEquals("varchar(1000)", b.build(RawTypes.String, 1000, 0, 0));
//		assertEquals("varchar(4000)", b.build(RawTypes.String, 4000, 0, 0));
//		assertEquals("blob(5000)", b.build(RawTypes.String, 5000, 0, 0));
//	}
//	
	public final void testRegisterRawTypesLongString_null() {
		b.register(RawTypes.String,"blob($l)");

		assertEquals("blob(100)", b.build(RawTypes.String, 100, 0, 0));
		assertEquals("blob(1000)", b.build(RawTypes.String, 1000, 0, 0));
		assertEquals("blob(4000)", b.build(RawTypes.String, 4000, 0, 0));
		assertEquals("blob(5000)", b.build(RawTypes.String, 5000, 0, 0));
	}

	public final void testRegisterRawTypesString() {
		b.register(RawTypes.Boolean, "smallint");// .BIGINT
		b.register(RawTypes.Long, "bigint");// .BIGINT
		b.register(RawTypes.Decimal, "numeric($p,$s)");
		b.register(RawTypes.String, "varchar($l)");
		b.register(RawTypes.Text, "varchar($l)");
		b.register(RawTypes.Date, "date");
		b.register(RawTypes.Time, "time");
		b.register(RawTypes.Datetime, "timestamp");
		b.register(RawTypes.Timestamp, "timestamp");
	}

}

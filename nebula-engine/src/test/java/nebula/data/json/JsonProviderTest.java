package nebula.data.json;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataRepos;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.DefaultDataRepos;
import nebula.data.impl.TypeDatastore;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JsonProviderTest extends TestCase {
	TypeLoaderForTest typeload;
	TypeDatastore typeBrokers;
	Broker<DataStore<Entity>> store;

	DataRepos persistence;

	protected void setUp() throws Exception {
		typeload = new TypeLoaderForTest();
		typeBrokers = new TypeDatastore(typeload);
		persistence = new DefaultDataRepos(typeBrokers);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPerson() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"	Age;" +
				"	Decimal;" +
				"	Date;" +
				"	Time;" +
				"	Datetime;" +
				"	Timestamp;" +
				"};";
		//@formatter:on		

		final Type type;
		type = typeload.testDefineNebula(new StringReader(text)).get(0);
		// store = persistence.define(String.class,Entity.class,
		// type.getName());

		DataHelper<Entity, Reader, Writer> json = JsonHelperProvider.getHelper(typeBrokers.getBroker(type.getName()));
		Entity n = new EditableEntity();

		n = json.readFrom(
				n,
				new StringReader("{" + "	\"Name\"		:\"wangshilian\",	" + "	\"Age\"			:12,					"
						+ "	\"Decimal\"		:9876.5432,	" + "	\"Date\"		:\"2012-12-20\",	" + "	\"Time\"		:\"12:00:12\",	"
						+ "	\"Datetime\"	:\"2012-12-20 23:58:59\",	" + "	\"Timestamp\"	:\"2012-12-20 23:58:59.789\"	"
						+ "}"));

		assertEquals("wangshilian", n.get("Name"));
		assertEquals(12L, n.get("Age"));

		assertEquals(new BigDecimal("9876.5432"), n.get("Decimal"));

		DateTimeFormatter sdf;
		sdf = DateTimeFormat.forPattern("yyyy-MM-dd");
		assertEquals(sdf.parseDateTime("2012-12-20"), n.get("Date"));

		sdf = DateTimeFormat.forPattern("kk:mm:ss");
		assertEquals(sdf.parseDateTime("12:00:12"), n.get("Time"));

		sdf = DateTimeFormat.forPattern("yyyy-MM-dd kk:mm:ss");
		assertEquals(sdf.parseDateTime("2012-12-20 23:58:59"), n.get("Datetime"));

		sdf = DateTimeFormat.forPattern("yyyy-MM-dd kk:mm:ss.SSS");
		assertEquals(sdf.parseDateTime("2012-12-20 23:58:59.789").getMillis(), n.get("Timestamp"));

		Writer out = new StringWriter();
		json.stringifyTo(n, out);
		assertEquals("{\"Name\":\"wangshilian\"," + "\"Age\":12,\"Decimal\":9876.5432," + "\"Date\":\"2012-12-20\","
				+ "\"Time\":\"12:00:12\"," + "\"Datetime\":\"2012-12-20 23:58:59\","
				+ "\"Timestamp\":\"2012-12-20 23:58:59.789\"}", out.toString());
	}
}

package http.json;

import http.json.JsonProvider.JsonDealer;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.Persistence;
import nebula.data.Store;
import nebula.data.impl.PersistenceMem;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;

public class JsonProviderTest extends TestCase {
	TypeLoader loader;
	Type t;
	Store<Entity> store;
	
	Persistence<Entity> persistence;
	
	
	protected void setUp() throws Exception {
		loader = new SystemTypeLoader();
		persistence = new PersistenceMem(loader);
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

		t = loader.defineNebula(new StringReader(text)).get(0);
		store = persistence.define(Entity.class, t.getName());
		
		JsonDealer<Entity> json =   JsonProvider.getSerialize(t);
		Entity n = store.createNew();		
		
		json.readFrom(n,new StringReader("{" +
				"	\"Name\"		:\"wangshilian\",	" +
				"	\"Age\"			:12,					" +
				"	\"Decimal\"		:9876.5432,	" +
				"	\"Date\"		:\"2012-12-20\",	" +
				"	\"Time\"		:\"12:00:12\",	" +
				"	\"Datetime\"	:\"2012-12-20 23:58:59\",	" +
				"	\"Timestamp\"	:\"2012-12-20 23:58:59.789\"	" +
				"}"));
		
		assertEquals("wangshilian", n.get("Name"));
		assertEquals(12L, n.get("Age"));

		assertEquals(new BigDecimal("9876.5432"), n.get("Decimal"));
		
		SimpleDateFormat sdf ;
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(sdf.parseObject("2012-12-20"), n.get("Date"));
		
		sdf = new SimpleDateFormat("kk:mm:ss");
		assertEquals(sdf.parseObject("12:00:12"), n.get("Time"));
		
		sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		assertEquals(sdf.parseObject("2012-12-20 23:58:59"), n.get("Datetime"));
		
		sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");
		assertEquals(sdf.parseObject("2012-12-20 23:58:59.789"), n.get("Timestamp"));
		
		Writer out = new StringWriter();
		json.stringifyTo(n, out);
		assertEquals("{\"Name\":\"wangshilian\"," +
				"\"Age\":12,\"Decimal\":9876.5432," +
				"\"Date\":\"2012-12-20\"," +
				"\"Time\":\"12:00:12\"," +
				"\"Datetime\":\"2012-12-20 23:58:59\"," +
				"\"Timestamp\":\"2012-12-20 23:58:59.789\"}", out.toString());
	}
}

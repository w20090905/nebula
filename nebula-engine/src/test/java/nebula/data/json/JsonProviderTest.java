package nebula.data.json;


import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;
import nebula.data.DataHolder;
import nebula.data.DataPersister;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.impl.EditableEntity;
import nebula.data.impl.InMemoryDataPersister;
import nebula.lang.Type;
import nebula.lang.TypeLoaderForTest;

public class JsonProviderTest extends TestCase {
	TypeLoaderForTest loader;
	Type t;
	DataHolder<DataStore<Entity>> store;
	
	DataPersister<Entity> persistence;
	
	
	protected void setUp() throws Exception {
		loader = new TypeLoaderForTest();
		persistence = new InMemoryDataPersister(loader);
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

		t = loader.testDefineNebula(new StringReader(text)).get(0);
		store = persistence.define(Entity.class, t.getName());
		
		DataHolder<DataHelper<Entity,Reader,Writer>> json =   JsonHelperProvider.getHelper(store);
		Entity n =  new EditableEntity();
		
		n=json.get().readFrom(n,new StringReader("{" +
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
		json.get().stringifyTo(n, out);
		assertEquals("{\"Name\":\"wangshilian\"," +
				"\"Age\":12,\"Decimal\":9876.5432," +
				"\"Date\":\"2012-12-20\"," +
				"\"Time\":\"12:00:12\"," +
				"\"Datetime\":\"2012-12-20 23:58:59\"," +
				"\"Timestamp\":\"2012-12-20 23:58:59.789\"}", out.toString());
	}
}

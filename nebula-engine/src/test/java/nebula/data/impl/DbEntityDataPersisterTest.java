package nebula.data.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.lang.SystemTypeLoader;

public class DbEntityDataPersisterTest extends TestCase {

	DbDataRepos p;
	DataStore<Entity> store;
	DbConfiguration dbconfig;

	protected void setUp() throws Exception {
		String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:memory:eh;create = true";
		String username = "user";
		String password = "password";

		dbconfig = DbConfiguration.getEngine(driverclass, url, username, password);
		p = new DbDataRepos(new TypeDatastore(new SystemTypeLoader()), dbconfig);

		store = p.define(String.class, Entity.class, "Person").get();
		store.clearChanges();
	}

	protected void tearDown() throws Exception {
		p.unload();
		dbconfig.shutdown();
		super.tearDown();
	}

	public final void testDefine() {
		assertNotNull(store);
//		assertEquals("Person", store.getID());

		Entity v = new EditableEntity();
		assertNotNull(v);

		v.put("Name", "wangshilian");

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		p.flush();

		assertEquals(true, v.isDirty());
		// assertEquals("wangshilian", v.get("ID"));
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		store.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals("wangshilian", store.get("wangshilian").getID());

		v.put("Height", 120L);

		assertEquals(true, v.isDirty());
		assertEquals(120L, v.get("Height"));
		assertEquals(null, store.get("wangshilian").get("Height"));

		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals(120L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		v.put("Height", 180L);

		assertEquals(true, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		p.clearChanges();
		p.flush();

		assertEquals(true, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(120L, store.get("wangshilian").get("Height"));

		store.add(v);
		store.flush();

		assertEquals(false, v.isDirty());
		assertEquals(180L, v.get("Height"));
		assertEquals(180L, store.get("wangshilian").get("Height"));
	}

	

	public final void testEntityList() {
		assertNotNull(store);
//		assertEquals("Person", store.getID());

		Entity v = new EditableEntity();
		assertNotNull(v);

		v.put("Name", "wangshilian");
		/**
		 * Education[..5]{
				DateFrom Date;
				DateTo Date;
				School Text;
			};
		 */
		List<EditableEntity> entities = new ArrayList<EditableEntity>();
		EditableEntity education = new EditableEntity();
		education.put("School", "kunming");
		education.put("DateFrom",Date.valueOf("1996-09-01"));
		education.put("DateTo", Date.valueOf("2000-07-01"));
		entities.add(education);
		
		education = new EditableEntity();
		education.put("School", "fuyang");
		education.put("DateFrom",Date.valueOf("1993-09-01"));
		education.put("DateTo", Date.valueOf("1996-07-01"));
		entities.add(education);
		
		v.put("Education", entities);

		assertEquals(true, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		p.flush();
		
		

		assertEquals(true, v.isDirty());
		// assertEquals("wangshilian", v.get("ID"));
		assertEquals("wangshilian", v.get("Name"));
		assertEquals(null, ((EditableEntity) v).source);

		store.add(v);
		p.flush();

		assertEquals(false, v.isDirty());
		assertEquals("wangshilian", v.get("Name"));
		assertEquals("wangshilian", store.get("wangshilian").get(Entity.PRIMARY_KEY));
		
		@SuppressWarnings("unchecked")
		List<Entity> educationList = (List<Entity>)v.get("Education");
		assertEquals(2, educationList.size());
		int i = 0;
		Entity edu =educationList.get(i);
		assertEquals("kunming" ,edu.get("School"));
		assertEquals(Date.valueOf("1996-09-01"), edu.get("DateFrom"));
		assertEquals(Date.valueOf("2000-07-01"), edu.get("DateTo") );
		
		i++;
		edu =educationList.get(i);
		assertEquals("fuyang" ,edu.get("School"));
		assertEquals(Date.valueOf("1993-09-01"), edu.get("DateFrom"));
		assertEquals(Date.valueOf("1996-07-01"), edu.get("DateTo") );
		
		education = new EditableEntity();
		education.put("School", "fuyang");
		education.put("DateFrom",Date.valueOf("1993-09-01"));
		education.put("DateFrom", Date.valueOf("1996-07-01"));
	}
}

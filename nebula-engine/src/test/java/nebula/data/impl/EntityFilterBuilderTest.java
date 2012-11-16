package nebula.data.impl;

import http.engine.EntityFilterBuilder;

import java.util.Map;

import nebula.Filter;
import nebula.data.Entity;

import org.simpleframework.http.Query;
import org.simpleframework.http.parse.QueryParser;

import junit.framework.TestCase;

public class EntityFilterBuilderTest extends TestCase {

	EntityFilterBuilder builder;

	Entity person1_21;
	Entity person2_22;
	Entity person3_23;
	
	protected void setUp() throws Exception {
		super.setUp();
		builder = new EntityFilterBuilder();
		
		person1_21 = new EditableEntity(null);
		person1_21.put("name", "wangshilian1");
		person1_21.put("age", "21");
				
		person2_22 = new EditableEntity(null);
		person2_22.put("name", "wangshilian2");
		person2_22.put("age", "22");
		
		person3_23 = new EditableEntity(null);
		person3_23.put("name", "wangshilian3");
		person3_23.put("age", "23");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testQuery() {
		Query query = new QueryParser("name=wangshilian1&age=21");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);
		
		boolean result = filter.match(person1_21);
		assertTrue(result);
				
		result = filter.match(person2_22);
		assertFalse(result);
	}
	

	public void testOp_from_Query() {
		Query query = new QueryParser("name:from=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);
		
		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_to_Query() {
		Query query = new QueryParser("name:to=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);
		
		assertTrue(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_gt_Query() {
		Query query = new QueryParser("name:gt=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_ge_Query() {
		Query query = new QueryParser("name:ge=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_lt_Query() {
		Query query = new QueryParser("name:lt=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertTrue(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_le_Query() {
		Query query = new QueryParser("name:le=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertTrue(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}


	public void testOp_eq_Query() {
		Query query = new QueryParser("name:eq=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}


	public void testOp_ne_Query() {
		Query query = new QueryParser("name:ne=wangshilian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertTrue(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}
	
	public void testOp_in_Query() {
		Query query = new QueryParser("name:has=lian2");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}
	
	public void testOp_and_Query() {
		Query query = new QueryParser("name:has=lian2&age=22");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));

		query = new QueryParser("name:has=lian2&age=23");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
		
		query = new QueryParser("name:has=lian1&age=23");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
		
		query = new QueryParser("name:has=lian3&age=23");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter =  builder.buildFrom(query,null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}
}

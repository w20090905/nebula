package nebula.data.impl;

import http.resource.EntityFilterBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import nebula.data.Entity;
import nebula.data.DataFilter;

public class EntityFilterBuilderTest extends TestCase {

	EntityFilterBuilder builder;

	Entity person1_21;
	Entity person2_22;
	Entity person3_23;

	public Map<String, String[]> parserQuery(String params) {
		String[] pairs = params.split("&");
		Map<String, List<String>> pams = new HashMap<String, List<String>>();
		for (String pair : pairs) {
			String[] kv = pair.split("=");
			if(!pams.containsKey(kv[0])){
				pams.put(kv[0], new ArrayList<String>());
				pams.get(kv[0]).add(kv[1]);	
			}else{
				pams.get(kv[0]).add(kv[1]);				
			}
		}
		Map<String, String[]> pam2s = new HashMap<String, String[]>();
		for (Map.Entry<String, List<String>> e : pams.entrySet()) {
			pam2s.put(e.getKey(), e.getValue().toArray(new String[0]));
		}
		
		return pam2s; //TODO  
	}

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
		Map<String, String[]> query = parserQuery("name=wangshilian1&age=21");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		boolean result = filter.match(person1_21);
		assertTrue(result);

		result = filter.match(person2_22);
		assertFalse(result);
	}

	public void testOp_from_Query() {
		Map<String, String[]> query = parserQuery("name:from=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_to_Query() {
		Map<String, String[]> query = parserQuery("name:to=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertTrue(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_gt_Query() {
		Map<String, String[]> query = parserQuery("name:gt=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_ge_Query() {
		Map<String, String[]> query = parserQuery("name:ge=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_lt_Query() {
		Map<String, String[]> query = parserQuery("name:lt=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertTrue(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_le_Query() {
		Map<String, String[]> query = parserQuery("name:le=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertTrue(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_eq_Query() {
		Map<String, String[]> query = parserQuery("name:eq=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_ne_Query() {
		Map<String, String[]> query = parserQuery("name:ne=wangshilian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertTrue(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}

	public void testOp_in_Query() {
		Map<String, String[]> query = parserQuery("name:has=lian2");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));
	}

	public void testOp_and_Query() {
		Map<String, String[]> query = parserQuery("name:has=lian2&age=22");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		DataFilter<Entity> filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertTrue(filter.match(person2_22));
		assertFalse(filter.match(person3_23));

		query = parserQuery("name:has=lian2&age=23");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));

		query = parserQuery("name:has=lian1&age=23");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertFalse(filter.match(person3_23));

		query = parserQuery("name:has=lian3&age=23");
		for (Map.Entry<String, String[]> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		filter = builder.buildFrom(query, null);

		assertFalse(filter.match(person1_21));
		assertFalse(filter.match(person2_22));
		assertTrue(filter.match(person3_23));
	}
}

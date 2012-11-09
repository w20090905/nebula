package nebula.data.impl;

import http.engine.EntityFilterBuilder;

import java.util.Map;

import nebula.Filter;
import nebula.data.Entity;

import org.simpleframework.http.Query;
import org.simpleframework.http.parse.QueryParser;

import junit.framework.TestCase;

public class AdvEntityFilterBuilderTest extends TestCase {

	EntityFilterBuilder builder;
	
	protected void setUp() throws Exception {
		super.setUp();
		builder = new EntityFilterBuilder();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testQuery() {
		Query query = new QueryParser("name=wangshilian&age=19");
		for (Map.Entry<String, String> entry : query.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		Filter<Entity>  filter =  builder.buildFrom(query,null);
		
		
		Entity person = new EditableEntity(null);
		person.put("name", "wangshilian");
		person.put("age", "19");
		
		boolean result = filter.match(person);
		assertTrue(result);
		
		person = new EditableEntity(null);
		person.put("name", "wangshilian");
		person.put("age", "13");
		
		result = filter.match(person);
		assertFalse(result);
	}

}

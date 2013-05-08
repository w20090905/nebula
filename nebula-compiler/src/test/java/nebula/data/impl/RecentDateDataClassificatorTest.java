package nebula.data.impl;

import java.util.Set;

import junit.framework.TestCase;
import nebula.data.Entity;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class RecentDateDataClassificatorTest extends TestCase {
	DataClassificator<Entity> classificator;

	protected void setUp() throws Exception {
		super.setUp();
		classificator = new DataClassificator<Entity>(new Function<Entity, String>() {
			Function<DateTime, String> convertFunction = new RecentDateClassificatorFunction();

			@Override
			public String apply(Entity from) {
				return convertFunction.apply((DateTime) from.get("Date"));
			}
		});
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testRecentDateDataClassificator() {

	}

	public final void testAdd_Update_Remove() {
		DateTime now = new DateTime();

		EditableEntity v = new EditableEntity();
		v.put("Date", now);
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.withDayOfWeek(1));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.withDayOfMonth(1));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(1).withDayOfMonth(1));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(2).withDayOfMonth(2));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(3).withDayOfMonth(2));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(4).withDayOfMonth(2));
		classificator.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(5).withDayOfMonth(2));
		classificator.add(v);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1, classificator.getData("This Month").size());
		assertEquals(2, classificator.getData("Three Month").size());
		assertEquals(3, classificator.getData("Six Month").size());

		// Three Month to today
		EditableEntity v1 = new EditableEntity();
		v1.put("Date", now.withDayOfMonth(1));
		classificator.update(v, v1);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1 + 1, classificator.getData("This Month").size());
		assertEquals(2, classificator.getData("Three Month").size());
		assertEquals(3 - 1, classificator.getData("Six Month").size());

		classificator.remove(v1);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1 + 1 - 1, classificator.getData("This Month").size());
		assertEquals(2, classificator.getData("Three Month").size());
		assertEquals(3 - 1, classificator.getData("Six Month").size());
		
		Set<String> classifications = classificator.getClassifications();
		assertEquals(5, classifications.size());
		System.out.println(Joiner.on(',').join(classifications));
//		assertEquals("Today", classifications.iterator().next());
		
	}

	public final void testGetClassifications() {
	}

}

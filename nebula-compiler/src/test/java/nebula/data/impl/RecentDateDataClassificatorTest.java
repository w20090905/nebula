package nebula.data.impl;

import junit.framework.TestCase;

import org.joda.time.DateTime;

public class RecentDateDataClassificatorTest extends TestCase {
	RecentDateDataClassificator classificator;

	protected void setUp() throws Exception {
		super.setUp();
		classificator = new RecentDateDataClassificator("Date");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testRecentDateDataClassificator() {
		classificator = new RecentDateDataClassificator("Date");
	}

	public final void testClassify() {

		DateTime now = new DateTime();

		EditableEntity v = new EditableEntity();
		v.put("Date", now);

		String classification = classificator.classify(v);
		assertEquals("Today", classification);

		v.put("Date", now.withDayOfWeek(1));
		classification = classificator.classify(v);
		assertEquals("This Week", classification);

		v.put("Date", now.withDayOfMonth(1));
		classification = classificator.classify(v);
		assertEquals("This Month", classification);

		v.put("Date", now.minusMonths(1).withDayOfMonth(1));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		v.put("Date", now.minusMonths(1).withDayOfMonth(2));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		v.put("Date", now.minusMonths(3).withDayOfMonth(2));
		classification = classificator.classify(v);
		assertEquals("Six Month", classification);

		v.put("Date", now.minusMonths(5).withDayOfMonth(2));
		classification = classificator.classify(v);
		assertEquals("Six Month", classification);
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
	}

	public final void testGetClassifications() {
		String[] classifications = classificator.getClassifications();
		assertEquals(5, classifications.length);
		assertEquals("Today", classifications[0]);
	}

}

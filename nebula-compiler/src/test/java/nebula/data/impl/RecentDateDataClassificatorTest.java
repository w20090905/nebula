package nebula.data.impl;

import java.sql.Date;
import java.util.Calendar;

import junit.framework.TestCase;

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
		EditableEntity v = new EditableEntity();
		v.put("Date", new Date(new java.util.Date().getTime()));

		String classification = classificator.classify(v);
		assertEquals("Today", classification);

		Calendar c = Calendar.getInstance();

		java.util.Date today = new java.util.Date();

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_WEEK, 1);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("This Week", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("This Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 0);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, -1);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 2);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Six Month", classification);

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 5);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Six Month", classification);
	}

	public final void testAdd_Update_Remove() {

		EditableEntity v = new EditableEntity();
		v.put("Date", new Date(new java.util.Date().getTime()));

		String classification = classificator.classify(v);
		assertEquals("Today", classification);

		Calendar c = Calendar.getInstance();

		java.util.Date today = new java.util.Date();

		// Today
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_WEEK, 1);
		v.put("Date", new Date(today.getTime()));

		classificator.add(v);

		// This Week
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_WEEK, 1);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// This Month
		v = new EditableEntity();

		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// Three Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 0);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// Three Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, -1);
		v.put("Date", new Date(c.getTime().getTime()));
		classification = classificator.classify(v);
		assertEquals("Three Month", classification);

		classificator.add(v);

		// Three Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// Three Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 2);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// Six Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		// Six Month
		v = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 5);
		v.put("Date", new Date(c.getTime().getTime()));

		classificator.add(v);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1, classificator.getData("This Month").size());
		assertEquals(4, classificator.getData("Three Month").size());
		assertEquals(2, classificator.getData("Six Month").size());

		

		// Three Month to today 
		EditableEntity v1 = new EditableEntity();
		c.setTime(today);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		v1.put("Date", new Date(c.getTime().getTime()));
		
		classificator.update(v,v1);
		

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1+1, classificator.getData("This Month").size());
		assertEquals(4, classificator.getData("Three Month").size());
		assertEquals(2-1, classificator.getData("Six Month").size());
		
		

		classificator.remove(v1);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("This Week").size());
		assertEquals(1+1-1, classificator.getData("This Month").size());
		assertEquals(4, classificator.getData("Three Month").size());
		assertEquals(2-1, classificator.getData("Six Month").size());
	}

	public final void testGetClassifications() {
		String[] classifications = classificator.getClassifications();
		assertEquals(5, classifications.length);
		assertEquals("Today", classifications[0]);
	}


}

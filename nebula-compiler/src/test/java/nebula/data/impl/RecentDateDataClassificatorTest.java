package nebula.data.impl;

import java.util.Set;

import junit.framework.TestCase;
import nebula.data.Classificator;
import nebula.data.Entity;
import nebula.data.LiveList;
import nebula.data.util.RecentDateClassificatorFunction;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class RecentDateDataClassificatorTest extends TestCase {
	LiveList<String, Entity> list;
	Classificator<String, Entity> classificator;

	protected void setUp() throws Exception {
		super.setUp();
		list = new LiveList<String, Entity>(new Function<Entity, String>() {

			@Override
			public String apply(Entity from) {
				return from.get("Date").toString();
			}
		});

		classificator = list.addListener(Entities.classify(new Function<Entity, String>() {
			Function<DateTime, String> convertFunction = new RecentDateClassificatorFunction(new DateTime()
					.withDayOfMonth(10).withDayOfWeek(3));
			@Override
			public String apply(Entity from) {
				return convertFunction.apply((DateTime) from.get("Date"));
			}
		}));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testRecentDateDataClassificator() {

	}

	public final void testAdd_Update_Remove() {
		DateTime now = new DateTime().withDayOfMonth(10).withDayOfWeek(3);

		EditableEntity v = new EditableEntity();
		v.put("Date", now);
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.withDayOfWeek(1));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.withDayOfMonth(1));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(1).withDayOfMonth(1));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(2).withDayOfMonth(2));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(3).withDayOfMonth(2));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(4).withDayOfMonth(2));
		list.add(v);

		v = new EditableEntity();
		v.put("Date", now.minusMonths(5).withDayOfMonth(2));
		list.add(v);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("ThisWeek").size());
		assertEquals(1, classificator.getData("ThisMonth").size());
		assertEquals(2, classificator.getData("ThreeMonth").size());
		assertEquals(3, classificator.getData("SixMonth").size());

		// ThreeMonth to today
		EditableEntity v1 = new EditableEntity();
		v1.put("Date", now.withDayOfMonth(2));
		list.remove(v);
		list.add(v1);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("ThisWeek").size());
		assertEquals(1 + 1, classificator.getData("ThisMonth").size());
		assertEquals(2, classificator.getData("ThreeMonth").size());
		assertEquals(3 - 1, classificator.getData("SixMonth").size());

		list.remove(v1);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("ThisWeek").size());
		assertEquals(1 + 1 - 1, classificator.getData("ThisMonth").size());
		assertEquals(2, classificator.getData("ThreeMonth").size());
		assertEquals(3 - 1, classificator.getData("SixMonth").size());

		Set<String> classifications = classificator.getClassifications();
		assertEquals(5, classifications.size());
		assertEquals("ThreeMonth,ThisWeek,Today,SixMonth,ThisMonth", Joiner.on(",").join(classifications));

		// ThreeMonth to today
		EditableEntity vOld = new EditableEntity();
		vOld.put("Date", now.minusMonths(9));
		list.add(vOld);

		assertEquals(1, classificator.getData("Today").size());
		assertEquals(1, classificator.getData("ThisWeek").size());
		assertEquals(1 + 1 - 1, classificator.getData("ThisMonth").size());
		assertEquals(2, classificator.getData("ThreeMonth").size());
		assertEquals(3 - 1, classificator.getData("SixMonth").size());

		assertEquals(0, classificator.getData("OLD").size());

	}

	public final void testGetClassifications() {
	}

}

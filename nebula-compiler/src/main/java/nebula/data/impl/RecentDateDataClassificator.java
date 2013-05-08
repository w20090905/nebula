package nebula.data.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import nebula.data.DataClassificator;
import nebula.data.Entity;

public class RecentDateDataClassificator implements DataClassificator<Entity> {

	String[] classifications = { "Today", "This Week", "This Month", "Three Month", "Six Month" };
	final String key;
	Date today;
	Date thisWeek;
	Date thisMonth;
	Date treeMonth;
	Date sixMonth;

	final Map<String, List<Entity>> datas = new HashMap<String, List<Entity>>();

	RecentDateDataClassificator(String key) {
		this.key = key;
		Calendar c = Calendar.getInstance();

		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		today = new Date(c.getTime().getTime());
		datas.put(classifications[0], new ArrayList<Entity>());

		c.setTime(today);
		c.set(Calendar.DAY_OF_WEEK, 1);
		thisWeek = new Date(c.getTime().getTime());
		datas.put(classifications[1], new ArrayList<Entity>());

		c.setTime(today);
		c.set(Calendar.DAY_OF_MONTH, 1);
		thisMonth = new Date(c.getTime().getTime());
		datas.put(classifications[2], new ArrayList<Entity>());

		c.setTime(today);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3 + 1);
		treeMonth = new Date(c.getTime().getTime());
		datas.put(classifications[3], new ArrayList<Entity>());

		c.setTime(today);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 6 + 1);
		sixMonth = new Date(c.getTime().getTime());
		datas.put(classifications[4], new ArrayList<Entity>());
	}

	public String classify(Entity v) {
		Date date = (Date) v.get(key);

		if (!date.before(today)) {
			return classifications[0];
		} else if (!date.before(thisWeek)) {
			return classifications[1];
		} else if (!date.before(thisMonth)) {
			return classifications[2];
		} else if (!date.before(treeMonth)) {
			return classifications[3];
		} else if (!date.before(sixMonth)) {
			return classifications[4];
		} else {
			return null;
		}
	}

	@Override
	public void add(Entity v) {
		String classification = classify(v);
		if (classification != null) this.datas.get(classification).add(v);
	}

	@Override
	public void update(Entity oldData, Entity newData) {
		if (Objects.equals(oldData.get(key), newData.get(key))) {
			return;
		}
		String oldClassification = classify(oldData);
		String newClassification = classify(newData);

		if (oldClassification != null) this.datas.get(oldClassification).remove(oldData);
		if (newClassification != null) this.datas.get(newClassification).add(newData);

	}

	@Override
	public void remove(Entity v) {
		String classification = classify(v);
		if (classification != null) this.datas.get(classification).remove(v);
	}

	@Override
	public String[] getClassifications() {
		return this.classifications;
	}

	@Override
	public List<Entity> getData(String classification) {
		return this.datas.get(classification);
	}

}

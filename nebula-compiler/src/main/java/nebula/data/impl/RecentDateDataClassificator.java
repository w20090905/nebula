package nebula.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import nebula.data.DataClassificator;
import nebula.data.Entity;

import org.joda.time.DateTime;

public class RecentDateDataClassificator implements DataClassificator<Entity> {

	String[] classifications = { "Today", "This Week", "This Month", "Three Month", "Six Month" };
	final String key;
	DateTime today;
	DateTime thisWeek;
	DateTime thisMonth;
	DateTime threeMonth;
	DateTime sixMonth;

	final Map<String, List<Entity>> datas = new HashMap<String, List<Entity>>();

	RecentDateDataClassificator(String key) {
		this.key = key;

		today = new DateTime().withMillisOfDay(0);
		thisWeek = today.withDayOfWeek(1);
		thisMonth = today.withDayOfMonth(1);
		threeMonth = today.minusMonths(2).withDayOfMonth(1);
		sixMonth = today.minusMonths(5).withDayOfMonth(1);

		datas.put(classifications[0], new ArrayList<Entity>());
		datas.put(classifications[1], new ArrayList<Entity>());
		datas.put(classifications[2], new ArrayList<Entity>());
		datas.put(classifications[3], new ArrayList<Entity>());
		datas.put(classifications[4], new ArrayList<Entity>());
	}

	public String classify(Entity v) {
		DateTime date = (DateTime) v.get(key);

		if (!date.isBefore(today)) {
			return classifications[0];
		} else if (!date.isBefore(thisWeek)) {
			return classifications[1];
		} else if (!date.isBefore(thisMonth)) {
			return classifications[2];
		} else if (!date.isBefore(threeMonth)) {
			return classifications[3];
		} else if (!date.isBefore(sixMonth)) {
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

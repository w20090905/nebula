package nebula.data.util;

import org.joda.time.DateTime;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;

@Beta
public class RecentDateClassificatorFunction implements Function<DateTime, String> {
	String[] classifications = { "Today", "ThisWeek", "ThisMonth", "ThreeMonth", "SixMonth" };
	final DateTime today;
	final DateTime thisWeek;
	final DateTime thisMonth;
	final DateTime threeMonth;
	final DateTime sixMonth;

	public RecentDateClassificatorFunction() {
		this(new DateTime().withMillisOfDay(0));
	}

	public RecentDateClassificatorFunction(DateTime today) {
		this.today = today;
		thisWeek = today.withDayOfWeek(1);
		thisMonth = today.withDayOfMonth(1);
		threeMonth = today.minusMonths(2).withDayOfMonth(1);
		sixMonth = today.minusMonths(5).withDayOfMonth(1);
	}

	@Override
	public String apply(DateTime date) {
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

}

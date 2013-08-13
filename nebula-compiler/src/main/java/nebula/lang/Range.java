package nebula.lang;

import java.util.List;

public abstract class Range {

	abstract <V> List<V> filter(List<V> from, List<V> to);

	/* [a..b] */
	public static Range closed(int from, int to) {
		return new RangeClosed(from, to);
	}

	static class RangeClosed extends Range {
		int from;
		int to;

		public RangeClosed(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		<V> List<V> filter(List<V> fromList, List<V> toList) {
			for (int i = from; i < fromList.size() && i <= to; i++) {
				toList.add(fromList.get(i));
			}
			return toList;
		}
	}

	/* [a..b){ return null;} */
	public static Range closedOpen(int from, int to) {
		return null;
	}

	/* (a..b] */
	public static Range openClosed(int from, int to) {
		return null;
	}

	/* [a..+∞){ return null;} */
	public static Range atLeast(int index) {
		return new RangeAtLeast(index);
	}

	static class RangeAtLeast extends Range {
		int from;

		public RangeAtLeast(int from) {
			this.from = from;
		}

		@Override
		<V> List<V> filter(List<V> fromList, List<V> toList) {
			for (int i = from; i < fromList.size(); i++) {
				toList.add(fromList.get(i));
			}
			return toList;
		}
	}

	/* (-∞..b] */
	public static Range atMost(int to) {
		return new RangeAtMost(to);
	}

	static class RangeAtMost extends Range {
		int to;

		public RangeAtMost(int to) {
			this.to = to;
		}

		@Override
		<V> List<V> filter(List<V> fromList, List<V> toList) {
			for (int i = 0; i <= to; i++) {
				toList.add(fromList.get(i));
			}
			return toList;
		}
	}
}

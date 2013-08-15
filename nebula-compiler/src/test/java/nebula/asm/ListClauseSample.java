package nebula.asm;

import java.util.List;

import nebula.data.Entity;
import nebula.lang.NebulaNative;
import nebula.lang.Range;

public class ListClauseSample {

	public List<Entity> search(List<Entity> in) {
		Range[] ranges = new Range[4];
		long ii = 10;
		ranges[0] = Range.atLeast((int) ii);
		ranges[1] = Range.atMost(10);
		ranges[2] = Range.closed(1, 3);
		ranges[3] = Range.closed(1, 1);

		List<Entity> out = NebulaNative.filter(in, ranges);
		return out;
	}
}

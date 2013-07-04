package nebula.data.util;

import com.google.common.base.Function;

@Deprecated
public class AttrClassificatorFunction implements Function<String, String> {
	public AttrClassificatorFunction() {
	}

	@Override
	public String apply(String data) {
		return data;
	}

}

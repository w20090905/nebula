package adempiere;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

public class StringHelper {
	static public Map<String, String> newMapsFrom(String keyvalues) {
		StringTokenizer st = new StringTokenizer(keyvalues);
		Hashtable<String, String> TheNameTypes = new Hashtable<String, String>();

		while (st.hasMoreTokens())
			TheNameTypes.put(st.nextToken(), st.nextToken());
		return TheNameTypes;
	}

	static public Map<String, String> newMapsFrom(String keyvalues, String d) {
		StringTokenizer st = new StringTokenizer(keyvalues);
		Hashtable<String, String> TheNameTypes = new Hashtable<String, String>();

		while (st.hasMoreTokens())
			TheNameTypes.put(st.nextToken(), st.nextToken());
		return TheNameTypes;
	}

}

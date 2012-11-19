package nebula.data.db;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import nebula.lang.RawTypes;

public class TypeNames {

	private Map<RawTypes, Map<Long, String>> weighted = new HashMap<RawTypes, Map<Long, String>>();
	private Map<RawTypes, String> defaults = new HashMap<RawTypes, String>();

	public String get(RawTypes typecode) {
		String result = defaults.get( typecode );
		if (result==null) throw new RuntimeException("No Dialect mapping for JDBC type: " + typecode);
		return result;
	}

	public String get(RawTypes typeCode, long size, int precision, int scale)  {
		Map<Long, String> map = weighted.get( typeCode );
		if ( map!=null && map.size()>0 ) {
			// iterate entries ordered by capacity to find first fit
			for (Map.Entry<Long, String> entry: map.entrySet()) {
				if ( size <= entry.getKey() ) {
					return replace( entry.getValue(), size, precision, scale );
				}
			}
		}
		return replace( get(typeCode), size, precision, scale );
	}
	
	private static String replace(String type, long size, int precision, int scale) {
		type = type.replaceFirst("$s", Integer.toString(scale) );
		type = type.replaceFirst("$l", Long.toString(size) );
		return type.replaceFirst("$p", Integer.toString(precision) );
	}

	public void put(RawTypes typecode, long capacity, String value) {
		Map<Long, String> map = weighted.get( typecode );
		if (map == null) {// add new ordered map
			map = new TreeMap<Long, String>();
			weighted.put( typecode, map );
		}
		map.put(capacity, value);
	}

	public void put(RawTypes typecode, String value) {
		defaults.put( typecode, value );
	}
}







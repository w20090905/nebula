package nebula.data.db;

import java.util.HashMap;
import java.util.Map;

import nebula.lang.RawTypes;

import com.google.common.base.Preconditions;

/*
 * 根据内部类型构建实际DB类型的字段说明
 */
public class RealDBTypeDefineBuilder {

	// 普通类型
	private Map<RawTypes, String> normalTypes = new HashMap<RawTypes, String>();

	// 带精度类型
//	private Map<RawTypes, Map<Integer, String>> weightedTypes = new HashMap<RawTypes, Map<Integer, String>>();

	// 生成用于构建实际类型的说明字符串
	public String build(RawTypes typecode) {
		return Preconditions.checkNotNull(normalTypes.get(typecode));
	}

	// 生成用于构建实际类型的说明字符串
	public String build(RawTypes typeCode, Integer size, int precision, int scale) {
//		Map<Integer, String> map = weightedTypes.get(typeCode);
//		if (map == null) return replace(normalTypes.get(typeCode), size, precision, scale);

//		// iterate entries ordered by capacity to find first fit
//		for (Map.Entry<Integer, String> entry : map.entrySet()) {
//			if (size <= entry.getKey()) {
//				return replace(entry.getValue(), size, precision, scale);
//			}
//		}

		return replace(normalTypes.get(typeCode), size, precision, scale);
	}

	/**
	 * 把参数替换为实际值
	 */
	private static String replace(String type, int size, int precision, int scale) {
		type = type.replace("$s", Integer.toString(scale));
		type = type.replace("$l", Integer.toString(size));
		return type.replace("$p", Integer.toString(precision));
	}

//	/**
//	 * 可以根据原始类型长度的不同，选择映射到不同的类型
//	 */
//	@Deprecated
//	public void register(RawTypes typecode, int capacity, String value) {
//		Map<Integer, String> map = weightedTypes.get(typecode);
//		if (map == null) {// add new ordered map
//			map = new TreeMap<Integer, String>();
//			weightedTypes.put(typecode, map);
//		}
//		map.put(capacity, value);
//	}

	public void register(RawTypes typecode, String value) {
		normalTypes.put(typecode, value);
	}
}

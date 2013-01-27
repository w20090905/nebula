package nebula.data.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import nebula.data.Entity;
import nebula.lang.RawTypes;

public class BasicTypeFieldSerializer extends DefaultFieldSerializer<Object> {

	private final BasicTypeAdapter<?> dataDealer;

	public BasicTypeFieldSerializer(String fieldName, String columnName, boolean key, boolean nullable, boolean array,
			RawTypes rawType, long size, int precision, int scale) {
		super(fieldName, columnName, key, nullable, array, rawType, size, precision, scale);
		if (array) dataDealer = ListDataDealer.typeMaps.get(rawType);
		else dataDealer = BasicTypeAdapter.typeMaps.get(rawType);

	}

	@Override
	public int input(ResultSet in, int pos, Entity parent, Object now) throws Exception {
		Object newly = dataDealer.readFrom(in, pos);
		parent.put(fieldName, newly);
		return ++pos;
	}

	@Override
	public int inputWithoutCheck(ResultSet in, int pos, Entity parent) throws Exception {
		Object newly = dataDealer.readFrom(in, pos);
		parent.put(fieldName, newly);
		return ++pos;
	}

	@Override
	public int output(PreparedStatement out, Object value, int pos) throws Exception {
		dataDealer.writeTo(pos, value, out);
		return ++pos;
	}

}

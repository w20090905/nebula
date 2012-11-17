package nebula.db.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nebula.db.DBDataDealer;

public class BigIntDataDealer implements DBDataDealer {

	@Override
	public Object readFrom(ResultSet res, int index) {
		try {
			return res.getLong(index);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setLong(index, v!=null?(Long) v:0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

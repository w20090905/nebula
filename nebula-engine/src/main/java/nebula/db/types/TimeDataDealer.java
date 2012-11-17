package nebula.db.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import nebula.db.DBDataDealer;

public class TimeDataDealer implements DBDataDealer {
	@Override
	public Object readFrom(ResultSet res,int i) {
		try {
			return res.getTime(i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setTime(index, (Time) v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

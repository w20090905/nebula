package nebula.db.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import nebula.db.DataDealer;

public class TimestampDataDealer implements DataDealer {
	@Override
	public Object readFrom(ResultSet res,int i) {
		try {
			return res.getTimestamp(i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setTimestamp(index, (Timestamp) v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

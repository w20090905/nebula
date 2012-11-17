package nebula.db.types;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nebula.db.DBDataDealer;

public class DateDataDealer implements DBDataDealer {

	@Override
	public Object readFrom(ResultSet res,int i) {
		try {
			return res.getDate(i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setDate(index, (Date) v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

package nebula.db.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nebula.db.DBDataDealer;

public class VarcharDataDealer implements DBDataDealer {

	@Override
	public Object readFrom(ResultSet res, int i) {
		try {
			return res.getString(i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setString(index, (String) v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

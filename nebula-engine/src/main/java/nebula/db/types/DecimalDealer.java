package nebula.db.types;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nebula.db.DBDataDealer;

public class DecimalDealer implements DBDataDealer {
	@Override
	public Object readFrom(ResultSet res,int i) {
		try {
			return res.getBigDecimal(i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void writeTo(int index, Object v, PreparedStatement res) {
		try {
			res.setBigDecimal(index, (BigDecimal) v);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

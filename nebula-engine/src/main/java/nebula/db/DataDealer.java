package nebula.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DataDealer {
	Object readFrom(ResultSet res,int index);

	void writeTo(int index,Object value, PreparedStatement res);
	
}

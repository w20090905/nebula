package nebula.data.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import nebula.data.DataDealer;

public interface DbDataDealer<T> extends DataDealer<T, ResultSet, PreparedStatement> {

}

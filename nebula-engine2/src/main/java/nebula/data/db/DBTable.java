package nebula.data.db;

import java.util.ArrayList;
import java.util.List;

public class DBTable {
	String name;
	List<DbColumn> columns = new ArrayList<DbColumn>();
	public DBTable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DbColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DbColumn> columns) {
		this.columns = columns;
	}

	public void add(String name, String columnName) {
		columns.add(new DbColumn(columnName, false, false, false, null, 0, 0, 0, 0, columnName));
	}
}

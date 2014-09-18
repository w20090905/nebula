package nebula.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.lang.RawTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbConfiguration {
	private final Log log = LogFactory.getLog(DbConfiguration2.class);

	protected final String driverClass;
	protected final String dbURL;
	protected final String userName;
	protected final String userPassword;

	protected Connection conn = null;

	protected RealDBTypeDefineBuilder realDBTypeBuilder = new RealDBTypeDefineBuilder();

	protected DbConfiguration(String driverClass, String dbUrl, String userName, String password) {
		this.driverClass = driverClass;
		this.dbURL = dbUrl;
		this.userName = userName;
		this.userPassword = password;
	}

	/**
	 * 初始化默认字段类型映射逻辑
	 */
	public void initDefaultMapping() {
		registerColumnType(RawTypes.Boolean, "smallint");// .BIGINT
		registerColumnType(RawTypes.Long, "bigint");// .BIGINT
		registerColumnType(RawTypes.Decimal, "numeric($p,$s)");
		registerColumnType(RawTypes.String, "varchar($l)");
		registerColumnType(RawTypes.Text, "varchar($l)");
		registerColumnType(RawTypes.Date, "date");
		registerColumnType(RawTypes.Time, "time");
		registerColumnType(RawTypes.Datetime, "timestamp");
		registerColumnType(RawTypes.Timestamp, "timestamp");
	}

	public void openConnection() {
		try {
			Class.forName(driverClass).newInstance();
			if (log.isTraceEnabled()) {
				log.trace("load driverClass - " + driverClass);
			}
			conn = DriverManager.getConnection(this.dbURL, this.userName, this.userPassword);
			if (log.isDebugEnabled()) {
				log.info("== open database - " + this.dbURL);
			}
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	@Override
	public void finalize() {
		try {
			if (conn != null) {
				conn.commit();
				conn.close();
			}
			if (log.isDebugEnabled()) {
				log.info("== disconnect database");
			}
		} catch (SQLException e) {
			log.debug("Exception When disconnect db");
			throw new RuntimeException(e);
		}
	}

	protected void registerColumnType(RawTypes jdbcType, String columnTypeName) {
		realDBTypeBuilder.register(jdbcType, columnTypeName);
	}
	
	

	protected String toColumnDefine(DbColumn column) {
		if (column.array) {
			return realDBTypeBuilder.build(RawTypes.Text).replaceFirst("\\$l", String.valueOf(column.size));
		} else {
			String typeName = realDBTypeBuilder.build(column.bizType);
			switch (column.bizType) {
			case Decimal:
				typeName = typeName.replaceFirst("\\$p", String.valueOf(column.precision));
				typeName = typeName.replaceFirst("\\$s", String.valueOf(column.scale));
				break;
			case String:
				typeName = typeName.replaceFirst("\\$l", String.valueOf(column.size));
				break;
			case Text:
				typeName = typeName.replaceFirst("\\$l", String.valueOf(column.size));
				break;
			default:
				break;
			}
			return typeName;
		}
	}

}

package nebula.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.data.Entity;
import nebula.data.db.derby.DerbyConfiguration;
import nebula.data.db.oracle.OracleConfiguration;
import nebula.lang.RawTypes;
import nebula.lang.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DbConfiguration {

	private static final Log log = LogFactory.getLog(DbConfiguration.class);
	protected final String driverClass;
	protected final String url;
	protected final String userName;
	protected final String userPassword;
	protected Connection conn = null;

	TypeNames typeNames = new TypeNames();

	public DbConfiguration(String driverClass, String url, String userName, String password) {
		this.driverClass = driverClass;
		this.url = url;
		this.userName = userName;
		this.userPassword = password;

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

	public void init() {
		try {
			Class.forName(driverClass).newInstance();
			if (log.isTraceEnabled()) {
				log.trace("\tload driverClass - " + driverClass);
			}
			conn = DriverManager.getConnection(this.url, this.userName, this.userPassword);
			log.info("== open database - " + this.url);
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static DbConfiguration getEngine(String driverClass, String url, String userName, String password) {
		String dbms = url.split(":")[1].toUpperCase();
		DbConfiguration dbEngine = null;
		if ("DERBY".equals(dbms)) {
			dbEngine = new DerbyConfiguration(driverClass, url, userName, password);
		} else if ("ORACLE".equals(dbms)) {
			dbEngine = new OracleConfiguration(driverClass, url, userName, password);
		} else if ("POSTGRESQL".equals(dbms)) {
			dbEngine = new OracleConfiguration(driverClass, url, userName, password);
		} else {
			throw new UnsupportedOperationException();
		}

		dbEngine.init();
		return dbEngine;
	}

	protected void registerColumnType(RawTypes jdbcType, String columnTypeName) {
		typeNames.put(jdbcType, columnTypeName);
	}

	protected String toColumnDefine(DbColumn column) {
		if (column.array) {
			return typeNames.get(RawTypes.Text).replaceFirst("\\$l", String.valueOf(column.size));
		} else {
			String typeName = typeNames.get(column.rawType);
			switch (column.rawType) {
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

	// public abstract <T extends HasID> Persistence<T> getPersister(Class<T> t,
	// Type type);

	@SuppressWarnings("unchecked")
	public <T> DbDataExecutor<T> getPersister(Type type, Class<T> clz) {
		if (log.isTraceEnabled()) {
			log.trace("\tload persister [" + type.getName() + "] from connection - " + conn);
		}
		DbDataExecutor<T> executor = null;

		DbSqlHelper helper = builderSQLHelper(type);
		DbSerializer<T> serializer = null;
		if (clz == Entity.class) {
			DbSerializer<Entity> entitySerializer = helper.getEntitySerializer();
			serializer = (DbSerializer<T>) entitySerializer;
		}

		switch (type.getStandalone()) {
		case Transaction:
		case Relation:
			executor = new DbDefaultExecutor<T>(conn, type, helper, serializer);
			break;

		default:
			executor = new DbDefaultExecutor<T>(conn, type, helper, serializer);
			break;
		}

		return executor;
	}

	public abstract DbSqlHelper builderSQLHelper(Type type);

	public void shutdown() {
		try {
			if (conn != null) {
				conn.commit();
				conn.close();
			}
			log.debug("== database disconnect");
		} catch (SQLException e) {
			log.debug("Exception When destroy db");
			log.debug(e);
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
	}

}

package nebula.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nebula.lang.RawTypes;
import nebula.lang.RawTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DbConfiguration2 {

	private static final Log log = LogFactory.getLog(DbConfiguration2.class);
	protected final String driverClass;
	protected final String dbURL;
	protected final String userName;
	protected final String userPassword;
	protected Connection conn = null;

	protected RealDBTypeDefineBuilder realDBTypeBuilder = new RealDBTypeDefineBuilder();

	protected DbConfiguration2(String driverClass, String dbUrl, String userName, String password) {
		this.driverClass = driverClass;
		this.dbURL = dbUrl;
		this.userName = userName;
		this.userPassword = password;

	}

	public void init() {
		try {
			Class.forName(driverClass).newInstance();
			if (log.isTraceEnabled()) {
				log.trace("\tload driverClass - " + driverClass);
			}
			conn = DriverManager.getConnection(this.dbURL, this.userName, this.userPassword);
			log.info("== open database - " + this.dbURL);
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static DbConfiguration2 getEngine(String driverClass, String url, String userName, String password) {
		String dbms = url.split(":")[1].toUpperCase();
		DbConfiguration2 dbEngine = null;
		// if ("DERBY".equals(dbms)) {
		// dbEngine = new DerbyConfiguration(driverClass, url, userName,
		// password);
		// } else if ("ORACLE".equals(dbms)) {
		// dbEngine = new OracleConfiguration(driverClass, url, userName,
		// password);
		// } else if ("POSTGRESQL".equals(dbms)) {
		// dbEngine = new OracleConfiguration(driverClass, url, userName,
		// password);
		// } else {
		throw new UnsupportedOperationException();
		// }

		// dbEngine.init();
		// return dbEngine;
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

	// public abstract <T extends HasID> Persistence<T> getPersister(Class<T> t,
	// Type type);
//
//	@SuppressWarnings("unchecked")
//	public <T> DbPersister<T> getPersister(Type type, Class<T> clz) {
//		if (log.isTraceEnabled()) {
//			log.trace("\tload persister [" + type.getName() + "] from connection - " + conn);
//		}
//		DbPersister<T> executor = null;
//
//		DbSqlHelper helper = builderSQLHelper(type);
//		BO2DBSerializer<T> serializer = null;
//		if (clz == Entity.class) {
//			BO2DBSerializer<Entity> entitySerializer = helper.getEntitySerializer();
//			serializer = (BO2DBSerializer<T>) entitySerializer;
//		}
//
//		switch (type.getStandalone()) {
//		case Transaction:
//		case Relation:
//			executor = new DbDefaultPersister<T>(conn, type, helper, serializer);
//			break;
//
//		default:
//			executor = new DbDefaultPersister<T>(conn, type, helper, serializer);
//			break;
//		}
//
//		return executor;
//	}
//
//	public DbSqlHelper builderSQLHelper(Type type) {
//		return new DbSqlHelper(this, type);
//	}

	public void shutdown() {
		try {
			if (conn != null) {
				conn.commit();
				conn.close();
			}
			log.debug("== database disconnect");
		} catch (SQLException e) {
			log.debug("Exception When disconnect db");
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

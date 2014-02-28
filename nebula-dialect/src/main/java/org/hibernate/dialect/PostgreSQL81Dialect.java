/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.dialect;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

//import org.hibernate.JDBCException;
import org.hibernate.LockOptions;
//import org.hibernate.PessimisticLockException;
import org.hibernate.cfg.AvailableSettings;

//import org.hibernate.exception.LockAcquisitionException;
//import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
//import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
//import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
//import org.hibernate.id.SequenceGenerator;
//import org.hibernate.internal.util.JdbcExceptionHelper;
//import org.hibernate.procedure.internal.PostgresCallableStatementSupport;
//import org.hibernate.procedure.spi.CallableStatementSupport;
//import org.hibernate.type.StandardBasicTypes;
//import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;
//import org.hibernate.type.descriptor.sql.ClobTypeDescriptor;
//import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

/**
 * An SQL dialect for Postgres
 * <p/>
 * For discussion of BLOB support in Postgres, as of 8.4, have a peek at
 * <a href="http://jdbc.postgresql.org/documentation/84/binary-data.html">http://jdbc.postgresql.org/documentation/84/binary-data.html</a>.
 * For the effects in regards to Hibernate see <a href="http://in.relation.to/15492.lace">http://in.relation.to/15492.lace</a>
 *
 * @author Gavin King
 */
@SuppressWarnings("deprecation")
public class PostgreSQL81Dialect extends Dialect {

	/**
	 * Constructs a PostgreSQL81Dialect
	 */
	public PostgreSQL81Dialect() {
		super();
		registerColumnType( Types.BIT, "bool" );
		registerColumnType( Types.BIGINT, "int8" );
		registerColumnType( Types.SMALLINT, "int2" );
		registerColumnType( Types.TINYINT, "int2" );
		registerColumnType( Types.INTEGER, "int4" );
		registerColumnType( Types.CHAR, "char(1)" );
		registerColumnType( Types.VARCHAR, "varchar($l)" );
		registerColumnType( Types.FLOAT, "float4" );
		registerColumnType( Types.DOUBLE, "float8" );
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "time" );
		registerColumnType( Types.TIMESTAMP, "timestamp" );
		registerColumnType( Types.VARBINARY, "bytea" );
		registerColumnType( Types.BINARY, "bytea" );
		registerColumnType( Types.LONGVARCHAR, "text" );
		registerColumnType( Types.LONGVARBINARY, "bytea" );
		registerColumnType( Types.CLOB, "text" );
		registerColumnType( Types.BLOB, "oid" );
		registerColumnType( Types.NUMERIC, "numeric($p, $s)" );
		registerColumnType( Types.OTHER, "uuid" );

		getDefaultProperties().setProperty( AvailableSettings.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE );
		getDefaultProperties().setProperty( AvailableSettings.NON_CONTEXTUAL_LOB_CREATION, "true" );
	}
//
//	@Override
//	public SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
//		SqlTypeDescriptor descriptor;
//		switch ( sqlCode ) {
//			case Types.BLOB: {
//				// Force BLOB binding.  Otherwise, byte[] fields annotated
//				// with @Lob will attempt to use
//				// BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING.  Since the
//				// dialect uses oid for Blobs, byte arrays cannot be used.
//				descriptor = BlobTypeDescriptor.BLOB_BINDING;
//				break;
//			}
//			case Types.CLOB: {
//				descriptor = ClobTypeDescriptor.CLOB_BINDING;
//				break;
//			}
//			default: {
//				descriptor = super.getSqlTypeDescriptorOverride( sqlCode );
//				break;
//			}
//		}
//		return descriptor;
//	}

	@Override
	public String getAddColumnString() {
		return "add column";
	}

	@Override
	public String getSequenceNextValString(String sequenceName) {
		return "select " + getSelectSequenceNextValString( sequenceName );
	}

	@Override
	public String getSelectSequenceNextValString(String sequenceName) {
		return "nextval ('" + sequenceName + "')";
	}

	@Override
	public String getCreateSequenceString(String sequenceName) {
		//starts with 1, implicitly
		return "create sequence " + sequenceName;
	}

	@Override
	public String getDropSequenceString(String sequenceName) {
		return "drop sequence " + sequenceName;
	}

	@Override
	public String getCascadeConstraintsString() {
		return " cascade";
	}

	@Override
	public boolean dropConstraints() {
		return true;
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public String getQuerySequencesString() {
		return "select relname from pg_class where relkind='S'";
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		return sql + (hasOffset ? " limit ? offset ?" : " limit ?");
	}

	@Override
	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

	@Override
	public boolean supportsIdentityColumns() {
		return true;
	}

	@Override
	public String getForUpdateString(String aliases) {
		return getForUpdateString() + " of " + aliases;
	}

	@Override
	public String getIdentitySelectString(String table, String column, int type) {
		return "select currval('" + table + '_' + column + "_seq')";
	}

	@Override
	public String getIdentityColumnString(int type) {
		return type==Types.BIGINT ?
			"bigserial not null" :
			"serial not null";
	}

	@Override
	public boolean hasDataTypeInIdentityColumn() {
		return false;
	}

	@Override
	public String getNoColumnsInsertString() {
		return "default values";
	}

	@Override
	public String getCaseInsensitiveLike(){
		return "ilike";
	}

	@Override
	public boolean supportsCaseInsensitiveLike() {
		return true;
	}
//
//	@Override
//	public Class getNativeIdentifierGeneratorClass() {
//		return SequenceGenerator.class;
//	}

	@Override
	public boolean supportsOuterJoinForUpdate() {
		return false;
	}

	@Override
	public boolean useInputStreamToInsertBlob() {
		return false;
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
	}

	/**
	 * Workaround for postgres bug #1453
	 * <p/>
	 * {@inheritDoc}
	 */
	@Override
	public String getSelectClauseNullString(int sqlType) {
		String typeName = getTypeName( sqlType, 1, 1, 0 );
		//trim off the length/precision/scale
		final int loc = typeName.indexOf( '(' );
		if ( loc > -1 ) {
			typeName = typeName.substring( 0, loc );
		}
		return "null::" + typeName;
	}

	@Override
	public boolean supportsCommentOn() {
		return true;
	}

	@Override
	public boolean supportsTemporaryTables() {
		return true;
	}

	@Override
	public String getCreateTemporaryTableString() {
		return "create temporary table";
	}

	@Override
	public String getCreateTemporaryTablePostfix() {
		return "on commit drop";
	}

	@Override
	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	@Override
	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	@Override
	public String getCurrentTimestampSelectString() {
		return "select now()";
	}

	@Override
	public boolean supportsTupleDistinctCounts() {
		return false;
	}

	@Override
	public String toBooleanValueString(boolean bool) {
		return bool ? "true" : "false";
	}
//
//	@Override
//	public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
//		return EXTRACTER;
//	}
//
//	/**
//	 * Constraint-name extractor for Postgres constraint violation exceptions.
//	 * Orginally contributed by Denny Bartelt.
//	 */
//	private static final ViolatedConstraintNameExtracter EXTRACTER = new TemplatedViolatedConstraintNameExtracter() {
//		public String extractConstraintName(SQLException sqle) {
//			try {
//				final int sqlState = Integer.valueOf( JdbcExceptionHelper.extractSqlState( sqle ) );
//				switch (sqlState) {
//					// CHECK VIOLATION
//					case 23514: return extractUsingTemplate( "violates check constraint \"","\"", sqle.getMessage() );
//					// UNIQUE VIOLATION
//					case 23505: return extractUsingTemplate( "violates unique constraint \"","\"", sqle.getMessage() );
//					// FOREIGN KEY VIOLATION
//					case 23503: return extractUsingTemplate( "violates foreign key constraint \"","\"", sqle.getMessage() );
//					// NOT NULL VIOLATION
//					case 23502: return extractUsingTemplate( "null value in column \"","\" violates not-null constraint", sqle.getMessage() );
//					// TODO: RESTRICT VIOLATION
//					case 23001: return null;
//					// ALL OTHER
//					default: return null;
//				}
//			}
//			catch (NumberFormatException nfe) {
//				return null;
//			}
//		}
//	};
//	
//	@Override
//	public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
//		return new SQLExceptionConversionDelegate() {
//			@Override
//			public JDBCException convert(SQLException sqlException, String message, String sql) {
//				final String sqlState = JdbcExceptionHelper.extractSqlState( sqlException );
//
//				if ( "40P01".equals( sqlState ) ) {
//					// DEADLOCK DETECTED
//					return new LockAcquisitionException( message, sqlException, sql );
//				}
//
//				if ( "55P03".equals( sqlState ) ) {
//					// LOCK NOT AVAILABLE
//					return new PessimisticLockException( message, sqlException, sql );
//				}
//
//				// returning null allows other delegates to operate
//				return null;
//			}
//		};
//	}

	@Override
	public int registerResultSetOutParameter(CallableStatement statement, int col) throws SQLException {
		// Register the type of the out param - PostgreSQL uses Types.OTHER
		statement.registerOutParameter( col++, Types.OTHER );
		return col;
	}

	@Override
	public ResultSet getResultSet(CallableStatement ps) throws SQLException {
		ps.execute();
		return (ResultSet) ps.getObject( 1 );
	}

	@Override
	public boolean supportsPooledSequences() {
		return true;
	}

	/**
	 * only necessary for postgre < 7.4  See http://anoncvs.postgresql.org/cvsweb.cgi/pgsql/doc/src/sgml/ref/create_sequence.sgml
	 * <p/>
	 * {@inheritDoc}
	 */
	@Override
	protected String getCreateSequenceString(String sequenceName, int initialValue, int incrementSize) {
		return getCreateSequenceString( sequenceName ) + " start " + initialValue + " increment " + incrementSize;
	}
	
	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@Override
	public boolean supportsEmptyInList() {
		return false;
	}

	@Override
	public boolean supportsExpectedLobUsagePattern() {
		return true;
	}

	@Override
	public boolean supportsLobValueChangePropogation() {
		return false;
	}

	@Override
	public boolean supportsUnboundedLobLocatorMaterialization() {
		return false;
	}

	@Override
	public String getForUpdateString() {
		return " for update";
	}

	@Override
	public String getWriteLockString(int timeout) {
		if ( timeout == LockOptions.NO_WAIT ) {
			return " for update nowait";
		}
		else {
			return " for update";
		}
	}

	@Override
	public String getReadLockString(int timeout) {
		if ( timeout == LockOptions.NO_WAIT ) {
			return " for share nowait";
		}
		else {
			return " for share";
		}
	}

	@Override
	public boolean supportsRowValueConstructorSyntax() {
		return true;
	}
	
	@Override
	public String getForUpdateNowaitString() {
		return getForUpdateString() + " nowait ";
	}
	
	@Override
	public String getForUpdateNowaitString(String aliases) {
		return getForUpdateString( aliases ) + " nowait ";
	}

//	@Override
//	public CallableStatementSupport getCallableStatementSupport() {
//		return PostgresCallableStatementSupport.INSTANCE;
//	}

	@Override
	public ResultSet getResultSet(CallableStatement statement, int position) throws SQLException {
		if ( position != 1 ) {
			throw new UnsupportedOperationException( "PostgreSQL only supports REF_CURSOR parameters as the first parameter" );
		}
		return (ResultSet) statement.getObject( 1 );
	}

	@Override
	public ResultSet getResultSet(CallableStatement statement, String name) throws SQLException {
		throw new UnsupportedOperationException( "PostgreSQL only supports accessing REF_CURSOR parameters by name" );
	}
}

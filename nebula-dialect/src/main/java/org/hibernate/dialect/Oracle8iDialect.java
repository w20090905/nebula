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
import java.util.List;


import org.hibernate.cfg.AvailableSettings;
import org.hibernate.internal.util.StringHelper;
//import org.hibernate.type.descriptor.sql.BitTypeDescriptor;
//import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

/**
 * A dialect for Oracle 8i.
 *
 * @author Steve Ebersole
 */
@SuppressWarnings("deprecation")
public class Oracle8iDialect extends Dialect {
	private static final int PARAM_LIST_SIZE_LIMIT = 1000;

	/**
	 * Constructs a Oracle8iDialect
	 */
	public Oracle8iDialect() {
		super();
		registerCharacterTypeMappings();
		registerNumericTypeMappings();
		registerDateTimeTypeMappings();
		registerLargeObjectTypeMappings();
		registerReverseHibernateTypeMappings();
		
		registerDefaultProperties();
	}

	protected void registerCharacterTypeMappings() {
		registerColumnType( Types.CHAR, "char(1)" );
		registerColumnType( Types.VARCHAR, 4000, "varchar2($l)" );
		registerColumnType( Types.VARCHAR, "long" );
	}

	protected void registerNumericTypeMappings() {
		registerColumnType( Types.BIT, "number(1,0)" );
		registerColumnType( Types.BIGINT, "number(19,0)" );
		registerColumnType( Types.SMALLINT, "number(5,0)" );
		registerColumnType( Types.TINYINT, "number(3,0)" );
		registerColumnType( Types.INTEGER, "number(10,0)" );

		registerColumnType( Types.FLOAT, "float" );
		registerColumnType( Types.DOUBLE, "double precision" );
		registerColumnType( Types.NUMERIC, "number($p,$s)" );
		registerColumnType( Types.DECIMAL, "number($p,$s)" );

		registerColumnType( Types.BOOLEAN, "number(1,0)" );
	}

	protected void registerDateTimeTypeMappings() {
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "date" );
		registerColumnType( Types.TIMESTAMP, "date" );
	}

	protected void registerLargeObjectTypeMappings() {
		registerColumnType( Types.BINARY, 2000, "raw($l)" );
		registerColumnType( Types.BINARY, "long raw" );

		registerColumnType( Types.VARBINARY, 2000, "raw($l)" );
		registerColumnType( Types.VARBINARY, "long raw" );

		registerColumnType( Types.BLOB, "blob" );
		registerColumnType( Types.CLOB, "clob" );

		registerColumnType( Types.LONGVARCHAR, "long" );
		registerColumnType( Types.LONGVARBINARY, "long raw" );
	}

	protected void registerReverseHibernateTypeMappings() {
	}

	protected void registerDefaultProperties() {
		getDefaultProperties().setProperty( AvailableSettings.USE_STREAMS_FOR_BINARY, "true" );
		getDefaultProperties().setProperty( AvailableSettings.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE );
		// Oracle driver reports to support getGeneratedKeys(), but they only
		// support the version taking an array of the names of the columns to
		// be returned (via its RETURNING clause).  No other driver seems to
		// support this overloaded version.
		getDefaultProperties().setProperty( AvailableSettings.USE_GET_GENERATED_KEYS, "false" );
	}

//	@Override
//	protected SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
//		return sqlCode == Types.BOOLEAN ? BitTypeDescriptor.INSTANCE : super.getSqlTypeDescriptorOverride( sqlCode );
//	}


	// features which change between 8i, 9i, and 10g ~~~~~~~~~~~~~~~~~~~~~~~~~~

//	@Override
//	public JoinFragment createOuterJoinFragment() {
//		return new OracleJoinFragment();
//	}

	@Override
	public String getCrossJoinSeparator() {
		return ", ";
	}

//	/**
//	 * Map case support to the Oracle DECODE function.  Oracle did not
//	 * add support for CASE until 9i.
//	 * <p/>
//	 * {@inheritDoc}
//	 */
//	@Override
//	public CaseFragment createCaseFragment() {
//		return new DecodeCaseFragment();
//	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith( " for update" ) ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}

		final StringBuilder pagingSelect = new StringBuilder( sql.length()+100 );
		if (hasOffset) {
			pagingSelect.append( "select * from ( select row_.*, rownum rownum_ from ( " );
		}
		else {
			pagingSelect.append( "select * from ( " );
		}
		pagingSelect.append( sql );
		if (hasOffset) {
			pagingSelect.append( " ) row_ ) where rownum_ <= ? and rownum_ > ?" );
		}
		else {
			pagingSelect.append( " ) where rownum <= ?" );
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}

		return pagingSelect.toString();
	}

	/**
	 * Allows access to the basic {@link Dialect#getSelectClauseNullString}
	 * implementation...
	 *
	 * @param sqlType The {@link java.sql.Types} mapping type code
	 * @return The appropriate select cluse fragment
	 */
	public String getBasicSelectClauseNullString(int sqlType) {
		return super.getSelectClauseNullString( sqlType );
	}

	@Override
	public String getSelectClauseNullString(int sqlType) {
		switch(sqlType) {
			case Types.VARCHAR:
			case Types.CHAR:
				return "to_char(null)";
			case Types.DATE:
			case Types.TIMESTAMP:
			case Types.TIME:
				return "to_date(null)";
			default:
				return "to_number(null)";
		}
	}

	@Override
	public String getCurrentTimestampSelectString() {
		return "select sysdate from dual";
	}

	@Override
	public String getCurrentTimestampSQLFunctionName() {
		return "sysdate";
	}


	// features which remain constant across 8i, 9i, and 10g ~~~~~~~~~~~~~~~~~~

	@Override
	public String getAddColumnString() {
		return "add";
	}

	@Override
	public String getSequenceNextValString(String sequenceName) {
		return "select " + getSelectSequenceNextValString( sequenceName ) + " from dual";
	}

	@Override
	public String getSelectSequenceNextValString(String sequenceName) {
		return sequenceName + ".nextval";
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
		return " cascade constraints";
	}

	@Override
	public boolean dropConstraints() {
		return false;
	}

	@Override
	public String getForUpdateNowaitString() {
		return " for update nowait";
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public boolean supportsPooledSequences() {
		return true;
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getForUpdateString(String aliases) {
		return getForUpdateString() + " of " + aliases;
	}

	@Override
	public String getForUpdateNowaitString(String aliases) {
		return getForUpdateString() + " of " + aliases + " nowait";
	}

	@Override
	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

	@Override
	public boolean useMaxForLimit() {
		return true;
	}

	@Override
	public boolean forUpdateOfColumns() {
		return true;
	}

	@Override
	public String getQuerySequencesString() {
		return    " select sequence_name from all_sequences"
				+ "  union"
				+ " select synonym_name"
				+ "   from all_synonyms us, all_sequences asq"
				+ "  where asq.sequence_name = us.table_name"
				+ "    and asq.sequence_owner = us.table_owner";
	}

	@Override
	public String getSelectGUIDString() {
		return "select rawtohex(sys_guid()) from dual";
	}

//	@Override
//	public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
//		return EXTRACTER;
//	}
//
//	private static final ViolatedConstraintNameExtracter EXTRACTER = new TemplatedViolatedConstraintNameExtracter() {
//
//		/**
//		 * Extract the name of the violated constraint from the given SQLException.
//		 *
//		 * @param sqle The exception that was the result of the constraint violation.
//		 * @return The extracted constraint name.
//		 */
//		public String extractConstraintName(SQLException sqle) {
//			final int errorCode = JdbcExceptionHelper.extractErrorCode( sqle );
//			if ( errorCode == 1 || errorCode == 2291 || errorCode == 2292 ) {
//				return extractUsingTemplate( "(", ")", sqle.getMessage() );
//			}
//			else if ( errorCode == 1400 ) {
//				// simple nullability constraint
//				return null;
//			}
//			else {
//				return null;
//			}
//		}
//
//	};
//
//	@Override
//	public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
//		return new SQLExceptionConversionDelegate() {
//			@Override
//			public JDBCException convert(SQLException sqlException, String message, String sql) {
//				// interpreting Oracle exceptions is much much more precise based on their specific vendor codes.
//
//				final int errorCode = JdbcExceptionHelper.extractErrorCode( sqlException );
//
//
//				// lock timeouts ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//				if ( errorCode == 30006 ) {
//					// ORA-30006: resource busy; acquire with WAIT timeout expired
//					throw new LockTimeoutException( message, sqlException, sql );
//				}
//				else if ( errorCode == 54 ) {
//					// ORA-00054: resource busy and acquire with NOWAIT specified or timeout expired
//					throw new LockTimeoutException( message, sqlException, sql );
//				}
//				else if ( 4021 == errorCode ) {
//					// ORA-04021 timeout occurred while waiting to lock object
//					throw new LockTimeoutException( message, sqlException, sql );
//				}
//
//
//				// deadlocks ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//				if ( 60 == errorCode ) {
//					// ORA-00060: deadlock detected while waiting for resource
//					return new LockAcquisitionException( message, sqlException, sql );
//				}
//				else if ( 4020 == errorCode ) {
//					// ORA-04020 deadlock detected while trying to lock object
//					return new LockAcquisitionException( message, sqlException, sql );
//				}
//
//
//				// query cancelled ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//				if ( 1013 == errorCode ) {
//					// ORA-01013: user requested cancel of current operation
//					throw new QueryTimeoutException(  message, sqlException, sql );
//				}
//
//
//				// data integrity violation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//				if ( 1407 == errorCode ) {
//					// ORA-01407: cannot update column to NULL
//					final String constraintName = getViolatedConstraintNameExtracter().extractConstraintName( sqlException );
//					return new ConstraintViolationException( message, sqlException, sql, constraintName );
//				}
//
//				return null;
//			}
//		};
//	}

	@Override
	public int registerResultSetOutParameter(CallableStatement statement, int col) throws SQLException {
		//	register the type of the out param - an Oracle specific type
		statement.registerOutParameter( col, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType() );
		col++;
		return col;
	}

	@Override
	public ResultSet getResultSet(CallableStatement ps) throws SQLException {
		ps.execute();
		return (ResultSet) ps.getObject( 1 );
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
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
	public String generateTemporaryTableName(String baseTableName) {
		final String name = super.generateTemporaryTableName( baseTableName );
		return name.length() > 30 ? name.substring( 1, 30 ) : name;
	}

	@Override
	public String getCreateTemporaryTableString() {
		return "create global temporary table";
	}

	@Override
	public String getCreateTemporaryTablePostfix() {
		return "on commit delete rows";
	}

	@Override
	public boolean dropTemporaryTableAfterUse() {
		return false;
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
	public boolean supportsEmptyInList() {
		return false;
	}
	
	@Override
	public boolean supportsExistsInSelect() {
		return false;
	}

	@Override
	public int getInExpressionCountLimit() {
		return PARAM_LIST_SIZE_LIMIT;
	}
	
	@Override
	public boolean forceLobAsLastValue() {
		return true;
	}

	@Override
	public boolean useFollowOnLocking() {
		return true;
	}
	
	@Override
	public String getNotExpression( String expression ) {
		return "not (" + expression + ")";
	}
	
	@Override
	public String getQueryHintString(String sql, List<String> hints) {
		final String hint = StringHelper.join( ", ", hints.iterator() );
		
		if ( StringHelper.isEmpty( hint ) ) {
			return sql;
		}

		final int pos = sql.indexOf( "select" );
		if ( pos > -1 ) {
			final StringBuilder buffer = new StringBuilder( sql.length() + hint.length() + 8 );
			if ( pos > 0 ) {
				buffer.append( sql.substring( 0, pos ) );
			}
			buffer.append( "select /*+ " ).append( hint ).append( " */" )
					.append( sql.substring( pos + "select".length() ) );
			sql = buffer.toString();
		}

		return sql;
	}
	
	@Override
	public int getMaxAliasLength() {
		// Oracle's max identifier length is 30, but Hibernate needs to add "uniqueing info" so we account for that,
		return 20;
	}

//	@Override
//	public CallableStatementSupport getCallableStatementSupport() {
//		// Oracle supports returning cursors
//		return StandardCallableStatementSupport.REF_CURSOR_INSTANCE;
//	}
}

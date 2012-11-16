locate, load, and link 

homogenous 
heterogeneous
multiple geographic locations, multi-lingual data, and maintenance and modification of product information within a centralized catalog to provide consistently accurate information to multiple channels in a cost-effective manner.

Creation and Receipt
Distribution
Use
Maintenance
Disposition


UI format type
	Long,
	Decimal,
	String,
	Date,
	Time,
	Datetime,
	Timestamp	




		log.warn( "The Oracle9Dialect dialect has been deprecated; use either Oracle9iDialect or Oracle10gDialect instead" );

62 		registerColumnType( Types.BIGINT, "number(19,0)" );
65 		registerColumnType( Types.INTEGER, "number(10,0)" );
66 		registerColumnType( Types.CHAR, "char(1 char)" );
67 		registerColumnType( Types.VARCHAR, 4000, "varchar2($l char)" );
68 		registerColumnType( Types.VARCHAR, "long" );
71 		registerColumnType( Types.DATE, "date" );
72 		registerColumnType( Types.TIME, "date" );
73 		registerColumnType( Types.TIMESTAMP, "timestamp" );
76 		registerColumnType( Types.NUMERIC, "number($p,$s)" );
77 		registerColumnType( Types.DECIMAL, "number($p,$s)" );
78 		registerColumnType( Types.BLOB, "blob" );
79 		registerColumnType( Types.CLOB, "clob" );


DB2
		registerColumnType( Types.BIT, "smallint" );
		registerColumnType( Types.BIGINT, "bigint" );
		registerColumnType( Types.SMALLINT, "smallint" );
		registerColumnType( Types.TINYINT, "smallint" );
		registerColumnType( Types.INTEGER, "integer" );
		registerColumnType( Types.CHAR, "char(1)" );
		registerColumnType( Types.VARCHAR, "varchar($l)" );
		registerColumnType( Types.FLOAT, "float" );
		registerColumnType( Types.DOUBLE, "double" );
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "time" );
		registerColumnType( Types.TIMESTAMP, "timestamp" );
		registerColumnType( Types.VARBINARY, "varchar($l) for bit data" );
		registerColumnType( Types.NUMERIC, "numeric($p,$s)" );
		registerColumnType( Types.BLOB, "blob($l)" );
		registerColumnType( Types.CLOB, "clob($l)" );
		registerColumnType( Types.LONGVARCHAR, "long varchar" );
		registerColumnType( Types.LONGVARBINARY, "long varchar for bit data" );
		registerColumnType( Types.BINARY, "varchar($l) for bit data" );
		registerColumnType( Types.BINARY, 254, "char($l) for bit data" );
		registerColumnType( Types.BOOLEAN, "smallint" );
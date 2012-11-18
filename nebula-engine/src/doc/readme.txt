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


Transaction data are data describing an event (the change as a result of a transaction) and is usually described with verbs. Transaction data always has a time dimension, a numerical value and refers to one or more objects (i.e. the reference data).
Typical transactions are:
Financial: orders, invoices, payments
Work: Plans, activity records
Logistics: Deliveries, storage records, travel records, etc.
Typical transaction processing systems (systems generating transactions) are SAP and Oracle Financials.

Master data is information that is key to the operation of a business. It is the primary focus of the Information Technology (IT) discipline of Master Data Management (MDM), and can include reference data. This key business information may include data about customers, products, employees, materials, suppliers, and the like. While it is often non-transactional in nature, it is not limited to non-transactional data, and often supports transactional processes and operations. For example, analysis and reporting is greatly dependent on an organization's master data. Because master data may not be stored and referenced centrally, but is often used by several functional groups and stored in different data systems across an organization, master data may be duplicated and inconsistent (and if so, inaccurate).
Thus Master Data is that persistent, non-transactional data that defines a business entity for which there is, or should be, an agreed-upon view across the organization. Care should be taken to properly version Master Data if the need arises to modify it. The versioning of Master Data can be a issue.



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
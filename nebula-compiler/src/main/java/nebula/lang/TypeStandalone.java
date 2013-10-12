package nebula.lang;

public enum TypeStandalone {
	Abstract,
	Type, 
	Basic, 
	/**
	 * Master data is information that is key to the operation of a business. 
	 * It is the primary focus of the Information Technology (IT) discipline 
	 * of Master Data Management (MDM), and can include reference data.
	 *  This key business information may include data about customers, 
	 *  products, employees, materials, suppliers, and the like. 
	 *  While it is often non-transactional in nature, it is not limited to 
	 *  non-transactional data, and often supports transactional processes 
	 *  and operations. For example, analysis and reporting is greatly 
	 *  dependent on an organization's master data. Because master data may 
	 *  not be stored and referenced centrally, but is often used by several 
	 *  functional groups and stored in different data systems across an organization,
	 *  master data may be duplicated and inconsistent (and if so, inaccurate).
	 *  
	 * */
	Master, 
	
	/**
	 * Transaction data are data describing an event (the change as a result of a transaction) and is usually described with verbs. Transaction data always has a time dimension, a numerical value and refers to one or more objects (i.e. the reference data).
	 * Typical transactions are:
	 *	Financial: orders, invoices, payments
	 *	Work: Plans, activity records
	 * 	Logistics: Deliveries, storage records, travel records, etc.
	 * */
	Transaction,	
	Config,
	Relation,
	Flow,
	Action,
	Mixin
}
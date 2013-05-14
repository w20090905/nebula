package nebula.data;

//TODO copy from hibernate need refact
public interface NamingStrategy {
	String classToTableName(String className);

	String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName);

	String columnName(String columnName);

	String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName,
			String referencedColumnName);

	String joinKeyColumnName(String joinedColumn, String joinedTable);

	String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn);

	String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable,
			String propertyName);

	String logicalColumnName(String columnName, String propertyName);

	String propertyToColumnName(String propertyName);

	String tableName(String tableName);
}

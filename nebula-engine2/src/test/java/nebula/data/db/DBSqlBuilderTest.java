package nebula.data.db;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.lang.TypeLoaderForFlowTest;
import nebula.lang.TypeLoaderForTest;
import junit.framework.TestCase;

public class DBSqlBuilderTest extends TestCase {
	String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
	String url = "jdbc:derby:memory:eh;create = true";
	String username = "user";
	String password = "password";
	DbConfiguration config;
	TypeLoaderForTest typeLoader;

	static class SubDbConfiguration extends DbConfiguration {
		public SubDbConfiguration(String driverClass, String dbUrl, String userName, String password) {
			super(driverClass, dbUrl, userName, password);
		}
	}

	DBSqlBuilder s;

	protected void setUp() throws Exception {
		super.setUp();
		config = new SubDbConfiguration(driverclass, url, username, password);
		typeLoader = new  TypeLoaderForTest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testDBSqlBuilder() {
		//@formatter:off
		String text = "" +
				"type Issue { \n" +
				"  !Name;" +
				"};\n";
		//@formatter:on		

		Type type = new TypeLoaderForTest().load(text);
		s = new DBSqlBuilder(config, type);
	}

	public final void testMakeMapping() {

		//@formatter:off
			String textRef = "" +
					"type Company { " +
					"	!Primary Name;" +
					"	*Core Name;" +
					"	Normal Name;" +
					"	Ignore Name;" +
					"};";
			String text = "" +
					"type TestPerson { " +
					"	!基本 Name;" +
					"   嵌入{" +
					"		!嵌入基本名称 Name;" +
					"		*嵌入嵌入{" +
					"			!Primary Name;" +
					"			*Core Name;" +
					"			Normal Name;" +
					"			Ignore Name;" +
					"		};" +
					"		#嵌入引用 Company;" +
					"		%嵌入递归 Company;" +
					"		嵌入列表基本[] Name;" +
					"		嵌入列表嵌入[]{" +
					"			!Primary Name;" +
					"			*Core Name;" +
					"			Normal Name;" +
					"			Ignore Name;" +
					"		};" +
					"	 };" +
					"	引用 Company;" +
					"	%递归引用 Company;" +
					"	列表基本[] Name;" +
					"	列表嵌入[]{" +
					"		列表嵌入基本名称 Name;" +
					"		列表嵌入嵌入{" +
					"			!Primary Name;" +
					"			*Core Name;" +
					"			Normal Name;" +
					"			Ignore Name;" +
					"		};" +
					"		列表嵌入引用 Company;" +
					"		%列表嵌入递归 Company;" +
					"	};" +
					"	引用[] Company;" +
					"	%递归引用[] Company;" +
					"};";
			//@formatter:on	
		Type type = typeLoader.load(textRef);
		type = typeLoader.load(text);
		s = new DBSqlBuilder(config, type);
		List<DBTable> tables = s.makeMapping(type);
		for(DBTable table : tables){
			System.out.println(table.getName());
			for(DbColumn column : table.getColumns()){
				System.out.println(column.columnName);
			}
		}
	}
	
	Calendar c = Calendar.getInstance();
	

}

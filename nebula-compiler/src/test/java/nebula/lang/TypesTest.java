package nebula.lang;

import junit.framework.TestCase;

public class TypesTest extends TestCase {
	Compiler compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new Compiler(new SystemTypeLoader());
	}

	final StringBuilder sb = new StringBuilder();

	public void test_types() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				"	Account;\n" + 
				"	Age;\n" + 
				"	Amount;\n" + 
				"	AutoID;\n" + 
				"	Birthday;\n" + 
				"	Code;\n" + 
				"	Comments;\n" + 
				"	Day;\n" + 
				"	Description;\n" + 
				"	EMail;\n" + 
				"	Fax;\n" + 
				"	Height;\n" + 
				"	ID;\n" + 
				"	Length;\n" + 
				"	Location;\n" + 
				"	Month;\n" + 
				"	Note;\n" + 
				"	Password;\n" + 
				"	Percent;\n" + 
				"	Phone;\n" + 
				"	Precision;\n" + 
				"	Price;\n" + 
				"	Quantity;\n" + 
				"	Rule;\n" + 
				"	Seq;\n" + 
				"	Sex;\n" + 
				"	Symbol;\n" + 
				"	Text;\n" + 
				"	URL;\n" + 
				"	Weight;\n" + 
				"	Year;\n" + 
				"	Zip;\n" + 
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(32, type.fields.size());
		int i = -1;
		i++;
		assertEquals("Account", type.fields.get(i).name);
		i++;
		assertEquals("Age", type.fields.get(i).name);
		i++;
		assertEquals("Amount", type.fields.get(i).name);
		i++;
		assertEquals("AutoID", type.fields.get(i).name);
		i++;
		assertEquals("Birthday", type.fields.get(i).name);
		i++;
		assertEquals("Code", type.fields.get(i).name);
		i++;
		assertEquals("Comments", type.fields.get(i).name);
		i++;
		assertEquals("Day", type.fields.get(i).name);
		i++;
		assertEquals("Description", type.fields.get(i).name);
		i++;
		assertEquals("EMail", type.fields.get(i).name);
		i++;
		assertEquals("Fax", type.fields.get(i).name);
		i++;
		assertEquals("Height", type.fields.get(i).name);
		i++;
		assertEquals("ID", type.fields.get(i).name);
		i++;
		assertEquals("Length", type.fields.get(i).name);
		i++;
		assertEquals("Location", type.fields.get(i).name);
		i++;
		assertEquals("Month", type.fields.get(i).name);
		i++;
		assertEquals("Note", type.fields.get(i).name);
		i++;
		assertEquals("Password", type.fields.get(i).name);
		i++;
		assertEquals("Percent", type.fields.get(i).name);
		i++;
		assertEquals("Phone", type.fields.get(i).name);
		i++;
		assertEquals("Precision", type.fields.get(i).name);
		i++;
		assertEquals("Price", type.fields.get(i).name);
		i++;
		assertEquals("Quantity", type.fields.get(i).name);
		i++;
		assertEquals("Rule", type.fields.get(i).name);
		i++;
		assertEquals("Seq", type.fields.get(i).name);
		i++;
		assertEquals("Sex", type.fields.get(i).name);
		i++;
		assertEquals("Symbol", type.fields.get(i).name);
		i++;
		assertEquals("Text", type.fields.get(i).name);
		i++;
		assertEquals("URL", type.fields.get(i).name);
		i++;
		assertEquals("Weight", type.fields.get(i).name);
		i++;
		assertEquals("Year", type.fields.get(i).name);
		i++;
		assertEquals("Zip", type.fields.get(i).name);

	}

}

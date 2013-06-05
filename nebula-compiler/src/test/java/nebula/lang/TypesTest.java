package nebula.lang;

import junit.framework.TestCase;

public class TypesTest extends TestCase {
	TypeLoaderForTest compiler;

	@Override
	protected void setUp() throws Exception {
		compiler = new TypeLoaderForTest(new SystemTypeLoader());
	}

	final StringBuilder sb = new StringBuilder();

	public void test_types() throws Exception {
		//@formatter:off
		String text = "" +
				"type Person { \n" +
				 " Account;\n" + 
				 " AccountNo;\n" + 
				 " Active;\n" + 
				 " Address;\n" + 
				 " Age;\n" + 
				 " Amount;\n" + 
				 " AutoID;\n" + 
				 " BBAN;\n" + 
				 " Birthday;\n" + 
				 " Code;\n" + 
				 " Comment;\n" + 
				 " Coordinates;\n" + 
				 " Count;\n" + 
				 " DateOfBirth;\n" + 
				 " Day;\n" + 
				 " Depth;\n" + 
				 " Description;\n" + 
				 " DUNS;\n" + 
				 " EMail;\n" + 
				 " Fax;\n" + 
				 " FirstName;\n" + 
				 " Frequency;\n" + 
				 " GAAP;\n" + 
				 " Height;\n" + 
				 " Help;\n" + 
				 " Host;\n" + 
				 " IBAN;\n" + 
				 " ID;\n" + 
				 " IPAddr;\n" + 
				 " JobTitle;\n" + 
				 " LastName;\n" + 
				 " Length;\n" + 
				 " Location;\n" + 
				 " Month;\n" + 
				 " MSG;\n" + 
				 " NAICS;\n" + 
				 " Note;\n" + 
				 " Password;\n" + 
				 " Percent;\n" + 
				 " Phone;\n" + 
				 " PIN;\n" + 
				 " PostalCode;\n" + 
				 " Precision;\n" + 
				 " Price;\n" + 
				 " Priority;\n" + 
				 " Probability;\n" + 
				 " Processed;\n" + 
				 " Processing;\n" + 
				 " Quantity;\n" + 
				 " Ranking;\n" + 
				 " Rate;\n" + 
				 " Ratio;\n" + 
				 " Rule;\n" + 
				 " SeqNo;\n" + 
				 " Sex;\n" + 
				 " SSN;\n" + 
				 " Subject;\n" + 
				 " SWIFTCode;\n" + 
				 " Symbol;\n" + 
				 " Tel;\n" + 
				 " Title;\n" + 
				 " Unit;\n" + 
				 " UOMEDICode;\n" + 
				 " UPC;\n" + 
				 " URL;\n" + 
				 " ValidFrom;\n" + 
				 " ValidTo;\n" + 
				 " Weight;\n" + 
				 " Width;\n" + 
				 " Year;\n" + 
				 " YesNo;\n" + 
				 " Zip;\n" + 
				"};";
		//@formatter:on		
		Type type = compiler.load(text);

		assertEquals("Person", type.name);

		assertEquals(72, type.fields.size());
		int i = -1;
		i++;	assertEquals("Account", type.fields.get(i).name);
		i++;	assertEquals("AccountNo", type.fields.get(i).name);
		i++;	assertEquals("Active", type.fields.get(i).name);
		i++;	assertEquals("Address", type.fields.get(i).name);
		i++;	assertEquals("Age", type.fields.get(i).name);
		i++;	assertEquals("Amount", type.fields.get(i).name);
		i++;	assertEquals("AutoID", type.fields.get(i).name);
		i++;	assertEquals("BBAN", type.fields.get(i).name);
		i++;	assertEquals("Birthday", type.fields.get(i).name);
		i++;	assertEquals("Code", type.fields.get(i).name);
		i++;	assertEquals("Comment", type.fields.get(i).name);
		i++;	assertEquals("Coordinates", type.fields.get(i).name);
		i++;	assertEquals("Count", type.fields.get(i).name);
		i++;	assertEquals("DateOfBirth", type.fields.get(i).name);
		i++;	assertEquals("Day", type.fields.get(i).name);
		i++;	assertEquals("Depth", type.fields.get(i).name);
		i++;	assertEquals("Description", type.fields.get(i).name);
		i++;	assertEquals("DUNS", type.fields.get(i).name);
		i++;	assertEquals("EMail", type.fields.get(i).name);
		i++;	assertEquals("Fax", type.fields.get(i).name);
		i++;	assertEquals("FirstName", type.fields.get(i).name);
		i++;	assertEquals("Frequency", type.fields.get(i).name);
		i++;	assertEquals("GAAP", type.fields.get(i).name);
		i++;	assertEquals("Height", type.fields.get(i).name);
		i++;	assertEquals("Help", type.fields.get(i).name);
		i++;	assertEquals("Host", type.fields.get(i).name);
		i++;	assertEquals("IBAN", type.fields.get(i).name);
		i++;	assertEquals("ID", type.fields.get(i).name);
		i++;	assertEquals("IPAddr", type.fields.get(i).name);
		i++;	assertEquals("JobTitle", type.fields.get(i).name);
		i++;	assertEquals("LastName", type.fields.get(i).name);
		i++;	assertEquals("Length", type.fields.get(i).name);
		i++;	assertEquals("Location", type.fields.get(i).name);
		i++;	assertEquals("Month", type.fields.get(i).name);
		i++;	assertEquals("MSG", type.fields.get(i).name);
		i++;	assertEquals("NAICS", type.fields.get(i).name);
		i++;	assertEquals("Note", type.fields.get(i).name);
		i++;	assertEquals("Password", type.fields.get(i).name);
		i++;	assertEquals("Percent", type.fields.get(i).name);
		i++;	assertEquals("Phone", type.fields.get(i).name);
		i++;	assertEquals("PIN", type.fields.get(i).name);
		i++;	assertEquals("PostalCode", type.fields.get(i).name);
		i++;	assertEquals("Precision", type.fields.get(i).name);
		i++;	assertEquals("Price", type.fields.get(i).name);
		i++;	assertEquals("Priority", type.fields.get(i).name);
		i++;	assertEquals("Probability", type.fields.get(i).name);
		i++;	assertEquals("Processed", type.fields.get(i).name);
		i++;	assertEquals("Processing", type.fields.get(i).name);
		i++;	assertEquals("Quantity", type.fields.get(i).name);
		i++;	assertEquals("Ranking", type.fields.get(i).name);
		i++;	assertEquals("Rate", type.fields.get(i).name);
		i++;	assertEquals("Ratio", type.fields.get(i).name);
		i++;	assertEquals("Rule", type.fields.get(i).name);
		i++;	assertEquals("SeqNo", type.fields.get(i).name);
		i++;	assertEquals("Sex", type.fields.get(i).name);
		i++;	assertEquals("SSN", type.fields.get(i).name);
		i++;	assertEquals("Subject", type.fields.get(i).name);
		i++;	assertEquals("SWIFTCode", type.fields.get(i).name);
		i++;	assertEquals("Symbol", type.fields.get(i).name);
		i++;	assertEquals("Tel", type.fields.get(i).name);
		i++;	assertEquals("Title", type.fields.get(i).name);
		i++;	assertEquals("Unit", type.fields.get(i).name);
		i++;	assertEquals("UOMEDICode", type.fields.get(i).name);
		i++;	assertEquals("UPC", type.fields.get(i).name);
		i++;	assertEquals("URL", type.fields.get(i).name);
		i++;	assertEquals("ValidFrom", type.fields.get(i).name);
		i++;	assertEquals("ValidTo", type.fields.get(i).name);
		i++;	assertEquals("Weight", type.fields.get(i).name);
		i++;	assertEquals("Width", type.fields.get(i).name);
		i++;	assertEquals("Year", type.fields.get(i).name);
		i++;	assertEquals("YesNo", type.fields.get(i).name);
		i++;	assertEquals("Zip", type.fields.get(i).name);

	}

}

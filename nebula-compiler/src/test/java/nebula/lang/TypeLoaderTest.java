package nebula.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

public class TypeLoaderTest extends TestCase {

	TypeLoader loader;
	File root1;
	File root2;

	protected void setUp() throws Exception {

		loader = new SystemTypeLoader();
	}

	public void testActualFields_Key() {
		//@formatter:off
		String text = "" +
				"type Person { " +
				"	!Name;" +
				"   !Test{" +
				"		!Key Name;" +
				"		*Core Age;" +
				"		#Require Age;" +
				"		?Ignore Age;" +
				"	 };" +
				"};";
		//@formatter:on		

		List<Type> fs = loader.defineNebula(new StringReader(text));
		Type type = fs.get(0);
//
//		List<Field> fields = type.actualFields;
//		int i = 0;
//		assertEquals("Name", fields.get(i).name);
//		assertEquals(Importance.Key, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Key", fields.get(i).name);
//		assertEquals(Importance.Key, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Core", fields.get(i).name);
//		assertEquals(Importance.Core, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Require", fields.get(i).name);
//		assertEquals(Importance.Require, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Ignore", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		assertEquals(i + 1, fields.size());
	}
//	public void testActualFields_Core() {
//		//@formatter:off
//		String text = "" +
//				"type Person { " +
//				"	!Name;" +
//				"   *Test{" +
//				"		!Key Name;" +
//				"		*Core Age;" +
//				"		#Require Age;" +
//				"		?Ignore Age;" +
//				"	 };" +
//				"};";
//		//@formatter:on		
//
//		List<Type> fs = loader.defineNebula(new StringReader(text));
//		Type type = fs.get(0);
//
//		List<Field> fields = type.actualFields;
//		int i = 0;
//		assertEquals("Name", fields.get(i).name);
//		assertEquals(Importance.Key, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Key", fields.get(i).name);
//		assertEquals(Importance.Core, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Core", fields.get(i).name);
//		assertEquals(Importance.Core, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Require", fields.get(i).name);
//		assertEquals(Importance.Require, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Ignore", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		assertEquals(i + 1, fields.size());
//	}
//
//	public void testActualFields_Require() {
//		//@formatter:off
//		String text = "" +
//				"type Person { " +
//				"	!Name;" +
//				"   Test{" +
//				"		!Key Name;" +
//				"		*Core Age;" +
//				"		#Require Age;" +
//				"		?Ignore Age;" +
//				"	 };" +
//				"};";
//		//@formatter:on		
//
//		List<Type> fs = loader.defineNebula(new StringReader(text));
//		Type type = fs.get(0);
//
//		List<Field> fields = type.actualFields;
//		int i = 0;
//		assertEquals("Name", fields.get(i).name);
//		assertEquals(Importance.Key, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Key", fields.get(i).name);
//		assertEquals(Importance.Require, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Core", fields.get(i).name);
//		assertEquals(Importance.Require, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Require", fields.get(i).name);
//		assertEquals(Importance.Require, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Ignore", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		assertEquals(i + 1, fields.size());
//	}
//	
//
//	public void testActualFields_Ignore() {
//		//@formatter:off
//		String text = "" +
//				"type Person { " +
//				"	!Name;" +
//				"   ?Test{" +
//				"		!Key Name;" +
//				"		*Core Age;" +
//				"		#Require Age;" +
//				"		?Ignore Age;" +
//				"	 };" +
//				"};";
//		//@formatter:on		
//
//		List<Type> fs = loader.defineNebula(new StringReader(text));
//		Type type = fs.get(0);
//
//		List<Field> fields = type.actualFields;
//		int i = 0;
//		assertEquals("Name", fields.get(i).name);
//		assertEquals(Importance.Key, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Key", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//		i++;
//		assertEquals("Test_Core", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Require", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		i++;
//		assertEquals("Test_Ignore", fields.get(i).name);
//		assertEquals(Importance.Unimportant, fields.get(i).importance);
//
//		assertEquals(i + 1, fields.size());
//	}
}
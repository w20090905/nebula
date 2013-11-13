package http.resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import nebula.data.Broker;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.db.DbConfiguration;
import nebula.data.impl.TypeDatastore;
import nebula.lang.EditableTypeLoader;
import nebula.lang.SystemTypeLoader;
import nebula.lang.Type;
import nebula.lang.TypeLoader;
import nebula.simpletemplate.ST;
import nebula.simpletemplate.STGroup;
import nebula.simpletemplate.STGroupFile;
import nebula.simpletemplate.STGroupDir;
import nebula.simpletemplate.CompiledST;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TypeSimpleTemplateResouceTest extends TestCase {
	public static final String tmpdir = "tmp";// System.getProperty("java.io.tmpdir");
	Log log = LogFactory.getLog(this.getClass());

	// Configuration templateConfig;
	DataStore<Entity> attributes;
	TypeLoader typeLoader;
	// DataRepos dataWareHouse;
	TypeDatastore typeBrokers;

	static final String PATH_OF_ROOT = "htdocs2";

	// static final String APP_DEFINE_PATH = "app_define_path";
	// static final String DB_DRIVERCLASS = "db_driverclass";
	// static final String DB_URL = "db_url";
	// static final String DB_USERNAME = "db_username";
	// static final String DB_PASSWORD = "db_password";

	protected void setUp() throws Exception {

		// String driverclass = "org.apache.derby.jdbc.EmbeddedDriver";
		// String dburl = "jdbc:derby:memory:eh;create = true";
		// String username = "user";
		// String password = "password";

		// ROOT Folder
		File root = null;
		URL url = this.getClass().getResource("/" + PATH_OF_ROOT + "/WEB-INF/web.xml");
		if (url != null) {
			root = new File(url.getPath()).getParentFile().getParentFile();
		}

		if (root == null) {
			root = new File(PATH_OF_ROOT);
		}

		if (!root.exists()) {
			throw new RuntimeException("cannot find " + PATH_OF_ROOT);
		}

		// Type Define locator
		EditableTypeLoader typeLoader = new EditableTypeLoader(new SystemTypeLoader(), new File("apps/system"));
		typeLoader.registerPath(new File("apps/ids"));

		this.typeBrokers = new TypeDatastore(typeLoader);

		// DbConfiguration dbConfiguration =
		// DbConfiguration.getEngine(driverclass, dburl, username, password);

		// dataWareHouse = new DbDataRepos(this.typeBrokers, dbConfiguration);

		// // Freemarker
		// templateConfig = new Configuration();
		// templateConfig.setDefaultEncoding("utf-8");
		// templateConfig.setEncoding(Locale.getDefault(), "utf-8");
		// templateConfig.setTemplateUpdateDelay(1);
		// templateConfig.setNumberFormat("0.####");
		// templateConfig.setDirectoryForTemplateLoading(new File(root,
		// "template"));

		// this.attributes = dataWareHouse.define(String.class, Entity.class,
		// "Attribute");

		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testTypeName() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "name=${type.name},standalone=${type.standalone}";
		String expected = "name=Person,standalone=Master";

		type = Broker.valueOf(type);

		ST st = new ST(template, '$', '}');
		assertEquals(expected, st.render(type));
	}

	public final void testWithSubTemplate() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "${type.fields : {f| { ${f.name} , ${f.type.name} ${f.key} ,${f.core} \\} }}";
		String expected = "{ Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		type = Broker.brokerOf(type).get();

		ST st = new ST(template, '$', '}');
		assertEquals(expected, st.render(type));
	}

	public final void testWithSubSubTemplate() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		String template = "${type.fields : {f| { ${f.name} , ${f.type.name} ${f.key} ,${f.core} ${f,f.name : {f,n | ${f.name} n } } \\} }}";
		String expected = "{ Name , Name true ,false Name n  } { Birthday , Birthday false ,false Birthday n  } { Height , Height false ,false Height n  } { Age , Age false ,false Age n  } { Sex , Sex false ,false Sex n  } { Detail , Person$Detail false ,false Detail n  } { Company , Company false ,false Company n  } { Roles1 , Text false ,false Roles1 n  } { Roles2 , Long false ,false Roles2 n  } { Roles3 , Date false ,false Roles3 n  } { Roles4 , Time false ,false Roles4 n  } { Education , Person$Education false ,false Education n  } ";

		type = Broker.brokerOf(type).get();

		ST st = new ST(template, '$', '}');
		assertEquals(expected, st.render(type));
	}

	public final void testWithGroupFile() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		// @formatter:off
		String template = "type(type) ::= << ${type.name} ${type.fields : field()}>>\n" +
				"field(f) ::= <<{ ${f.name} , ${f.type.name} ${f.key} ,${f.core} } >>\n";
		// @formatter:on
		String expected = "Person { Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		writeFile(tmpdir, "type.stg", template);

		STGroup group = new STGroupFile(tmpdir + "/" + "type.stg", '$', '}');
		CompiledST tmp = group.lookupTemplate("type");

		type = Broker.brokerOf(type).get();

		assertEquals(expected, tmp.exec(type));
	}

	// STGroup group = new STGroupPath("tmp");

	public final void testWithGroupFilePer() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");
		// @formatter:off
		String template = "type(type) ::= << ${type.name} ${type.fields : field()}>>\n" +
				"field(f) ::= <<{ ${f.name} , ${f.type.name} ${f.key} ,${f.core} } >>\n";
		// @formatter:on
		String expected = "Person { Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		writeFile(tmpdir, "type.stg", template);

		STGroup group = new STGroupFile(tmpdir + "/" + "type.stg", '$', '}');
		CompiledST tmp = group.lookupTemplate("type");

		assertEquals(expected, tmp.exec(type));
	}

	public void testGroupPath() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");

		String templateType = " ${type.name} ${type.fields : field()}";
		String templateField = " { ${f.name} , ${f.type.name} ${f.key} ,${f.core} } ";
		String expected = "Person { Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		writeFile(tmpdir, "type.st", templateType);
		writeFile(tmpdir, "field.st", templateField);

		STGroup group = new STGroupDir("tmp", '$', '}');
		CompiledST tmp = group.lookupTemplate("type");

		type = Broker.brokerOf(type).get();

		assertEquals(expected, tmp.exec(type));
	}

	public void testGroupPath_real() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");

		String expected = "Person { Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } ";

		STGroup group = new STGroupFile("tmp/typeList.stg", '$', '$');
		CompiledST tmpl = group.lookupTemplate("type");

		type = Broker.brokerOf(type).get();

//		assertEquals(expected, tmpl.exec(type));
//		System.out.println(trim(tmpl.exec(type)));
		System.out.println(tmpl.exec(type));
	}

	public void testFreeMarker_real() throws Exception {
		Type type = this.typeBrokers.getBroker("Person");

		{
			String desc = "type freemarker";

			Configuration templateConfiguration = new Configuration();
			Template ft = new Template("test", new FileReader("tmp/master_basic_list.html.ftl"), templateConfiguration);

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("type", type);

			StringWriter sw = new StringWriter();
			ft.process(root, sw);
			System.out.println(trim(sw.toString()));

		}

	}

	private String trim(String source){
		return source.toString().replaceAll("\r\n", "\n").replaceAll("\n\n", "\n").replaceAll("\n\n", "\n").replaceAll("\n\n", "\n").replaceAll("\n\n", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("\n\t", "\n").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("\n ", "\n");
	}

	/*
	 * public final void testLoadIssueTypePer() throws Exception { Type type =
	 * this.typeBrokers.getBroker("Person"); String template =
	 * "${type.fields : {f| { ${f.name} , ${f.type.name} ${f.key} ,${f.core} \\} }}"
	 * ; String expected =
	 * "{ Name , Name true ,false } { Birthday , Birthday false ,false } { Height , Height false ,false } { Age , Age false ,false } { Sex , Sex false ,false } { Detail , Person$Detail false ,false } { Company , Company false ,false } { Roles1 , Text false ,false } { Roles2 , Long false ,false } { Roles3 , Date false ,false } { Roles4 , Time false ,false } { Education , Person$Education false ,false } "
	 * ;
	 * 
	 * type = Broker.brokerOf(type).get();
	 * 
	 * ST st = new ST(template);
	 * 
	 * int MAX = 1000 * 10; { String desc = "stringtemplate"; // setUp
	 * 
	 * // prepare long start, end, nanoAll, nanoEvery;
	 * 
	 * start = System.nanoTime(); for (int i = 0; i < MAX; i++) {
	 * st.render(type); } end = System.nanoTime(); nanoAll = end - start;
	 * nanoEvery = nanoAll / MAX;
	 * 
	 * System.out.printf(
	 * "[ %20s ]    All :%8d ms;    every : %8d nano;    one second : %8d times;\n"
	 * , desc, (nanoAll / (1000 * 1000)), +nanoEvery, 1000 * 1000 * 1000 /
	 * nanoEvery); } }
	 */

	public static void writeFile(String dir, String fileName, String content) {
		try {
			File f = new File(dir, fileName);
			if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(content);
			bw.close();
			w.close();
		} catch (IOException ioe) {
			System.err.println("can't write file");
			ioe.printStackTrace(System.err);
		}
	}
}

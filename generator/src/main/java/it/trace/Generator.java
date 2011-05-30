package it.trace;

import it.trace.model.Field;
import it.trace.model.Type;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class Generator {

	protected static String TO_PATH = "E:/Project/Trace/workspace/bookstore/gen/auto/";		// TODO change me
	protected static String JSP_TO_PATH = "E:/Project/Trace/workspace/bookstore/WebRoot/";	// TODO change me

	protected static String FREE_MARKER_TEMPLETE_PATH = Generator.class.getResource("").getFile();
	
	private static Configuration FREE_MARKER_CONFIG;
	
	static {
		FREE_MARKER_CONFIG = new Configuration();
		try {
			FREE_MARKER_CONFIG.setDirectoryForTemplateLoading(new File(
					FREE_MARKER_TEMPLETE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FREE_MARKER_CONFIG.setObjectWrapper(new DefaultObjectWrapper());
	}
	
	public static void generate(Type t) {
		try {
			System.out.println("generator start...");
			generateDDL(t);
			generateEntity(t);
			generateDao(t);
			generateService(t);
			generateAction(t);
			generateJsp(t);
			
			System.out.println("generator successful.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateFromFreeMarker(Object rootMap, String fltName, String toFileName) throws Exception {
		
		Template temp = FREE_MARKER_CONFIG.getTemplate(fltName);

		FileUtils.forceMkdir(new File(toFileName).getParentFile());

		Writer out = new OutputStreamWriter(new FileOutputStream(toFileName));
		temp.process(rootMap, out);
		out.flush();
		out.close();
	}
	
	public static void generateDDL(Type t) throws Exception {
		generateFromFreeMarker(t, "ddl.flt", TO_PATH + "ddl/" + t.getName() + ".sql");
	}
	
	public static void generateEntity(Type t) throws Exception {
		generateFromFreeMarker(t, "entity.flt", TO_PATH + "entity/" + t.getName() + ".java");
	}

	public static void generateDao(Type t) throws Exception {
		generateFromFreeMarker(t, "basedao.flt", TO_PATH + "dao/AbstractBaseDao.java");
		generateFromFreeMarker(t, "dao.flt", TO_PATH + "dao/" + t.getName() + "Dao.java");
	}

	public static void generateService(Type t) throws Exception {
		generateFromFreeMarker(t, "service.flt", TO_PATH + "service/" + t.getName() + "Manager.java");
	}

	public static void generateAction(Type t) throws Exception {
		generateFromFreeMarker(t, "action.flt", TO_PATH + "action/" + t.getName() + "Action.java");
	}

	public static void generateJsp(Type t) throws Exception {
		generateFromFreeMarker(t, "jsp_selectAll.flt", JSP_TO_PATH + t.getName() + "/selectAll.jsp");
		generateFromFreeMarker(t, "jsp_toInsert.flt", JSP_TO_PATH + t.getName() + "/toInsert.jsp");
		generateFromFreeMarker(t, "jsp_toUpdate.flt", JSP_TO_PATH + t.getName() + "/toUpdate.jsp");
		generateFromFreeMarker(t, "jsp_toDelete.flt", JSP_TO_PATH + t.getName() + "/toDelete.jsp");
	}
	
	public static void main(String[] args) {
		
		Type bt = new Type("Book");
		bt.addField(new Field("pkid", long.class,true, null,true));
		bt.addField(new Field("type", int.class, null, false));
		bt.addField(new Field("name", String.class, null, false));
		bt.addField(new Field("description", String.class, null, false));
		bt.addField(new Field("publisher", String.class, null, false));
		bt.addField(new Field("count", int.class, 0, true));
		bt.addField(new Field("price", float.class, 0, true));
		
		Generator.generate(bt);
		
	}
}

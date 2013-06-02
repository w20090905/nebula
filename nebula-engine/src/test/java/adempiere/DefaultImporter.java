package adempiere;

import static adempiere.DBColumnType.Bit;
import static adempiere.DBColumnType.Blob;
import static adempiere.DBColumnType.Char;
import static adempiere.DBColumnType.Date;
import static adempiere.DBColumnType.Datetime;
import static adempiere.DBColumnType.Decimal;
import static adempiere.DBColumnType.Long;
import static adempiere.DBColumnType.NVarchar;
import static adempiere.DBColumnType.String;
import static adempiere.DBColumnType.Text;
import static adempiere.DBColumnType.Time;
import static adempiere.DBColumnType.Timestamp;
import static adempiere.DBColumnType.Varchar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nebula.lang.TypeStandalone;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DefaultImporter {

	List<Type> types = Lists.newArrayList();
	Map<String, Type> typeMapByName = Maps.newHashMap();
	Map<String, Type> typeMapByRawName = Maps.newHashMap();

	Map<String, String> tables = Maps.newHashMap();

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

	List<RuleBuilder> rules = Lists.newArrayList();

	EnumMap<DBColumnType, List<String>> dbColumnMap = Maps.newEnumMap(DBColumnType.class);

	public DefaultImporter() {
		dbColumnMap.put(Char, Lists.newArrayList("CHAR"));
		dbColumnMap.put(Varchar, Lists.newArrayList("VARCHAR", "VARCHAR2"));
		dbColumnMap.put(NVarchar, Lists.newArrayList("NVARCHAR", "NVARCHAR2"));
		dbColumnMap.put(Bit, Lists.newArrayList("BIT"));
		dbColumnMap.put(Long, Lists.newArrayList("INT", "LONG", "NUMBER"));
		dbColumnMap.put(Decimal, Lists.newArrayList("FLOAT", "NUMERIC"));
		dbColumnMap.put(Date, Lists.newArrayList("DATE"));
		dbColumnMap.put(Time, Lists.newArrayList("TIME"));
		dbColumnMap.put(Datetime, Lists.newArrayList("DATETIME"));
		dbColumnMap.put(Timestamp, Lists.newArrayList("BIT"));
		dbColumnMap.put(String, Lists.newArrayList("CHAR", "VARCHAR", "VARCHAR2", "NVARCHAR", "NVARCHAR2", "TEXT"));
		dbColumnMap.put(Text, Lists.newArrayList("TEXT", "LONGTEXT"));
		dbColumnMap.put(Blob, Lists.newArrayList("BLOB", "LONGBLOB", "CLOB"));
	}

	public void initRules() {
		// when(EndWith).
	}

	// Load and parse XML file into DOM
	public Document parse(String filePath) {
		Document document = null;
		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public Type addType(String rawName, String name, Type type) {
		types.add(type);
		typeMapByName.put(name, type);
		typeMapByRawName.put(rawName, type);
		return type;
	}

	public Type addType(String rawName, String name, String remarks) {
		return addType(rawName, name, new Type(rawName, name, remarks));
	}

	public Rule when(MatchPattern... ruleTypes) {
		RuleBuilder builder = new RuleBuilder(ruleTypes);
		this.rules.add(0, builder);
		return builder;
	}

	public Rule when() {
		RuleBuilder builder = new RuleBuilder();
		this.rules.add(0, builder);
		return builder;
	}

	protected boolean match(String typename, DBColumnType type) {
		for (String dbtype : this.dbColumnMap.get(type)) {
			if (dbtype.equalsIgnoreCase(typename)) {
				return true;
			}
		}
		return false;
	}

	public Field match(Field field) {
		Field lastResult = null;
		if ("Mail_Notification".equalsIgnoreCase(field.name)) {
			System.out.println("sdfdsf");
		}
		for (RuleBuilder rule : rules) {

			boolean matched = true;
			DBColumnType matchedColumnType = null;
			if (rule.dbTypes != null) {
				matched = false;
				for (DBColumnType type : rule.dbTypes) {
					if (this.match(field.typename, type)) {
						matched = true;
						matchedColumnType = type;
						break;
					}
				}
			}

			if (!matched) continue;
			System.out.println("%%%%" + rule.tableName);
			if (rule.tableName != null) {
				if (!field.resideType.rawName.equalsIgnoreCase(rule.tableName)) {
					continue;
				}else{
					System.out.println("match table");
				}
			}

			MatchPattern matchedMatchPattern = null;
			String matchedName = null;
			if (rule.with != null) {
				boolean machPattern = false;
				for (MatchPattern pattern : rule.ruleTypes) {
					matchedName = pattern.match(field.name, rule.with);
					if (matchedName != null) {
						machPattern = true;
						matchedMatchPattern = pattern;
						break;
					}
				}
				matched = matched && machPattern;
			}

			if (matched) {
				field.comment = "Matched\t" + field.name + "\t"
						+ (matchedMatchPattern == null ? "" : matchedMatchPattern.name()) + "\t" + matchedName + "\t"
						+  (matchedColumnType == null ? "" : matchedColumnType.name()) ;
				for (Action action : rule.actions) {
					action.apply(field, matchedName);
				}
				lastResult = field;
			}
		}
		return null;
	}

	public void output(String outputFolderName) throws IOException {

		File outputFolder = new File(outputFolderName);

		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		} else { //
			// for (File file : outputFolder.listFiles()) {
			// // file.delete();
			// }
		}
		OutputStreamWriter out = null;
		StringBuilder sb = new StringBuilder(5000);
		for (Type type : types) {
			out = new OutputStreamWriter(new FileOutputStream(new File(outputFolder, type.name + ".nebula")));

			sb.setLength(0);
			sb.append("@Remarks(\"" + escape(type.remarks) + "\")\n");
			sb.append("@Refby(\"" + type.cntReference + "\")\n");
			if (type.standalone == TypeStandalone.Transaction) {
				sb.append("tx " + type.name + " {\n");
			} else {
				sb.append("type " + type.name + " {\n");
			}

			for (Field field : type.fields) {
				if (field.remarks.length() > 0) {
					sb.append("\t@Remarks(\"" + escape(field.remarks) + "\")\n");
				}
				sb.append("\t");
				if (field.nullable) {
					sb.append("?");
				}
				if (field.isKey) {
					sb.append("!");
				}
				if (!field.nullable ){
					if("Name Subject Title".toUpperCase().indexOf(field.resultTypeName.toUpperCase())>=0) {
						sb.append("*");
					}
				} 

				if (field.resultName.equalsIgnoreCase(field.resultTypeName)) {
					sb.append(field.resultTypeName);
				}else{
					sb.append(field.resultName);		
					sb.append(" " + field.resultTypeName);			
				}
				sb.append(";");

				if (field.defaultValue.length() > 0) {
					sb.append("/" + "* " + escape(field.defaultValue) + " *" + "/");
				}
				sb.append("\n");
			}
			sb.append("};\n\n");
			out.write(sb.toString());
			out.close();
		}

	}

	public void read(String outputFolderName, Element rootElement) throws IOException {
		NodeList nodeList = rootElement.getElementsByTagName("table");
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String tableName = element.getAttribute("name");
				this.tables.put(tableName, tableName);
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				this.readTable(element);
			}
		}
	}

	public Type readTable(Element table) throws IOException {
		String tableName = table.getAttribute("name");
		String remarks = table.getAttribute("remarks");

		// buf += "@NumRows(" + table.getAttribute("numRows") + ")\n";

		Type type = this.addType(tableName, refineName(tableName), remarks);

		NodeList nodes = table.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if ("column".equals(node.getNodeName())) {
					readColumn(type, (Element) node);
				} else if ("primaryKey".equals(node.getNodeName())) {
					String primaryKey = ((Element) node).getAttribute("column");
					type.addKeyField(primaryKey);
				}
			}
		}
		// log.trace("loaded " + type.rawName);
		return type;
	}

	public void readColumn(Type type, Element column) throws IOException {
		String rawColName = column.getAttribute("name");

		String tColName = null;
		String tTypeName = null;

		tColName = refineName(rawColName);

		String defaultValue = column.getAttribute("defaultValue");

		Field field = type.addField(type, rawColName, tColName, tTypeName, defaultValue);
		field.autoUpdated = "true".equals(column.getAttribute("autoUpdated"));
		field.digits = Integer.parseInt(column.getAttribute("digits"));
		field.nullable = "true".equals(column.getAttribute("nullable"));
		field.remarks = column.getAttribute("remarks");
		field.size = column.getAttribute("size");
		field.typename = column.getAttribute("type");
		field.name = column.getAttribute("name");

		NodeList nodes = column.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if ("parent".equals(node.getNodeName())) {
					Element parent = (Element) node;
					field.resultTypeName = refineName(parent.getAttribute("table"));
					field.isForeignKey = true;
					field.foreignKeyTable = parent.getAttribute("table");
				} else if ("child".equals(node.getNodeName())) {
					type.cntReference++;
				}
			}
		}
	}

	public DBColumnType typeFromString(String type) {
		return DBColumnType.Varchar;
	}

	class Type {
		String rawName;
		String name;
		String remarks;
		long cntReference;
		String comment = "";
		TypeStandalone standalone = TypeStandalone.Abstract;

		public Type(String rawName, String name, String remarks) {
			this.rawName = rawName;
			this.name = name;
			this.remarks = remarks;
		}

		List<Field> fields = Lists.newArrayList();
		Map<String, Field> fieldMapByName = Maps.newHashMap();
		Map<String, Field> fieldMapByRawName = Maps.newHashMap();

		public Field addField(String rawName, String name, Field field) {
			fields.add(field);
			fieldMapByName.put(name, field);
			fieldMapByRawName.put(rawName, field);
			return field;
		}

		public Field addField(Type resideType, String rawName, String name, String fieldType, String defaultValue) {
			return addField(rawName, name, new Field(resideType, rawName, name, fieldType, defaultValue, false));
		}

		public void addKeyField(String primaryKey) {
			Field key = fieldMapByRawName.get(primaryKey);
			key.isKey = true;
		}
	}

	class Field {
		final Type resideType;
		boolean autoUpdated;
		String defaultValue;
		int digits;
		String name;
		boolean nullable;
		String remarks;
		String size;
		String typename;

		String comment;

		boolean skip = false;
		String resultName;
		String resultTypeName;
		boolean isKey = false;
		boolean isForeignKey = false;
		String foreignKeyTable;

		public Field(Type resideType, String rawName, String name) {
			this(resideType, rawName, name, name, null, false);
		}

		public Field(Type resideType, String rawName, String name, String type) {
			this(resideType, rawName, name, type, null, false);
		}

		public Field(Type resideType, String rawName, String name, String type, String defaultValue) {
			this(resideType, rawName, name, type, defaultValue, false);
		}

		public Field(Type resideType, String rawName, String name, String type, String defaultValue, boolean isKey) {
			this.resideType = resideType;
			this.name = rawName;
			this.resultName = name;
			this.resultTypeName = type;
			this.defaultValue = defaultValue;
			this.isKey = isKey;
		}

		@Override
		public String toString() {
			return name + "\t" + typename + "\t" + resultName + "\t" + resultTypeName + " " + "\t" + defaultValue
					+ "\t" + digits + "\t" + nullable + "\t" + remarks + "\t" + size + "\t" + comment + " " + "\t"
					+ skip + "\t" + autoUpdated + "\t" + isKey;
		}

	}

	public String escape(String text) {
		return text.replace('\"', '\'').replace('\n', ' ').replace('\r', ' ');
	}

	static final String[] revs = ("CREATED issues issue is display field where Enforce Support release database"
			+ " Logger source method class Function ldap Date Selection client Alert role Frequency "
			+ "Keep Select Form Event Change Request Auto Smtp Store unix windows 	File Discount "
			+ " Line Bpartner cash Readonly Other Error Post Record entity Access Centrally Dependent "
			+ "Label Validation Old New Text Dimension format paper Margin Hdr Image Footer header "
			+ "value  Paint Printer Print orderby Line Procedure Beta Server show Unrealized Docno Overwrite "
			+ "Create Days between after Charge Interest Dunning Doctype Invoice  payment Times Tender "
			+ " Archive Conversion Amt Foreign Document Fee Total Qty Price Freight Resource Tax"
			+ " Accept Bpcontact Delivery Priority Grand Product Creditcard Voice Writeoff Proxy Attribute"
			+ "Require Planned Project  Reference balance Committed Sales Standard Notification"
			+ " Country System Remuneration  Revenue Unearned Gross " + " UnixAttachmentpath Language Decimal")
			.toUpperCase().split(" ");

	public String refineName(String name) {
		if (name == null) return null;
		for (String rev : revs) {
			name = name.replaceFirst("^" + rev, rev + "_");
			name = name.replaceFirst("_" + rev, "_" + rev + "_");
		}
		name = name.replaceFirst("__", "_");

		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
	}

}

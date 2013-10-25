package typeimport;

import static typeimport.DBColumnType.Bit;
import static typeimport.DBColumnType.Blob;
import static typeimport.DBColumnType.Char;
import static typeimport.DBColumnType.Date;
import static typeimport.DBColumnType.Datetime;
import static typeimport.DBColumnType.Decimal;
import static typeimport.DBColumnType.Long;
import static typeimport.DBColumnType.NVarchar;
import static typeimport.DBColumnType.String;
import static typeimport.DBColumnType.Text;
import static typeimport.DBColumnType.Time;
import static typeimport.DBColumnType.Timestamp;
import static typeimport.DBColumnType.Varchar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.io.PatternFilenameFilter;

public class DefaultImporter {
	int MAX = 1500;
	final boolean manageRelationInDB;
	final boolean seperateWithUnder;
	final String indenfyKey;

	List<Type> types = Lists.newArrayList();
	Map<String, Type> typeMapByName = Maps.newHashMap();
	Map<String, Type> typesByRawName = Maps.newHashMap();

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

	List<RuleBuilder> rules = Lists.newArrayList();

	EnumMap<DBColumnType, List<String>> dbColumnMap = Maps.newEnumMap(DBColumnType.class);

	final static String WORDS_File_NAME = "words.txt";

	Map<String, String> definedWords = Maps.newHashMap();

	private void loadWords() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(WORDS_File_NAME)));
			String line = null;
			while ((line = in.readLine()) != null) {
				String word = line.trim().toLowerCase();
				if (word.length() == 0) continue;
				definedWords.put(word, word);
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void addDefineWords(String strWords) {
		String[] words = strWords.split(" ");
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if (word.length() == 0) continue;
			definedWords.put(word, word);
		}
	}

	File importFile = null;
	File rootFolder = null;

	public void load() throws IOException {

		Document document = this.parse(importFile);
		// get root element
		Element rootElement = document.getDocumentElement();
		this.readAll(rootElement);
		this.analyze(this.types);
		this.outputAll(rootFolder);
	}

	final String legacyName;

	public DefaultImporter(final String name, boolean manageRelationInDB, boolean seperateWithUnder) {
		this(name, manageRelationInDB, seperateWithUnder, "ID");
		this.loadWords();
	}

	public DefaultImporter(final String name, boolean manageRelationInDB, boolean seperateWithUnder, String indenfyKey) {
		this.legacyName = name;
		this.manageRelationInDB = manageRelationInDB;
		this.seperateWithUnder = seperateWithUnder;
		this.indenfyKey = indenfyKey;

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

		rootFolder = new File("apps/" + this.legacyName);
		for (File file : rootFolder.listFiles(new PatternFilenameFilter(".*[.]xml"))) {
			importFile = file;
		}
	}

	public void initRules() {
		// when(EndWith).
	}

	// Load and parse XML file into DOM
	public Document parse(File filePath) {
		Document document = null;
		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(filePath);
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
		typesByRawName.put(rawName, type);
		return type;
	}

	public Type addType(Element element, String rawName, String name, String remarks) {
		return addType(rawName, name, new Type(element, rawName, name, remarks));
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

		for (RuleBuilder rule : rules) {

			// Match db type
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

			// match size
			if (rule.length > 0) {
				matched = false;
				if (field.size == rule.length) {
					matched = true;
				}
			}

			// match table name
			if (!matched) continue;
			if (rule.tableName != null) {
				if (!field.resideType.rawName.equalsIgnoreCase(rule.tableName)) {
					continue;
				} else {
					System.out.println("match table");
				}
			}

			// match name
			MatchPattern matchedMatchPattern = null;
			String matchedName = null;
			if (rule.with != null) {
				boolean machPattern = false;
				for (MatchPattern pattern : rule.ruleTypes) {
					matchedName = pattern.match(field.resultName, rule.with);
					if (matchedName != null) {
						machPattern = true;
						matchedMatchPattern = pattern;
						break;
					}
				}
				matched = matched && machPattern;
			}

			if (!matched) continue;

			// match default value
			if (rule.defaultValues != null) {
				matched = false;
				for (String defaultValue : rule.defaultValues) {
					if (defaultValue.equalsIgnoreCase(field.defaultValue.replace("&#10;", "").trim())) {
						matched = true;
						break;
					}
				}
			}

			if (!matched) continue;

			if (matched) {
				field.comment = "Matched\t" + field.name + "\t" + (matchedMatchPattern == null ? "" : matchedMatchPattern.name()) + "\t" + matchedName + "\t"
						+ (matchedColumnType == null ? "" : matchedColumnType.name());
				for (Action action : rule.actions) {
					action.apply(field, matchedName);
				}
				lastResult = field;
			}
		}
		return lastResult;
	}

	public void analyze(List<Type> types) {

		// Costruct Type
		for (Type type : types) {
			for (Field field : type.fields) {
				Field result = this.match(field);
				if (result != null) {
				} else {
					System.out.println(field);
				}
			}
		}

		// check type
		for (Type type : types) {
			boolean hasIDKey = false;
			boolean hasNameKey = false;
			boolean hasNameRequired = false;
			boolean hasName = false;
			for (Field field : type.fields) {
				if ("Name".equalsIgnoreCase(field.resultName)) {
					hasName = true;
					if (!field.nullable) {
						hasNameRequired = true;
					}
					if (field.isKey) {
						hasNameKey = true;
					}
				} else if (field.isKey) {
					if ("ID".equalsIgnoreCase(field.resultTypeName)) {
						hasIDKey = true;
					}
				}
			}

			if (hasIDKey && !hasName) {
				type.standalone = TypeStandalone.Transaction;
			} else if (hasNameKey) {
				type.standalone = TypeStandalone.Master;
			} else if (hasIDKey && hasNameRequired) {
				type.standalone = TypeStandalone.Master;
			}

			// System.out.println("##\t" + type.rawName + "\t" + type.name +
			// "\t" + hasIDKey + "\t" + hasNameKey + "\t"
			// + hasNameRequired + "\t" + hasName);
		}

		if (manageRelationInDB) {
			String key = this.seperateWithUnder ? "_" + this.indenfyKey : this.indenfyKey;

			for (Type type : types) {
				for (Field field : type.fields) {
					if (field.isForeignKey) {
						if (typesByRawName.containsKey(field.foreignKeyTable)) {
							field.resultTypeName = typesByRawName.get(field.foreignKeyTable).name;
							if (field.name.toUpperCase().endsWith(key)) {
								if (field.name.substring(0, field.name.length() - key.length()).equalsIgnoreCase(field.foreignKeyTable)) {
									field.resultName = field.resultTypeName;
								} else {
									field.resultName = field.resultName.substring(0, field.resultName.length() - indenfyKey.length());
								}
							}
						}
					} else if (!field.isKey && this.indenfyKey.equals(field.resultTypeName)) {
						String typename = field.name;
						if (typename.toUpperCase().endsWith(key)) {
							typename = typename.substring(0, typename.length() - 3);
						}
						if (typesByRawName.containsKey(typename)) {
							// field.resultTypeName =
							// typesByRawName.get(typename).name;
							field.isForeignKey = false;
							field.foreignKeyTable = typename;
						} else if (typesByRawName.containsKey(typename + "s")) {
							// field.resultTypeName =
							// typesByRawName.get(typename + "s").name;
							field.isForeignKey = false;
							field.foreignKeyTable = typename + "s";
						} else {
							System.out.println("Fail check foreign key : " + type.name + " - " + field.name);
						}
					}
				}
			}

		} else {
			String key = this.seperateWithUnder ? "_" + this.indenfyKey : this.indenfyKey;
			for (Type type : types) {
				for (Field field : type.fields) {
					if (this.indenfyKey.equals(field.resultTypeName)) {
						String typename = field.name;
						if (typename.toUpperCase().endsWith(key)) {
							typename = typename.substring(0, typename.length() - 3);
						}
						if (typesByRawName.containsKey(typename)) {
							field.resultTypeName = typesByRawName.get(typename).name;
							field.isForeignKey = true;
							field.foreignKeyTable = typename;
						} else if (typesByRawName.containsKey(typename + "s")) {
							field.resultTypeName = typesByRawName.get(typename + "s").name;
							field.isForeignKey = true;
							field.foreignKeyTable = typename + "s";
						} else {
							// System.out.println("Fail check foreign key : " +
							// type.name + " - " + field.name);
						}
					}
				}
			}
		}

		// 附属表的情况，主键为另一个对象的主键
		for (Type type : types) {
			for (Field field : type.fields) {
				if (field.isKey) {
					if (field.name.endsWith("_ID") && !"ID".equals(field.resultTypeName) && typeMapByName.containsKey(field.resultTypeName)) {
						Type refType = typeMapByName.get(field.resultTypeName);
						switch (refType.standalone) {
						case Master:
							type.standalone = TypeStandalone.Master;
							break;
						case Transaction:
							type.standalone = TypeStandalone.Transaction;
							break;
						}
					}
				}
			}
		}

		for (Type type : types) {
			if (type.standalone == TypeStandalone.Abstract) {
				type.standalone = TypeStandalone.Master;
				type.comment = "TODO Type not sure ！！ ";
				// System.out.println("## Type not sure  " + type.name);
			}
		}
		// System.out.println("\n\n\n=================================================\n\n\n");
		// for (Type type : types) {
		// for (Field field : type.fields) {
		// System.out.println(type.rawName + "\t" + type.name + "\t" +
		// type.standalone.name() + "\t"
		// + type.comment + "\t" + field);
		// }
		// }
	}

	protected void outputToFile(File outputFolder, Type type) throws IOException {
		OutputStreamWriter out = null;
		StringBuilder sb = new StringBuilder(5000);
		out = new OutputStreamWriter(new FileOutputStream(new File(outputFolder, type.name + ".nebula")));

		sb.setLength(0);
		sb.append("@Legacy(\"" + escape(type.remarks) + "\")\n");
		sb.append("@Remarks(\"" + escape(type.remarks) + "\")\n");
		sb.append("@Refby(\"" + type.cntReference + "\")\n");
		if (type.standalone == TypeStandalone.Transaction) {
			sb.append("tx " + type.name + " {\n");
		} else {
			sb.append("type " + type.name + " {\n");
		}

		for (Field field : type.fields) {
			if (field.skip) {
				continue;
			}
			if (field.remarks.length() > 0) {
				// sb.append("\t@Remarks(\"" + escape(field.remarks) + "\")\n");
			}
			sb.append("\t");
			if (field.nullable) {
				sb.append("?");
			}
			if (field.isKey) {
				sb.append("!");
			}

			/*
			 * if (!field.nullable && field.resultTypeName != null) { if
			 * ("Name Subject Title"
			 * .toUpperCase().indexOf(field.resultTypeName.toUpperCase()) >= 0)
			 * { sb.append("*"); } }
			 */
			if (!field.nullable && field.resultTypeName != null) {
				if ("Name".toUpperCase().indexOf(field.resultTypeName.toUpperCase()) >= 0 && "Name".toUpperCase().indexOf(field.resultName.toUpperCase()) >= 0) {
					sb.append("*");
				}
			}

			if (field.resultName.equalsIgnoreCase(field.resultTypeName)) {
				sb.append(field.resultTypeName);
			} else {
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

	public void outputAll(File outputFolder) throws IOException {
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		} else { //
			// for (File file : outputFolder.listFiles()) {
			// // file.delete();
			// }
		}
		for (int i = 0; i < types.size() && i < MAX; i++) {
			Type type = types.get(i);
			outputToFile(outputFolder, type);
		}
	}

	Map<String, Type> outputed = Maps.newHashMap();

	protected void outputRelationType(final File outputFolder, Type rootType) throws IOException {
		if (outputed.containsKey(rootType.name)) {
			return;
		}
		outputed.put(rootType.name, rootType);

		System.out.println("#### Output " + rootType.name);

		outputToFile(outputFolder, rootType);

		// for (Type refer : rootType.referby.values()) {
		// this.outputRelationType(outputFolder, refer);
		// }

		for (Field field : rootType.fields) {
			if (field.isForeignKey) {
				this.outputRelationType(outputFolder, this.typesByRawName.get(field.foreignKeyTable));
			}
		}

	}

	public void outputByRelations(File outputFolder, Type rootType) throws IOException {

		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		} else { //
			// for (File file : outputFolder.listFiles()) {
			// // file.delete();
			// }
		}
		outputRelationType(outputFolder, rootType);

	}

	public void readByReations(Element rootElement, String rootTable) throws IOException {
		NodeList nodeList = rootElement.getElementsByTagName("table");
		if (nodeList == null) {
			return;
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String tableName = element.getAttribute("name");
			this.addType(element, tableName, refineName(tableName), element.getAttribute("remarks"));
		}
		// System.out.println("preload reading " + nodeList.getLength());

		Type rootType = this.typeMapByName.get(rootTable);

		this.readByReations(rootType);

		// for (int i = 0; i < nodeList.getLength() && i < MAX; i++) {
		// Element element = (Element) nodeList.item(i);
		// Type type = this.readTable(element);
		// System.out.println("reading... " + i + " " + type.name);
		// }
	}

	Map<String, Type> readedTypes = Maps.newHashMap();

	public void readByReations(Type rootType) throws IOException {
		if (readedTypes.containsKey(rootType.rawName)) {
			return;
		}
		readedTypes.put(rootType.rawName, rootType);
		System.out.println("$$$$ Read   :  " + rootType.name);

		readTable(rootType.element);

		for (Field field : rootType.fields) {
			if (field.isForeignKey) {
				this.readByReations(this.typesByRawName.get(field.foreignKeyTable));
			}
		}
	}

	public void readAll(Element rootElement) throws IOException {
		NodeList nodeList = rootElement.getElementsByTagName("table");
		if (nodeList == null) {
			return;
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String tableName = element.getAttribute("name");
			this.addType(element, tableName, refineName(tableName), element.getAttribute("remarks"));
		}
		// System.out.println("preload reading " + nodeList.getLength());

		for (int i = 0; i < nodeList.getLength() && i < MAX; i++) {
			Element element = (Element) nodeList.item(i);
			this.readTable(element);
			// System.out.println("reading... " + i + " " + type.name);
		}
	}

	public Type readTable(Element table) throws IOException {
		String tableName = table.getAttribute("name");

		Type type = this.typesByRawName.get(tableName);

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
		field.size = Integer.parseInt(column.getAttribute("size"));
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

					Type parentType = this.typesByRawName.get(parent.getAttribute("table"));
					parentType.referby.put(type.name, type);
				} else if ("child".equals(node.getNodeName())) {
					type.cntReference++;
					// type.referby.add(type);
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
		Map<String, Type> referby = Maps.newHashMap();
		final Element element;

		public Type(Element element, String rawName, String name, String remarks) {
			this.element = element;
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
		int size;
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
			return name + "\t" + typename + "\t" + resultName + "\t" + resultTypeName + " " + "\t" + defaultValue + "\t" + digits + "\t" + nullable + "\t"
					+ remarks + "\t" + size + "\t" + comment + " " + "\t" + skip + "\t" + autoUpdated + "\t" + isKey;
		}

	}

	public String escape(String text) {
		return text.replace('\"', '\'').replace('\n', ' ').replace('\r', ' ');
	}

	static final Multiset<String> hadWords = TreeMultiset.create();
	//@formatter:off
 static final String words = ("" +
 		"AVS ARC A_P_A_R and_or ad_text adhoc add ACCUMULATION ALIGNMENT ACCUMDE ADVANCED ALL ALLOCATE ALLOCATED ANY APPROVAL AREA AUCTION AUTHOR AUTHORIZATION AVAILABLE AVERAGE As Asp activity Accept Access Account Acct Achievement Acqusition Actual Address After Alert Allocation Allow Always Amt Amount Approve Approved Archive Archived Asset Attachment Attachments Attribute Auto ALLOCATION Averaged ALLOCATION auth author authorization Addition Addr Address adjust ad_flag ASSOCIATION ACCEPTED ADJUSTMENT " +
 		"branch BAR Build Building Best By BACK BASIS BEFORE BELOW BILL BIRTHDAY BOOK BREAK Balance bank Base Based basket batch Begin beginning benchmark Beta Between Bid binary black bom boundary bp Bpartner Bp_contact broadcast budget business Buyer BALANCED " +
 		"CS CANDIDATE C_BP CALENDAR COORDINATE COORDINATES CONVERTED COMPANY Chat cum CALCULATED CALCULATION CHARACTER CHECK CHEQUE CLOSE CLOSED CODE COL COLLAPSED COMMIT COMMITMENT COMPLETE CONFIG CONSTANT CONTENT CONTROL CORRECTS CORRECTION COUNT COUNTED C_STAGE CTL CUMULATED CUSTOM CUSTOMIZATION CUR CYCLE commodity current currency cumulative confidential Can Cash category Centrally Change Chare Charge chars Class Classes Click Client collection color column columns Commission Committed Confirm Connection contact container Conversion copies copy Cost Costs costing Country Counter create created created_by credit Credit_card currency Customer cut_off Configuration ConfirmEd Checked Close_date Cost_Standard currencies COMMITTED CONVERTED CHARGEABLE creator CANDIDATE CONTAINERT COST_SUMMARY CS_TAGET COLUMN_SET COLUMN_SQL COLUMN_SORT CONFIRMATION CONTAINER CONFIRMATION CLASSIFICATION CVV_2_MATCH " +
 		"do_pricing disposal divide delta deviation DAY DAYS DATA DB DECISION DEMAND DEPRECIATED DEPRECIATION DETAIL DEVELOPER DIFFERENCE DECISION Due direct discontinued dashboard dyn Database Date Days Declaration Decimal Definite Delayed default Delivery Dependent Description Desktop Dimension Discount Display distribution Doc Document Download Dunning Duration DISTRIBUTION DECLARATION Details Differences Controlled DEPRECIATION drop_ship DISPLAYED " +
 		"EE ELAPSED ED EDI EFT EMU ENCRYPTION ENCRYPTED entry even exclude element Email Enforce End Ending entity Error Event expression Exp Expense export entered exempt " +
 		"fiscal For Fail_on FILLED FINAL FUTURE func flat flow first Factory Fee Field File Files File_System Finish fix Footer Foreign Form Format Forecast FINANCIAL Freight Frequency From Full Function Functionality Functions Function_symbols fund Fixed fields FUNCTION_SUFFIX FILE_SIZE " +
 		"GOOD GENERATE GENERATED GRAPH gl group Grand Gross Guarantee Goal Grace " +
 		"HOST HEIGHT high has Hdr Header HEADER_LINES " +
 		"industry income include inquiry imp import imported IF_CLEARING ID_RANGE INDUSTRY ITEM increment Image Immediate Info Inactivity Inout instance Interest inventory Invoice Interest Interests INVOICED " +
 		"INCOME INDEX INDEXED INSERT INTER INTERNAL INV Invoice INVITED Is Iso IS_POSITIVE Is_Own Is_Online Is_Over Is_Overwrite Is_Owned Is_One Is_Order_by IS_OFFER Issue Issues Interest Inventory INSTITUTION ISSUE_SOURCE ISSUE_STATUS ISSUE_SYSTEM ISSUE_SUMMARY INCLUDED INVOICED IN_WORDS in_dispute in_transit IN_PRODUCTION IN_POSESSION ISDN INVOICE_DAY INVOICE_DOCUMENT " +
 		"Just JAVA Jsp join journal Jasper job " +
 		"Key Keep " +
 		"Link LEAD LEASE LICIT LIFE LIMIT LOAD LOC LOCK LOCATOR LOCATION LOG logged Logon Login Lost local lifetime Label Language Language_Iso Landed Last Ldap Level Levels Line Lines Line_Stroke Lingual List Logger lookup Lot Logic Loader Lo_code List_Invoices LINE_SET loader stack size shape " +
 		"media MAINTENENCE MM MAINTENANCE MAINTAINED MANUAL MARK Market MEASURE MENU MESSAGE Min MINIMUM MODE month months mail mandatory Margin match Max Method migration Model modify Movement Msg Multi Maintenance Missing Moderator moderation Menu_Assets Menu_Payments Menu_Invoices MULTIPLIER MATCHER MULTIPLY maintenence " +
 		"NOT NEGATIVE NET NULL Name Natural New next No Non Not Node nodes Note notesq Notification number NEWS " +
 		"OF OPT OUT OBSCURE OFFER OFFSET OPERATING Old On Online Only One Open Operation Or Order Order_by Ordered Org orgs Orig Other Over Overwrite own Owned OverDue Orders O_PROCESSING ORIGINAL " +
 		"PPV partner PHONE PRECIATION PRIVATE PROC PROD PROFILE PUBLIC PAID PARAM PARAMETER PARENTELEMENT PASTDUE PHASE PLATFORM PURCHASE PURCHASED publish priority Package Paint Page paper Path Pay Payer payment Per PERIOD Percent Personal Pick Planned Po Point Port Pos Post postal P_instance Posting Position Preference Price Print Printed Printer Priority Procedure process Processor Processors Processing Processed Product Proj Project Proxy PRODUCTION Parent Picked POTENTIAL Payee POLICY PERPETUAL POSTED PAINTH PAINT PAINT_H PAINT_V PUBLISHER PUBLISHED PERFORMANCE PUB_DATE PAINT_HEADER_LINES " +
 		"QUALITY QUOTE Qa Qty Quantity Query Readonly " +
 		"RENEWAL r_amt r_cost remit RR REPLENISHMENT RMI read readonly rank RMA RECEIVE RECEIVED Recurring Relative Reval Revaluation RFQ Row RT Rule Run Running ratio repeat routing recognition Record Reference region registration related replenish release Remuneration replication report Request Require Required requisition Resource Response Responsible ression Revenue rfq_response Role runs remaining Reval_cr Reval_Dr Requests RfQs Registrations RESTRICTION RETAINED REALIZED resp remind recognized reciprocal RR_AMT RESPONSE_TEXT RESPONSE RESPONSIBLE ROBOTS RANKING royalty REPLENISHMENT REMITTANCE Records " +
 		"stax Sum Summary Step Schedule Scrapped Scribe Scriber Share Sign Signment SQL Statistics Structure Suppress SWIFT self same Sales scheduler schema script seconds security Select Selected Selection seller Send seq sequence sequentially ser_no Server service Set share shelf Ship Shipper Show Single Smtp So Sold Source Split Standard start Status statement Std Store sub subscription substitute Support Sync Sys System Sort Subscribe SubScriber Shipments SUBJECT suspense style stroke stmt strategy SHIPMENT SUMMARIZED SETTLEMENT SYNONYM SYNCHRONIZED SETNL " +
 		"Term Termination Temporary Teardown Tandard Token Transition Transfer Trade Translation Translated tential track type table Target Task Trx Tax Tender Template Test Text time Times topic Total Tracking transaction tree Transferred TAGET TRIAL to_deliver to_invoice to_3_party TO_ORDER threshold TAB_LEVEL TENDERED " +
 		"UNIT Until under Unearned units Unix Unrealized Uom Use used User Update Updated Updates Unallocated Updateable UNEARNED USE_DATE " +
 		"VALUTA VIA VEND valid validate Validation Value vendor Verify Version Voice vendors validator V_FORMAT VARIANCE vanilla " +
 		"wf wait waiting warehouse Watch web web_store Week Weekday When Where Willing Willing_To win wiki Wstore Window Windows Work workbench workflow Working Write_off WINNER " +
 		"X XY XML XST " +
 		"Y YEAR YES_NO " +
 		"Z Zip");
  //@formatter:on

	static final String[] finds = words.toUpperCase().replace("_", "").split(" ");

	static final String[] replaces = words.toLowerCase().split(" ");

	public String refineName(String inname) {
		String name = inname;
		if (name == null) return null;

		boolean isFind = false;
		do {
			for (int i = finds.length - 1; i >= 0; i--) {
				String find = finds[i];
				String replace = replaces[i];
				if (name.startsWith(find)) {
					name = name.replaceFirst("^" + find, replace + "_");
					isFind = true;
					break;
				} else if (name.indexOf("_" + find) >= 0) {
					name = name.replaceFirst("_" + find, "_" + replace + "_");
					isFind = true;
					break;
				} else {
					isFind = false;
				}
			}
		} while (isFind);
		name = name.replaceAll("__", "_");

		String[] sp = name.toLowerCase().split("_");
		for (String string : sp) {
			if (string.length() == 0) continue;
			if (!definedWords.containsKey(string)) {
				System.out.println(string + "\t" + name + "\t" + inname);
			}
			hadWords.add(string);
		}

		name = name.toUpperCase();

		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
	}

	public void printInfo() {
		System.out.println("=============================");
		System.out.println("====        word       ====");
		System.out.println("=============================\n");
		for (Multiset.Entry<String> set : hadWords.entrySet()) {
			if (!definedWords.containsKey(set.getElement())) {
				System.out.println(set.getElement() + "\t" + set.getCount());
			}
		}
		System.out.println("=============================");
		System.out.println("====        type       ====");
		System.out.println("=============================\n");

		Multiset<String> fieldTypes = TreeMultiset.create();

		for (Type type : types) {
			for (Field field : type.fields) {
				if (field.skip) continue;
				if (field.isForeignKey) continue;
				if (field.resultTypeName != null) {
					fieldTypes.add(field.resultTypeName);
				} else {
					fieldTypes.add("NULL");
					System.out.println(type.name + "\t" + field.name + "\t" + field.resultName + "\t" + field.typename + "\t" + field.size + "\t"
							+ field.defaultValue + "\t" + field.remarks);
				}
			}
		}

		for (Multiset.Entry<String> set : fieldTypes.entrySet()) {
			System.out.println(set.getElement() + "\t" + set.getCount());
		}
	}

}

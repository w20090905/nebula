package adempiere;

import static adempiere.DBColumnType.Blob;
import static adempiere.DBColumnType.Char;
import static adempiere.DBColumnType.Date;
import static adempiere.DBColumnType.Datetime;
import static adempiere.DBColumnType.Decimal;
import static adempiere.DBColumnType.Long;
import static adempiere.DBColumnType.NVarchar;
import static adempiere.DBColumnType.String;
import static adempiere.DBColumnType.Text;
import static adempiere.DBColumnType.Timestamp;
import static adempiere.DBColumnType.Varchar;
import static adempiere.MatchPattern.EndWithIgnoreCase;
import static adempiere.MatchPattern.EqualsIgnoreCase;
import static adempiere.MatchPattern.Include;
import static adempiere.MatchPattern.StartWithIgnoreCase;

import java.io.IOException;
import java.util.List;

import nebula.lang.TypeStandalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ImportHHTDataDefine extends DefaultImporter {
	Log log = LogFactory.getLog(getClass());

	public static void main(String[] args) throws IOException {
		String inputFileName = "apps/hht/orcl.DMS2.xml";
		String outputFolder = "apps/hht";

		ImportHHTDataDefine parser = new ImportHHTDataDefine();
		Document document = parser.parse(inputFileName);
		// get root element
		Element rootElement = document.getDocumentElement();
		parser.read(outputFolder, rootElement);
		parser.analyze(parser.types);
		parser.output(outputFolder);
	}

	public ImportHHTDataDefine() {
		super(true, false);
		// ID
		when(EqualsIgnoreCase).with("ID").typeOf(Long).then().setTypeName("ID");
		when(EndWithIgnoreCase).with("ID").typeOf(Long).then().setTypeName("ID");

		// String
		when(EqualsIgnoreCase)
				.with("Name", "Description", "Comment", "Account", "Regexp", "Title", "Host", "Filename", "TimeZone", "Status", "Url", "Password", "Subject",
						"Content", "Summary", "Revision", "Symbol", "Fax").typeOf(String).then().useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(StartWithIgnoreCase, EndWithIgnoreCase)
				.with("Path", "Password", "Description", "FirstName", "FileName", "LastName", "Phone", "Postal", "Title", "Url", "Host", "Name", "Msg", "Info",
						"Memo", "Notice", "Symbol", "Help").typeOf(String).then().useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("EMail", "Name", "Phone", "Postal").typeOf(String).then().useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Addr").typeOf(String).then().setTypeName("Address");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Address").typeOf(String).then().useMatchedNameAsFieldName();
		when(StartWithIgnoreCase).with("Message", "Help").typeOf(String).then().useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Text").typeOf(Text).then().useMatchedNameAsTypeName();

		when(EndWithIgnoreCase)
				.with("Value", "GREETING", "TERM", "PREFIX", "SUFFIX", "KEYWORD", "POREFERENCE", "REFERENCE", "REFERRER", "RELEASENO", "TAXINDICATOR",
						"DIRECTORY", "CREATEDDATE", "UPDATEDDATE", "DATECOLUMN", "USERID").typeOf(String).then().setTypeName("String");

		when(EndWithIgnoreCase)
				.with("CREDITCARDNUMBER", "TAXID", "CHECKNO", "ACCOUNTNO", "DOCUMENTNO", "CUSTOMERNO", "CONFIRMATIONNO", "TRXID", "REFERENCENO", "ROUTINGNO",
						"SERNO", "RPRODUCTNO", "VERSIONNO", "CODE", "PONUM").typeOf(String).then().setTypeName("Code");

		when(EndWithIgnoreCase).with("Note").typeOf(NVarchar).then().setTypeName("Note");

		when(StartWithIgnoreCase)
				.with("WEBPARAM", "X", "Y", "WHERECLAUSE", "Z", "ARGS", "FOLDER", "CREDITCARDVV", "FOLDER", "PRODUCTATTRIBUTE", "LOOKUPCLIENTID")
				.typeOf(String).then().setTypeName("String");

		when(EqualsIgnoreCase).with("A_STREET", "A_CITY", "A_STATE", "A_ZIP", "A_IDENT_DL", "A_IDENT_SSN").typeOf(String).then().setTypeName("String");
		when(EqualsIgnoreCase).with("UNINSTALL", "A_CITY", "A_STATE", "A_ZIP", "A_IDENT_DL", "A_IDENT_SSN").typeOf(String).then().setTypeName("String");

		// HTML
		when(EqualsIgnoreCase).with("HEADERLEFT", "HEADERCENTER", "HEADERRIGHT", "FOOTERLEFT", "FOOTERCENTER", "FOOTERRIGHT").typeOf(String).then()
				.setTypeName("String");
		when(EqualsIgnoreCase, EndWithIgnoreCase)
				.with("PROTOCOL", "ACCEPTLANGUAGE", "CONTENTHTML", "LOGIC", "DISPLAYSEQUENCE", "DISPLAYSEQUENCELOCAL", "FORMAT", "WEBSESSION", "USERAGENT",
						"SESSION_ID", "SCRIPT", "REQUESTUSER", "REQUESTUSERPW", "REQUESTFOLDER", "REPLY_REMARKS", "REPLY").typeOf(String).then()
				.setTypeName("String");

		when(EqualsIgnoreCase).with("VALUEMIN", "VALUEMAX", "DISPLAYLOGIC", "DISPLAYSEQUENCE", "DISPLAYSEQUENCELOCAL", "CALLOUT").typeOf(String).then()
				.setTypeName("String");

		// SQL

		when(EndWithIgnoreCase).with("CLAUSE", "SQL").typeOf(String).then().setTypeName("String");
		when(EndWithIgnoreCase).with("XML").typeOf(String).then().setTypeName("Note");
		when(StartWithIgnoreCase).with("META_", "VERSION").typeOf(String).then().setTypeName("String");

		// Others
		when(EndWithIgnoreCase).with("SUMMARY").typeOf(String).then().setTypeName("Comment");
		when(EndWithIgnoreCase).with("COMMITWARNING").typeOf(String).then().setTypeName("Msg");

		when(StartWithIgnoreCase).with("EFT", "R_", "A_").typeOf(String).then().setTypeName("String");
		when(StartWithIgnoreCase).with("OS_COMMAND", "ORGCOLUMN", "NULLCOLUMNS").typeOf(String).then().setTypeName("String");

		when(EqualsIgnoreCase).with("Redirects_to", "Homepage").typeOf(String).then().setTypeName("Url");

		when(EqualsIgnoreCase).with("DUNS", "BBAN", "NAICS", "APAR", "SKU", "UPC", "SWIPE", "IBAN", "ISDN", "LOT", "SWIFTCode").typeOf(Char, String).then()
				.useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(Include).with("Text").typeOf(String).then().useMatchedNameAsTypeName();

		// Number

		when(EqualsIgnoreCase).with("FileSize", "Port", "Status", "Version", "Position", "SeqNo", "Priority").typeOf(Long).then().useMatchedNameAsTypeName()
				.useMatchedNameAsFieldName();
		when(EndWithIgnoreCase).with("Port").typeOf(Long).then().useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase)
				.with("Count", "Length", "Height", "Width", "Size", "Weight", "Ratio", "Rate", "Rating", "Depth", "Price", "Line", "Cost", "Volume", "Amount",
						"Percent", "Frequency", "Sequence", "Unit", "Precision", "Ranking").typeOf(Long).then().useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Days", "Months", "Years", "DELIVERYTIME").typeOf(Long).then().setTypeName("Count");
		when(StartWithIgnoreCase).with("Level", "SERVICELEVELPROVIDED").typeOf(Long).then().setTypeName("Count");
		when(EndWithIgnoreCase).with("Limit", "Copies").typeOf(Long).then().setTypeName("Count");

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("AMT", "GrandTotal").typeOf(Long).then().setTypeName("Amount");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Qty").typeOf(Long).then().setTypeName("Quantity");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Lines").typeOf(Long).then().setTypeName("Line");
		when(EndWithIgnoreCase).with("No").typeOf(Long).then().setTypeName("SeqNo");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Due", "PASTDUE").typeOf(Long).then().setTypeName("Amount");// TODO
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Acct").typeOf(Long).then().setTypeName("Amount");// TODO
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Time").typeOf(Long).then().setTypeName("Count");// TODO
		when(StartWithIgnoreCase).with("Measure").typeOf(Long).then().setTypeName("Length");// TODO

		when(EndWithIgnoreCase).with("QTYAVAILABLE", "QTYCONFIRMED").typeOf(Long).then().setTypeName("Quantity");

		// HTML
		when(EndWithIgnoreCase).with("Margin", "Left", "Right", "Top", "Bottom", "Position", "Space").typeOf(Long).then().setTypeName("Length");
		when(StartWithIgnoreCase).with("Col").typeOf(Long).then().setTypeName("Length");

		// Date

		when(EqualsIgnoreCase).with("Birthday").typeOf(Date, Datetime).then().useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(EndWithIgnoreCase).with("_On").typeOf(Datetime).then().setTypeName(Datetime.name());
		when(EndWithIgnoreCase).with("_On").typeOf(Date).then().setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Date").typeOf(Date).then().setTypeName(Date.name());

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Date", "Valid").typeOf(Date).then().setTypeName(Date.name());

		when(EndWithIgnoreCase).with("_Timestamp").typeOf(Timestamp).then().setTypeName(Timestamp.name());

		when(EqualsIgnoreCase)
				.with("ASSIGNDATEFROM", "ASSIGNDATETO", "DISCONTINUEDBY", "DUNNINGGRACE", "ENDTIME", "FIRSTSALE", "LASTCONTACT", "LINEDATEWORKCOMPLETE",
						"LINEDATEWORKSTART", "PRICEEFFECTIVE", "REPLY_RECEIVED", "SOURCEUPDATED", "STARTTIME", "T_DATETIME", "TIMESLOTEND", "TIMESLOTSTART",
						"TRXRECEIVED", "TRXSENT").typeOf(Date).then().setTypeName("Date");

		// Attr

		when(EndWithIgnoreCase).with("Rule", "Status").typeOf(Char).then().setTypeName("Attr");
		when(StartWithIgnoreCase, EndWithIgnoreCase, EqualsIgnoreCase).with("Type").typeOf(String).then().setTypeName("Attr");

		when(EqualsIgnoreCase)
				.with("AD_LANGUAGE", "COUNTRYCODE", "COSTINGMETHOD", "DOCACTION", "GENERATEORDER", "GENERATETO", "PROJECTLINELEVEL", "ACCESSLEVEL")
				.typeOf(Char, Varchar).then().setTypeName("Attr");

		// Boolean
		when(EqualsIgnoreCase).with("PROCESSING", "PROCESSED", "POSTED").typeOf(Char).then().setTypeName("YesNo");
		when(StartWithIgnoreCase).with("Is", "I_IS", "HAS", "POST", "ALLOW", "Create").typeOf(Char).then().setTypeName("YesNo");
		when(StartWithIgnoreCase).with("On", "ACCEPT", "AFTER", "OVERWRITE", "ANY", "DISCONTINUED").typeOf(Char).then().setTypeName("YesNo");
		when(EqualsIgnoreCase)
				.with("DOPRICING", "CREATEPO", "CLASSIFICATION", "CREATESO", "PRIORITY", "PRIORITYUSER", "REQUIREVV", "PROJECTCATEGORY",
						"REQUIRESTAXCERTIFICATE", "UPDATEQTY", "LISTTRX", "LISTSOURCES", "GENERATELIST", "ENFORCEPRICELIMIT", "CREATERECIPROCALRATE",
						"COUNTHIGHMOVEMENT").typeOf(Char).then().setTypeName("YesNo");// TODO

		// Spacial
		when(EndWithIgnoreCase).with("Comments").typeOf(String).then().setTypeName("Note");
		when(EndWithIgnoreCase).with("Mail").typeOf(String).then().setTypeName("EMail");
		when(EndWithIgnoreCase).with("Hours").typeOf(Decimal).then().setTypeName("Number");
		when(EndWithIgnoreCase).with("Encoding").typeOf(String).then().setTypeName("String");
		when(EndWithIgnoreCase).with("Notes").typeOf(Text).then().setTypeName("Note");

		// notsure

		// when().is(Bit).then().setTypeName("YesNo");
		// when().is(Varchar, NVarchar).then().setTypeName("String");
		when().typeOf(Text, Blob).then().setTypeName("Note");
		// when().is(Long).then().setTypeName("Count");

		when(EqualsIgnoreCase).with("repository_id").inTable("Changesets").then().setReferTo("Repositories");

		// Skip System Column
		when(EqualsIgnoreCase).with("ISACTIVE").typeOf(Char).skip();
		when(EqualsIgnoreCase).with("CREATEDBY", "UPDATEDBY").typeOf(Long).skip();
		when(EqualsIgnoreCase).with("CREATED", "UPDATED").typeOf(Date).skip();
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

		for (Type type : types) {
			for (Field field : type.fields) {
				if (field.isForeignKey) {
					if (typesByRawName.containsKey(field.foreignKeyTable)) {
						field.resultTypeName = typesByRawName.get(field.foreignKeyTable).name;
					}
				} else if (!field.isKey && "ID".equals(field.resultTypeName)) {
					String typename = field.name;
					if (typename.toUpperCase().endsWith("_ID")) {
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

}

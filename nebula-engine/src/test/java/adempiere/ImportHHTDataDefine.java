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
		// ID
		when(EqualsIgnoreCase).with("ID").is(Long).setTypeName("ID");
		when(EndWithIgnoreCase).with("ID").is(Long).setTypeName("ID");

		// String
		when(EqualsIgnoreCase)
				.with("Name", "Description", "Comment", "Account", "Regexp", "Title", "Host", "Filename", "TimeZone",
						"Status", "Url", "Password", "Subject", "Content", "Summary", "Revision", "Symbol", "Fax")
				.is(String).useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(StartWithIgnoreCase, EndWithIgnoreCase)
				.with("Path", "Password", "Description", "FirstName", "FileName", "LastName", "Phone", "Postal",
						"Title", "Url", "Host", "Name", "Msg", "Info", "Memo", "Notice", "Symbol", "Help").is(String)
				.useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("EMail", "Name", "Phone", "Postal").is(String)
				.useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Addr").is(String).setTypeName("Address");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Address").is(String).useMatchedNameAsFieldName();
		when(StartWithIgnoreCase).with("Message", "Help").is(String).useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Text").is(Text).useMatchedNameAsTypeName();

		when(EndWithIgnoreCase)
				.with("Value", "GREETING", "TERM", "PREFIX", "SUFFIX", "KEYWORD", "POREFERENCE", "REFERENCE",
						"REFERRER", "RELEASENO", "TAXINDICATOR","DIRECTORY","CREATEDDATE","UPDATEDDATE","DATECOLUMN","USERID").is(String).setTypeName("String");

		when(EndWithIgnoreCase)
				.with("CREDITCARDNUMBER", "TAXID", "CHECKNO", "ACCOUNTNO", "DOCUMENTNO", "CUSTOMERNO",
						"CONFIRMATIONNO", "TRXID", "REFERENCENO", "ROUTINGNO", "SERNO", "RPRODUCTNO", "VERSIONNO",
						"CODE", "PONUM").is(String).setTypeName("Code");

		when(EndWithIgnoreCase).with("Note").is(NVarchar).setTypeName("Note");

		when(StartWithIgnoreCase)
				.with("WEBPARAM", "X", "Y", "WHERECLAUSE", "Z", "ARGS", "FOLDER", "CREDITCARDVV", "FOLDER",
						"PRODUCTATTRIBUTE", "LOOKUPCLIENTID").is(String).setTypeName("String");

		when(EqualsIgnoreCase).with("A_STREET", "A_CITY", "A_STATE", "A_ZIP", "A_IDENT_DL", "A_IDENT_SSN").is(String)
				.setTypeName("String");
		when(EqualsIgnoreCase).with("UNINSTALL", "A_CITY", "A_STATE", "A_ZIP", "A_IDENT_DL", "A_IDENT_SSN").is(String)
				.setTypeName("String");

		// HTML
		when(EqualsIgnoreCase)
				.with("HEADERLEFT", "HEADERCENTER", "HEADERRIGHT", "FOOTERLEFT", "FOOTERCENTER", "FOOTERRIGHT")
				.is(String).setTypeName("String");
		when(EqualsIgnoreCase, EndWithIgnoreCase)
				.with("PROTOCOL", "ACCEPTLANGUAGE", "CONTENTHTML", "LOGIC", "DISPLAYSEQUENCE", "DISPLAYSEQUENCELOCAL",
						"FORMAT", "WEBSESSION", "USERAGENT", "SESSION_ID", "SCRIPT", "REQUESTUSER", "REQUESTUSERPW",
						"REQUESTFOLDER", "REPLY_REMARKS", "REPLY").is(String).setTypeName("String");

		when(EqualsIgnoreCase)
				.with("VALUEMIN", "VALUEMAX", "DISPLAYLOGIC", "DISPLAYSEQUENCE", "DISPLAYSEQUENCELOCAL", "CALLOUT")
				.is(String).setTypeName("String");

		// SQL

		when(EndWithIgnoreCase).with("CLAUSE", "SQL").is(String).setTypeName("String");
		when(EndWithIgnoreCase).with("XML").is(String).setTypeName("Note");
		when(StartWithIgnoreCase).with("META_", "VERSION").is(String).setTypeName("String");

		// Others
		when(EndWithIgnoreCase).with("SUMMARY").is(String).setTypeName("Comment");
		when(EndWithIgnoreCase).with("COMMITWARNING").is(String).setTypeName("Msg");

		when(StartWithIgnoreCase).with("EFT", "R_", "A_").is(String).setTypeName("String");
		when(StartWithIgnoreCase).with("OS_COMMAND", "ORGCOLUMN", "NULLCOLUMNS").is(String).setTypeName("String");

		when(EqualsIgnoreCase).with("Redirects_to", "Homepage").is(String).setTypeName("Url");

		when(EqualsIgnoreCase)
				.with("DUNS", "BBAN", "NAICS", "APAR", "SKU", "UPC", "SWIPE", "IBAN", "ISDN", "LOT", "SWIFTCode")
				.is(Char, String).useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(Include).with("Text").is(String).useMatchedNameAsTypeName();

		// Number

		when(EqualsIgnoreCase).with("FileSize", "Port", "Status", "Version", "Position", "SeqNo", "Priority").is(Long)
				.useMatchedNameAsTypeName().useMatchedNameAsFieldName();
		when(EndWithIgnoreCase).with("Port").is(Long).useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase)
				.with("Count", "Length", "Height", "Width", "Size", "Weight", "Ratio", "Rate", "Rating", "Depth",
						"Price", "Line", "Cost", "Volume", "Amount", "Percent", "Frequency", "Sequence", "Unit",
						"Precision", "Ranking").is(Long).useMatchedNameAsTypeName();

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Days", "Months", "Years", "DELIVERYTIME").is(Long)
				.setTypeName("Count");
		when(StartWithIgnoreCase).with("Level", "SERVICELEVELPROVIDED").is(Long).setTypeName("Count");
		when(EndWithIgnoreCase).with("Limit", "Copies").is(Long).setTypeName("Count");

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("AMT", "GrandTotal").is(Long).setTypeName("Amount");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Qty").is(Long).setTypeName("Quantity");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Lines").is(Long).setTypeName("Line");
		when(EndWithIgnoreCase).with("No").is(Long).setTypeName("SeqNo");
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Due", "PASTDUE").is(Long).setTypeName("Amount");// TODO
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Acct").is(Long).setTypeName("Amount");// TODO
		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Time").is(Long).setTypeName("Count");// TODO
		when(StartWithIgnoreCase).with("Measure").is(Long).setTypeName("Length");// TODO

		when(EndWithIgnoreCase).with("QTYAVAILABLE", "QTYCONFIRMED").is(Long).setTypeName("Quantity");

		// HTML
		when(EndWithIgnoreCase).with("Margin", "Left", "Right", "Top", "Bottom", "Position", "Space").is(Long)
				.setTypeName("Length");
		when(StartWithIgnoreCase).with("Col").is(Long).setTypeName("Length");

		// Date

		when(EqualsIgnoreCase).with("Birthday").is(Date, Datetime).useMatchedNameAsTypeName()
				.useMatchedNameAsFieldName();

		when(EndWithIgnoreCase).with("_On").is(Datetime).setTypeName(Datetime.name());
		when(EndWithIgnoreCase).with("_On").is(Date).setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Date").is(Date).setTypeName(Date.name());

		when(StartWithIgnoreCase, EndWithIgnoreCase).with("Date", "Valid").is(Date).setTypeName(Date.name());

		when(EndWithIgnoreCase).with("_Timestamp").is(Timestamp).setTypeName(Timestamp.name());

		when(EqualsIgnoreCase)
				.with("ASSIGNDATEFROM", "ASSIGNDATETO", "DISCONTINUEDBY", "DUNNINGGRACE", "ENDTIME", "FIRSTSALE",
						"LASTCONTACT", "LINEDATEWORKCOMPLETE", "LINEDATEWORKSTART", "PRICEEFFECTIVE", "REPLY_RECEIVED",
						"SOURCEUPDATED", "STARTTIME", "T_DATETIME", "TIMESLOTEND", "TIMESLOTSTART", "TRXRECEIVED",
						"TRXSENT").is(Date).setTypeName("Date");

		// Attr

		when(EndWithIgnoreCase).with("Rule", "Status").is(Char).setTypeName("Attr");
		when(StartWithIgnoreCase, EndWithIgnoreCase, EqualsIgnoreCase).with("Type").is(String).setTypeName("Attr");

		when(EqualsIgnoreCase)
				.with("AD_LANGUAGE", "COUNTRYCODE", "COSTINGMETHOD", "DOCACTION", "GENERATEORDER", "GENERATETO",
						"PROJECTLINELEVEL", "ACCESSLEVEL").is(Char, Varchar).setTypeName("Attr");

		// Boolean
		when(EqualsIgnoreCase).with("PROCESSING", "PROCESSED", "POSTED").is(Char).setTypeName("YesNo");
		when(StartWithIgnoreCase).with("Is", "I_IS", "HAS", "POST", "ALLOW", "Create").is(Char).setTypeName("YesNo");
		when(StartWithIgnoreCase).with("On", "ACCEPT", "AFTER", "OVERWRITE", "ANY", "DISCONTINUED").is(Char)
				.setTypeName("YesNo");
		when(EqualsIgnoreCase)
				.with("DOPRICING", "CREATEPO", "CLASSIFICATION", "CREATESO", "PRIORITY", "PRIORITYUSER", "REQUIREVV",
						"PROJECTCATEGORY", "REQUIRESTAXCERTIFICATE", "UPDATEQTY", "LISTTRX", "LISTSOURCES",
						"GENERATELIST", "ENFORCEPRICELIMIT", "CREATERECIPROCALRATE", "COUNTHIGHMOVEMENT").is(Char)
				.setTypeName("YesNo");// TODO

		// Spacial
		when(EndWithIgnoreCase).with("Comments").is(String).setTypeName("Note");
		when(EndWithIgnoreCase).with("Mail").is(String).setTypeName("EMail");
		when(EndWithIgnoreCase).with("Hours").is(Decimal).setTypeName("Number");
		when(EndWithIgnoreCase).with("Encoding").is(String).setTypeName("String");
		when(EndWithIgnoreCase).with("Notes").is(Text).setTypeName("Note");

		// notsure

		// when().is(Bit).setTypeName("YesNo");
		// when().is(Varchar, NVarchar).setTypeName("String");
		when().is(Text, Blob).setTypeName("Note");
		// when().is(Long).setTypeName("Count");

		when(EqualsIgnoreCase).with("repository_id").table("Changesets").setReferTo("Repositories");

		// Skip System Column
		when(EqualsIgnoreCase).with("ISACTIVE").is(Char).skip();
		when(EqualsIgnoreCase).with("CREATEDBY", "UPDATEDBY").is(Long).skip();
		when(EqualsIgnoreCase).with("CREATED", "UPDATED").is(Date).skip();
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
					if (typeMapByRawName.containsKey(field.foreignKeyTable)) {
						field.resultTypeName = typeMapByRawName.get(field.foreignKeyTable).name;
					}
				} else if (!field.isKey && "ID".equals(field.resultTypeName)) {
					String typename = field.name;
					if (typename.toUpperCase().endsWith("_ID")) {
						typename = typename.substring(0, typename.length() - 3);
					}
					if (typeMapByRawName.containsKey(typename)) {
						field.resultTypeName = typeMapByRawName.get(typename).name;
						field.isForeignKey = true;
						field.foreignKeyTable = typename;
					} else if (typeMapByRawName.containsKey(typename + "s")) {
						field.resultTypeName = typeMapByRawName.get(typename + "s").name;
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
					if (field.name.endsWith("_ID") && !"ID".equals(field.resultTypeName)
							&& typeMapByName.containsKey(field.resultTypeName)) {
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

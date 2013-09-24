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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ImportAdempiereDataDefine extends DefaultImporter {
	Log log = LogFactory.getLog(getClass());

	public static void main(String[] args) throws IOException {
		String inputFileName = "apps/adempiere/orclqss.ADEMPIERE340.xml";
		String outputFolder = "apps/adempiere";

		ImportAdempiereDataDefine parser = new ImportAdempiereDataDefine();
		Document document = parser.parse(inputFileName);
		// get root element
		Element rootElement = document.getDocumentElement();
		parser.read(outputFolder, rootElement);
		parser.analyze(parser.types);
		parser.output(outputFolder);
	}

	public ImportAdempiereDataDefine() {
		super(true, true);

		// ID
		when(EqualsIgnoreCase).with("ID").typeOf(Long).then().setTypeName("ID");
		when(EndWithIgnoreCase).with("_ID").typeOf(Long).then().setTypeName("ID");

		// String

		/* NEW */

		when(StartWithIgnoreCase).with("is").typeOf(Char).length(1).then().setTypeName("YesNo");
		when(EqualsIgnoreCase).with("Action").typeOf(Char).length(1).then().setTypeName("YesNo");
		when(StartWithIgnoreCase).defaultValue("'Y'", "'N'").typeOf(Char).length(1).then().setTypeName("YesNo");

		when(EqualsIgnoreCase).with("Languageiso", "CountryCode").typeOf(String).then().setTypeName("Code");
		when(EqualsIgnoreCase).with("Summary").typeOf(String).then().setTypeName("Summary");
		when(EqualsIgnoreCase).with("Dbaddress", "Remote_Addr").then().setTypeName("Name");

		when(EqualsIgnoreCase).with("RequestEmail", "RequestUser", "DocumentDir", "ReleaseTag", "FieldGroup", "Constantvalue", "FunctionColumn").typeOf(String)
				.then().setTypeName("Description");

		when(EqualsIgnoreCase).with("Help").typeOf(String).then().setTypeName("Help");
		when(EqualsIgnoreCase).with("Value", "LdapQuery", "DatePattern", "Timepattern", "Version", "Duns").typeOf(String).then().setTypeName("String");

		when(EndWithIgnoreCase).with("Msg").typeOf(String).then().setTypeName("Note");
		when(EndWithIgnoreCase).with("Subject", "Version", "SupportEmail", "Prefix", "Suffix").typeOf(String).then().setTypeName("Description");

		when(EndWithIgnoreCase)
				.with("Message", "Reply", "Clause", "Path", "Help", "Preprocessing", "Modelpackage", "Code", "Logic", "Callout", "Sql", "Infofactoryclass",
						"PostProcessing", "Value", "DisplayLogic", "Modelvalidationclasses", "info", "Trace", "Responsetext", "Script", "warning", "Msgtext",
						"Msgtip", "Reference", "Modelvalidationclass").typeOf(String).then().setTypeName("Note");

		when(EqualsIgnoreCase).with("Comments").typeOf(String).then().setTypeName("Comment");
		when(EqualsIgnoreCase).with("Callout", "Vformat", "Value2").typeOf(String).then().setTypeName("Description");

		when(EqualsIgnoreCase).with("Title", "EntityType", "Ad_Language", "Operation", "RequestDocumentNo").then().setTypeName("String");

		when(EqualsIgnoreCase).with("V_String").typeOf(String).then().setTypeName("Note");
		when(EqualsIgnoreCase).with("V_Number").typeOf(Long).then().setTypeName("Number");
		when(EqualsIgnoreCase).with("LineNo").typeOf(Long).then().setTypeName("Long");

		when(EqualsIgnoreCase).with("EVENTCHANGELOG", "Undo", "Redo").typeOf(Char).length(1).then().setTypeName("YesNo");
		when(EndWithIgnoreCase).with("Type", "Level", "Status").typeOf(Char).then().setTypeName("Attr");
		when(EndWithIgnoreCase).with("Jspurl").typeOf(Char).then().setTypeName("URL");

		when(EqualsIgnoreCase).with("LineWidth").typeOf(Long).then().setTypeName("Number");
		when(EqualsIgnoreCase).with("Version").typeOf(Long).then().setTypeName("Number");

		/* End New */

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

		when(EndWithIgnoreCase)
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
		when().typeOf(Long).then().setTypeName("Number");

		when(EqualsIgnoreCase).with("repository_id").inTable("Changesets").then().setReferTo("Repositories");

		// Skip System Column
		when(EqualsIgnoreCase).with("ISACTIVE").typeOf(Char).skip();
		when(EqualsIgnoreCase).with("CREATEDBY", "UPDATEDBY").typeOf(Long).skip();
		when(EqualsIgnoreCase).with("CREATED", "UPDATED").typeOf(Date).skip();
	}

}

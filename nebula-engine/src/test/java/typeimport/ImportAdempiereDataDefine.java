package typeimport;

import static typeimport.DBColumnType.*;
import static typeimport.DBColumnType.Long;
import static typeimport.MatchPattern.*;

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

		// String rootType = "AdClient";
		String rootType = "COrder";

		parser.readByReations(outputFolder, rootElement, rootType);
		parser.analyze(parser.types);
		parser.outputByRelations(outputFolder, parser.typeMapByName.get(rootType));

		// parser.readAll(outputFolder, rootElement);
		// parser.analyze(parser.types);
		// parser.outputAll(outputFolder);
		parser.info();
	}

	public ImportAdempiereDataDefine() {
		super(true, true);

		when(Equals, EndWith).with("Id").typeOf(Long).then().setTypeName("ID");

		when(Equals, EndWith).with("Id").typeOf(String).then().setTypeName("String");

		when(StartWith).with("Is").typeOf(Char).length(1).then().setTypeName("YesNo");
		when().defaultValue("'Y'", "'N'").typeOf(Char).length(1).then().setTypeName("YesNo");

		// Start Or End
		when(StartWith, EndWith).with("Email", "Host Path Url", "Phone Fax", "Upc Duns Naics Iban Bban Sku Isdn Swipe").typeOf(String).then()
				.useMatchedNameAsTypeName();

		when(EndWith).with("Type Level Mode Status Action From To Profile Unit").typeOf(Char).then().setTypeName("Attr");

		// End only
		when(StartWith, EndWith).with("Name Title Description Code Help Note Text").typeOf(String).then().useMatchedNameAsTypeName();
		when(StartWith, EndWith).with("Comments").typeOf(String).then().setTypeName("Comment");

		when(EndWith).with("SeqNo", "Distance  Count", "Length Weight   Width Height Depth Precision Frequency Position Percent", "Priority", "Port")
				.typeOf(Long, Decimal).then().useMatchedNameAsTypeName();

		when(EndWith).with("Discount Percent").typeOf(Long, Decimal).then().setTypeName("Percent");

		when(EndWith).with("Amount Rate Ratio Rating Cost").typeOf(Long, Decimal).then().setTypeName("Amount");

		when(EndWith).with("Red Green Blue Alpha Duration Volume").typeOf(Long, Decimal).then().setTypeName("Number");

		when(StartWith, EndWith).with("Margin Top Left Right Bottom Stroke SizeX SizeY X Y Z").typeOf(Long, Decimal).then().setTypeName("Length");

		when(EndWith)
				.with("Value ValueMax ValueMin Format Callout Query Rule Logic Sql Package Class Version Clause Command Script Args MailHeader Symbol",
						"Prefix Suffix", "Country City State Street Zip", "Greeting", "User Password Pw", "Folder", "Classes", "Dir", "Iso", "Pattern", "No")
				.typeOf(String).then().setTypeName("String");

		// 垃圾
		// String
		when(EndWith).with("Separator", "HeaderLeft HeaderCenter HeaderRight FooterLeft FooterCenter FooterRight JasperReport").typeOf(String).then()
				.setTypeName("String");
		when(EndWith).with("Overwrite").typeOf(Char).length(2).then().setTypeName("String");
		when(EndWith).with("DiscontinuedBy DunningGrace").typeOf(Date).then().setTypeName("Date");
		when(EndWith).with("BinaryData").typeOf(String).length(4000).then().setTypeName("Text");

		when(Equals).with("CurrentNext X Y Z").typeOf(String).then().setTypeName("String");

		// Number

		when(EndWith).with("Msg").typeOf(String).then().setTypeName("Message");
		when(EndWith).with("Addr").typeOf(String).then().setTypeName("Message");

		when(Include).with("Address").typeOf(String).then().setTypeName("Address");
		when(Equals).with("CreditCard Lot").typeOf(String).then().setTypeName("Code");

		when(EndWith).with("Acct").typeOf(String).then().setTypeName("Account");

		when(EndWith).with("Policy").typeOf(Char).then().setTypeName("Attr");

		when(StartWith, EndWith).with("Birthday Time Times Date").typeOf(Date).then().setTypeName("Date");
		when(StartWith).with("Last First Begin Start End Stop From To").typeOf(Date).then().setTypeName("Date");
		when(StartWith, EndWith).with("From To").typeOf(Date).then().setTypeName("Date");

		// count
		when(StartWith, EndWith).with("Lines Day Days DaysMin Months Years Seconds Minutes Records Copies Time Costs").typeOf(Long, Decimal).then()
				.setTypeName("Count");
		when(EndWith).with("SeqNo SerNo No").typeOf(Long).then().setTypeName("SeqNo");
		// when(Include).with("Credit").typeOf(Long,
		// Decimal).then().setTypeName("Amount");
		when(StartWith, EndWith).with("Amt").typeOf(Long, Decimal).then().setTypeName("Amount");
		when(EndWith).with("Qty").typeOf(Long, Decimal).then().setTypeName("Quantity");

		// Special
		when(EndWith).with("Processing").typeOf(Char).length(1).then().setTypeName("YesNo");
		when(EndWith).with("Language").typeOf(String).then().setTypeName("Code");
		when().typeOf(String).then().setTypeName("String");
		when().typeOf(Long, Decimal).then().setTypeName("Number");

		// 垃圾
		// when().typeOf(Char).length(1).then().setTypeName("YesNo");

		when(Equals).with("Processing").then().setTypeName("YesNo");
		when(Equals).with("Value").typeOf(String).then().setTypeName("String");
		// Skip System Column
		when(Equals).with("IsActive").typeOf(Char).skip();
		when(Equals).with("CreatedBy", "UpdatedBy").typeOf(Long).skip();
		when(Equals).with("Created", "Updated").typeOf(Date).skip();

		// Package name
		super.addDefineWords("Ad A B CM C GL I K CH CB M PA RV R S T U W");
		super.addDefineWords("BCK O LO AP E DC EFT VV CS CMC CMM CMS CMT FG BG TBG TFG OS DL RR PR CR QTR TRL V V1 VT LOG PARA INST MA MM VAL Wf U1 U2 U3 U4 RMA SLA P");
		super.addDefineWords("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 30 60 90 100 1percent 2percent 3percent 4percent");
		super.addDefineWords("AMEX pub H MC PNREF CVV ppv ms oper cum stax QA IDENT WIP SR PK NUM MICR std addr dict diff db cur pwd REVAL uid loc yy avs so po pct pj rep amt trx rev ip sync xml meta seq");
		super.addDefineWords("FQDN GAAP VIA ISDN iban bban NAICS upc sku ATM");
		super.addDefineWords("x y z xy");

		super.addDefineWords("accumde VALUTA XST SETNL TTABLE maintenence 12DE355 chare");
	}
}

package adempiere;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;

public class ImportDataDefine {
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	OutputStreamWriter out;

	public ImportDataDefine() {
		try {
			out = new OutputStreamWriter(new FileOutputStream("columns.txt"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() throws IOException {
		out.close();
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

	Map<String, String> tables = Maps.newHashMap();

	public static void main(String[] args) throws IOException {
		ImportDataDefine parser = new ImportDataDefine();
		Document document = parser.parse("orclqss.ADEMPIERE340.xml");
		// get root element
		Element rootElement = document.getDocumentElement();

		// traverse child elements

		NodeList nodeList = rootElement.getElementsByTagName("table");
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String tableName = element.getAttribute("name");
				parser.tables.put(tableName, tableName);
			}

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				parser.readTable(element);
			}
		}
		parser.close();
	}

	public void readTable(Element table) throws IOException {
		/*
		 * <table name="AD_ACCESSLOG" numRows="0"
		 * remarks="Log of Access to the System" schema="ADEMPIERE340"
		 * type="TABLE">
		 */
		String buf = "";
		String tableName =table.getAttribute("name");

		buf += "@NumRows(" + table.getAttribute("numRows") + ")\n";
		buf += "@Remarks(\"" + table.getAttribute("remarks") + "\")\n";
		buf += "type " + refineName(tableName) + " {\n";

		out.write(buf);

		NodeList nodes = table.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE && "column".equals(node.getNodeName())) {
				readColumn(tableName, (Element) node);
			}
		}

		out.write("}\n");

	}

	static final String[] systems = "IsActive Created Createdby Updated Updatedby".toUpperCase().split(" ");

	protected static Hashtable<String, String> TheNameTypes = new Hashtable<String, String>();
	static {
		//@formatter:off
			StringTokenizer st = new StringTokenizer(
					  			" "
					  				     + "A_CITY     Name "
					  				     + "A_EMAIL     Email "
					  				     + "A_STATE     Name "
					  				     + "A_STREET     Name "
					  				     + "A_ZIP     Name "
					  				     + "ACCOUNTNO     AccountNo "
					  				     + "ALPHA     Number "
					  				     + "AMOUNT     Amount "
					  				     + "BIRTHDAY     Birthday "
					  				     + "BLUE     Number "
					  				     + "CITY     Name "
					  				     + "COMMENTS     Comment "
					  				     + "DELIVERYDAYS     Count "
					  				     + "DESCRIPTION     Description "
					  				     + "DISCOUNT     Number "
					  				     + "DISCOUNTDAYS     Count "
					  				     + "DISCOUNTDAYS2     Count "
					  				     + "DOCACTION     Attr "
					  				     + "DOCSTATUS     Attr "
					  				     + "DOCUMENTNO     Long "
					  				     + "DUNS     String "
					  				     + "EMAIL     Email "
					  				     + "FAX     Fax "
					  				     + "GREEN     Number "
					  				     + "HELP     Help "
					  				     + "IBAN     IBAN "
					  				     + "ISO_CODE     String "
					  				     + "ISVALID     YesNo "
					  				     + "LDAPQUERY     String "
					  				     + "MESSAGE     Description "
					  				     + "MESSAGE2     Description "
					  				     + "MESSAGE3     Description "
					  				     + "NAICS     NAICS "
					  				     + "NAME     Name "
					  				     + "NOTE     Note "
					  				     + "PASSWORD     String "
					  				     + "PHONE     PhoneNumber "
					  				     + "PHONE2     PhoneNumber "
					  				     + "PIN     PIN "
					  				     + "POSTED     YesNo "
					  				     + "PREFIX     String "
					  				     + "PRIORITY     Priority "
					  				     + "PROBABILITY     Probability "
					  				     + "PROCESSED     YesNo "
					  				     + "QTY     Quantity "
					  				     + "RANKING     Ranking "
					  				     + "RATE     Rate "
					  				     + "RATIO     Ratio "
					  				     + "RED     Number "
					  				     + "SEQNO     SeqNo "
					  				     + "SKU     String "
					  				     + "SSN     SSN "
					  				     + "SUBJECT     Subject "
					  				     + "SUFFIX     String "
					  				     + "SWIFTCode     SWIFTCode "
					  				     + "SYMBOL     Symbol "
					  				     + "TITLE     String "
					  				     + "UOMEDICode     UOMEDICode "
					  				     + "UPC     UPC "
					  				     + "VALIDFROM     Date "
					  				     + "VALIDTO     Date "
					  				     + "VALUE     String "
					  				     + "WEIGHT     Weight "
					  				     + "DISCONTINUED     YesNo "
					  				     + "DISCONTINUEDBY     Date "
					  				     + "POSTAL     Code "
					  				     + "FREQUENCY     Number "
					  				     + "POREFERENCE     Name "
					  				     + "PRODUCTDESCRIPTION     Description "
					  				     + "LINEDESCRIPTION     Description "
					  				     + "INFO     Description "
					  				     + "RESOURCEDESCRIPTION     Description "
					  				     + "KEYWORD     String "
					  				     + "PO_DESCRIPTION     Description "
					  				     + "MEMO     Note "
					  				     + "PERCENT     Percent "
					  				     + "Cost     Number "
					  				     + "AD_LANGUAGE     String "
					  				     + "TAXID     String "
					  				     + "TOTALLINES     Count "
					  				     + "PAYMENTRULE     Name "
					  				     + "TAXID     Code "
					  				     + "BPTAXID     Code "
					  				     + "I_ISIMPORTED     YesNo "
					  				     + "DELIVERYRULE     Attr "
					  				     + "DELIVERYVIARULE     Attr "
					  				     + "PRIORITYRULE     Attr "
					  				     + "ACCESSLEVEL     Attr "
					  				     + "FREIGHTCOSTRULE     Name "
					  				     + "SORTNO     SeqNo "
					  				     + "COUNTRYCODE     Code "
					  				     + "SEQUENCE     SeqNo "
					  				     + "ASSIGNDATEFROM     Date "
					  				     + "ASSIGNDATETO     Date "
					  				     + "COUNTER     Count "
					  				     + "PRICEEFFECTIVE     Price "
					  				     + "RED_1     Long "
					  				     + "GREEN_1     Long "
					  				     + "BLUE_1     Long "
					  				     + "ALPHA_1     Long "
					  				     + "LDAPPORT     Long "
					  				     + "URL     URL "
					  				     + "XSPACE     Number "
					  				     + "YSPACE     Number "
					  				     + "SIZEX     Number "
					  				     + "SIZEY     Number "
					  				     + "HOSTPORT     Long "
					  				     + "IDRANGESTART     Long "
					  				     + "IDRANGEEND     Long "
					  				     + "IMAGEALPHA     Number "
					  				     + "STARTPOINT     Long "
					  				     + "LANGUAGEISO     Code "
					  				     + "DURATION     Number "
					  				     + "LIMIT     Number "
					  				     + "IP_ADDRESS     String "
					  				     + "FOLDER     String "
					  				     + "GAAP     GAAP "
					  				     + "ARGS     String "
					  				     + "LOCODE     Code "
					  				     + "COORDINATES     Code "
					  				     + "AREACODE     Code "
					  				     + "META_LANGUAGE     Attr "

					  				     + "LASTSYNCHRONIZED     Date "
					  				     + "LASTCONTACT     Date "
					  				     + "LASTSYNCHRONIZED     Date "
					  				     + "LASTSYNCHRONIZED     Date "
					  				     + "FIRSTSALE     Date "
					  				     + "FIRSTSALE     Date "
					  				     + "LASTCONTACT     Date "
					  				     + "CHECKED     Date "
					  				     + "SOURCEUPDATED     Date "
					  				     + "TRXSENT     Date "
					  				     + "TRXRECEIVED     Date "
					  				     + "REPLY_RECEIVED     Date "
					  				     + "LINEDATEWORKSTART     Date "
					  				     + "LINEDATEWORKCOMPLETE     Date "
					  				     + "DUNNINGGRACE     Date "
					  				     + "P_DATE_TO     Date "
					  				     + "      "
					  				     + "RELEASENO     String "
					  				     + "OPERATION     String "
					  				     + "META_LANGUAGE     Attr "
					  				     + "TOPICACTION     String "
					  				     + "DOCSUBTYPESO     String "
					  				     + "ISO_CODE_TO     String "
					  				     + "REPLENISHMENTCREATE     String "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "      "
					  				     + "ACCEPTAMEX     YesNo "
					  				     + "ACCEPTATM     YesNo "
					  				     + "ACCEPTCHECK     YesNo "
					  				     + "ACCEPTCORPORATE     YesNo "
					  				     + "ACCEPTDINERS     YesNo "
					  				     + "ACCEPTDIRECTDEBIT     YesNo "
					  				     + "ACCEPTDIRECTDEPOSIT     YesNo "
					  				     + "ACCEPTDISCOVER     YesNo "
					  				     + "ACCEPTMC     YesNo "
					  				     + "ACCEPTVISA     YesNo "
					  				     + "ACCESSTYPERULE     YesNo "
					  				     + "ACCOUNTSIGN     YesNo "
					  				     + "ACTION     YesNo "
					  				     + "AD_OVERRIDE_DICT     YesNo "
					  				     + "AFTERDELIVERY     YesNo "
					  				     + "ALLFIELDS     YesNo "
					  				     + "ALLOW_INFO_ACCOUNT     YesNo "
					  				     + "ALLOW_INFO_ASSET     YesNo "
					  				     + "ALLOW_INFO_BPARTNER     YesNo "
					  				     + "ALLOW_INFO_CASHJOURNAL     YesNo "
					  				     + "ALLOW_INFO_INOUT     YesNo "
					  				     + "ALLOW_INFO_INVOICE     YesNo "
					  				     + "ALLOW_INFO_ORDER     YesNo "
					  				     + "ALLOW_INFO_PAYMENT     YesNo "
					  				     + "ALLOW_INFO_PRODUCT     YesNo "
					  				     + "ALLOW_INFO_RESOURCE     YesNo "
					  				     + "ALLOW_INFO_SCHEDULE     YesNo "
					  				     + "ALLOWUOMFRACTIONS     YesNo "
					  				     + "ANDOR     YesNo "
					  				     + "ANYACCT     YesNo "
					  				     + "ANYACTIVITY     YesNo "
					  				     + "ANYBPARTNER     YesNo "
					  				     + "ANYCAMPAIGN     YesNo "
					  				     + "ANYLOCFROM     YesNo "
					  				     + "ANYLOCTO     YesNo "
					  				     + "ANYORG     YesNo "
					  				     + "ANYORGTRX     YesNo "
					  				     + "ANYPRODUCT     YesNo "
					  				     + "ANYPROJECT     YesNo "
					  				     + "ANYSALESREGION     YesNo "
					  				     + "ANYUSER1     YesNo "
					  				     + "ANYUSER2     YesNo "
					  				     + "APAR     YesNo "
					  				     + "AUTOARCHIVE     YesNo "
					  				     + "AUTOPERIODCONTROL     YesNo "
					  				     + "BOMUSE     YesNo "
					  				     + "BPBANKACCTUSE     YesNo "
					  				     + "BUDGETCONTROLSCOPE     YesNo "
					  				     + "CHARGEFEE     YesNo "
					  				     + "CHARGEINTEREST     YesNo "
					  				     + "CHECKCOMPLETE     YesNo "
					  				     + "CLASSIFICATION     YesNo "
					  				     + "COMMISSIONORDERS     YesNo "
					  				     + "CONFIDENTIALTYPEENTRY     YesNo "
					  				     + "CONFIGURATIONLEVEL     YesNo "
					  				     + "CONNECTIONPROFILE     YesNo "
					  				     + "COPYFROM     YesNo "
					  				     + "COPYLINES     YesNo "
					  				     + "COSTINGLEVEL     YesNo "
					  				     + "COSTINGMETHOD     YesNo "
					  				     + "COUNTHIGHMOVEMENT     YesNo "
					  				     + "CREATECONFIRM     YesNo "
					  				     + "CREATECOPY     YesNo "
					  				     + "CREATEFROM     YesNo "
					  				     + "CREATELEVELSSEQUENTIALLY     YesNo "
					  				     + "CREATEPACKAGE     YesNo "
					  				     + "CREATEPAYMENT     YesNo "
					  				     + "CREATEPO     YesNo "
					  				     + "CREATERECIPROCALRATE     YesNo "
					  				     + "CREATESO     YesNo "
					  				     + "CUMULATIVELEVEL     YesNo "
					  				     + "DECIMALPOINT     YesNo "
					  				     + "DIMENSIONUNITS     YesNo "
					  				     + "DIRECTDEPLOY     YesNo "
					  				     + "DIVIDEBY100     YesNo "
					  				     + "DOPRICING     YesNo "
					  				     + "DURATIONUNIT     YesNo "
					  				     + "DYNPRIORITYUNIT     YesNo "
					  				     + "EMAILRECIPIENT     YesNo "
					  				     + "EMAILTEST     YesNo "
					  				     + "ENFORCECLIENTSECURITY     YesNo "
					  				     + "ENFORCEPRICELIMIT     YesNo "
					  				     + "ENFORCEROLESECURITY     YesNo "
					  				     + "EVENINVOICEWEEK     YesNo "
					  				     + "EVENTCHANGELOG     YesNo "
					  				     + "FINISHMODE     YesNo "
					  				     + "GENERATELIST     YesNo "
					  				     + "GENERATEORDER     YesNo "
					  				     + "GENERATETO     YesNo "
					  				     + "IMAGEISATTACHED     YesNo "
					  				     + "IMPORTFIELDS     YesNo "
					  				     + "IMPORTTABLE     YesNo "
					  				     + "INVOICEFREQUENCY     YesNo "
					  				     + "INVOICERULE     YesNo "
					  				     + "INVOICEWEEKDAY     YesNo "
					  				     + "INVOICEWEEKDAYCUTOFF     YesNo "
					  				     + "JOINELEMENT     YesNo "
					  				     + "LANDEDCOSTDISTRIBUTION     YesNo "
					  				     + "LIMIT_BASE     YesNo "
					  				     + "LIMIT_ROUNDING     YesNo "
					  				     + "LIST_BASE     YesNo "
					  				     + "LIST_ROUNDING     YesNo "
					  				     + "LISTDETAILS     YesNo "
					  				     + "LISTSOURCES     YesNo "
					  				     + "LISTTRX     YesNo "
					  				     + "MATCHSTATEMENT     YesNo "
					  				     + "MEASUREDISPLAY     YesNo "
					  				     + "MEASURESCOPE     YesNo "
					  				     + "MMPOLICY     YesNo "
					  				     + "NETDAY     YesNo "
					  				     + "NEXTACTION     YesNo "
					  				     + "OVERWRITEACCT     YesNo "
					  				     + "OVERWRITEACTIVITY     YesNo "
					  				     + "OVERWRITEBPARTNER     YesNo "
					  				     + "OVERWRITECAMPAIGN     YesNo "
					  				     + "OVERWRITELOCFROM     YesNo "
					  				     + "OVERWRITELOCTO     YesNo "
					  				     + "OVERWRITEORG     YesNo "
					  				     + "OVERWRITEORGTRX     YesNo "
					  				     + "OVERWRITEPRICELIMIT     YesNo "
					  				     + "OVERWRITEPRODUCT     YesNo "
					  				     + "OVERWRITEPROJECT     YesNo "
					  				     + "OVERWRITESALESREGION     YesNo "
					  				     + "OVERWRITEUSER1     YesNo "
					  				     + "OVERWRITEUSER2     YesNo "
					  				     + "PAYMENTRULEPO     YesNo "
					  				     + "PERIODACTION     YesNo "
					  				     + "POSTACTUAL     YesNo "
					  				     + "POSTBUDGET     YesNo "
					  				     + "POSTENCUMBRANCE     YesNo "
					  				     + "POSTSTATISTICAL     YesNo "
					  				     + "PRIORITYBASE     YesNo "
					  				     + "PRIORITYUSER     YesNo "
					  				     + "PROCCREATE     YesNo "
					  				     + "PROJECTCATEGORY     YesNo "
					  				     + "PROJECTLINELEVEL     YesNo "
					  				     + "PROJINVOICERULE     YesNo "
					  				     + "PUBLISHRFQ     YesNo "
					  				     + "QUERYSOURCE     YesNo "
					  				     + "R_AVSADDR     YesNo "
					  				     + "R_AVSZIP     YesNo "
					  				     + "R_CVV2MATCH     YesNo "
					  				     + "RANKRFQ     YesNo "
					  				     + "RATING     YesNo "
					  				     + "RATIOOPERAND     YesNo "
					  				     + "RECEIVEINQUIRYREPLY     YesNo "
					  				     + "RECEIVEORDERREPLY     YesNo "
					  				     + "RECOGNITIONFREQUENCY     YesNo "
					  				     + "REDO     YesNo "
					  				     + "REQUIRESTAXCERTIFICATE     YesNo "
					  				     + "REQUIREVV     YesNo "
					  				     + "SCRIPTROLL     YesNo "
					  				     + "SENDDUNNINGLETTER     YesNo "
					  				     + "SENDEMAIL     YesNo "
					  				     + "SENDINQUIRY     YesNo "
					  				     + "SENDIT     YesNo "
					  				     + "SENDORDER     YesNo "
					  				     + "SEPARATOR     YesNo "
					  				     + "SHOWHELP     YesNo "
					  				     + "SPLITELEMENT     YesNo "
					  				     + "STARTMODE     YesNo "
					  				     + "STARTNEWYEAR     YesNo "
					  				     + "STD_BASE     YesNo "
					  				     + "STD_ROUNDING     YesNo "
					  				     + "STOREARCHIVEONFILESYSTEM     YesNo "
					  				     + "STOREATTACHMENTSONFILESYSTEM     YesNo "
					  				     + "SUBFLOWEXECUTION     YesNo "
					  				     + "UNDO     YesNo "
					  				     + "UNINSTALL     YesNo "
					  				     + "UPDATEQTY     YesNo "
					  				     + "USECURRENCYBALANCING     YesNo "
					  				     + "USESUSPENSEBALANCING     YesNo "
					  				     + "USESUSPENSEERROR     YesNo "
					  				     + "VALIDATEWORKFLOW     YesNo "
					  				     + "WEEKDAY     YesNo "





						);
			//@formatter:on

		while (st.hasMoreTokens())
			TheNameTypes.put(st.nextToken(), st.nextToken());
	}

	public void readColumn(String tableName, Element column) throws IOException {
		for (String rev : systems) {
			if (rev.equals(column.getAttribute("name"))) {
				return;
			}
		}
		/*
		 * <table name="AD_ACCESSLOG" numRows="0"
		 * remarks="Log of Access to the System" schema="ADEMPIERE340"
		 * type="TABLE">
		 */
		String buf = "";
		String rawColName = column.getAttribute("name");
		String rawTypeName = column.getAttribute("type");
		String rawTypeSize = column.getAttribute("size");
		String tColName = null;
		String tTypeName = null;
		/*
		 * buf += "\t@autoUpdated(" + column.getAttribute("autoUpdated") +
		 * ")\n";
		 */
		/* buf += "\t@remarks(\"" + column.getAttribute("remarks") + "\")\n"; */
		buf += "\t";
		buf += "true".equals(column.getAttribute("nullable")) ? "?" : "";

		boolean isEntity = false;

		NodeList nodes = column.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE && "parent".equals(node.getNodeName())) {
				if (rawColName.endsWith("_ID")) {
					tColName = rawColName.substring(0, rawColName.length() - 3);
				} else {
					tColName = rawColName;
				}
				Element parent = (Element) node;
				tTypeName = refineName(parent.getAttribute("table"));
				isEntity = true;
			}
		}

		if (!isEntity) {
			if (rawColName.endsWith("_ID")) {
				String fieldTableName = rawColName.substring(0, rawColName.length() - 3);
				if (tableName.equalsIgnoreCase(fieldTableName)) {
					tColName = "ID";
					tTypeName = "ID";
				} else {
					tColName = fieldTableName;
					tTypeName = "ID";
				}
			}
		}

		if (tTypeName == null) {
			if (TheNameTypes.containsKey(rawColName)) {
				tColName = rawColName;
				tTypeName = TheNameTypes.get(rawColName);
			}
		}

		if (tTypeName == null) {
			if (rawColName.startsWith("IS") && "CHAR".equals(rawTypeName) && "1".equals(rawTypeSize)) {
				tColName = rawColName;
				tTypeName = "YesNo";
			}
		}
		if (tTypeName == null) {
			if ("DATE".equals(rawTypeName) && (rawColName.startsWith("DATE") || rawColName.endsWith("DATE"))) {
				tColName = rawColName;
				tTypeName = "Date";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("LINE") || rawColName.endsWith("LINE"))) {
				tColName = rawColName;
				tTypeName = "Long";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("QTY") || rawColName.endsWith("QTY"))) {
				tColName = rawColName;
				tTypeName = "Quantity";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("WIDTH") || rawColName.endsWith("WIDTH"))) {
				tColName = rawColName;
				tTypeName = "Width";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("HEIGHT") || rawColName.endsWith("HEIGHT"))) {
				tColName = rawColName;
				tTypeName = "Height";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("LENGTH") || rawColName.endsWith("LENGTH"))) {
				tColName = rawColName;
				tTypeName = "Length";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("DEPTH") || rawColName.endsWith("DEPTH"))) {
				tColName = rawColName;
				tTypeName = "Depth";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("WEIGHT") || rawColName.endsWith("WEIGHT"))) {
				tColName = rawColName;
				tTypeName = "Weight";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("VOLUME") || rawColName.endsWith("VOLUME"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("COST") || rawColName.endsWith("COST"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("POSITION") || rawColName.endsWith("POSITION"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("MARGIN") || rawColName.endsWith("MARGIN"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && rawColName.endsWith("NO")) {
				tColName = rawColName;
				tTypeName = "SeqNo";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("DUE") || rawColName.endsWith("DUE"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("PASTDUE") || rawColName.endsWith("PASTDUE"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("AMT") || rawColName.endsWith("AMT"))) {
				tColName = rawColName;
				tTypeName = "Amount";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("AMOUNT") || rawColName.endsWith("AMOUNT"))) {
				tColName = rawColName;
				tTypeName = "Amount";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("PRICE") || rawColName.endsWith("PRICE"))) {
				tColName = rawColName;
				tTypeName = "Price";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("UNIT") || rawColName.endsWith("UNIT"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("CLICK") || rawColName.endsWith("CLICK"))) {
				tColName = rawColName;
				tTypeName = "Count";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName)
					&& (rawColName.startsWith("IMPRESSION") || rawColName.endsWith("IMPRESSION"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("ACCT") || rawColName.endsWith("ACCT"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("VERSION") || rawColName.endsWith("VERSION"))) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("COUNT") || rawColName.endsWith("COUNT"))) {
				tColName = rawColName;
				tTypeName = "Count";
			}
		}

		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("TIME") || rawColName.endsWith("TIME"))) {
				tColName = rawColName;
				tTypeName = "Long";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("LEVEL") || rawColName.endsWith("LEVEL"))) {
				tColName = rawColName;
				tTypeName = "Long";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("COL") || rawColName.endsWith("COL"))) {
				tColName = rawColName;
				tTypeName = "Long";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName) && (rawColName.startsWith("DAYS") || rawColName.endsWith("DAYS"))) {
				tColName = rawColName;
				tTypeName = "Count";
			}
		}
		if (tTypeName == null) {
			if ("NUMBER".equals(rawTypeName)) {
				tColName = rawColName;
				tTypeName = "Number";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("URL") || rawColName.endsWith("URL"))) {
				tColName = rawColName;
				tTypeName = "URL";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("NAME") || rawColName.endsWith("NAME"))) {
				tColName = rawColName;
				tTypeName = "Name";
			}
		}
		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("STATE") || rawColName.endsWith("STATE"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && (rawColName.startsWith("STATUS") || rawColName.endsWith("STATUS"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && (rawColName.startsWith("TYPE") || rawColName.endsWith("TYPE"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && (rawColName.startsWith("LEVEL") || rawColName.endsWith("LEVEL"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && (rawColName.startsWith("TYPE") || rawColName.endsWith("TYPE"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && (rawColName.startsWith("STATE") || rawColName.endsWith("STATE"))) {
				tColName = rawColName;
				tTypeName = "Attr";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && rawColName.startsWith("ON")) {
				tColName = rawColName;
				tTypeName = "YesNo";
			}
		}
		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName) && rawColName.startsWith("HAS")) {
				tColName = rawColName;
				tTypeName = "YesNo";
			}
		}

		if (tTypeName == null) {
			if ("CHAR".equals(rawTypeName)
					&& (rawColName.startsWith("PROCESSING") || rawColName.endsWith("PROCESSING"))) {
				tColName = rawColName;
				tTypeName = "YesNo";
			}
		}
		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && Long.parseLong(rawTypeSize) >= 2000) {
				tColName = rawColName;
				tTypeName = "Note";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && Long.parseLong(rawTypeSize) >= 2000) {
				tColName = rawColName;
				tTypeName = "Note";
			}
		}
		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName) && Long.parseLong(rawTypeSize) >= 2000) {
				tColName = rawColName;
				tTypeName = "Note";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("ADDRESS") || rawColName.endsWith("ADDRESS"))) {
				tColName = rawColName;
				tTypeName = "Name";
			}
		}
		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("HOST") || rawColName.endsWith("HOST"))) {
				tColName = rawColName;
				tTypeName = "Hose";
			}
		}
		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && (rawColName.startsWith("ADDR") || rawColName.endsWith("ADDR"))) {
				tColName = rawColName;
				tTypeName = "Name";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName) && rawColName.endsWith("NAME")) {
				tColName = rawColName;
				tTypeName = "Name";
			}
		}
		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName) && rawColName.endsWith("NAME")) {
				tColName = rawColName;
				tTypeName = "Name";
			}
		}

		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName) && rawColName.endsWith("VALUE")) {
				tColName = rawColName;
				tTypeName = "String";
			}
		}
		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName) && (rawColName.startsWith("LINK") || rawColName.endsWith("LINK"))) {
				tColName = rawColName;
				tTypeName = "Url";
			}
		}

		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName)
					&& (rawColName.startsWith("DESCRIPTION") || rawColName.endsWith("DESCRIPTION"))) {
				tColName = rawColName;
				tTypeName = "Description";
			}
		}

		if (tTypeName == null) {
			if ("DATE".equals(rawTypeName) && (rawColName.startsWith("TIME") || rawColName.endsWith("TIME"))) {
				tColName = rawColName;
				tTypeName = "Time";
			}
		}

		if (tTypeName == null) {
			if ("BLOB".equals(rawTypeName)) {
				tColName = rawColName;
				tTypeName = "Note";
			}
			if ("CLOB".equals(rawTypeName)) {
				tColName = rawColName;
				tTypeName = "Note";
			}
		}

		if (tTypeName == null) {
			if ("NVARCHAR2".equals(rawTypeName)) {
				int size = Integer.parseInt(rawTypeSize);
				switch (size) {
				case 1020:
					tTypeName = "Note";

					break;
				case 510:
					tTypeName = "Note";

					break;
				case 480:
					tTypeName = "Note";

					break;

				case 240:
					tTypeName = "Description";

					break;

				case 120:
					tTypeName = "Description";

					break;

				case 80:
					tTypeName = "Description";

					break;
				case 60:
					tTypeName = "String";

					break;
				case 44:
					tTypeName = "String";

					break;
				case 40:
					tTypeName = "String";

					break;
				case 20:
					tTypeName = "String";

					break;
				default:
					tTypeName = "String";
					break;
				}
				tColName = rawColName;
			}
		}

		if (tTypeName == null) {
			if ("VARCHAR2".equals(rawTypeName)) {
				tTypeName = "String";
				tColName = rawColName;
			}
			if ("NCHAR".equals(rawTypeName)) {
				tTypeName = "String";
				tColName = rawColName;
			}
		}

		if (tColName == null) {
			out.write(rawColName + "\t" + rawTypeName + "\t" + column.getAttribute("size").trim() + "\t" + tableName
					+ "\n");
		}

		tColName = refineName(tColName);

		if (tColName.equalsIgnoreCase(tTypeName)) {
			buf += tColName;
			buf += ";";
		} else {
			buf += tColName;
			buf += " " + tTypeName + ";";
		}

		String defaultValue = column.getAttribute("defaultValue");
		if (defaultValue.length() > 0) {
			buf += "\t/*";
			buf += " " + defaultValue.trim() + " ";
			buf += "*" + "/";
		}

		buf += "\n";

		out.write(buf);

	}

	static final String[] revs = ("is display field where Enforce Support release database"
			+ " Logger source method class Function ldap Date Selection client Alert role Frequency "
			+ "Keep Select Form Event Change Request Auto Smtp Store unix windows 	File Discount "
			+ " Line Bpartner cash Readonly Other Error Post Record entity Access Centrally Dependent "
			+ "Label Validation Old New Text Dimension format paper Margin Hdr Image Footer header "
			+ "value  Paint Printer Print orderby Line Procedure Beta Server show Unrealized Docno Overwrite "
			+ "Create Days between after Charge Interest Dunning Doctype Invoice  payment Times Tender "
			+ " Archive Conversion Amt Foreign Document Fee Total ").toUpperCase().split(" ");

	public String refineName(String name) {
		for (String rev : revs) {
			name = name.replaceFirst("^" + rev, rev + "_");
			name = name.replaceFirst("_" + rev, "_" + rev + "_");
		}
		name = name.replaceFirst("__", "_");

		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
	}
}

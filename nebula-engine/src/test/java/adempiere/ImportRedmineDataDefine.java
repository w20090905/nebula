package adempiere;

import static adempiere.DBColumnType.Bit;
import static adempiere.DBColumnType.Blob;
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
import static adempiere.MatchPattern.StartWithIgnoreCase;

import java.io.IOException;
import java.util.List;

import nebula.lang.TypeStandalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ImportRedmineDataDefine extends DefaultImporter {
	Log log = LogFactory.getLog(getClass());

	public static void main(String[] args) throws IOException {
		String inputFileName = "apps/redmine/redmine_baseline.xml";
		String outputFolder = "apps/redmine";

		ImportRedmineDataDefine parser = new ImportRedmineDataDefine();
		Document document = parser.parse(inputFileName);
		// get root element
		Element rootElement = document.getDocumentElement();
		parser.read(outputFolder, rootElement);
		parser.analyze(parser.types);
		parser.output(outputFolder);
	}

	public ImportRedmineDataDefine() {

		when(EqualsIgnoreCase).with("repository_id").table("Changesets").setReferTo("Repositories");
		when(EqualsIgnoreCase).with("status_id").table("issues").setReferTo("IssueStatuses");

		when(EqualsIgnoreCase).with("login").table("users").setAsMaster();
		
		when(EqualsIgnoreCase).with("ID").is(Long).setTypeName("ID");

		when(EqualsIgnoreCase)
				.with("Name", "Description", "Comment", "Account", "Regexp", "Title", "Host", "Filename", "TimeZone",
						"Status", "Url", "Password", "Subject", "Content", "Summary", "Revision").is(String)
				.useMatchedNameAsTypeName().useMatchedNameAsFieldName();
		when(EqualsIgnoreCase).with("FileSize", "Port", "Status", "Version", "Position").is(Long)
				.useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(EndWithIgnoreCase).with("Path", "Password", "FirstName", "FileName", "LastName", "Title", "Url")
				.is(String).useMatchedNameAsTypeName();
		when(EndWithIgnoreCase).with("Count", "Length", "Height", "Size", "Weight", "Ratio").is(Long)
				.useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Text").is(Text).useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("_On").is(Datetime).setTypeName(Datetime.name());
		when(EndWithIgnoreCase).with("_On").is(Date).setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Date").is(Date).setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Timestamp").is(Timestamp).setTypeName(Timestamp.name());

		when(StartWithIgnoreCase, EndWithIgnoreCase, EqualsIgnoreCase).with("Type").is(String).setTypeName("Attr");
		when(StartWithIgnoreCase).with("Is_").is(Bit).setTypeName("YesNo");
		when(EndWithIgnoreCase).with("_ID").is(Long).setTypeName("ID");

		when(EndWithIgnoreCase).with("Value").is(String).setTypeName("String");

		when(EndWithIgnoreCase).with("Comments").is(String).setTypeName("Note");
		when(EndWithIgnoreCase).with("Mail").is(Varchar, NVarchar).setTypeName("EMail");
		when(EndWithIgnoreCase).with("Hours").is(Decimal).setTypeName("Number");
		when(EndWithIgnoreCase).with("Encoding").is(String).setTypeName("String");
		when(EndWithIgnoreCase).with("Notes").is(Text).setTypeName("Note");

		when(EqualsIgnoreCase).with("tyear", "tmonth", "tweek").is(Long).setTypeName("Count");
		// notsure

		when(EqualsIgnoreCase).with("Redirects_to", "Homepage").is(String).setTypeName("Url");

		when().is(Bit).setTypeName("YesNo");
		when().is(Varchar, NVarchar).setTypeName("String");
		when().is(Text, Blob).setTypeName("Note");
		when().is(Long).setTypeName("Count");

		// Skip System Column
		// when(EqualsIgnoreCase).with("CREATED_ON","UPDATED_ON").is(Date,Datetime,Timestamp).skip();
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

			if (type.standalone == TypeStandalone.Abstract) {
				if (hasIDKey && !hasName) {
					type.standalone = TypeStandalone.Transaction;
				} else if (hasNameKey) {
					type.standalone = TypeStandalone.Master;
				} else if (hasIDKey && hasNameRequired) {
					type.standalone = TypeStandalone.Master;
				}
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
						System.out.println("Fail check foreign key : " + type.name + " - " + field.name);
					}
				}
			}
		}

		// 附属表的情况，主键为另一个对象的主键
		for (Type type : types) {
			for (Field field : type.fields) {
				if (field.isKey) {
					if (field.name.toUpperCase().endsWith("_ID") && !"ID".equals(field.resultTypeName)
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
				System.out.println("## Type not sure  " + type.name);
			}
		}
		System.out.println("\n\n\n=================================================\n\n\n");
		for (Type type : types) {
			for (Field field : type.fields) {
				System.out.println(type.rawName + "\t" + type.name + "\t" + type.standalone.name() + "\t"
						+ type.comment + "\t" + field);
			}
		}
	}

}

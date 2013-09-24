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
		super(false, true);

		when(EqualsIgnoreCase).with("repository_id").inTable("Changesets").then().setReferTo("Repositories");
		when(EqualsIgnoreCase).with("status_id").inTable("issues").then().setReferTo("IssueStatuses");

		when(EqualsIgnoreCase).with("login").inTable("users").then().setAsMaster();

		when(EqualsIgnoreCase).with("ID").typeOf(Long).then().useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(EqualsIgnoreCase)
				.with("Name", "Description", "Comment", "Account", "Regexp", "Title", "Host", "Filename", "TimeZone",
						"Status", "Url", "Password", "Subject", "Content", "Summary", "Revision","Path", "FirstName","LastName").typeOf(String)
				.then().useMatchedNameAsTypeName().useMatchedNameAsFieldName();
		when(EqualsIgnoreCase).with("FileSize", "Port", "Status", "Version", "Position").typeOf(Long)
				.then().useMatchedNameAsTypeName().useMatchedNameAsFieldName();

		when(EndWithIgnoreCase).with("Path", "Password", "FirstName", "FileName", "LastName", "Title", "Url")
				.typeOf(String).then().useMatchedNameAsTypeName();
		when(EndWithIgnoreCase).with("Count", "Length", "Height", "Size", "Weight", "Ratio").typeOf(Long)
				.then().useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("Text").typeOf(Text).then().useMatchedNameAsTypeName();

		when(EndWithIgnoreCase).with("_On").typeOf(Datetime).then().setTypeName(Datetime.name());
		when(EndWithIgnoreCase).with("_On").typeOf(Date).then().setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Date").typeOf(Date).then().setTypeName(Date.name());
		when(EndWithIgnoreCase).with("_Timestamp").typeOf(Timestamp).then().setTypeName(Timestamp.name());

		when(StartWithIgnoreCase, EndWithIgnoreCase, EqualsIgnoreCase).with("Type").typeOf(String).then().setTypeName("Attr");
		when(StartWithIgnoreCase).with("Is_").typeOf(Bit).then().setTypeName("YesNo");
		when(EndWithIgnoreCase).with("_ID").typeOf(Long).then().setTypeName("ID");

		when(EndWithIgnoreCase).with("Value").typeOf(String).then().setTypeName("String");

		when(EndWithIgnoreCase).with("Comments").typeOf(String).then().setTypeName("Note");
		when(EndWithIgnoreCase).with("Mail").typeOf(Varchar, NVarchar).then().setTypeName("EMail");
		when(EndWithIgnoreCase).with("Hours").typeOf(Decimal).then().setTypeName("Number");
		when(EndWithIgnoreCase).with("Encoding").typeOf(String).then().setTypeName("String");
		when(EndWithIgnoreCase).with("Notes").typeOf(Text).then().setTypeName("Note");

		when(EqualsIgnoreCase).with("tyear", "tmonth", "tweek").typeOf(Long).then().setTypeName("Count");
		// notsure

		when(EqualsIgnoreCase).with("Redirects_to", "Homepage").typeOf(String).then().setTypeName("Url");

		when().typeOf(Bit).then().setTypeName("YesNo");
		when().typeOf(Varchar, NVarchar).then().setTypeName("String");
		when().typeOf(Text, Blob).then().setTypeName("Note");
		when().typeOf(Long).then().setTypeName("Count");

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
					if (typesByRawName.containsKey(field.foreignKeyTable)) {
						field.resultTypeName = typesByRawName.get(field.foreignKeyTable).name;
					}
				} else if (!field.isKey && "ID".equals(field.resultTypeName)) {
					String typename = field.name;
					if (typename.toUpperCase().endsWith("_ID")) {
						typename = typename.substring(0, typename.length() - 3);
						field.resultName = field.resultName.substring(0, field.resultName.length() - 2);
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

		// 附属表的情况，主键为另一个对象的主键
		for (Type type : types) {
			for (Field field : type.fields) {
				if(field.resultName.equalsIgnoreCase(field.typename)){
					field.resultName = field.typename;
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

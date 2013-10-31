package test.language.antlr.StringTemplate;

import java.util.Map;

import test.language.antlr.StringTemplate.StringTemplatePerformance.Person;

public class PageManual {
	public String compile(Map<String, Object> root) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\r\n<head>\r\n<title>");
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append(root.get("name"));
		sb.append("</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>");
		return sb.toString();
	}

	public String compile2(Map<String, Object> root) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\r\n<head>\r\n<title>");
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append(((Person) root.get("person")).getName());
		sb.append("</title>\r\n<body>\r\n<hr>\r\n</body>\r\n</html>");
		return sb.toString();
	}
}

package nebula.data.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

import nebula.data.BasicFieldDealer;
import nebula.lang.RawTypes;

class PageField {
	public PageField(String name, String pageName, RawTypes innerType) {
		this.name = name;
		this.pageName = pageName;
		this.innerType = innerType;
	}

	public String name;
	public String pageName;
	public RawTypes innerType;
	public BasicFieldDealer<?,JsonParser,JsonGenerator> dataDealer;
}

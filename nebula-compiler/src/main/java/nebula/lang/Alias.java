package nebula.lang;

import java.util.HashMap;
import java.util.Map;

public class Alias {
	final String defaultValue;
	public final Map<String, String> alias;
	public Alias(String defaultValue){
		this.alias = new HashMap<String, String>();
		this.defaultValue = defaultValue;
	}
	
	public void add(String language,String value){
		alias.put(language, value);
	}
	
	public String getDefault(){
		return defaultValue;		
	}
	
	public String get(String language){
		if(language==null){
			return defaultValue;
		}
		String str = alias.get(language);
		if(str==null){
			str = alias.get(language.substring(0, 2));
		}
		return str;
	}
}

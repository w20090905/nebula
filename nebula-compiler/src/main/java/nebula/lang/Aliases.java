package nebula.lang;

import java.util.HashMap;
import java.util.Map;

public class Aliases {
	String defaultValue;
	public final Map<String, String> alias;
	public Aliases(String defaultValue){
		this.alias = new HashMap<String, String>();
		this.defaultValue = defaultValue;
	}
	
	public void add(String language,String value){
		alias.put(language, value);
	}
	
	public String getDefault(){
		return defaultValue;		
	}
	
	public void extend(Aliases other){
		this.defaultValue = other.defaultValue;
		this.alias.putAll(other.alias);
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

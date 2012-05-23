package nebula.compiler;

import java.util.HashMap;
import java.util.Map;

public class Alias {
	final Map<String, String> alias;
	public Alias(){
		alias = new HashMap<>();
	}
	
	public Alias(String defaultValue){
		alias = new HashMap<>();
		alias.put("<>", defaultValue);
	}
	
	public void setDefault(String value){
		alias.put("<>", value);
	}
	
	public void add(String language,String value){
		alias.put(language, value);
	}
	
	public String getDefault(){
		return alias.get("<>");		
	}
	
	public String get(String language){
		if(language==null){
			language = "<>";
		}
		return alias.get(language);
	}
}

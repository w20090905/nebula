package nebula.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Type {

    public static final String Master = "Master";
    public static final String Attribute = "Attribute";
    public static final String Underlying = "Underlying";
    public static final String Sequence = "Sequence";
    public static final String Scala = "Scala";
    public static final String Eembedded = "Eembedded";

    String name;
    String displayName;

    boolean standalone = true;

    String master;

    Type declaringType = null;

    List<Field> fields;

    Map<String,Object> attrs = new HashMap<>();
    // Type declaringType;
    
    final Type superType;    
    final String rawType;

    static Type BUILDERINTYPE = new Type("BuildInType");
    static Type ENTITY = new Type("ENTITY");
    
    
    
    private Type(String name) {
        super();
        this.name = name;
        superType = null;
        rawType = null;
        
        //this.fields = new ArrayList<Field>();
    }
    
    Type(String name,Type superType) {
        super();
        this.superType = superType;
        this.rawType = superType.rawType;
        
        this.name = name;
        this.fields = new ArrayList<Field>();
    }
    
    Type(String name,Type superType,String rawType) {
        super();
        this.superType = superType;
        this.rawType = rawType;
        
        this.name = name;
        this.fields = new ArrayList<Field>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isStandalone() {
        return standalone;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }

    public Type getDeclaringType() {
        return declaringType;
    }

    public void setDeclaringType(Type declaringType) {
        this.declaringType = declaringType;
    }

	@Override
	public String toString() {
		return "Type [name=" + name + ", displayName=" + displayName + ", standalone=" + standalone + ", master="
				+ master + ", declaringType=" + declaringType + ", fields=" + fields + "]";
	}

   
}

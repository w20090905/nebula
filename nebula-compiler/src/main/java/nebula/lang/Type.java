package nebula.lang;

import java.net.URL;
import java.util.List;

import nebula.data.Timable;
import util.InheritHashMap;

public interface Type extends Timable {
	public static final String ATTACH = "Attach";
	public static final String ATTACH_TO = "AttachTo";
	public static final String CTOR = "<ctor>";
	public static final String ForeignKey = "ForeignKey";
	public static final String GROUP_BY = "GroupBy";

	public static final String LEGACY = "Legacy";
	public static final String ONLOAD = "<onLoad>";
	public static final String ONSAVE = "<onSave>";

	public static String ROOT_TYPE = "T";

	public Field getActionByName(String name) ;

	public List<Field> getActions() ;

	public List<Type> getAttachedBy() ;

	public InheritHashMap getAttrs();

	public String getCode();

	public List<Field> getDeclaredFields();

	public String getDisplayName() ;

	public List<Field> getFields() ;
	public Field getKeyField();

	public long getLastModified() ;
	
	public String getName() ;

	public Aliases getNameAlias();

	public RawTypes getRawType() ;

	public List<Field> getReferences();

	public Type getResidedType() ;

	public TypeStandalone getStandalone();

	public Type getSuperType();

	public TypeLoader getTypeLoader() ;

	public URL getUrl();

	public boolean isArray();

	public boolean isMutable() ;

	public List<Type> getRelations();

	public List<Type> getSubTypes();
}

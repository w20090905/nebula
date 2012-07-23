package nebula.lang;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import static nebula.lang.Importance.*;

enum TypeStandalone {
	Master, Sequence, Basic, Eembedded
}

public class Type {
	final TypeLoader loader;

	final Type residedType;
	final Type superType;
	final String rawType;// For Basic Type

	String name;
	Alias nameAlias;

	TypeStandalone standalone = TypeStandalone.Master;

	List<Field> fields;

	Properties attrs;

	URL url;
	boolean mutable = false;
	String code;

	List<Field> references;
	List<Field> actualFields;

	public static String TYPE = "Type";
	public static String ENTITY = "Entity";
	public static String BASIC = "Basic";

	Type(TypeLoader typeLoader, String name) {
		this(typeLoader, name, null, null);
	}

	Type(TypeLoader typeLoader, String name, Type superType) {
		this(typeLoader, name, superType, superType.rawType);
	}

	Type(TypeLoader typeLoader, String name, Type superType, String rawType) {
		this(typeLoader, null, name, superType, rawType);
	}

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType) {
		this(typeLoader, residedType, name, superType, superType.rawType);
	}

	Type(TypeLoader typeLoader, Type residedType, String name, Type superType, String rawType) {
		super();
		this.loader = typeLoader;
		this.residedType = residedType;
		this.superType = superType;
		this.rawType = rawType;

		if (residedType != null) {
			standalone = TypeStandalone.Eembedded;
		}

		if (this.superType != null) {
			attrs = new Properties(this.superType.attrs);
			this.standalone = superType.standalone;
		} else {
			attrs = new Properties();
			if ("Entity".equals(name)) {
				this.standalone = TypeStandalone.Master;
			} else {
				this.standalone = TypeStandalone.Basic;
			}
		}
		this.name = name;
		this.fields = new ArrayList<Field>();
		references = new CopyOnWriteArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Alias getNameAlias() {
		return nameAlias;
	}

	public Properties getAttrs() {
		return attrs;
	}

	public TypeLoader getTypeLoader() {
		return loader;
	}

	public Type getResidedType() {
		return residedType;
	}

	public Type getSuperType() {
		return superType;
	}

	public String getRawType() {
		return rawType;
	}

	public TypeStandalone getStandalone() {
		return standalone;
	}

	public URL getUrl() {
		return url;
	}

	public boolean isMutable() {
		return mutable;
	}

	public String getCode() {
		return code;
	}

	public void link(TypeLoader loader) {
		for (Field f : fields) {
			f.type.references.add(f);
		}

		actualFields = new ArrayList();

		for (Field f : fields) {

			if (f.isArray()) {
				// TODO
			} else {

				switch (f.getRefer()) {
				case ByVal:
					actualFields.add(f);
					break;
				case Inline:
					for (Field rF : f.type.fields) {
						String cName = f.name + "_" + rF.name;
						Field nF = new Field(this, cName);
						nF.type = rF.type;
						if(f.importance.ordinal() < rF.importance.ordinal()){
							nF.importance = f.importance;
						}else{
							nF.importance = rF.importance;
						}

					/*	switch (f.importance) {
						case Key:
							nF.importance = rF.importance;
						case Core:
							switch (rF.importance) {
							case Key:
								nF.importance = Importance.Core;
								break;
							default:
								nF.importance = rF.importance;
							}
						case Require:
							switch (rF.importance) {
							case Key:
							case Core:
							case Require:
								nF.importance = Importance.Require;
								break;
							default:
								nF.importance = rF.importance;
							}
						case Unimportant:
							nF.importance = f.importance;
						}*/
						actualFields.add(nF);
					}
					break;
				case ByRef:
//					for (Field rF : f.type.fields) {
//						if (rF.importance == Field.KEY || rF.importance == Field.CORE) {
//							String cName = f.getName() + "_" + rF.getName();
//							Field newField = new Field(this, cName);
//							newField.type = rF.type;
//							if (f.importance == Field.KEY && rF.importance == Field.KEY) {
//								newField.importance = Field.KEY;
//							}
//							actualFields.add(newField);
//						}
//					}
				}
			}
		}
	}

	public List<Field> getReferences() {
		return references;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", nameAlias=" + nameAlias + ", standalone=" + standalone + ", text=" + code
				+ "]";
	}

}

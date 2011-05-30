package it.trace.model;

public class Field {

	private String name;
	
	private Class<?> javaType = null;
	
	private boolean key = false;
	
	private Object defaultValue = null;
	
	private boolean notNull = false;
	
	public Field(String name) {
		this.name = name;
	}
	
	public Field(String name, Class<?> javaType) {
		this.name = name;
		this.javaType = javaType;
	}
	
	public Field(String name, Class<?> javaType, boolean isKey) {
		this.name = name;
		this.javaType = javaType;
		this.key = isKey;
	}
	
	public Field(String name, Class<?> javaType, Object defaultValue, boolean notNull) {
		this.name = name;
		this.javaType = javaType;
		this.defaultValue = defaultValue;
		this.notNull = notNull;
	}
	
	public Field(String name, Class<?> javaType, boolean isKey, Object defaultValue, boolean notNull) {
		this.name = name;
		this.javaType = javaType;
		this.key = isKey;
		this.defaultValue = defaultValue;
		this.notNull = notNull;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}
	
	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public String getPackage() {
		return this.javaType.getPackage().getName();
	}
	
}

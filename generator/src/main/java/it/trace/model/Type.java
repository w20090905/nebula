package it.trace.model;

import java.util.ArrayList;
import java.util.List;

public class Type {

	private String name;
	// private String pkg;

	private List<Field> fields = new ArrayList<Field>();

	public Type(String name) {
		this.name = name;
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

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void addField(Field f) {
		this.fields.add(f);
	}
	
	public List<Field> getIdFields() {
		List<Field> l = new ArrayList<Field>();
		for (Field f : this.fields) {
			if (f.isKey()) {
				l.add(f);
			}
		}
		return l;
	}
	
	public List<Field> getCommonFields() {
		List<Field> l = new ArrayList<Field>();
		for (Field f : this.fields) {
			if (!f.isKey()) {
				l.add(f);
			}
		}
		return l;
	}

}

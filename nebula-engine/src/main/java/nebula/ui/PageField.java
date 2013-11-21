package nebula.ui;

import nebula.lang.Code;

public class PageField {

	String internalName;
	String name;
	String description;
	String help;
	// IsCentrallyMaintained YesNo;/* 'Y' */
	//
	// @ForeignKeyColumn("AD_TAB_ID") @Column("AD_TAB_ID") AdTab;
	// @ForeignKeyColumn("AD_COLUMN_ID") @Column("AD_COLUMN_ID") ?AdColumn;
	// @ForeignKeyColumn("AD_FIELDGROUP_ID") @Column("AD_FIELDGROUP_ID")
	// ?AdFieldGroup;
	boolean displayed;
	Code displayLogic;
	int length;
	boolean readonly = false;
	int seqNo;
	int sortNo;
	boolean sameLine = true;
	boolean heading = false;
	boolean fieldOnly = false;
	boolean encrypted = false;
	String inputType;
	
	String render;

	// ?ObscureType Attr;
	// @ForeignKeyColumn("AD_REFERENCE_ID") @Column("AD_REFERENCE_ID")
	// ?AdReference;
	boolean mandatory = false;
	// @Column("INCLUDED_TAB_ID") ?IncludedTabId ID;
	Code defaultValue;
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getHelp() {
		return help;
	}
	public boolean isDisplayed() {
		return displayed;
	}
	public Code getDisplayLogic() {
		return displayLogic;
	}
	public int getLength() {
		return length;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public int getSortNo() {
		return sortNo;
	}
	public boolean isSameLine() {
		return sameLine;
	}
	public boolean isHeading() {
		return heading;
	}
	public boolean isFieldOnly() {
		return fieldOnly;
	}
	public boolean isEncrypted() {
		return encrypted;
	}
	public String getInputType() {
		return inputType;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public Code getDefaultValue() {
		return defaultValue;
	}

	// @ForeignKeyColumn("AD_REFERENCE_ID") @Column("AD_REFERENCE_VALUE_ID")
	// ?AdReferenceValue AdReference;/* NULL */
	// @ForeignKeyColumn("AD_VAL_RULE_ID") @Column("AD_VAL_RULE_ID")
	// ?AdValRule;/* NULL */
	// ?InfoFactoryClass String;
}

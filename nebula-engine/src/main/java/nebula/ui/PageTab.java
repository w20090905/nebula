package nebula.ui;

import java.util.List;

import nebula.lang.Code;
import nebula.lang.Type;

public class PageTab {
	// @Column("AD_TAB_ID") !AdTabId ID;
	String name;
	String description;
	String help;
	int seqNo;
	int tabLevel;
	boolean singleRow = true;
	boolean infoTab = false;
	boolean ttranslationTab = false;
	boolean readonly = false;

	// @ForeignKeyColumn("AD_COLUMN_ID") @Column("AD_COLUMN_ID") ?AdColumn;
	boolean hasTree = false;

	// ?WhereClause String;
	// ?OrderByClause String;
	// ?CommitWarning String;
	// @ForeignKeyColumn("AD_PROCESS_ID") @Column("AD_PROCESS_ID") ?AdProcess;
	// ?Processing YesNo;
	// @ForeignKeyColumn("AD_IMAGE_ID") @Column("AD_IMAGE_ID") ?AdImage;
	// // ?ImportFields String;
	// @ForeignKeyColumn("AD_COLUMN_ID") @Column("AD_COLUMNSORTORDER_ID")
	// ?AdColumnSortOrder AdColumn;
	// @ForeignKeyColumn("AD_COLUMN_ID") @Column("AD_COLUMNSORTYESNO_ID")
	// ?AdColumnSortYesNo AdColumn;
	boolean sortTab = false;
	Type type;
	// @ForeignKeyColumn("ENTITYTYPE") EntityType AdEntityType;/* 'D' */
	// @ForeignKeyColumn("AD_TAB_ID") @Column("INCLUDED_TAB_ID") ?IncludedTab
	// AdTab;
	Code readonlyLogic;
	Code displayLogic;
	boolean insertRecord = true;
	boolean advancedTab = false;
	
	List<PageField> pageFields;
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getHelp() {
		return help;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public int getTabLevel() {
		return tabLevel;
	}
	public boolean isSingleRow() {
		return singleRow;
	}
	public boolean isInfoTab() {
		return infoTab;
	}
	public boolean isTtranslationTab() {
		return ttranslationTab;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public boolean isHasTree() {
		return hasTree;
	}
	public boolean isSortTab() {
		return sortTab;
	}
	public Type getType() {
		return type;
	}
	public Code getReadonlyLogic() {
		return readonlyLogic;
	}
	public Code getDisplayLogic() {
		return displayLogic;
	}
	public boolean isInsertRecord() {
		return insertRecord;
	}
	public boolean isAdvancedTab() {
		return advancedTab;
	}

}

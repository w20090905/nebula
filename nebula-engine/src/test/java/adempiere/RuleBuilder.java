package adempiere;

import java.util.List;

import adempiere.DefaultImporter.Field;

import com.google.common.collect.Lists;

interface Rule {
	public Rule with(String... strings);

	public Rule is(DBColumnType... dbTypes);

	public Rule setTypeName(String type);

	public Rule skip();
	
	public Rule table(String tablenames);

	public Rule useMatchedNameAsTypeName();

	public Rule useMatchedNameAsFieldName();
	
	public Rule setReferTo(String typename);
}

interface Action {
	Field apply(Field input,String match, String... params);
}

class RuleBuilder implements Rule {
	DBColumnType[] dbTypes;
	MatchPattern[] ruleTypes;
	String[] with;
	String tableName = null;
	final List<Action> actions;

	public RuleBuilder() {
		this.actions = Lists.newCopyOnWriteArrayList();
	}

	public RuleBuilder(MatchPattern... ruleTypes) {
		this.actions = Lists.newCopyOnWriteArrayList();
		this.ruleTypes = ruleTypes;
	}

	public Rule table(String tablename) {
		this.tableName = tablename;
		return this;
	}
	
	public Rule with(String... strings) {
		this.with = strings;
		return this;
	}

	@Override
	public Rule is(DBColumnType... dbTypes) {
		this.dbTypes = dbTypes;
		return this;
	}

	@Override
	public Rule setTypeName(String type) {
		this.actions.add(new SetDbType(type));
		return this;
	}

	@Override
	public Rule useMatchedNameAsTypeName() {
		this.actions.add(new UseMatchedNameAsTypeName());
		return this;
	}

	@Override
	public Rule useMatchedNameAsFieldName() {
		this.actions.add(new UseMatchedNameAsFieldName());
		return this;
	}

	@Override
	public Rule skip() {
		this.actions.add(new SkipField());
		return this;
	}
	@Override
	public Rule setReferTo(String typename) {
		this.actions.add(new SetReferTo(typename));
		return null;
	}


	class UseMatchedNameAsTypeName implements Action {
		@Override
		public DefaultImporter.Field apply(Field input, String match, String... params) {
			input.resultTypeName = match;
			input.comment = input.comment + "\tset Type Name with Matched Name\t" + match;
			return input;
		}
	}

	class SetReferTo implements Action {
		final String typename;
		SetReferTo(String typename) {
			this.typename = typename;
		}
		@Override
		public DefaultImporter.Field apply(Field input, String match, String... params) {
			input.resultTypeName = typename; // Type Name
			input.isForeignKey = true;
			input.foreignKeyTable = typename;//Raw Table Name
			input.comment = input.comment + "\tset Type as foreign key \t" + typename;
			return input;
		}
	}

	class UseMatchedNameAsFieldName implements Action {
		@Override
		public DefaultImporter.Field apply(Field input, String match, String... params) {
			input.resultName = match;
			input.comment = input.comment + "\tset Field Name with Matched Name\t" + match;
			return input;
		}
	}

	class SetDbType implements Action {
		final String typename;

		SetDbType(String typename) {
			this.typename = typename;
		}

		@Override
		public DefaultImporter.Field apply(Field input, String match, String... params) {
			input.resultTypeName = typename;
			input.comment = input.comment + "\tset type\t" + typename;
			return input;
		}
	}

	class SkipField implements Action {
		@Override
		public DefaultImporter.Field apply(Field input, String match, String... params) {
			input.skip = true;
			input.comment = input.comment + "\tskip";
			return input;
		}
	}

}

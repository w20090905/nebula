package typeimport;

enum MatchPattern {
	StartWith {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.startsWith(match)) {
					return match;
				}
			}
			return null;
		}
	},
	EndWith {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.endsWith(match)) {
					return match;
				}
			}
			return null;
		}
	},
	Include {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.indexOf(match) >= 0) {
					return match;
				}
			}
			return null;
		}
	},
	Equals {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.equals(match)) {
					return match;
				}
			}
			return null;
		}
	},
	StartWithIgnoreCase {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.toUpperCase().startsWith(match.toUpperCase())) {
					return match;
				}
			}
			return null;
		}
	},
	EndWithIgnoreCase {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.toUpperCase().endsWith(match.toUpperCase())) {
					return match;
				}
			}
			return null;
		}
	},
	IncludeIgnoreCase {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.toUpperCase().indexOf(match.toUpperCase()) >= 0) {
					return match;
				}
			}
			return null;
		}
	},
	EqualsIgnoreCase {
		@Override
		public String match(String value, String... matches) {
			value = value.replaceAll("[0-9]","");
			for (String match : matches) {
				if (value.equalsIgnoreCase(match)) {
					return match;
				}
			}
			return null;
		}
	};

	public abstract String match(String value, String... matches);
}

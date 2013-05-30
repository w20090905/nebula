package adempiere;

enum MatchPattern {
	StartWithIgnoreCase {
		@Override
		public String match(String value, String... matches) {
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
			for (String match : matches) {
				if (value.toUpperCase().endsWith(match.toUpperCase())) {
					return match;
				}
			}
			return null;
		}
	},
	Include {
		@Override
		public String match(String value, String... matches) {
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

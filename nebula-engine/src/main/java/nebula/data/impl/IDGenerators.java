package nebula.data.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import nebula.data.IDGenerator;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

public class IDGenerators {

	public static IDGenerator build(Type type) {
		Field keyField;
		String idGenerationStrategy;
		switch (type.getStandalone()) {
		case Transaction:
			keyField = null;
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					if (f.getType().getStandalone() == TypeStandalone.Basic) {
						keyField = f;
					}
				}
			}
			checkNotNull(keyField);

			idGenerationStrategy = (String) keyField.getAttrs().get("IDGenerationStrategy");

			if ("default".equals(idGenerationStrategy)) {
				return new CurrentTimeIDGenerator();
			} else if ("native".equals(idGenerationStrategy)) {
				return new NativeIDGenerator();
			} else {
				throw new UnsupportedOperationException("not master,");
			}

		case Master:
			keyField = null;
			for (Field f : type.getFields()) {
				if (f.isKey()) {
					if (f.getType().getStandalone() == TypeStandalone.Basic) {
						keyField = f;
					}
				}
			}
			checkNotNull(keyField);

			idGenerationStrategy = (String) keyField.getAttrs().get("IDGenerationStrategy");

			if ("default".equals(idGenerationStrategy)) {
				return new CurrentTimeIDGenerator();
			} else if ("native".equals(idGenerationStrategy)) {
				return new NativeIDGenerator();
			} else {
				throw new UnsupportedOperationException("not master,");
			}

		default:
			throw new UnsupportedOperationException("not master,");
		}
	}
}

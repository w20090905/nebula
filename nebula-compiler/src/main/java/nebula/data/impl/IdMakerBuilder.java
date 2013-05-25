package nebula.data.impl;

import java.util.List;

import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class IdMakerBuilder {
	static Log log = LogFactory.getLog(IdMakerBuilder.class);

	public static Function<Entity, Object> getIDReader(Type type) {
		List<String> keys = Lists.newArrayList();
		switch (type.getStandalone()) {
		case Master:
			for (Field f : type.getFields()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic && f.isKey()) {
					keys.add(f.getName());
				} else if (f.getRefer() == Reference.ByRef && f.isKey()) {
					for (Field inf : f.getType().getFields()) {
						if (inf.getRefer() == Reference.ByVal && inf.isKey()) {
							keys.add(f.getName() + "_" + inf.getName());
						}
					}
				}
			}

			if (log.isTraceEnabled()) {
				log.trace("\tbuilder " + type.getName() + " IDSetter with " + keys.size() + " keys " + keys);
			}
			switch (keys.size()) {
			case 1:
				return new IDReader1(keys.get(0));
			case 2:
				return new IDReader2(keys.get(0), keys.get(1));
			case 3:
				return new IDReader3(keys.get(0), keys.get(1), keys.get(2));
			case 4:
				return new IDReader4(keys.get(0), keys.get(1), keys.get(2), keys.get(3));
			default:
				throw new UnsupportedOperationException("max 4 key");
			}
		case Transaction:
			for (Field f : type.getFields()) {
				if (f.getType().getStandalone() == TypeStandalone.Basic && f.isKey()) {
					keys.add(f.getName());
				} else if (f.getRefer() == Reference.ByRef && f.isKey()) {
					for (Field inf : f.getType().getFields()) {
						if (inf.getRefer() == Reference.ByVal && inf.isKey()) {
							keys.add(f.getName() + "_" + inf.getName());
						}
					}
				}
			}

			if (log.isTraceEnabled()) {
				log.trace("\tbuilder " + type.getName() + " IDSetter with " + keys.size() + " keys " + keys);
			}
			switch (keys.size()) {
			case 1:
				return new IDSetterLong(keys.get(0));
			default:
				throw new UnsupportedOperationException("max 4 key");
			}
		default:
			throw new UnsupportedOperationException("not master,");
		}
	}

	static private class IDSetterLong implements Function<Entity, Object> {
		final String key1;

		IDSetterLong(String key1) {
			this.key1 = key1;
		}

		@Override
		public Long apply(Entity entity) {
			return (Long) entity.get(key1);
		}
	}

	static private class IDReader1 implements Function<Entity, Object> {
		final String key1;

		IDReader1(String key1) {
			this.key1 = key1;
		}

		@Override
		public String apply(Entity entity) {
			return (String) entity.get(key1).toString();
		}
	}

	static private class IDReader2 implements Function<Entity, Object> {
		final String key1;
		final String key2;

		IDReader2(String key1, String key2) {
			this.key1 = key1;
			this.key2 = key2;
		}

		@Override
		public String apply(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2);
		}
	}

	static private class IDReader3 implements Function<Entity, Object> {
		final String key1;
		final String key2;
		final String key3;

		IDReader3(String key1, String key2, String key3) {
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
		}

		@Override
		public String apply(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2) + (String) entity.get(key3);
		}
	}

	static private class IDReader4 implements Function<Entity, Object> {
		final String key1;
		final String key2;
		final String key3;
		final String key4;

		IDReader4(String key1, String key2, String key3, String key4) {
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.key4 = key4;
		}

		@Override
		public String apply(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2) + (String) entity.get(key3)
					+ (String) entity.get(key4);
		}
	}
}

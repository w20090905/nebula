package nebula.data.impl;

import java.util.List;

import nebula.data.Entity;
import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;
import nebula.lang.TypeStandalone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Lists;

public class IDSetterBuilder {
	static Log log = LogFactory.getLog(IDSetterBuilder.class); 

	public static IDSetter getIDSetter(Type type) {
		List<String> keys = Lists.newArrayList();
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
		
		if(log.isDebugEnabled()){
			log.debug("builder " + type.getName() + " IDSetter with " + keys.size() + " keys " + keys);
		}
		switch (keys.size()) {
		case 1:
			return new IDSetter1(keys.get(0));
		case 2:
			return new IDSetter2(keys.get(0), keys.get(1));
		case 3:
			return new IDSetter3(keys.get(0), keys.get(1), keys.get(2));
		case 4:
			return new IDSetter4(keys.get(0), keys.get(1), keys.get(2), keys.get(3));
		default:
			throw new UnsupportedOperationException("max 4 key");
		}
	}

	static private  class IDSetter1 implements IDSetter {
		final String key1;

		IDSetter1(String key1) {
			this.key1 = key1;
		}

		@Override
		public String getID(Entity entity) {
			return (String) entity.get(key1);
		}
	}

	static private  class IDSetter2 implements IDSetter {
		final String key1;
		final String key2;

		IDSetter2(String key1, String key2) {
			this.key1 = key1;
			this.key2 = key2;
		}

		@Override
		public String getID(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2);
		}
	}

	static private  class IDSetter3 implements IDSetter {
		final String key1;
		final String key2;
		final String key3;

		IDSetter3(String key1, String key2, String key3) {
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
		}

		@Override
		public String getID(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2) + (String) entity.get(key3);
		}
	}

	static private class IDSetter4 implements IDSetter {
		final String key1;
		final String key2;
		final String key3;
		final String key4;

		IDSetter4(String key1, String key2, String key3, String key4) {
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.key4 = key4;
		}

		@Override
		public String getID(Entity entity) {
			return (String) entity.get(key1) + (String) entity.get(key2) + (String) entity.get(key3)
					+ (String) entity.get(key4);
		}
	}
}

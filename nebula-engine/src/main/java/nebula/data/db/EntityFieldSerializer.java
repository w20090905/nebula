package nebula.data.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.data.Entity;
import nebula.data.impl.EditableEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EntityFieldSerializer extends DefaultFieldSerializer<Entity> {
	private static final Log log = LogFactory.getLog(EntityFieldSerializer.class);

	final List<DefaultFieldSerializer<?>> fieldSerializer;

	public EntityFieldSerializer(List<DefaultFieldSerializer<?>> fieldSerializer) {
		super(null, null);
		this.fieldSerializer = new CopyOnWriteArrayList<DefaultFieldSerializer<?>>(fieldSerializer);
	}

	public EntityFieldSerializer(String fieldName, String columnName, List<DefaultFieldSerializer<?>> fieldSerializer) {
		super(fieldName, columnName);
		this.fieldSerializer = new CopyOnWriteArrayList<DefaultFieldSerializer<?>>(fieldSerializer);
	}

	int fromEntity(PreparedStatement prepareStatement, Entity entity) throws Exception {
		int pos = 1;
		for (DefaultFieldSerializer<?> c : fieldSerializer) {
			@SuppressWarnings("unchecked")
			DefaultFieldSerializer<Object> m = (DefaultFieldSerializer<Object>) c;
			pos = m.output(prepareStatement, entity.get(c.fieldName), pos);
		}
		return pos;
	}

	EditableEntity toEntity(ResultSet result) throws Exception {
		EditableEntity entity = new EditableEntity();

		int pos = 1;
		for (DefaultFieldSerializer<?> c : fieldSerializer) {
			pos = c.inputWithoutCheck(result, pos, entity);
		}
		return entity;
	}

	@Override
	public int input(ResultSet in, int pos, Entity parent, Entity now) throws Exception {
		return inputWithoutCheck(in, pos, parent);
	}

	@Override
	public int inputWithoutCheck(ResultSet in, int pos, Entity parent) throws Exception {
		EditableEntity entity = new EditableEntity();
		for (DefaultFieldSerializer<?> c : fieldSerializer) {
			pos = c.inputWithoutCheck(in, pos, entity);
		}
		parent.put(this.fieldName, entity);
		return pos;
	}

	@Override
	public int output(PreparedStatement out, Entity value, int pos) throws Exception {
		if (value != null) {
			for (DefaultFieldSerializer<?> c : fieldSerializer) {
				@SuppressWarnings("unchecked")
				DefaultFieldSerializer<Object> m = (DefaultFieldSerializer<Object>) c;
				pos = m.output(out, value.get(c.fieldName), pos);
			}
		} else {
			for (DefaultFieldSerializer<?> c : fieldSerializer) {
				@SuppressWarnings("unchecked")
				DefaultFieldSerializer<Object> m = (DefaultFieldSerializer<Object>) c;
				pos = m.output(out, null, pos);
			}
		}
		return pos;
	}

}

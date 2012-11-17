package http.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import nebula.data.Entity;
import nebula.data.impl.EntityJsonSerialize;
import nebula.data.impl.EntitySerialize;
import nebula.lang.Field;
import nebula.lang.Type;
import nebula.lang.TypeSerialize;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;

public class JsonProvider {
	@SuppressWarnings("unchecked")
	public static <T> JsonDealer<T> getSerialize(Class<T> clz) {
		JsonDealer<T> s;
		if (clz == Type.class) {
			JsonSerialize<T> json = (JsonSerialize<T>) new TypeSerialize();
			s = new DefaultJsonDealer<T>(json);

		} else if (clz == Entity.class) {
			JsonSerialize<T> json = (JsonSerialize<T>) new EntitySerialize();
			s = new DefaultJsonDealer<T>(json);
		} else {
			s = null;
		}

		return s;
	}

	private static void addPageDataDealer(final List<PageField> pageFields,Field field,String name){
		String pageName = name;
		PageField pageField = new PageField(pageName, pageName, field.getType().getRawType());
		pageFields.add(pageField);
	}
	
	public static JsonDealer<Entity> getSerialize(Type type) {
		List<PageField> pageFields = new ArrayList<>();
		
		for (Field f : type.getFields()) {
			Type rT;
			switch (f.getRefer()) {
			case ByVal:
				addPageDataDealer(pageFields,f, f.getName());
				break;
			case Inline:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					addPageDataDealer(pageFields,rf, f.getName() + rf.getName());
				}
				break;
			case ByRef:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					switch (rf.getImportance()) {
					case Key:
					case Core:
						addPageDataDealer(pageFields,rf, f.getName() + rf.getName());
						break;
					}
				}
				break;
			case Cascade:
				rT = f.getType();
				for (Field rf : rT.getFields()) {
					switch (rf.getImportance()) {
					case Key:
					case Core:
						addPageDataDealer(pageFields,rf, f.getName() + rf.getName());
						break;
					}
				}
				break;
			}
		}
		return new EntityJsonSerialize(pageFields);
	}

	public static interface JsonDealer<T> {
		void stringifyTo(T d, Writer o);

		T readFrom(T d, Reader in);
	}

	static class DefaultJsonDealer<T> implements JsonDealer<T> {
		final JsonSerialize<T> json;

		DefaultJsonDealer(JsonSerialize<T> json) {
			this.json = json;
		}

		@Override
		public void stringifyTo(T d, Writer o) {
			try {
				JsonFactory f = new JsonFactory();
				JsonGenerator g = f.createJsonGenerator(o);
				json.write(g, d);
				g.flush();
				// g.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public T readFrom(T d, Reader in) {
			try {
				JsonFactory f = new JsonFactory();
				JsonParser p = f.createJsonParser(in);
				return json.read(p, d);
				// p.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}

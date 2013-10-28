package http.resource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;
import nebula.data.DataStore;
import nebula.data.Entity;
import nebula.data.json.DataHelper;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EntityResouceTest extends TestCase {
	@Mock
	DataHelper<Entity, Reader, Writer> json;

	@Mock
	HttpServletRequest req;
	@Mock
	DataStore<Entity> datastore;
	@Mock
	Entity data;

	String key = "EntityResouceTest";

	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testGet() throws IOException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		EntityResouce entityResouce = new EntityResouce(json, datastore, key);

		when(datastore.get(key)).thenReturn(data);
		long lastModified = 100L;
		when(data.get("LastModified_")).thenReturn(lastModified);

		entityResouce.get(req);

		verify(datastore).get(key);
		verify(data).get("LastModified_");
		verify(json).stringifyTo(eq(data), any(OutputStreamWriter.class));

		// Method method = targetClass.getDeclaredMethod(methodName,
		// argClasses);
		// method.setAccessible(true);
		// return method.invoke(targetObject, argObjects);

		assertEquals(lastModified, getPrivateField(AbstractResouce.class, entityResouce, "lastModified"));
	}

	<T> Object getPrivateField(T o, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = o.getClass().getDeclaredField("lastModified");
		field.setAccessible(true);
		return field.get(o);
	}

	<T> Object getPrivateField(Class<?> clz, T o, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = clz.getDeclaredField("lastModified");
		field.setAccessible(true);
		return field.get(o);
	}

	public final void testPut() {
		// fail TODO ("Not yet implemented");
	}

	public final void testDelete() {
		// fail TODO ("Not yet implemented");
	}

	public final void testEntityResouce() {
		// fail TODO ("Not yet implemented");
	}

	public final void testAbstractResouce() {
		// fail TODO ("Not yet implemented");
	}

	public final void testHeader() {
		// fail TODO ("Not yet implemented");
	}

	public final void testPost() {
		// fail TODO ("Not yet implemented");
	}

	public final void testHandle() {
		// fail TODO ("Not yet implemented");
	}

}

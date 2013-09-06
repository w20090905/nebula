package tech;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import junit.framework.TestCase;

/**
 * 
 * @author Brian Zhao
 */
public class SimpleTest extends TestCase {

	public void testSimple() {
		// arrange
		@SuppressWarnings("unchecked")
		Iterator<String> i = (Iterator<String>) mock(Iterator.class);
		when(i.next()).thenReturn("Hello").thenReturn("World");
		// act
		String result = i.next() + " " + i.next();
		// verify
		verify(i, times(2)).next();
		// assert
		assertEquals("Hello World", result);
	}
}

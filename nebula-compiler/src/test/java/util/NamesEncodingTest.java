package util;

import junit.framework.TestCase;

public class NamesEncodingTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testEncode() {
		assertEquals("HELLOWORLD", NamesEncoding.encode("hello world"));
		assertEquals("D9HELLOWORLD_", NamesEncoding.encode("9hello world"));
		assertEquals("HELLO_WORLD", NamesEncoding.encode("hello-world"));
		assertEquals("HELLO_WORLD", NamesEncoding.encode("hello_world"));
		
		assertEquals("D9HELLOWORLD_", NamesEncoding.encode("_9hello world"));
		assertEquals("D9HELLOW_ORLD_", NamesEncoding.encode("_9hello w_orld"));
		assertEquals("HELLOWORLD_", NamesEncoding.encode("_hello world_"));
		assertEquals("HELLO_WORLD_", NamesEncoding.encode("_hello-world_"));
		
		assertEquals("ZU8B9RCE52W5IIZ_", NamesEncoding.encode("中华人民共和国"));
		assertEquals("Z9O1E1BDH_", NamesEncoding.encode("很讨厌你"));
		assertEquals("ZBPXEBPXE1E1BDH_", NamesEncoding.encode("非常非常讨厌你"));
		assertEquals("ZB4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4_", NamesEncoding.encode("㐺㥊㹚䍪䡺䶊劚垪岺懊曚毪烺瘊笚耪蔺詊轚鑪饺麊"));
	}

}

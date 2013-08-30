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
		assertEquals("HELLO_WORLD", NamesEncoding.encode("hello world"));
		assertEquals("D9HELLO_WORLD_", NamesEncoding.encode("9hello world"));
		assertEquals("HELLO_WORLD", NamesEncoding.encode("hello-world"));
		assertEquals("HELLO_WORLD", NamesEncoding.encode("hello_world"));
		
		assertEquals("D9HELLO_WORLD_", NamesEncoding.encode("_9hello world"));
		assertEquals("D9HELLO_W_ORLD_", NamesEncoding.encode("_9hello w_orld"));
		assertEquals("HELLO_WORLD_", NamesEncoding.encode("_hello world_"));
		assertEquals("HELLO_WORLD_", NamesEncoding.encode("_hello-world_"));
		assertEquals("HELLO_WORLD_", NamesEncoding.encode("_hello/world_"));
		
		assertEquals("ZU8B9RCE52W5IIZ_", NamesEncoding.encode("中华人民共和国"));
		assertEquals("Z9O1E1BDH_", NamesEncoding.encode("很讨厌你"));
		assertEquals("ZBPXEBPXE1E1BDH_", NamesEncoding.encode("非常非常讨厌你"));
		assertEquals("ZB4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4_", NamesEncoding.encode("㐺㥊㹚䍪䡺䶊劚垪岺懊曚毪烺瘊笚耪蔺詊轚鑪饺麊"));
	}

	public final void testEncodeRawCase() {
		assertEquals("hello_world", NamesEncoding.encode("hello world",false));
		assertEquals("D9hello_world_", NamesEncoding.encode("9hello world",false));
		assertEquals("hello_world", NamesEncoding.encode("hello-world",false));
		assertEquals("hello_world", NamesEncoding.encode("hello_world",false));
		
		assertEquals("D9hello_world_", NamesEncoding.encode("_9hello world",false));
		assertEquals("D9hello_w_orld_", NamesEncoding.encode("_9hello w_orld",false));
		assertEquals("hello_world_", NamesEncoding.encode("_hello world_",false));
		assertEquals("hello_world_", NamesEncoding.encode("_hello-world_",false));
		assertEquals("hello_world_", NamesEncoding.encode("_hello/world_",false));
		
		assertEquals("ZU8B9RCE52W5IIZ_", NamesEncoding.encode("中华人民共和国",false));
		assertEquals("Z9O1E1BDH_", NamesEncoding.encode("很讨厌你",false));
		assertEquals("ZBPXEBPXE1E1BDH_", NamesEncoding.encode("非常非常讨厌你",false));
//		assertEquals("ZB4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4B4_", NamesEncoding.encode("㐺㥊㹚䍪䡺䶊劚垪岺懊曚毪烺瘊笚耪蔺詊轚鑪饺麊"),false);
	}
}

package nebula.compiler;

import java.io.StringReader;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;

public class InputCharStreamTest extends TestCase {
	
	CharStream input;
	String text  =null;
	
	@Override
	protected void setUp() throws Exception {
		//input = new ANTLRStringStream("1234\n56789\n987654321\n");
		input = new InputCharStream(new StringReader("1234\n56789\n987654321\n"));
	}

//	public void testInputCharStream() {
//		fail("Not yet implemented");
//	}


	public void testConsume() {
		input.consume(); //1
		assertEquals(1, input.index());
		assertEquals(1, input.getLine());
		assertEquals(1, input.getCharPositionInLine());

		text = input.substring(input.index()-1,input.index()-1);
		assertEquals("1", text);
		
		input.consume(); //2
		assertEquals(2, input.index());
		assertEquals(1, input.getLine());
		assertEquals(2, input.getCharPositionInLine());

		input.consume();
		assertEquals(3, input.index());
		assertEquals(1, input.getLine());
		assertEquals(3, input.getCharPositionInLine());
		

		input.consume();
		assertEquals(4, input.index());
		assertEquals(1, input.getLine());
		assertEquals(4, input.getCharPositionInLine());
		

		text = input.substring(input.index()-1,input.index()-1);
		assertEquals("4", text);
		
		input.consume();
		assertEquals(5, input.index());
		assertEquals(2, input.getLine());
		assertEquals(0, input.getCharPositionInLine());
		

		
		input.consume();
		assertEquals(6, input.index());
		assertEquals(2, input.getLine());
		assertEquals(1, input.getCharPositionInLine());
		

		input.consume();
		assertEquals(7, input.index());
		assertEquals(2, input.getLine());
		assertEquals(2, input.getCharPositionInLine());
		

		input.consume();
		assertEquals(8, input.index());
		assertEquals(2, input.getLine());
		assertEquals(3, input.getCharPositionInLine());
		
		System.out.println(input);
		
		text = input.substring(input.index()-2,input.index()-1);
		assertEquals("67", text);

		input.consume();
		assertEquals(9, input.index());
		assertEquals(2, input.getLine());
		assertEquals(4, input.getCharPositionInLine());
		
		
		
		input.consume();
		assertEquals(10, input.index());
		assertEquals(2, input.getLine());
		assertEquals(5, input.getCharPositionInLine());
		

		input.consume();
		input.consume();
		input.consume();
		assertEquals(13, input.index());
		assertEquals(3, input.getLine());
		assertEquals(2, input.getCharPositionInLine());

		text = input.substring(input.index()-2,input.index()-1);
		assertEquals("98", text);
	}

	public void testLA() {
		input.consume(); //1
		assertEquals(1, input.index());
		assertEquals(1, input.getLine());
		assertEquals(1, input.getCharPositionInLine());
		
		int c = input.LA(1); //2
		assertEquals('2', c);
		assertEquals(1, input.index());
		assertEquals(1, input.getLine());
		assertEquals(1, input.getCharPositionInLine());

		text = input.substring(input.index()-1,input.index());
		assertEquals("12", text);
		
		
		input.consume();
		assertEquals(2, input.index());
		assertEquals(1, input.getLine());
		assertEquals(2, input.getCharPositionInLine());

		text = input.substring(input.index()-2,input.index()-1);
		assertEquals("12", text);
		
		c = input.LA(1); //2
		assertEquals('3', c);
		assertEquals(2, input.index());
		assertEquals(1, input.getLine());
		assertEquals(2, input.getCharPositionInLine());

		text = input.substring(input.index()-1,input.index());
		assertEquals("23", text);
		
		c = input.LA(2); //2
		assertEquals('4', c);
		assertEquals(2, input.index());
		assertEquals(1, input.getLine());
		assertEquals(2, input.getCharPositionInLine());
		

		text = input.substring(input.index(),input.index()+1);
		assertEquals("34", text);
		
		

		input.consume();
		assertEquals(3, input.index());
		assertEquals(1, input.getLine());
		assertEquals(3, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(4, input.index());
		assertEquals(1, input.getLine());
		assertEquals(4, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(5, input.index());
		assertEquals(2, input.getLine());
		assertEquals(0, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(6, input.index());
		assertEquals(2, input.getLine());
		assertEquals(1, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(7, input.index());
		assertEquals(2, input.getLine());
		assertEquals(2, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(8, input.index());
		assertEquals(2, input.getLine());
		assertEquals(3, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(9, input.index());
		assertEquals(2, input.getLine());
		assertEquals(4, input.getCharPositionInLine());
		
		input.consume();
		assertEquals(10, input.index());
		assertEquals(2, input.getLine());
		assertEquals(5, input.getCharPositionInLine());
		
		
		input.consume();
		assertEquals(11, input.index());
		assertEquals(3, input.getLine());
		assertEquals(0, input.getCharPositionInLine());
		
		
		input.consume();
		assertEquals(12, input.index());
		assertEquals(3, input.getLine());
		assertEquals(1, input.getCharPositionInLine());
		
		
		input.consume();
		
		assertEquals(13, input.index());
		assertEquals(3, input.getLine());
		assertEquals(2, input.getCharPositionInLine());
		

		c = input.LA(1); //2
		assertEquals('7', c);
		assertEquals(13, input.index());
		assertEquals(3, input.getLine());
		assertEquals(2, input.getCharPositionInLine());

		text = input.substring(input.index()-1,input.index());
		assertEquals("87", text);
		
		c = input.LA(2); //2
		assertEquals('6', c);
		assertEquals(13, input.index());
		assertEquals(3, input.getLine());
		assertEquals(2, input.getCharPositionInLine());
		

		text = input.substring(input.index()-1,input.index());
		assertEquals("87", text);
		

		input.consume();
		c = input.LA(2); //2
		assertEquals('5', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals('4', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals('3', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals('2', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals('1', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals('\n', c);
		input.consume();
		c = input.LA(2); //2
		assertEquals(-1, c);
		input.consume();
		c = input.LA(1); //2
		assertEquals(-1, c);
	}
//
//	public void testLT() {
//		fail("Not yet implemented");
//	}
//
//	public void testIndex() {
//		fail("Not yet implemented");
//	}
//
//	public void testSize() {
//		fail("Not yet implemented");
//	}
//
//	public void testMark() {
//		fail("Not yet implemented");
//	}
//
//	public void testRewindInt() {
//		fail("Not yet implemented");
//	}
//
//	public void testRewind() {
//		fail("Not yet implemented");
//	}
//
//	public void testRelease() {
//		fail("Not yet implemented");
//	}
//
//	public void testSeek() {
//		fail("Not yet implemented");
//	}
//
//	public void testSubstring() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetLine() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetCharPositionInLine() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetLine() {
//		fail("Not yet implemented");
//	}
//
//	public void testSetCharPositionInLine() {
//		fail("Not yet implemented");
//	}
//
//	public void testGetSourceName() {
//		fail("Not yet implemented");
//	}
//
//	public void testToString() {
//		fail("Not yet implemented");
//	}

}

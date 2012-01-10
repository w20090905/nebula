package nebula.compiler;

import java.io.IOException;
import java.io.Reader;

import org.antlr.runtime.CharStream;

public class InputCharStream implements CharStream {

	Reader reader;

	public InputCharStream(Reader reader) {
		this.reader = reader;
		data = new char[8*1024];
	}

	/** The data being scanned */
	protected char[] data;

	protected int n = Integer.MAX_VALUE;
	/** line number 1..n within the input */
	protected int line = 1;
	protected int posOfLineStart = 0;
	

	/** The index of the character relative to the beginning of the line 0..n-1 */
	protected int charPositionInLine = 0;
	protected int prevPosition = -2;

	/**
	 * Reset the stream so that it's in the same state it was when the object
	 * was created *except* the data array is not touched.
	 */
	public void reset() {
		// p = 0;
		line = 1;
		posOfLineStart = 0;
		charPositionInLine = -1;
		prevPosition = -2;
	}

	public void consume() {
		if (posOfLineStart + charPositionInLine >= posOfLineStart +prevPosition) {
			int c = this.read(); // TODO
			data[posOfLineStart +charPositionInLine] = (char) c;
		}

		if (data[ posOfLineStart +charPositionInLine] > 0) {
			if (data[ posOfLineStart +charPositionInLine] == '\n') {
				/*
				 * System.out.println("newline char found on line: "+line+
				 * "@ pos="+charPositionInLine);
				 */

				line++;
				charPositionInLine++;
				posOfLineStart += charPositionInLine;

				charPositionInLine = -1;
				prevPosition = -1;
			}
			// System.out.println("p moves to "+p+" (c='"+(char)data[p]+"')");
		}
		charPositionInLine++;	
	}

	public int LA(int i) {
		if (i == 0) {
			throw new UnsupportedOperationException();
		}
		if (i < 0) {
			throw new UnsupportedOperationException();
		}

		prevPosition = prevPosition > charPositionInLine ? prevPosition : charPositionInLine;

		for (; prevPosition < charPositionInLine + i;) {
			int c = this.read(); // TODO
			//prevPosition;

			data[posOfLineStart +prevPosition] = (char) c;
			prevPosition++;
			if (c > 0) {
			} else {
				n = posOfLineStart + prevPosition-1;
			}
		}
		
		if(posOfLineStart + charPositionInLine + i -1 >=n ){
			return CharStream.EOF;
		}
		// System.out.println("char LA("+i+")="+(char)data[p+i-1]+"; p="+p);
		// System.out.println("LA("+i+"); p="+p+" n="+n+" data.length="+data.length);
		return data[ posOfLineStart +charPositionInLine + i -1];
	}

	int countRead=0;
	private int read() {
		try {
			int c = reader.read();
			//System.out.println("++" + (countRead++) + " [ " + String.valueOf(c) + " ]");
			return c;
		} catch (IOException e) {
			throw new UnsupportedOperationException();
		}
	}

	public int LT(int i) {
		return LA(i);
	}

	/**
	 * Return the current input symbol index 0..n where n indicates the last
	 * symbol has been read. The index is the index of char to be returned from
	 * LA(1).
	 */
	public int index() {
		return posOfLineStart + charPositionInLine;
	}

	public int size() {
		return n;
	}

	public int mark() {
		throw new UnsupportedOperationException();
	}

	public void rewind(int m) {
		throw new UnsupportedOperationException();
	}

	public void rewind() {
		throw new UnsupportedOperationException();
	}

	public void release(int marker) {
		throw new UnsupportedOperationException();
	}

	/**
	 * consume() ahead until p==index; can't just set p=index as we must update
	 * line and charPositionInLine.
	 */
	public void seek(int index) {
		if (index <= posOfLineStart + charPositionInLine) {
			throw new UnsupportedOperationException();
		}
		// seek forward, consume until p hits index
		while (posOfLineStart + charPositionInLine < index) {
			consume();
		}
		throw new UnsupportedOperationException();
	}

	public String substring(int start, int stop) {
//		if (start < posOfLineStart || stop < posOfLineStart) {
//			System.out.println("start: " + start + " - stop: " + stop + " posOfLineStart: " + posOfLineStart );
//			throw new UnsupportedOperationException();
//		}
		return new String(data, start, stop - start + 1);
	}

	public int getLine() {
		return line;
	}

	public int getCharPositionInLine() {
		return charPositionInLine;
	}

	public void setLine(int line) {
		throw new UnsupportedOperationException();
	}

	public void setCharPositionInLine(int pos) {
		throw new UnsupportedOperationException();
	}

	public String getSourceName() {
		return "Input Stream";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputCharStream [line=").append(line).append(", posOfLineStart=").append(posOfLineStart)
				.append(", charPositionInLine=").append(charPositionInLine).append(", prevPosition=")
				.append(prevPosition).append(", countRead=").append(countRead).append("]");
		return builder.toString();
	}


}
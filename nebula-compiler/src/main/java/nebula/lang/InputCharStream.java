package nebula.lang;

import java.io.IOException;
import java.io.Reader;

import org.antlr.runtime.CharStream;


/*
 * 改进Input Char Stream,默认使用8K缓冲区。也就是说最大的语法单元为8K；
 * pos使用了int型，导致最大可处理2G数据。
 */
public class InputCharStream implements CharStream {

	final protected int BUFFER_SIZE;
	final protected int BUFFER_MASK;
	Reader reader = null;

	public InputCharStream(Reader reader) {
		this(reader, 0x8000);
	}

	public InputCharStream(Reader reader, int bufferSize) {
		this.reader = reader;
		this.BUFFER_SIZE = bufferSize;
		this.BUFFER_MASK = this.BUFFER_SIZE - 1;
		data = new char[BUFFER_SIZE];
	}

	/** The data being scanned */
	protected char[] data;

	protected int n = Integer.MAX_VALUE;
	/** line number 1..n within the input */

	protected int line = 1;
	protected int p = 0;
	protected int pLA = 0;

	/** The index of the character relative to the beginning of the line 0..n-1 */
	protected int charPositionInLine = 0;

	public void reset() {
		p = 0;
		pLA = 0;

		line = 1;
		charPositionInLine = 0;
	}

	public void consume() {
		int c;
		if (p >= pLA) {
			c = this.read();
			data[p++ & BUFFER_MASK] = (char) c;
		} else {
			c = data[p++ & BUFFER_MASK];
		}

		charPositionInLine++;

		if (c > 0) {
			if (c == '\n') {
				/*
				 * System.out.println("newline char found on line: "+line+
				 * "@ pos="+charPositionInLine);
				 */
				line++;
				charPositionInLine = 0;
			}
			// System.out.println("p moves to "+p+" (c='"+(char)data[p]+"')");
		}
	}

	public int LA(int i) {
		if (i == 0) {
			throw new UnsupportedOperationException();
		}
		if (i < 0) {
			throw new UnsupportedOperationException();
		}

		pLA = pLA > p ? pLA : p;

		for (; pLA < p + i;) {
			int c = this.read(); // TODO

			data[pLA++ & BUFFER_MASK] = (char) c;

			if (c > 0) {
			} else {
				n = pLA - 1;
			}
		}

		if (p + i - 1 >= n) {
			return CharStream.EOF;
		}
		// System.out.println("char LA("+i+")="+(char)data[p+i-1]+"; p="+p);
		// System.out.println("LA("+i+"); p="+p+" n="+n+" data.length="+data.length);
		return data[(p + i - 1) & BUFFER_MASK];
	}

	private int read() {
		try {
			int c = reader.read();
			return c;
		} catch (IOException e) {
			throw new UnsupportedOperationException();
		}
	}

	public int LT(int i) {
		return LA(i);
	}

	public int index() {
		return p;
	}

	@Deprecated
	public int size() {
		return n;
	}

	@Deprecated
	public int mark() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void rewind(int m) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void rewind() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	public void release(int marker) {
		throw new UnsupportedOperationException();
	}

	/**
	 * consume() ahead until p==index; can't just set p=index as we must update
	 * line and charPositionInLine.
	 */
	public void seek(int index) {
		if (index <= p) {
			throw new UnsupportedOperationException();
		}
		// seek forward, consume until p hits index
		while (p < index) {
			consume();
		}
		throw new UnsupportedOperationException();
	}

	public String substring(int start, int stop) {
		assert start + BUFFER_SIZE < p;
		assert stop > pLA && stop > p;

		start = start & BUFFER_MASK;
		stop = stop & BUFFER_MASK;

		if (stop >= start) {
			return new String(data, start, stop - start + 1);
		} else {
			return new String(data, start, BUFFER_MASK - start + 1) + new String(data, 0, stop + 1);
		}
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
		builder.append("InputCharStream [n=").append(n).append(", line=").append(line).append(", p=").append(p)
				.append(", pLA=").append(pLA).append(", charPositionInLine=").append(charPositionInLine).append("]");
		return builder.toString();
	}

}
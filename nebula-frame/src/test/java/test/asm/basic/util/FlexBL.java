package test.asm.basic.util;

import java.util.Enumeration;
import java.util.Iterator;

public class FlexBL implements Enumeration<FlexBL.Entry> ,Iterable<FlexBL.Entry> {
	private int currentPosition;
	private int newPosition;
	private int maxPosition;
	private String str;
	private char delimToken;
	private char delimValue;

	private char maxDelimChar;

	private String spaceDelum = " \t\n\r\f";

	public static void main(String[] args) {
		FlexBL fb = new FlexBL(
				"core  \n; VL:Key,Core,Important,Normal;VL-zh:Key->主键,Core->核心,Important->重点,Normal->一般,attach->附属");
		while (fb.next()) {
			System.out.println(fb.getKey() + ":" + fb.getValue());
		}

	}

	public FlexBL(String str, char delimValue, char delimToken) {
		currentPosition = 0;
		newPosition = -1;
		this.str = str;
		maxPosition = str.length();
		this.delimToken = delimToken;
		this.delimValue = delimValue;
		char m = 0;
		for (char c : spaceDelum.toCharArray()) {
			m = m > c ? m : c;
		}
		this.maxDelimChar = m;
	}

	public FlexBL(String str) {
		this(str, ':', ';');
	}

	public boolean next() {
		newPosition = currentPosition;
		while (newPosition < maxPosition) {
			char c = str.charAt(newPosition);
			if ((c > maxDelimChar) && delimToken != c)
				break;
			newPosition++;
		}
		return (newPosition < maxPosition);
	}

	public String getKey() {
		currentPosition = newPosition;

		while (newPosition < maxPosition) {
			char c = str.charAt(newPosition);
			if (delimValue == c || delimToken == c || (c <= maxDelimChar))
				break;
			newPosition++;
		}

		if (str.charAt(newPosition) == delimValue) {
			String v = str.substring(currentPosition, newPosition);
			currentPosition = newPosition + 1;
			return v;
		} else {
			return null;
		}
	}

	public String getValue() {
		newPosition = currentPosition;

		while (newPosition < maxPosition) {
			char c = str.charAt(newPosition);
			if ((delimToken == c || c <= maxDelimChar))
				break;
			newPosition++;
		}

		String v = str.substring(currentPosition, newPosition);
		currentPosition = newPosition + 1;
		return v;
	}

	@Override
	public boolean hasMoreElements() {
		return next();
	}

	@Override
	public Entry nextElement() {
		return entry;
	}

	Entry entry = new Entry();

	public class Entry {
		public String getKey() {
			return FlexBL.this.getKey();
		}

		public String getValue() {
			return FlexBL.this.getValue();
		}
	}

	@Override
	public Iterator<Entry> iterator() {
		return new Iterator<FlexBL.Entry>() {
			@Override
			public boolean hasNext() {
				return FlexBL.this.next();
			}

			Entry entry = new Entry();
			
			@Override
			public Entry next() {
				return entry;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove");
			}
		};
	}
}

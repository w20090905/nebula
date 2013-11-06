package nebula.simpletemplate;

public class Misc {

	/** Replace >\> with >> in s. Replace \>> unless prefix of \>>> with >>.
	 *  Do NOT replace if it's <\\>
	 */
	public static String replaceEscapedRightAngle(String s) {
		StringBuilder buf = new StringBuilder();
		int i = 0;
		while ( i<s.length() ) {
			char c = s.charAt(i);
			if ( c=='<' && s.substring(i).startsWith("<\\\\>") ) {
				buf.append("<\\\\>");
				i += "<\\\\>".length();
				continue;
			}
			if ( c=='>' && s.substring(i).startsWith(">\\>") ) {
				buf.append(">>");
				i += ">\\>".length();
				continue;
			}
			if ( c=='\\' && s.substring(i).startsWith("\\>>") &&
				!s.substring(i).startsWith("\\>>>") )
			{
				buf.append(">>");
				i += "\\>>".length();
				continue;
			}
			buf.append(c);
			i++;
		}
		return buf.toString();
	}

    public static String strip(String s, int n) {
        return s.substring(n, s.length()-n);
    }

	public static String replaceEscapes(Object strip) {
		// TODO Auto-generated method stub
		return null;
	}

}

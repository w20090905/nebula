package nebula.lang;

public class Modifier {

	public static boolean isKey(int mod) {
		return (mod & Key) != 0;
	}

	public static boolean isUnique(int mod) {
		return (mod & Unique) != 0;
	}

	public static boolean isCore(int mod) {
		return (mod & Core) != 0;
	}

	public static boolean isNullable(int mod) {
		return (mod & Nullable) != 0;
	}

	public static boolean isDerived(int mod) {
		return (mod & Derived) != 0;
	}

	public static boolean isDefaultValue(int mod) {
		return (mod & DefaultValue) != 0;
	}

	public static boolean isRequired(int mod) {
		return (mod & Required) != 0;
	}

	public static boolean isTransparent(int mod) {
		return (mod & Transparent) != 0;
	}

	public static boolean isNative(int mod) {
		return (mod & NATIVE) != 0;
	}

	public static boolean isAbstract(int mod) {
		return (mod & ABSTRACT) != 0;
	}
	
	public static boolean isCascade(int mod) {
		return (mod & CASCADE) != 0;
	}

	public static String toString(int mod) {
		StringBuffer sb = new StringBuffer();
		int len;

		if ((mod & Key) != 0) sb.append("public ");
		if ((mod & Core) != 0) sb.append("protected ");
		if ((mod & Unique) != 0) sb.append("private ");

		/* Canonical order */
		if ((mod & ABSTRACT) != 0) sb.append("abstract ");
		if ((mod & Nullable) != 0) sb.append("static ");
		if ((mod & Derived) != 0) sb.append("final ");
		if ((mod & Transparent) != 0) sb.append("transient ");
		if ((mod & Required) != 0) sb.append("volatile ");
		if ((mod & DefaultValue) != 0) sb.append("synchronized ");
		if ((mod & NATIVE) != 0) sb.append("native ");
		if ((mod & STRICT) != 0) sb.append("strictfp ");
		if ((mod & INTERFACE) != 0) sb.append("interface ");

		if ((len = sb.length()) > 0) /* trim trailing space */
		return sb.toString().substring(0, len - 1);
		return "";
	}

	/**
	 * The <code>int</code> value representing the <code>private</code>
	 * modifier.
	 */
	public static final int Unique = 0x00000001;

	/**
	 * The <code>int</code> value representing the <code>public</code> modifier.
	 */
	public static final int Key = 0x00000002;

	/**
	 * The <code>int</code> value representing the <code>protected</code>
	 * modifier.
	 */
	public static final int Core = 0x00000004;

	/**
	 * The <code>int</code> value representing the <code>static</code> modifier.
	 */
	public static final int Nullable = 0x00000008;

	/**
	 * The <code>int</code> value representing the <code>final</code> modifier.
	 */
	public static final int Derived = 0x00000010;

	/**
	 * The <code>int</code> value representing the <code>synchronized</code>
	 * modifier.
	 */
	public static final int DefaultValue = 0x00000020;

	/**
	 * The <code>int</code> value representing the <code>volatile</code>
	 * modifier.
	 */
	public static final int Required = 0x00000040;

	/**
	 * The <code>int</code> value representing the <code>transient</code>
	 * modifier.
	 */
	public static final int Transparent = 0x00000080;

	/**
	 * The <code>int</code> value representing the <code>native</code> modifier.
	 */
	public static final int NATIVE = 0x00000100;

	/**
	 * The <code>int</code> value representing the <code>interface</code>
	 * modifier.
	 */
	public static final int INTERFACE = 0x00000200;

	/**
	 * The <code>int</code> value representing the <code>abstract</code>
	 * modifier.
	 */
	public static final int ABSTRACT = 0x00000400;

	/**
	 * The <code>int</code> value representing the <code>strictfp</code>
	 * modifier.
	 */
	public static final int STRICT = 0x00000800;

	static final int CASCADE = 0x00000040;
	static final int VARARGS = 0x00000080;
	static final int SYNTHETIC = 0x00001000;
	static final int ANNOTATION = 0x00002000;
	static final int ENUM = 0x00004000;

	static boolean isSynthetic(int mod) {
		return (mod & SYNTHETIC) != 0;
	}
}

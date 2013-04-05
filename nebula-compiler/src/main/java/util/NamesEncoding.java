package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NamesEncoding {
	private static final Log log = LogFactory.getLog(NamesEncoding.class);
	static char[] e = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static final int SPAC = e.length;

	public static String encode(String value) {
		char[] vs = value.toCharArray();
		char[] nvs = new char[vs.length * 3 + 1];
		int posV = 0;
		int posNv = 0;
		boolean special = false;
		for (; posV < vs.length; ) {
			if (vs[posV] < 0xFF) {
				char v = vs[posV++];
				if ('0' <= v && v <= '9') {
					nvs[posNv++] = 'D';
					nvs[posNv++] = v;
					special = true;
					break;
				} else if ('A' <= v && v <= 'Z') {
					nvs[posNv++] = v;
					break;
				} else if ('a' <= v && v <= 'z') {
					nvs[posNv++] = (char) (v - 0x20);
					break;
				} else {
				}
			} else {
				nvs[posNv++] = 'Z';
				special = true;
				break;
			}
		}

		for (; posV < vs.length; posV++) {
			char v = vs[posV];

			if (v < 0xFF) {
				if (('0' <= v && v <= '9') || ('A' <= v && v <= 'Z')) {
					nvs[posNv++] = v;
				} else if ('a' <= v && v <= 'z') {
					nvs[posNv++] = (char) (v - 0x20);
				} else if ('_' == v) {
					nvs[posNv++] = v;
				} else if ('-' == v) {
					nvs[posNv++] = '_';
				} else {
				}
			} else {
				int nv = v - 0xFF;
				nvs[posNv++] = e[nv % SPAC];
				nv /= SPAC;
				nvs[posNv++] = e[nv % SPAC];
			}
		}
		if (special) {
			nvs[posNv++] = '_';
		}

//		if (log.isTraceEnabled()) {
//			log.trace("[" + value + "] > [" + new String(nvs, 0, posNv) + "]");
//		}
		return new String(nvs, 0, posNv);
	}
}

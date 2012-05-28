package test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {
	
	public static void main(String[] args) {

		String s1 ="WEAPON[TYPE=1,SUBTYPE=1].OUTPUT=0.01;";
		String s2 ="WEAPON[TYPE:1].OUTPUT=0.01;";
		
		Pattern p1 = Pattern.compile("([a-zA-Z]*)([\\[]([a-zA-Z]*)[\\=]([0-9]*)([,][a-zA-Z]*)[\\=]([0-9]*)*[\\]])?[\\.]([a-zA-Z]*)[\\=]([0-9\\.]*)[;]");
		Pattern p2 = Pattern.compile("");
		
		Matcher m;
		
		m = p1.matcher(s1);
//		System.out.println(m.matches());
		while (m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
			System.out.println(m.group(5));
			System.out.println(m.group(6));
		}
		
	}

}

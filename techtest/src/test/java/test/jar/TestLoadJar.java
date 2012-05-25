package test.jar;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ServiceLoader;

import freemarker.template.Template;

public class TestLoadJar{
	public static void main(String[] args) {
		checkClassPath(TestLoadJar.class);
		checkClassPath(Template.class);
	}

	private static void checkClassPath(Class<?> clz) {
		ProtectionDomain pd = clz.getProtectionDomain();
		CodeSource cs =  pd.getCodeSource();
		URL url = cs.getLocation();
		String path = url.getPath();
		
//		String path = clz.getProtectionDomain().getCodeSource().getLocation().getPath();
		File file = new File(path);
		if(file.isFile()){
			System.out.println(path + " is in jar file");
		}else{
			System.out.println(path + " is in folder");			
		}
	}
}
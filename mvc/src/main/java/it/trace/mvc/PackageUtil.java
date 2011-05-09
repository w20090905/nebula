package it.trace.mvc;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class PackageUtil {

    public static List<String> findClassesInPackage(String packageName) throws IOException {
        return findClassesInPackage(packageName, null);
    }

    public static List<String> findClassesInPackage(String packageName, Pattern pattern) throws IOException {
        String packageOnly = packageName;
        boolean recursive = false;
        if (packageName.endsWith(".*")) {
            packageOnly = packageName.substring(0, packageName.lastIndexOf(".*"));
            recursive = true;
        }

        List<String> vResult = new ArrayList<String>();
        String packageDirName = packageOnly.replace('.', '/');

        Enumeration<URL> dirs =
            Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();

            if ("file".equals(protocol)) {
                // FIXME: need to handle filesystem files
            } else if ("jar".equals(protocol)) {
                JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();

                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();

                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');

                        if (name.indexOf("$") != -1) {
                            // don't include inner classes
                            continue;
                        }

                        if (idx != -1) {
                            packageName = name.substring(0, idx).replace('/', '.');
                        }

                        if ((idx == packageDirName.length()) || recursive) {
                            //it's not inside a deeper dir
                            if (name.endsWith(".class") && !entry.isDirectory()) {
                                String className = name.substring(packageName.length()+1, name.length() - 6);

                                if (pattern != null && !pattern.matcher(className).matches()) {
                                    // doesn't match pattern, skip
                                    continue;
                                }

                                vResult.add(packageName + "." + className);
                            }
                        }
                    }
                }
            }
        }

        return vResult;
    }

}

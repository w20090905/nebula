package nebula.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

public class SystemTypeLoaderTest extends TestCase {

	SystemTypeLoader loader;
	File root1;
	File root2;

	protected void setUp() throws Exception {
		loader = new SystemTypeLoader();
		


		File tmp = new File("tmp");
		root1 = new File(tmp,"test-nebula");
		root2 = new File(tmp,"test-nebula2");
		if(tmp.exists()){
			if(root1.exists()){
				for(File f : root1.listFiles()){
					f.delete();
				}
				root1.delete();
			}

			if(root2.exists()){
				for(File f : root2.listFiles()){
					f.delete();
				}
				root2.delete();
			}
			
			for(File f : tmp.listFiles()){
				f.delete();
			}
			tmp.delete();
		}
		
		tmp.mkdir();
		root1.mkdir();
		root2.mkdir();

		String sample1 = "type Sample{Name;};";
		File file1 = new File(root1,"Sample.nebula");
		
		Writer w1 = new FileWriter(file1);
		w1.write(sample1);
		w1.close();

		String sample2= "type Sample{Name;Age;};";
		File file2 = new File(root2,"Sample.nebula");
		Writer w2 = new FileWriter(file2);
		w2.write(sample2);
		w2.close();

	}

	protected void tearDown() throws Exception {

		File tmp = new File("tmp");
		root1 = new File(tmp,"test-nebula");
		root2 = new File(tmp,"test-nebula2");
		
		if(tmp.exists()){

			if(root1.exists()){
				for(File f : root1.listFiles()){
					f.delete();
				}
				root1.delete();
			}

			if(root2.exists()){
				for(File f : root2.listFiles()){
					f.delete();
				}
				root2.delete();
			}
			
			for(File f : tmp.listFiles()){
				f.delete();
			}
			tmp.delete();
		}
	}

	public void testInsertResourcePathResourcePath() {

		loader.appendResourcePath(root1.getName());

		final String name = root2.getAbsolutePath();
		loader.insertResourcePath(new ResourcePath() {
			String directory = name;

			@Override
			public InputStream openResouce(String name, String ext) {
				try {
					char sep = File.separatorChar;
					String filename = directory + sep + name.replace('.', sep) + ext;
					return new FileInputStream(filename.toString());
				} catch (FileNotFoundException e) {
				} catch (SecurityException e) {
				}
				return null;
			}

			public URL find(String name, String ext) {
				char sep = File.separatorChar;
				String filename = directory + sep + name.replace('.', sep) + ext;
				File f = new File(filename);
				if (f.exists())
					try {
						return f.getCanonicalFile().toURI().toURL();
					} catch (MalformedURLException e) {
					} catch (IOException e) {
					}

				return null;
			}

			public void close() {
			}

			public String toString() {
				return directory;
			}
		});

		Type type = loader.findType("Sample");
		assertEquals("Sample", type.getName());
		assertEquals(2, type.getFields().size());
	}

	public void testAppendResourcePathResourcePath() {

		loader.appendResourcePath(root1.getAbsolutePath());

		final String name = root2.getAbsolutePath();
		loader.appendResourcePath(new ResourcePath() {
			String directory = name;

			@Override
			public InputStream openResouce(String name, String ext) {
				try {
					char sep = File.separatorChar;
					String filename = directory + sep + name.replace('.', sep) + ext;
					return new FileInputStream(filename.toString());
				} catch (FileNotFoundException e) {
				} catch (SecurityException e) {
				}
				return null;
			}

			public URL find(String name, String ext) {
				char sep = File.separatorChar;
				String filename = directory + sep + name.replace('.', sep) + ext;
				File f = new File(filename);
				if (f.exists())
					try {
						return f.getCanonicalFile().toURI().toURL();
					} catch (MalformedURLException e) {
					} catch (IOException e) {
					}

				return null;
			}

			public void close() {
			}

			public String toString() {
				return directory;
			}
		});

		Type type = loader.findType("Sample");
		assertEquals("Sample", type.getName());
		assertEquals(1, type.getFields().size());
	}

	public void testInsertResourcePathString() {
		loader.appendResourcePath(root1.getAbsolutePath());
		loader.insertResourcePath(root2.getAbsolutePath());

		Type type = loader.findType("Sample");
		assertEquals("Sample", type.getName());
		assertEquals(2, type.getFields().size());
	}

	public void testAppendResourcePathString() {
		loader.appendResourcePath(root1.getAbsolutePath());
		loader.appendResourcePath(root2.getAbsolutePath());

		Type type = loader.findType("Sample");
		assertEquals("Sample", type.getName());
		assertEquals(1, type.getFields().size());
	}

//	public void testReloadFile() throws IOException {
//		loader.appendResourcePath(root1.getName());
//		
//		File file = new File(root1,"Sample.nebula");
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyymmddHHMMss");
//
//		File newFile = new File(root1,"Sample.nebula" + format.format(new Date()));
//		
//		file.renameTo(newFile);
//
//		String newSrc = "type Sample{Name;Age;Sex;Name4 Name;};";
//
//		file = new File(root1,"Sample.nebula");
//		Writer w = new FileWriter(file);
//		w.write(newSrc);
//		w.close();
//
////		loader.update(file);
//		
//		file.delete();
//		newFile.renameTo(file);
//		
//		Type type = loader.findType("Sample");
//		assertEquals("Sample", type.getName());
//		assertEquals(4, type.getFields().size());
//
//	}

	public void testReloadURL() {
	}

}

package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//TODO Need refactoring  FileUtil 
public class FileUtil {
	private static Log log = LogFactory.getLog(FileUtil.class);

	public static InputStream print(InputStream in) {
		try {
			BufferedInputStream bin = new BufferedInputStream(in);
			bin.mark(4 * 1024 * 1024);
			System.out.println(readAllTextFrom(bin));
			bin.reset();
			return bin;
		} catch (IOException e) {
			log.error("IOException",e);
			throw new RuntimeException(e);
		}
	}

	public static String readAllTextFrom(File file) {
		try {
			return readAllTextFrom(new FileReader(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readAllTextFrom(URL url) {
		try {
			return readAllTextFrom(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readAllTextFrom(InputStream in) {
		return readAllTextFrom(new InputStreamReader(in));
	}

	public static String readAllTextFrom(BufferedInputStream bin) {
		try {
			bin.mark(4 * 1024 * 1024);
			String text = readAllTextFrom(new InputStreamReader(bin));
			bin.reset();
			return text;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readAllTextFrom(BufferedReader br) {
		try {
			br.mark(4 * 1024 * 1024);
			String text = readAllTextFrom((Reader) br);
			br.reset();
			return text;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String readAllTextFrom(Reader reader) {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(reader);
			String textLine = null;
			while ((textLine = in.readLine()) != null) {
				sb.append(textLine);
				sb.append('\n');
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	public static File saveTo(InputStream in, File file) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			if (file.exists()) {
				String abpath = file.getAbsolutePath();
				File newFile = new File(abpath + "." + format.format(new Date(file.lastModified())));

				// backup old file
				if (newFile.exists()) {
					newFile.delete();
				}
				boolean result = newFile.createNewFile();
				if (result) {
					log.trace("createNewFile " + newFile);
				} else {
					log.trace("createNewFile fail " + newFile);
				}
				byte[] buffer = new byte[1024];
				int length = -1;
				InputStream inOld = new FileInputStream(file);
				OutputStream outNew = new FileOutputStream(newFile);
				while ((length = inOld.read(buffer)) > 0) {
					outNew.write(buffer, 0, length);
				}
				inOld.close();
				outNew.close();
			}

			OutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			return file;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static File saveTo(String text, File file) {
		try {
			return saveTo(new ByteArrayInputStream(text.getBytes("utf-8")), file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

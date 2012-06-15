package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
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

public class FileUtil {
	private static Log log = LogFactory.getLog(FileUtil.class);

	// TODO move to other place
	public static String readAllTextFrom(File file) {
		try {
			return readAllTextFrom(new FileReader(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO move to other place
	public static String readAllTextFrom(URL url) {
		try {
			return readAllTextFrom(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO move to other place
	public static String readAllTextFrom(InputStream in) {
		return readAllTextFrom(new InputStreamReader(in));
	}

	// TODO move to other place
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
	// TODO move to other place
	public static String readAllTextFrom(BufferedReader br) {
		try {
			br.mark(4 * 1024 * 1024);
			String text = readAllTextFrom((Reader)br);
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

	public static File replace(File file, InputStream in) {
		try {

			if (file.exists()) {
				log.trace("replace file " + file);
				log.trace(FileUtil.readAllTextFrom(file));
			} else {
				log.trace("file not exist " + file);
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			String abpath = file.getAbsolutePath();
			File newFile = new File(abpath + "." + format.format(new Date(file.lastModified())));

			file.renameTo(newFile);

			if (newFile.exists()) {
				log.trace("copy to " + newFile);
				log.trace(FileUtil.readAllTextFrom(newFile));
			} else {
				log.trace("copy fail " + newFile);
			}

			file = new File(abpath);
			OutputStream out = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			log.trace(file.getName());
			log.trace(FileUtil.readAllTextFrom(file));
			return file;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static File replace(File file, String text) {
		try {
			return replace(file, new ByteArrayInputStream(text.getBytes("utf-8")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

package nebula.simpletemplate;

import java.io.File;
import java.net.*;

import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.ErrorType;

/**
 * The internal representation of a single group file (which must end in
 * ".stg"). If we fail to find a group file, look for it via the CLASSPATH as a
 * resource. Templates are only looked up in this file or an import.
 */
public class STGroupFile extends STGroup {
	public String fileName;
	public URL url;

	protected boolean alreadyLoaded = false;

	/** Load a file relative to current directory or from root or via CLASSPATH. */
	public STGroupFile(String fileName) {
		this(fileName, '<', '>');
	}

	public STGroupFile(String fileName, char delimiterStartChar, char delimiterStopChar) {
		super(delimiterStartChar, delimiterStopChar);
		if (!fileName.endsWith(GROUP_FILE_EXTENSION)) {
			throw new IllegalArgumentException("Group file names must end in .stg: " + fileName);
		}
		// try {
		File f = new File(fileName);
		if (f.exists()) {
			try {
				url = f.toURI().toURL();
			} catch (MalformedURLException e) {
				throw new STException("can't load group file " + fileName, e);
			}
			if (verbose) System.out.println("STGroupFile(" + fileName + ") == file " + f.getAbsolutePath());
		} else { // try in classpath
			url = getURL(fileName);
			if (url == null) {
				throw new IllegalArgumentException("No such group file: " + fileName);
			}
			if (verbose) System.out.println("STGroupFile(" + fileName + ") == url " + url);
		}
		this.fileName = fileName;
	}

	public STGroupFile(String fullyQualifiedFileName, String encoding) {
		this(fullyQualifiedFileName, encoding, '<', '>');
	}

	public STGroupFile(String fullyQualifiedFileName, String encoding, char delimiterStartChar, char delimiterStopChar) {
		this(fullyQualifiedFileName, delimiterStartChar, delimiterStopChar);
		this.encoding = encoding;
	}

	public STGroupFile(URL url, String encoding, char delimiterStartChar, char delimiterStopChar) {
		super(delimiterStartChar, delimiterStopChar);
		this.url = url;
		this.encoding = encoding;
		try {
			// When group is loaded from jar file the URL starts with
			// "jar:file:" which cannot be converted into a URI/File.
			// Remove the "jar:" prefix to enable the conversion.
			String urlString = url.toString();
			if (urlString.startsWith("jar:file:")) {
				urlString = urlString.substring(4);
			}
			this.fileName = new File(new URI(urlString)).getAbsolutePath();
		} catch (Exception e) {
			// ignore. If this happens (bad url etc.) filename is null
		}
	}

//	@Override
//	public boolean isDictionary(String name) {
//		if (!alreadyLoaded) load();
//		return super.isDictionary(name);
//	}

	@Override
	public boolean isDefined(String name) {
		if (!alreadyLoaded) load();
		return super.isDefined(name);
	}

	@Override
	public synchronized void unload() {
		super.unload();
		alreadyLoaded = false;
	}

	@Override
	protected CompiledST load(String name) {
		if (!alreadyLoaded) load();
		return rawGetTemplate(name);
	}

	@Override
	public void load() {
		if (alreadyLoaded) return;
		alreadyLoaded = true; // do before actual load to say we're doing it
		// no prefix since this group file is the entire group, nothing lives
		// beneath it.
		if (verbose) System.out.println("loading group file " + url.toString());
		loadGroupFile("/", url.toString());
		if (verbose)
			System.out.println("found " + templates.size() + " templates in " + url.toString() + " = "
					+ templates.keySet());
	}

	@Override
	public String show() {
		if (!alreadyLoaded) load();
		return super.show();
	}

	@Override
	public String getName() {
		return Misc.getFileNameNoSuffix(fileName);
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public URL getRootDirURL() {
		// System.out.println("url of "+fileName+" is "+url.toString());
		String parent = Misc.stripLastPathElement(url.toString());
		try {
			return new URL(parent);
		} catch (MalformedURLException mue) {
			errMgr.runTimeError(null, null, ErrorType.INVALID_TEMPLATE_NAME, mue, parent);
		}
		return null;
	}

	public void setListener(STErrorListener errors) {
		// TODO Auto-generated method stub
		
	}
}

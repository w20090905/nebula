package org.lilystudio.smarty4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.lilystudio.smarty4j.statement.Document;
import org.lilystudio.util.DynamicClassLoader;
import org.lilystudio.util.StringReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class Template {
	public static final String NAME = Template.class.getName().replace('.', '/');

	private static String[] INTERFACES = { IParser.NAME };
	private Engine engine;
	private String name;
	private long lastModified;
	private String path;
	private File file;
	private List<File> associatedFiles;
	private List<INode> nodes;
	private INode doc;
	private Map<String, Boolean> variables;
	private IParser parser;

	public Template(Engine engine, String text) throws TemplateException {
		this(engine, null, new StringReader(text), true);
	}

	public Template(Engine engine, String path, Reader reader, boolean parse) throws TemplateException {
		this.variables = new HashMap();

		this.engine = engine;
		if (path != null) {
			this.path = path.replace('\\', '/');
			this.name = path.substring(engine.getTemplatePath().length());
		}
		TemplateReader in = new TemplateReader(reader);
		this.doc = new Document(this, in);
		in.checkStatus(this.name);
		if (parse) this.parser = toParser(null);
	}

	Template(Engine engine, File file) throws IOException, TemplateException {
		this(engine, file.getAbsolutePath(), new InputStreamReader(new FileInputStream(file), engine.getEncoding()), true);
		this.file = file;
		this.name = this.path.substring(engine.getTemplatePath().length());
		this.lastModified = file.lastModified();
	}

	public Engine getEngine() {
		return this.engine;
	}

	public String getName() {
		return this.name;
	}

	public boolean isUpdated() {
		if (this.file.lastModified() > this.lastModified) {
			return true;
		}
		if (this.associatedFiles != null) {
			for (File file : this.associatedFiles) {
				if (file.lastModified() > this.lastModified) {
					return true;
				}
			}
		}
		return false;
	}

	public String getPath() {
		return this.path;
	}

	public String getPath(String path, boolean isTemplateName) {
		if (this.name == null) return path;
		if (path.charAt(0) != '/') {
			String s = (isTemplateName) ? this.name : this.path;
			int last = s.lastIndexOf(47);
			if (last >= 0) path = s.substring(0, last + 1) + path;
		} else if (!(isTemplateName)) {
			return this.engine.getTemplatePath() + path;
		}
		return path;
	}

	public void associate(File file) {
		if (this.associatedFiles == null) {
			this.associatedFiles = new ArrayList();
		}
		this.lastModified = Math.max(file.lastModified(), this.lastModified);
		this.associatedFiles.add(file);
	}

	public INode getNode(int index) {
		return ((INode) this.nodes.get(index));
	}

	public int addNode(INode node) {
		if (this.nodes == null) {
			this.nodes = new ArrayList();
		}
		this.nodes.add(node);
		return (this.nodes.size() - 1);
	}

	public void addUsedVariable(String name) {
		if (this.variables != null) {
			Boolean value = (Boolean) this.variables.get(name);
			if (value == null) this.variables.put(name, Boolean.TRUE);
		}
	}

	public void preventCacheVariable(String name) {
		if (this.variables != null) this.variables.put(name, Boolean.FALSE);
	}

	public void preventAllCache() {
		this.variables = null;
	}

	public void merge(Context context, OutputStream out) throws Exception {
		Writer writer = new TemplateWriter(out, this.engine.getEncoding());
		try {
			merge(context, writer);
		} finally {
			writer.flush();
		}
	}

	public void merge(Context context, Writer out) {
		context.setTemplate(this);
		this.parser.merge(context, out);
	}

	public IParser toParser(String name) {
		ClassWriter cw = new ClassWriter(3);
		if (name != null) {
			cw.visitSource(name, null);
		}

		name = "template";
		cw.visit(49, 1, name, null, "java/lang/Object", INTERFACES);

		MethodVisitor mw = cw.visitMethod(1, "<init>", "()V", null, null);
		mw.visitVarInsn(25, 0);
		mw.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
		mw.visitInsn(177);
		mw.visitMaxs(0, 0);
		mw.visitEnd();

		mw = cw.visitMethod(1, "merge", "(L" + Context.NAME + ";Ljava/io/Writer;)V", null, null);
		mw.visitVarInsn(25, 1);
		mw.visitMethodInsn(182, Context.NAME, "getTemplate", "()L" + NAME + ";");
		mw.visitVarInsn(58, 3);

		this.doc.scan(this);
		Map variableNames = null;
		if (this.variables != null) {
			variableNames = new HashMap();
			for (Map.Entry variable : this.variables.entrySet()) {
				if (((Boolean) variable.getValue()).booleanValue()) {
					int value = 4 + variableNames.size();
					String key = (String) variable.getKey();
					mw.visitVarInsn(25, 1);
					mw.visitLdcInsn(key);
					mw.visitMethodInsn(182, Context.NAME, "get", "(Ljava/lang/String;)Ljava/lang/Object;");
					mw.visitVarInsn(58, value);
					variableNames.put(key, Integer.valueOf(value));
				}
			}
			this.variables = null;
		}

		this.doc.parse(mw, 4 + ((variableNames != null) ? variableNames.size() : 0), variableNames);

		mw.visitInsn(177);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		cw.visitEnd();
		byte[] code = cw.toByteArray();

		try {
			FileOutputStream fos = new FileOutputStream("tmp/smarty4j_" + name + ".class");
			fos.write(code);
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try {
			return ((IParser) DynamicClassLoader.getClass(name, code).newInstance());
		} catch (Exception e) {
			throw new RuntimeException("不能实例化Parser对象");
		}
	}
}

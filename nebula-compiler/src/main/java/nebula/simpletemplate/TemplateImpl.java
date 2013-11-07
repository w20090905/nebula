package nebula.simpletemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.ImmutableMap;

public class TemplateImpl {

	final static Log log = LogFactory.getLog(TemplateImpl.class);
	STGroup nativeGroup;

	String[] formalArguments;

	boolean hasFormalArgs;

	public List<TemplateImpl> implicitlyDefinedTemplates;
	String name;
	Code code;

	TemplateImpl(STGroup group, final Code code) {
		this.nativeGroup = group;
		this.code = code;
		this.bytecodeWithKnownClass = ImmutableMap.of();
		bufferes[0] = new StringBuilder(INITIAL_SIZE);
		this.formalArguments = new String[] { "at" };
		for (int i = 0; i < BUFFER_SIZE; i++) {
			canuse[i] = true;
		}
	}

	TemplateImpl(STGroup group, final Code code, List<String> arguments) {
		this.nativeGroup = group;
		this.code = code;
		this.bytecodeWithKnownClass = ImmutableMap.of();
		bufferes[0] = new StringBuilder(INITIAL_SIZE);
		this.formalArguments = arguments.toArray(new String[0]);
		for (int i = 0; i < BUFFER_SIZE; i++) {
			canuse[i] = true;
		}
	}

	TemplateImpl(STGroup group, final Code code, List<String> arguments, List<TemplateImpl> implicitlyDefinedTemplates) {
		this(group, code, arguments);
		this.implicitlyDefinedTemplates = implicitlyDefinedTemplates;
	}

	// 命名参数形式数据
	public <T> String execNamed(Map<String, T> data) {
		Object[] args = new Object[this.formalArguments.length];
		int max = this.formalArguments.length;
		for (int i = 0; i < max; i++) {
			args[i] = data.get(formalArguments[i]);
		}
		return this.exec(args);
	}

	static int INITIAL_SIZE = 128;
	static int BUFFER_SIZE = 1;
	static int BUFFER_MASK = BUFFER_SIZE - 1;

	ReentrantLock lock = new ReentrantLock();

	StringBuilder[] bufferes = new StringBuilder[BUFFER_SIZE];
	boolean[] canuse = new boolean[BUFFER_SIZE];
	volatile int lastCanuse = 0;

	int cntExec = 0;
	int cntMiss = 0;

	public String exec(Object... argv) {
		cntExec++;
		String paramsNames = null;
		if (argv.length == 1 && argv[0] != null) {
			paramsNames = argv[0].getClass().getName();
		} else if (argv.length > 1) {
			StringBuilder sbParams = new StringBuilder();
			for (Object obj : argv) {
				if (obj != null) {
					sbParams.append(obj.getClass().getName());
				} else {
					sbParams.append("NULL");
				}
			}
			paramsNames = sbParams.toString();
		} else {
			paramsNames = "nop";
		}

		try {
			StringBuilder sb = null;

			int usedIndex = -1;
			{// buffer string builder
				int lastc = lastCanuse++ & BUFFER_MASK;

				if (canuse[lastc]) {// 需要更加强健的代码，当然也要考量缓存机制是否划算。
					usedIndex = lastc;
					canuse[usedIndex] = false;
					sb = bufferes[usedIndex];
				} else {
					sb = new StringBuilder(bufferes[0].capacity());
					cntMiss++;
					if (cntExec / cntMiss < 10) {
						int lastBUFFER_SIZE = BUFFER_SIZE;
						StringBuilder[] lastBufferes = bufferes;
						boolean[] lastCanuse = canuse;

						BUFFER_SIZE = BUFFER_SIZE + BUFFER_SIZE;

						bufferes = new StringBuilder[BUFFER_SIZE];
						canuse = new boolean[BUFFER_SIZE];

						for (int i = 0; i < lastBUFFER_SIZE; i++) {
							bufferes[i] = lastBufferes[i];
							canuse[i] = lastCanuse[i];

						}
						for (int i = lastBUFFER_SIZE; i < BUFFER_SIZE; i++) {
							bufferes[i] = new StringBuilder(bufferes[0].capacity());
							canuse[i] = true;
						}

						BUFFER_MASK = BUFFER_SIZE - 1;

						cntMiss = 0;
					}
				}
			}

			if (argv.length < this.formalArguments.length) {
				Object[] lastArgv = argv;
				argv = new Object[this.formalArguments.length];
				System.arraycopy(lastArgv, 0, argv, 0, lastArgv.length);
			}
			get(paramsNames, argv).exec(this.nativeGroup, this, sb, argv);
			String result = sb.toString();

			{// clear string builder
				if (usedIndex >= 0) {
					sb.setLength(0);
					canuse[usedIndex] = true;
				}
			}
			return result;
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public <T> String execList(Object... argv) {
		cntExec++;
		@SuppressWarnings("unchecked")
		List<T> dataList = (List<T>) argv[0];
		String paramsNames = dataList.get(0).getClass().getName();

		try {
			StringBuilder sb = null;

			int usedIndex = -1;
			{// buffer string builder
				int lastc = lastCanuse++ & BUFFER_MASK;

				if (canuse[lastc]) {// 需要更加强健的代码，当然也要考量缓存机制是否划算。
					usedIndex = lastc;
					canuse[usedIndex] = false;
					sb = bufferes[usedIndex];
				} else {
					sb = new StringBuilder(bufferes[0].capacity());
					cntMiss++;
					if (cntExec / cntMiss < 10) {
						int lastBUFFER_SIZE = BUFFER_SIZE;
						StringBuilder[] lastBufferes = bufferes;
						boolean[] lastCanuse = canuse;

						BUFFER_SIZE = BUFFER_SIZE + BUFFER_SIZE;

						bufferes = new StringBuilder[BUFFER_SIZE];
						canuse = new boolean[BUFFER_SIZE];

						for (int i = 0; i < lastBUFFER_SIZE; i++) {
							bufferes[i] = lastBufferes[i];
							canuse[i] = lastCanuse[i];

						}
						for (int i = lastBUFFER_SIZE; i < BUFFER_SIZE; i++) {
							bufferes[i] = new StringBuilder(bufferes[0].capacity());
							canuse[i] = true;
						}

						BUFFER_MASK = BUFFER_SIZE - 1;

						cntMiss = 0;
					}
				}
			}

			for (int i = 0; i < dataList.size(); i++) {
				argv[0] = dataList.get(i);
				get(paramsNames, argv).exec(this.nativeGroup, this, sb, argv);
			}

			String result = sb.toString();

			{// clear string builder
				if (usedIndex >= 0) {
					sb.setLength(0);
					canuse[usedIndex] = true;
				}
			}
			return result;
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	// 遍历对象
	public <T> String execList(List<T> dataList, Object... argv) {
		cntExec++;

		StringBuilder sbParams = new StringBuilder();
		{
			Object obj = dataList.get(0);
			if (obj != null) {
				sbParams.append(obj.getClass().getName());
			} else {
				sbParams.append("NULL");
			}
		}

		for (Object obj : argv) {
			if (obj != null) {
				sbParams.append(obj.getClass().getName());
			} else {
				sbParams.append("NULL");
			}
		}
		sbParams.append(Integer.class.getName());

		String paramsNames = sbParams.toString();

		try {
			StringBuilder sb = null;

			int usedIndex = -1;
			{// buffer string builder
				int lastc = lastCanuse++ & BUFFER_MASK;

				if (canuse[lastc]) {// 需要更加强健的代码，当然也要考量缓存机制是否划算。
					usedIndex = lastc;
					canuse[usedIndex] = false;
					sb = bufferes[usedIndex];
				} else {
					sb = new StringBuilder(bufferes[0].capacity());
					cntMiss++;
					if (cntExec / cntMiss < 10) {
						int lastBUFFER_SIZE = BUFFER_SIZE;
						StringBuilder[] lastBufferes = bufferes;
						boolean[] lastCanuse = canuse;

						BUFFER_SIZE = BUFFER_SIZE + BUFFER_SIZE;

						bufferes = new StringBuilder[BUFFER_SIZE];
						canuse = new boolean[BUFFER_SIZE];

						for (int i = 0; i < lastBUFFER_SIZE; i++) {
							bufferes[i] = lastBufferes[i];
							canuse[i] = lastCanuse[i];

						}
						for (int i = lastBUFFER_SIZE; i < BUFFER_SIZE; i++) {
							bufferes[i] = new StringBuilder(bufferes[0].capacity());
							canuse[i] = true;
						}

						BUFFER_MASK = BUFFER_SIZE - 1;

						cntMiss = 0;
					}
				}
			}

			for (int i = 0; i < dataList.size(); i++) {
				Object[] datas = new Object[] { dataList.get(i), argv, i };
				get(paramsNames, datas).exec(this.nativeGroup, this, sb, datas);
			}

			String result = sb.toString();

			{// clear string builder
				if (usedIndex >= 0) {
					sb.setLength(0);
					canuse[usedIndex] = true;
				}
			}
			return result;
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	Map<String, Action> bytecodeWithKnownClass;

	public Action get(String paramsNames, Object... argv) {

		Action builder = bytecodeWithKnownClass.get(paramsNames);

		if (builder != null) return builder;

		lock.lock();
		try {

			builder = bytecodeWithKnownClass.get(paramsNames);
			if (builder != null) return builder;

			CompilerContext c = new CompilerContext(argv);
			if (this.name != null) {
				builder = ActionComplier.DEFAULT.compile(c, this.name + "__" + paramsNames, code);
			} else {
				builder = ActionComplier.DEFAULT.compile(c, paramsNames, code);
			}

			ImmutableMap.Builder<String, Action> mapBuilder = ImmutableMap.builder();
			bytecodeWithKnownClass = mapBuilder.putAll(bytecodeWithKnownClass).put(paramsNames, builder).build();

			return builder;

		} finally {
			lock.unlock();
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("String tempalte");
		if (formalArguments.length > 0) {
			sb.append("(");
			for (String argName : formalArguments) {
				sb.append(argName);
				sb.append(',');
			}
			sb.setCharAt(sb.length() - 1, ')');
		} else {
			sb.append("()");
		}

		sb.append(code);
		sb.append("\n");
		return sb.toString();
	}
}

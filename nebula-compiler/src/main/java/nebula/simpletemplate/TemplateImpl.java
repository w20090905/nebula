package nebula.simpletemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class TemplateImpl {

	final static Log log = LogFactory.getLog(TemplateImpl.class);
	STGroup group;

	List<String> formalArguments;

	boolean hasFormalArgs;

	public List<TemplateImpl> implicitlyDefinedTemplates;
	String name;
	Code code;

	TemplateImpl(STGroup group, final Code code) {
		this.group = group;
		this.code = code;
		this.bytecodeWithKnownClass = ImmutableMap.of();
		bufferes[0] = new StringBuilder(INITIAL_SIZE);
		this.formalArguments = Lists.newArrayList("at");
	}

	TemplateImpl(STGroup group, final Code code, List<String> arguments) {
		this.group = group;
		this.code = code;
		this.bytecodeWithKnownClass = ImmutableMap.of();
		bufferes[0] = new StringBuilder(INITIAL_SIZE);
		this.formalArguments = arguments;
	}

	TemplateImpl(STGroup group, final Code code, List<String> arguments, List<TemplateImpl> implicitlyDefinedTemplates) {
		this(group, code, arguments);
		this.implicitlyDefinedTemplates = implicitlyDefinedTemplates;
	}

	// 命名参数形式数据
	public <T> String execNamed(Map<String, T> data) {
		List<Object> args = Lists.newArrayList();
		for (String name : this.formalArguments) {
			args.add(data.get(name));
		}
		return this.exec(args.toArray(new Object[0]));
	}

	static int INITIAL_SIZE = 128;
	static int BUFFER_SIZE = 1;
	ReentrantLock lock = new ReentrantLock();

	StringBuilder[] bufferes = new StringBuilder[BUFFER_SIZE];
	final boolean[] used = new boolean[BUFFER_SIZE];
	volatile int lastCanuse = 0;

	int cntCount = 0;
	int cntMiss = 0;

	public String exec(Object... argv) {
		cntCount++;
		String paramsNames = null;
		if (argv.length == 1) {
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
				int lastc = lastCanuse++;
				lastCanuse = lastCanuse < BUFFER_SIZE ? lastCanuse : 0;

				if (!used[lastc]) {// 需要更加强健的代码，当然也要考量缓存机制是否划算。
					usedIndex = lastc;
					used[usedIndex] = true;
					if (bufferes[usedIndex] == null) {
						bufferes[usedIndex] = new StringBuilder(bufferes[0].capacity());
					}
					sb = bufferes[usedIndex];
				} else {
					sb = new StringBuilder(bufferes[0].capacity());
					cntMiss++;
					if (cntCount / cntMiss < 10) {
						StringBuilder[] last = bufferes;
						bufferes = new StringBuilder[BUFFER_SIZE + 1];
						for (int i = 0; i < BUFFER_SIZE; i++) {
							bufferes[i] = last[i];
						}
						BUFFER_SIZE++;
						cntMiss = 0;
					}
				}
			}

			get(paramsNames, argv).exec(this.group, this, sb, argv);
			String result = sb.toString();

			{// clear string builder
				if (usedIndex >= 0) {
					sb.setLength(0);
					used[usedIndex] = false;
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
		cntCount++;

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
				int lastc = lastCanuse++;
				lastCanuse = lastCanuse < BUFFER_SIZE ? lastCanuse : 0;

				if (!used[lastc]) {// 需要更加强健的代码，当然也要考量缓存机制是否划算。
					usedIndex = lastc;
					used[usedIndex] = true;
					if (bufferes[usedIndex] == null) {
						bufferes[usedIndex] = new StringBuilder(bufferes[0].capacity());
					}
					sb = bufferes[usedIndex];
				} else {
					sb = new StringBuilder(bufferes[0].capacity());
					cntMiss++;
					if (cntCount / cntMiss < 10) {
						StringBuilder[] last = bufferes;
						bufferes = new StringBuilder[BUFFER_SIZE + 1];
						for (int i = 0; i < BUFFER_SIZE; i++) {
							bufferes[i] = last[i];
						}
						BUFFER_SIZE++;
						cntMiss = 0;
					}
				}
			}

			for (int i = 0; i < dataList.size(); i++) {
				Object[] datas = new Object[] { dataList.get(i), argv, i };
				get(paramsNames, datas).exec(this.group, this, sb, datas);
			}

			String result = sb.toString();

			{// clear string builder
				if (usedIndex >= 0) {
					sb.setLength(0);
					used[usedIndex] = false;
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
			builder = ActionComplier.DEFAULT.compile(c, "test", code);

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
		if (formalArguments.size() > 0) {
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

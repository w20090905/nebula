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
	STGroup group;

	TemplateImpl(STGroup group, final Code code) {
		this.group = group;
		this.code = code;
		this.bytecodeWithKnownClass = ImmutableMap.of();
		bufferes[0] = new StringBuilder(INITIAL_SIZE);
	}

	public String name;
	Code code;
	Map<String, Action> bytecodeWithKnownClass;

	public String exec(Object root) {
		return this.exec(root.getClass(), root);
	}

	public <T> String exec(Map<String, T> root) {
		return this.exec(Map.class, root);
	}

	public <T> String exec(List<T> root) {
		return this.exec(List.class, root);
	}

	ReentrantLock lock = new ReentrantLock();

	static int INITIAL_SIZE = 128;

	static int BUFFER_SIZE = 1;
	StringBuilder[] bufferes = new StringBuilder[BUFFER_SIZE];
	final boolean[] used = new boolean[BUFFER_SIZE];
	volatile int lastCanuse = 0;

	int cntCount = 0;
	int cntMiss = 0;

	public String exec(Class<?> clz, Object root) {
		cntCount++;

		try {
			StringBuilder sb = null;

			int usedIndex = -1;
			{// buffer string builder
				int lastc = lastCanuse++;
				lastCanuse = lastCanuse < BUFFER_SIZE ? lastCanuse : 0;
				
				if (!used[lastc]) {//需要更加强健的代码，当然也要考量缓存机制是否划算。
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
						bufferes = new StringBuilder[BUFFER_SIZE+1];
						for (int i = 0; i < BUFFER_SIZE; i++) {
							bufferes[i]=last[i];
						}
						BUFFER_SIZE++;
						cntMiss = 0;
					}
				}
			}

			get(clz).exec(sb, root);
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

	public Action get(Class<?> clz) {
		String name = clz.getName();
		Action builder = bytecodeWithKnownClass.get(name);

		if (builder != null) return builder;

		lock.lock();
		try {

			builder = bytecodeWithKnownClass.get(name);
			if (builder != null) return builder;

			CompilerContext c = new CompilerContext(clz);
			builder = ActionComplier.DEFAULT.compile(c, "test", code);

			ImmutableMap.Builder<String, Action> mapBuilder = ImmutableMap.builder();
			bytecodeWithKnownClass = mapBuilder.putAll(bytecodeWithKnownClass).put(name, builder).build();

			return builder;

		} finally {
			lock.unlock();
		}

	}
}

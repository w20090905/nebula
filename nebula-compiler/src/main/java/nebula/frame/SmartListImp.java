package nebula.frame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.SmartList;

public class SmartListImp<E> extends CopyOnWriteArrayList<E> implements SmartList<E> {

	private static final long serialVersionUID = 8013096094469473667L;

	final Map<String, E> indexes = new HashMap<>();
	final Map<String, Integer> indexI = new HashMap<>();
	final Identifiable<E> identifier;

	private final String name;

	public String getName() {
		return name;
	}

	public SmartListImp(String name, Identifiable<E> identifier) {
		super();
		this.identifier = identifier;
		this.name = name;
	}

	public SmartListImp(String name, Identifiable<E> identifier, Collection<? extends E> c) {
		super(c);
		this.name = name;
		this.identifier = identifier;
		rebuildIndex();
	}

	public SmartListImp(String name, Identifiable<E> identifier, E[] toCopyIn) {
		super(toCopyIn);
		this.name = name;
		this.identifier = identifier;
		rebuildIndex();
	}

	protected void rebuildIndex() {
		indexI.clear();
		indexes.clear();
		if (this.identifier instanceof AutoIdentifiable) {
			ListIterator<E> list = super.listIterator();
			while (list.hasNext()) {
				int i = list.nextIndex();
				E e = list.next();
				String key = identifier.getId(e);
				indexI.put(key, i);
				indexes.put(key, e);
			}
			AutoIdentifiable<E> auto = (AutoIdentifiable<E>) this.identifier;
			for (E e : this) {
				auto.set(indexes, (E) e);
			}
		} else {
			ListIterator<E> list = super.listIterator();
			while (list.hasNext()) {
				int i = list.nextIndex();
				E e = list.next();
				String key = identifier.getId(e);
				indexI.put(key, i);
				indexes.put(key, e);
			}
		}
	}

	@Override
	public E get(String key) {
		return indexes.get(key);
	}

	@Override
	public boolean add(E e) {
		int i = indexOfByKey(e);
		if (i >= 0) {
			super.set(i, e);
		} else {
			super.add(e);
		}
		rebuildIndex();
		return true;
	}

	public int indexOfByKey(E element) {
		Integer in = indexI.get(identifier.getId(element));
		if(in!=null){
			return in;
		}else{
			return -1;
		}
	}

	@Override
	public E remove(int index) {
		E ret = super.remove(index);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		List<E> news = new ArrayList<>();
		for (E e : c) {
			int i = indexOfByKey(e);
			if (i >= 0) {
				super.set(i, e);
			} else {
				news.add(e);
			}
		}
		boolean ret = super.addAll(news);
		rebuildIndex();
		return ret;
	}

	@Override
	// TODO SmartList<E> find(String match)
	public SmartList<E> query(String match) {
		return null;
	}

	@Override
	public void clear() {
		super.clear();
		indexes.clear();
		indexI.clear();
	}

	@Override
	@Deprecated
	public E set(int index, E element) {
		throw new UnsupportedOperationException("add");
	}

	@Override
	@Deprecated
	public void add(int index, E element) {
		throw new UnsupportedOperationException("add");
	}

	@Override
	@Deprecated
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("add");
	}

	@Override
	@Deprecated
	public boolean addIfAbsent(E e) {
		throw new UnsupportedOperationException("addIfAbsent");
	}

	@Override
	@Deprecated
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("removeAll");
	}

	@Override
	@Deprecated
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("retainAll");
	}

	@Override
	@Deprecated
	public int addAllAbsent(Collection<? extends E> c) {
		throw new UnsupportedOperationException("addAllAbsent");
	}

	@Override
	@Deprecated
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("addAll");
	}

	@Override
	public void save(E data) {
		this.add(data);		
	}
}

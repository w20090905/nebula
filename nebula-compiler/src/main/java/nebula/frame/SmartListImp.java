package nebula.frame;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import nebula.SmartList;

public class SmartListImp<E> extends CopyOnWriteArrayList<E> implements SmartList<E> {

	private static final long serialVersionUID = 8013096094469473667L;

	final Map<String, E> datas = new HashMap<>();
	final Identifiable<E> identifier;
	
	private final String name;
	
	public String getName() {
		return name;
	}

	public SmartListImp(String name,Identifiable<E> identifier) {
		super();
		this.identifier = identifier;
		this.name = name;
	}

	public SmartListImp(String name,Identifiable<E> identifier, Collection<? extends E> c) {
		super(c);
		this.name = name;
		this.identifier = identifier;
		rebuildIndex();
	}


	public SmartListImp(String name,Identifiable<E> identifier, E[] toCopyIn) {
		super(toCopyIn);
		this.name = name;
		this.identifier = identifier;
		rebuildIndex();
	}
	
	protected void rebuildIndex(){
		if (this.identifier instanceof AutoIdentifiable) {
			AutoIdentifiable<E> auto = (AutoIdentifiable<E>) this.identifier;
			for (E e : this) {
				auto.set(datas, (E) e);
			}
		} else {
			for (E e : this) {
				datas.put(identifier.getId(e), e);
			}
		}
	}

	@Override
	public E get(String key) {
		return datas.get(key);
	}

	@Override
	public E set(int index, E element) {
		E e =  super.set(index, element);
		rebuildIndex();
		return e;
	}

	@Override
	public boolean add(E e) {
		boolean ret = super.add(e);
		rebuildIndex();
		return ret;
	}

	@Override
	public void add(int index, E element) {
		super.add(index, element);
		rebuildIndex();
	}

	@Override
	public E remove(int index) {
		E ret =  super.remove(index);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean remove(Object o) {
		boolean ret = super.remove(o);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean addIfAbsent(E e) {
		boolean ret =  super.addIfAbsent(e);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean ret =  super.removeAll(c);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean ret =  super.retainAll(c);
		rebuildIndex();
		return ret;
	}

	@Override
	public int addAllAbsent(Collection<? extends E> c) {
		int ret =  super.addAllAbsent(c);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean ret =  super.addAll(c);
		rebuildIndex();
		return ret;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean ret =  super.addAll(index, c);
		rebuildIndex();
		return ret;
	}

	@Override
	//TODO SmartList<E> find(String match) 
	public SmartList<E> find(String match) {
		return null;
	}

	public interface Identifiable<E> {
		public String getId(E data);
	}

	public interface AutoIdentifiable<E> extends Identifiable<E> {
		public void set(Map<String, E> map, E data);
	}
}

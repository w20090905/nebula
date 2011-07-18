package nebula.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class FindableList<K, V> implements List<V> {

    Map<K, Integer> keys = new HashMap<K, Integer>();

    public Map<K, Integer> getKeys() {
        return keys;
    }

    List<V> innerList = new ArrayList<V>();

    @Override
    public int size() {
        return this.innerList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.innerList.isEmpty();
    }

    public void put(K key, V value) {
        if (keys.containsKey(key)) {
            this.innerList.set(keys.get(key), value);
        } else {
            this.innerList.add(value);
            keys.put(key, innerList.size() - 1);
        }
    }

    public V get(K key) {
        return this.innerList.get(keys.get(key));
    }

    @Override
    public boolean contains(Object o) {
        return this.innerList.contains(o);
    }

    @Override
    public Iterator<V> iterator() {
        return this.innerList.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.innerList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.innerList.toArray(a);
    }

    @Override
    public boolean add(V e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        return this.innerList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.innerList.containsAll(c);
    }

    @Override
    @Deprecated
    public boolean addAll(Collection<? extends V> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends V> c) {
        // VODO Auto-generated method stub
        return false;
    }

    @Override
    @Deprecated
    public boolean removeAll(Collection<?> c) {
        return this.innerList.removeAll(c);
    }

    @Override
    @Deprecated
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void clear() {
        this.innerList.clear();

    }

    @Override
    public V get(int index) {
        return this.innerList.get(index);
    }

    @Override
    public V set(int index, V element) {
        return this.innerList.set(index, element);
    }

    @Override
    public void add(int index, V element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<V> listIterator() {
        return this.innerList.listIterator();
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        return this.innerList.listIterator(index);
    }

    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        return this.innerList.subList(fromIndex, toIndex);
    }

}

package nebula;

import java.util.List;

import nebula.data.DataFilter;


public interface SmartList<E> extends List<E> {// extends List<T> {
    E get(String key);
    SmartList<E> query(DataFilter<E> matcher);
    void save(E data);
}

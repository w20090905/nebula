package nebula;

import java.util.List;


public interface SmartList<E> extends List<E> {// extends List<T> {
    E get(String key);
    SmartList<E> query(Filter<E> matcher);
    void save(E data);
}

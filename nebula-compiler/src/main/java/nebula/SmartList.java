package nebula;

import java.util.List;

import com.google.common.base.Predicate;


public interface SmartList<E> extends List<E> {// extends List<T> {
    E get(String key);
    SmartList<E> query(Predicate<E> matcher);
    void save(E data);
}

package nebula;

import java.util.List;

public interface SmartList<E> extends List<E> {// extends List<T> {
    E get(String key);
    SmartList<E> query(String match);
}

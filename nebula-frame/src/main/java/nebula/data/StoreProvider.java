package nebula.data;

public interface StoreProvider {
    <T> Store<T> get(Class<T> clz);
    Store<?> get(String name);
}
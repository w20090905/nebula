package nebula.data;

import junit.framework.TestCase;

public class ReposTest extends TestCase {
    Repos repos;

    public ReposTest() {
        repos = new Repos();

    }

    public void testGet() {
        repos.bindTo("*", new StoreProvider() {

            @Override
            public <T> Store<T> get(Class<T> clz) {
                return new DefaultStore<T>();
            }

            @Override
            public Store<?> get(String name) {
                // TODO Auto-generated method stub
                return null;
            }

        });
        repos.bindTo("nebula.*", new StoreProvider() {

            @Override
            public <T> Store<T> get(Class<T> clz) {
                return new DbStore<T>();
            }

            @Override
            public Store<?> get(String name) {
                // TODO Auto-generated method stub
                return null;
            }

        });
        repos.bindTo("nebula.*", new StoreProvider() {

            @Override
            public <T> Store<T> get(Class<T> clz) {
                return new DbStore<T>();
            }

            @Override
            public Store<?> get(String name) {
                // TODO Auto-generated method stub
                return null;
            }

        });
        
        Store<java.lang.String> s = repos.get(java.lang.String.class);
        assertEquals(DefaultStore.class, s.getClass());
        Store<nebula.entity.Person> s2 = repos.get(nebula.entity.Person.class);
        assertEquals(DbStore.class, s2.getClass());

    }

    public void testFind() {
    }

    public void testBindTo() {
    }

    class DefaultStore<T> implements Store<T> {
    }

    class DbStore<T> implements Store<T> {
    }
}

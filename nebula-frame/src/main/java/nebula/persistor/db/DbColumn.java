/**
 * 
 */
package nebula.persistor.db;

public class DbColumn {
    public DbColumn(Object parent, String name, boolean key) {
        this.name = name;
        this.key = key;
    }

    public DbColumn(String name, boolean key) {
        this.name = name;
        this.key = key;
    }

    public String name;
    public boolean key;
}
package nebula.persistor.db;

import java.sql.Connection;


import com.google.inject.Inject;
import com.google.inject.Provider;

public class ConnectionProvider implements Provider<Connection> {
    protected final DbConfiguration cfg;    
    
    @Inject
    public ConnectionProvider(DbConfiguration cfg){
        this.cfg = cfg;
    }
    
    @Override
    public Connection get() {
        return cfg.getConnection();
    }
    
}

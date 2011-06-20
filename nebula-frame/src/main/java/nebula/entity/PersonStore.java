package nebula.entity;

import java.util.List;

import nebula.data.Repos;
import nebula.data.Store;

public class PersonStore implements Store<Person>{
    final Repos repos ;
    final PersonPersistor persistor;
    final List<PersonImp> ps;
    
    public PersonStore(Repos repos, PersonPersistor persistor){
        this.persistor = persistor;
        this.repos = repos;
        ps = this.persistor.list();
    }
    
    public Person get(String... key){        
        return new PersonAgent(ps.get(0));
    }    
}

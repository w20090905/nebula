package nebula.entity;

import nebula.data.Repos;
import nebula.data.Store;

public class PersonStore implements Store<PersonImp>{
    final Repos repos ;
    final PersonPersistor persistore;
    public PersonStore(Repos repos, PersonPersistor persistore){
        this.persistore = persistore;
        this.repos = repos;
    }

}

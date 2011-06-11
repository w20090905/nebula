package module;


import it.trace.action.brm.BookAction;
import it.trace.dao.BookDao;
import it.trace.service.BookManager;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ProjectModule implements Module {

    @Override
    public void configure(Binder binder) {

        // action
        binder.bind(BookAction.class);

        // service
        binder.bind(BookManager.class);

        // dao
        binder.bind(BookDao.class);


    }

}

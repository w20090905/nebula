package it.trace.nebula.rest.sample.resources.simple;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.sample.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookResource {

    public List<Book> list() {
        return new ArrayList<Book>();
    }

    public static DataBinder<Book> createDataBinder() {
        return new DataBinder<Book>() {
            @Override
            public Object[] bind(Context context) {
                return null;
            }
        };
    }

}

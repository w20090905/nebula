package it.trace.nebula.rest.sample.resources.simple;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.sample.entity.Book;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookResource {

    public static final Map<Object, Book> books = new LinkedHashMap<Object, Book>();

    public static long nextId;

    static {
        Book b;
        b = new Book();
        b.setId(1);
        b.setName("线性代数");
        books.put(b.getId(), b);
        b = new Book();
        b.setId(2);
        b.setName("模拟电路");
        books.put(b.getId(), b);
        b = new Book();
        b.setId(3);
        b.setName("CPLD系统设计");
        books.put(b.getId(), b);

        nextId = books.size() + 1;

    }


    public Collection<Book> list() {
        return books.values();
    }

    public Book editable(long id) {
        return books.get(id);
    }

    public void update(Book b) {
        books.put(b.getId(), b);
    }

    public void editNew() {
        System.out.println("editNew");
    }

    public void create(Book b) {
        books.put(nextId, b);
        nextId++;
    }
    public Book removable(long id) {
        return books.get(id);
    }

    public void remove(long id) {
        books.remove(id);
    }

    public static DataBinder<Book> createDataBinder() {
        return new DataBinder<Book>() {

            @Override
            public Object[] bind(Context context, Method method) {

                if ("update".equals(method.getName()) || "create".equals(method.getName())) {
                    Book b = new Book();
                    if (context.getId() != null)
                        b.setId(Long.parseLong(context.getId()));
                    b.setType(Integer.parseInt((String) context.getParameter("book.type")));
                    b.setName((String) context.getParameter("book.name"));
                    return new Object[] { b };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }

}

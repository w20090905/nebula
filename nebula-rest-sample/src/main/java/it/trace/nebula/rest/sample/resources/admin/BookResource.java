package it.trace.nebula.rest.sample.resources.admin;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.sample.entity.Book;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookResource {

    public static final Map<Object, Book> books = new LinkedHashMap<Object, Book>();

    public static long nextId;

    static {

        Book book;

        book = new Book();
        book.setId(1);
        book.setName("Think in Java");
        book.setDescription("讲JAVA的");
        book.setType(1);
        book.setPublisher("上海教育出版社");
        book.setPrice(66.6F);
        books.put(book.getId(), book);

        book = new Book();
        book.setId(2);
        book.setName("Think in C++");
        book.setDescription("讲C++的");
        book.setType(1);
        book.setPublisher("上海外文出版社");
        book.setPrice(55.5F);
        books.put(book.getId(), book);

        book = new Book();
        book.setId(3);
        book.setName("时尚快照");
        book.setDescription("讲时尚的");
        book.setType(2);
        book.setPublisher("上海文艺出版社");
        book.setPrice(44.4F);
        books.put(book.getId(), book);

        book = new Book();
        book.setId(4);
        book.setName("中国地图");
        book.setDescription("讲地图的");
        book.setType(3);
        book.setPublisher("上海地理出版社");
        book.setPrice(33.3F);
        books.put(book.getId(), book);

        nextId = books.size() + 1;

    }


    public List<Book> list() {
        return new ArrayList<Book>(books.values());
    }

    public Book view(long id){
        return books.get(id);
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
                } else if ("view".equals(method.getName())) {
                    long id = Long.valueOf(context.getId());
                    return new Object[] { id };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }

}

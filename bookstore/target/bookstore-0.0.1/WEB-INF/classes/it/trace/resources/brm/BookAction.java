package it.trace.resources.brm;


import it.trace.entiry.Book;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.service.BookManager;

import java.lang.reflect.Method;
import java.util.List;


import com.google.inject.Inject;

public class BookAction {

    private Book book;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    private List<Book> list;
    public List<Book> getList() {
        return list;
    }

    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    private BookManager manager;
    @Inject
    public void setManager(BookManager manager) {
        this.manager = manager;
    }

    public String list() {
        this.list = manager.selectAll();
        return "success";
    }

    public String editNew() {
        return "success";
    }

    public String create() {
        manager.insert(this.book);
        return "success";
    }

    // TODO 名字
    public String editable() {
        this.book = manager.select(id);
        return "success";
    }

    public String update() {
        manager.update(this.book);
        return "success";
    }

    // TODO 名字
    public String removable() {
        this.book = manager.select(id);
        return "success";
    }

    public String remove() {
        manager.delete(id);
        return "success";
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
                    b.setDescription((String)context.getParameter("book.description"));
                    b.setPublisher((String)context.getParameter("book.publisher"));
                    b.setCount(Integer.parseInt((String)context.getParameter("book.count")));
                    b.setPrice(Float.parseFloat((String)context.getParameter("book.price")));
                    return new Object[] { b };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }
}


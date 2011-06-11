package it.trace.action.brm;


import it.trace.entiry.Book;
import it.trace.mvc.binder.Context;
import it.trace.mvc.binder.DataBinder;
import it.trace.service.BookManager;

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

    public static DataBinder<BookAction> createDataBinder() {
        return new DataBinder<BookAction>() {

            @Override
            public void bind(Context context, BookAction instance) {

                if (context.getId() != null && !context.getId().isEmpty()) {
                    instance.setId(Long.parseLong(context.getId()));
                }

                Book book = new Book();
                String v;
                v = context.getParameter("book.id");
                if (v != null) {
                    book.setId(Long.parseLong(v));
                }
                v = context.getParameter("book.type");
                if (v != null) {
                    book.setType(Integer.parseInt(v));
                }
                v = context.getParameter("book.name");
                if (v != null) {
                    book.setName(v);
                }
                v = context.getParameter("book.description");
                if (v != null) {
                    book.setDescription(v);
                }
                v = context.getParameter("book.publisher");
                if (v != null) {
                    book.setPublisher(v);
                }
                v = context.getParameter("book.count");
                if (v != null) {
                    book.setCount(Integer.parseInt(v));
                }
                v = context.getParameter("book.price");
                if (v != null) {
                    book.setPrice(Float.parseFloat(v));
                }
                instance.setBook(book);

            }
        };
    }
}


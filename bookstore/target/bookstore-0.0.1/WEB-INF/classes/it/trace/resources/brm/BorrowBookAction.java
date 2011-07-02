package it.trace.resources.brm;


import it.trace.entiry.Administrator;
import it.trace.entiry.BorrowBook;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.service.BorrowBookManager;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;


import com.google.inject.Inject;

public class BorrowBookAction {

    private BorrowBook borrowBook;
    public BorrowBook getBorrowBook() {
        return borrowBook;
    }
    public void setBorrowBook(BorrowBook borrowBook) {
        this.borrowBook = borrowBook;
    }

    private List<BorrowBook> list;
    public List<BorrowBook> getList() {
        return list;
    }

    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    private BorrowBookManager manager;
    @Inject
    public void setManager(BorrowBookManager manager) {
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
        manager.insert(this.borrowBook);
        return "success";
    }

    // TODO 名字
    public String editable() {
        this.borrowBook = manager.select(id);
        return "success";
    }

    public String update() {
        manager.update(this.borrowBook);
        return "success";
    }

    // TODO 名字
    public String removable() {
        this.borrowBook = manager.select(id);
        return "success";
    }

    public String remove() {
        manager.delete(id);
        return "success";
    }

    public static DataBinder<Administrator> createDataBinder() {
        return new DataBinder<Administrator>() {

            @Override
            public Object[] bind(Context context, Method method) {

                if ("update".equals(method.getName()) || "create".equals(method.getName())) {
                	BorrowBook borrowBook = new BorrowBook();
                    if (context.getId() != null)
                    	borrowBook.setId(Long.parseLong(context.getId()));
                    borrowBook.setBookId(Integer.parseInt((String)context.getParameter("borrowBook.bookId")));
                    borrowBook.setBookName((String)context.getParameter("borrowBook.bookName"));
                    borrowBook.setCustomerId(Integer.parseInt((String)context.getParameter("borrowBook.customerId")));
                    borrowBook.setBorrowDate(new Date(1900,01,01));
                    borrowBook.setEndDate(new Date(1900,01,01));
                    borrowBook.setReturnDate(new Date(1900,01,01));
                    borrowBook.setMemo((String)context.getParameter("borrowBook.memo"));
                    return new Object[] { borrowBook };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }
    
}


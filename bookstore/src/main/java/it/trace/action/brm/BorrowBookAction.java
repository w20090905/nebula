package it.trace.action.brm;


import it.trace.entiry.BorrowBook;
import it.trace.mvc.binder.Context;
import it.trace.mvc.binder.DataBinder;
import it.trace.service.BorrowBookManager;

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

    public static DataBinder<BorrowBookAction> createDataBinder() {
        return new DataBinder<BorrowBookAction>() {

            @Override
            public void bind(Context context, BorrowBookAction instance) {

                if (context.getId() != null && !context.getId().isEmpty()) {
                    instance.setId(Long.parseLong(context.getId()));
                }

                BorrowBook borrowBook = new BorrowBook();
                String v;
                v = context.getParameter("borrowBook.bookId");
                if (v != null) {
                    borrowBook.setBookId(Integer.valueOf(v));
                }
                v = context.getParameter("borrowBook.bookName");
                if (v != null) {
                    borrowBook.setBookName(v);
                }
                v = context.getParameter("borrowBook.customerId");
                if (v != null) {
                    borrowBook.setCustomerId(Integer.valueOf(v));
                }
                v = context.getParameter("borrowBook.borrowDate");
                if (v != null) {
                    borrowBook.setBorrowDate(new Date(1L));
                }
                v = context.getParameter("borrowBook.borrowDate");
                if (v != null) {
//    TODO            	borrowBook.setBorrowDate(new Date(1L));
                }
                v = context.getParameter("borrowBook.endDate");
                if (v != null) {
//     TODO           	borrowBook.setBorrowDate(new Date(1L));
                }
                v = context.getParameter("borrowBook.returnDate");
                if (v != null) {
                	borrowBook.setReturnDate(new Date(1L));
                }
                v = context.getParameter("borrowBook.memo");
                if (v != null) {
                    borrowBook.setMemo(v);
                }
                v = context.getParameter("borrowBook.applyStatus");
                if (v != null) {
                    borrowBook.setApplyStatus(v);
                }
                instance.setBorrowBook(borrowBook);

            }
        };
    }
}


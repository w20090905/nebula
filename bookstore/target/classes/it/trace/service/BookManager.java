package it.trace.service;

import it.trace.dao.BookDao;
import it.trace.entiry.Book;

import java.util.List;


import com.google.inject.Inject;

public class BookManager {

    private BookDao dao;

    @Inject
    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    public Book select(long id) {
        return dao.select(id);
    }

    public List<Book> selectAll() {
        return dao.selectAll();
    }

    public int update(Book book) {
        return dao.update(book);
    }

    public int insert(Book book) {
        return dao.insert(book);
    }

    public int delete(long id) {
        return dao.delete(id);
    }

}
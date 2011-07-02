package it.trace.service;

import it.trace.dao.BorrowBookDao;
import it.trace.entiry.BorrowBook;

import java.util.List;


import com.google.inject.Inject;

public class BorrowBookManager {

    private BorrowBookDao dao;

    @Inject
    public void setDao(BorrowBookDao dao) {
        this.dao = dao;
    }

    public BorrowBook select(long id) {
        return dao.select(id);
    }

    public List<BorrowBook> selectAll() {
        return dao.selectAll();
    }

    public int update(BorrowBook borrowBook) {
        return dao.update(borrowBook);
    }

    public int insert(BorrowBook borrowBook) {
        return dao.insert(borrowBook);
    }

    public int delete(long id) {
        return dao.delete(id);
    }

}
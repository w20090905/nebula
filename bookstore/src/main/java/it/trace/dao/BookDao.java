package it.trace.dao;

import it.trace.entiry.Book;

import java.util.List;


public class BookDao extends AbstractBaseDao {

    public Book select(long id) {
    	return super.selectOne(Book.class, "select * from Book where id = ?", id);
    }

    public List<Book> selectAll() {
    	return super.select(Book.class, "select * from Book");
    }

    public int update(Book book) {
        String sql = "update Book set"
        + " type = ?,"
        + " name = ?,"
        + " description = ?,"
        + " publisher = ?,"
        + " count = ?,"
        + " price = ?"
        + " where"
        + " id = ?";

    	return super.update(sql, book.getType(), book.getName(), book.getDescription(), book.getPublisher(), book.getCount(), book.getPrice(), book.getId());
    }

    public int insert(Book book) {
        String sql = "insert into Book ( "
        + " type,"
        + " name,"
        + " description,"
        + " publisher,"
        + " count,"
        + " price"
        + " ) values ( "
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?"
        + " )";

    	return super.update(sql, book.getType(), book.getName(), book.getDescription(), book.getPublisher(), book.getCount(), book.getPrice());
    }

    public int delete(long id) {
    	return super.update("delete from Book where id = ?", id);
    }

}

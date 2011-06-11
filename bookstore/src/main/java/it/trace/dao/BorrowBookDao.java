package it.trace.dao;

import it.trace.entiry.BorrowBook;

import java.util.List;

public class BorrowBookDao extends AbstractBaseDao  {

	public BorrowBook select(long id) {
    	return super.selectOne(BorrowBook.class, "select * from BorrowBook where id = ?", id);
    }

    public List<BorrowBook> selectAll() {
    	return super.select(BorrowBook.class, "select * from BorrowBook");
    }

    public int update(BorrowBook borrowBook) {
        String sql = "update BorrowBook set"
        + " bookId = ?,"
        + " bookName = ?,"
        + " customerId = ?,"
        + " borrowDate = ?,"
        + " endDate = ?,"
        + " returnDate = ?"
        + " borrowMemo = ?"
        + " where"
        + " id = ?";

    	return super.update(sql, borrowBook.getBookId(), borrowBook.getBookName(), borrowBook.getCustomerId(), borrowBook.getBorrowDate(), borrowBook.getEndDate(), borrowBook.getReturnDate(), borrowBook.getBorrowMemo(),borrowBook.getId());
    }

    public int insert(BorrowBook borrowBook) {
        String sql = "insert into Book ( "
					+ " bookId ,"
					+ " bookName ,"
					+ " customerId ,"
					+ " borrowDate ,"
					+ " endDate ,"
					+ " returnDate "
					+ " borrowMemo "
					+ " ) values ( "
					+ " ?,"
					+ " ?,"
					+ " ?,"
					+ " ?,"
					+ " ?,"
					+ " ?,"
					+ " ?"
					+ " )";

    	return super.update(sql, borrowBook.getBookId(), borrowBook.getBookName(), borrowBook.getCustomerId(), borrowBook.getBorrowDate(), borrowBook.getEndDate(), borrowBook.getReturnDate(), borrowBook.getBorrowMemo());
    }

    public int delete(long id) {
    	return super.update("delete from BorrowBook where id = ?", id);
    }

}

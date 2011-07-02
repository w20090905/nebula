package it.trace.dao;

import it.trace.entiry.Customer;

import java.util.List;

public class CustomerDao extends AbstractBaseDao {

    public Customer select(long id) {
    	return super.selectOne(Customer.class, "select * from Customer where id = ?", id);
    }

    public List<Customer> selectAll() {
    	return super.select(Customer.class, "select * from Customer");
    }

    public int update(Customer customer) {
        String sql = "update Customer set"
        + " userName = ?,"
        + " userPassword = ?,"
        + " name = ?,"
        + " sex = ?,"
        + " birthday = ?,"
        + " level = ?,"
        + " memo = ?"
        + " where"
        + " id = ?";

    	return super.update(sql, customer.getUserName(), customer.getUserPassword(), customer.getName(), customer.getSex(), customer.getBirthday(), customer.getLevel(),customer.getMemo(), customer.getId());
    }

    public int insert(Customer customer) {
        String sql = "insert into Customer ( "
        	  + " userName ,"
              + " userPassword ,"
              + " name ,"
              + " sex ,"
              + " birthday ,"
              + " level ,"
              + " memo "
        + " ) values ( "
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?"
        + " )";

        return super.update(sql, customer.getUserName(), customer.getUserPassword(), customer.getName(), customer.getSex(), customer.getBirthday(), customer.getLevel(),customer.getMemo());
    }

    public int delete(long id) {
    	return super.update("delete from Customer where id = ?", id);
    }

	public Customer selectByNameAndPassword(Customer customer) {
		return super.selectOne(Customer.class, "select * from Customer where userName = ? and userPassword = ?",customer.getUserName(),customer.getUserPassword());
	}

}

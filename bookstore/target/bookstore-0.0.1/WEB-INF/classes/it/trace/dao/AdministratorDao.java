package it.trace.dao;

import it.trace.entiry.Administrator;
import it.trace.entiry.Customer;

import java.util.List;


public class AdministratorDao extends AbstractBaseDao {

    public Administrator select(long id) {
    	return super.selectOne(Administrator.class, "select * from Administrator where id = ?", id);
    }

    public List<Administrator> selectAll() {
    	return super.select(Administrator.class, "select * from Administrator");
    }

    public int update(Administrator administrator) {
        String sql = "update Administrator set"
        + " groupId = ?,"
        + " name = ?,"
        + " password = ?,"
        + " role = ?,"
        + " memo = ?"
        + " id = ?";

    	return super.update(sql, administrator.getGroupId(), administrator.getName(), administrator.getPassword(), administrator.getRole(), administrator.getMemo(), administrator.getId());
    }

    public int insert(Administrator administrator) {
        String sql = "insert into Administrator ( "
        + " groupId,"
        + " name,"
        + " password,"
        + " role,"
        + " memo "
        + " ) values ( "
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?"
        + " )";

    	return super.update(sql, administrator.getGroupId(), administrator.getName(), administrator.getPassword(), administrator.getRole(), administrator.getRole());
    }

    public int delete(long id) {
    	return super.update("delete from Administrator where id = ?", id);
    }
    
	public Administrator selectByNameAndPassword(Administrator administrator) {
		return super.selectOne(Administrator.class, "select * from Administrator where name = ? and password = ?",administrator.getName(),administrator.getPassword());
	}

}

package it.trace.dao;

import it.trace.entiry.Task;

import java.util.List;

public class TaskDao extends AbstractBaseDao {

    public Task select(long id) {
    	return super.selectOne(Task.class, "select * from Task where id = ?", id);
    }

    public List<Task> selectAll() {
    	return super.select(Task.class, "select * from Task");
    }

    public int update(Task task) {
        String sql = "update Task set"
        + " administratorId = ?,"
        + " borrrowId = ?,"
        + " name = ?,"
        + " status = ?,"
        + " memo = ?,"
        + " createDate = ?"
        + " updateDate"
        + " id = ?";

    	return super.update(sql, task.getAdministratorId(), task.getBorrowId(), task.getName(), task.getStatus(), task.getMemo(), task.getCreateDate(),task.getUpdateDate(), task.getId());
    }

    public int insert(Task task) {
        String sql = "insert into Task ( "
            + " administratorId = ?,"
            + " borrrowId = ?,"
            + " name,"
            + " status,"
            + " memo,"
            + " createDate,"
            + " updateDate"
        + " ) values ( "
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?,"
        + " ?"
        + " )";

        return super.update(sql, task.getAdministratorId(), task.getBorrowId(), task.getName(), task.getStatus(), task.getMemo(), task.getCreateDate(),task.getUpdateDate());
    }

    public int delete(long id) {
    	return super.update("delete from Task where id = ?", id);
    }

}

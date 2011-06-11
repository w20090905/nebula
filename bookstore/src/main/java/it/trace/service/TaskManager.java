package it.trace.service;

import it.trace.dao.TaskDao;
import it.trace.entiry.Task;

import java.util.List;


import com.google.inject.Inject;

public class TaskManager {

    private TaskDao dao;

    @Inject
    public void setDao(TaskDao dao) {
        this.dao = dao;
    }

    public Task select(long id) {
        return dao.select(id);
    }

    public List<Task> selectAll() {
        return dao.selectAll();
    }

    public int update(Task Task) {
        return dao.update(Task);
    }

    public int insert(Task Task) {
        return dao.insert(Task);
    }

    public int delete(long id) {
        return dao.delete(id);
    }

}
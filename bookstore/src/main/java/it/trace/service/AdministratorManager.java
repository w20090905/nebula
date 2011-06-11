package it.trace.service;

import it.trace.dao.AdministratorDao;
import it.trace.entiry.Administrator;
import it.trace.entiry.Customer;

import java.util.List;

import com.google.inject.Inject;

public class AdministratorManager {

    private AdministratorDao dao;

    @Inject
    public void setDao(AdministratorDao dao) {
        this.dao = dao;
    }

    public Administrator select(long id) {
        return dao.select(id);
    }

    public List<Administrator> selectAll() {
        return dao.selectAll();
    }

    public int update(Administrator administrator) {
        return dao.update(administrator);
    }

    public int insert(Administrator administrator) {
        return dao.insert(administrator);
    }

    public int delete(long id) {
        return dao.delete(id);
    }

	public boolean login(Administrator administrator) {
		if (dao.selectByNameAndPassword(administrator) != null) {
			return false;
		} else {
			return true;
		}

	}
}
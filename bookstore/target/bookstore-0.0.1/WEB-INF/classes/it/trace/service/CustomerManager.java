package it.trace.service;

import it.trace.dao.CustomerDao;
import it.trace.entiry.Customer;

import java.util.List;

import com.google.inject.Inject;

public class CustomerManager {

	private CustomerDao dao;

	@Inject
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}

	public Customer select(long id) {
		return dao.select(id);
	}

	public List<Customer> selectAll() {
		return dao.selectAll();
	}

	public int update(Customer customer) {
		return dao.update(customer);
	}

	public int insert(Customer customer) {
		return dao.insert(customer);
	}

	public int delete(long id) {
		return dao.delete(id);
	}

	public boolean login(Customer customer) {
		if (dao.selectByNameAndPassword(customer) != null) {
			return false;
		} else {
			return true;
		}

	}

}
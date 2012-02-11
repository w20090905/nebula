package it.trace.manager;

import it.trace.dao.BaseDao;
import it.trace.entity.Company;
import it.trace.entity.Contact;
import it.trace.entity.Touch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyManager extends BaseDao<Company> {

	@SuppressWarnings("unchecked")
	public List<Company> selectAll() {
		return this.getSession().createCriteria(Company.class).list();
	}

	public Company select(int id) {
		return super.getObject(Company.class, id);
	}

	public void update(Company company) {
		super.updateObject(company);
	}

	public int insert(Company company) {
		super.saveObject(company);
		return 1;
	}

	public void delete(Integer id) {
		super.removeObject(Company.class, id);
	}

	public static void main(String[] args) {
		CompanyManager cm = new CompanyManager();
		Company com = new Company();
		com.setName("com name");
		Contact con = new Contact();
		con.setName("com contact name");
		Touch touch = new Touch();
		touch.setContact(con);
		touch.setTouchDate(new Date());
		touch.setMemo("touch memo");
		con.setCompany(com);
		List<Contact> contactList = new ArrayList<Contact>();
		List<Touch> touchList = new ArrayList<Touch>();
		touchList.add(touch);
		con.setTouchList(touchList);
		contactList.add(con);
		com.setContactList(contactList);
		cm.insert(com);
		
		List<Company> list = cm.selectAll();
		for (Company admin : list) {
			System.out.println(admin.getName() + "\t"+admin.getContactList().size());

		}
	}
}
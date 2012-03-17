package it.trace.manager;

import it.trace.dao.BaseDao;
import it.trace.entity.Contact;
import it.trace.entity.Touch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

public class ContactManager extends BaseDao<Contact> {

	@SuppressWarnings("unchecked")
	public List<Contact> selectAll() {
		return this.getSession().createCriteria(Contact.class).list();
	}
	
	public List<Contact> getContactByCompany(int companyId){
		 String HQL = "select c from Contact c where c.company.id ="+companyId;
		 Query query = getSession().createQuery(HQL);
		 System.out.println("HQL:"+HQL);
		return query.list();
		
	}

	public Contact select(int id) {
		return super.getObject(Contact.class, id);
	}

	public void update(Contact contact) {
		super.updateObject(contact);
	}

	public int insert(Contact contact) {
		super.saveObject(contact);
		return 1;
	}

	public int delete(Integer id) {
		super.removeObject(Contact.class, id);
		return 1;
	}

	public static void main(String[] args) {
		ContactManager cm = new ContactManager();
		Contact con = new Contact();
		con.setName("con name");
		Touch touch = new Touch();
		touch.setContact(con);
		touch.setTouchDate(new Date());
		touch.setMemo("touch memo");
		List<Touch> touchList = new ArrayList<Touch>();
		touchList.add(touch);
		con.setTouchList(touchList);
		cm.insert(con);
		List<Contact> list = cm.selectAll();
		for (Contact admin : list) {
			System.out.println(admin.getName() + "\t");
			if (admin.getCompany() != null) {
				System.out.println("com:" + admin.getCompany().getName());
			}

		}
	}
}
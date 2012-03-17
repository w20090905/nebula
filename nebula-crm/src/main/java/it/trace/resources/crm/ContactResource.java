package it.trace.resources.crm;

import it.trace.entity.Company;
import it.trace.entity.Contact;
import it.trace.manager.ContactManager;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;

import java.lang.reflect.Method;
import java.util.List;

import com.google.inject.Inject;

public class ContactResource {

	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setCustomer(Contact contact) {
		this.contact = contact;
	}

	private List<Contact> list;

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	private ContactManager manager = new ContactManager();

	@Inject
	public void setManager(ContactManager manager) {
		this.manager = manager;
	}

	public List<Contact> list() {
		this.list = manager.selectAll();
		return list;
	}

	public String editNew() {
		return "success";
	}

	public String create(Contact contact) {
		manager.insert(contact);
		return "success";
	}

	public Contact view(String id) {
		return manager.select(Integer.valueOf(id));
	}

	public List<Contact> getByContact(int companyId) {
		this.list = manager.getContactByCompany(companyId);
		return list;
	}

	// TODO 名字
	public String editable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String update(Contact contact) {
		manager.update(contact);
		return "success";
	}

	// TODO 名字
	public String removable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String remove(Integer id) {
		manager.delete(id);
		return "success";
	}

	public static DataBinder<Contact> createDataBinder() {
		return new DataBinder<Contact>() {

			@Override
			public Object[] bind(Context context, Method method) {

				if ("update".equals(method.getName())
						|| "create".equals(method.getName())) {
					Contact contact = new Contact();
					if (context.getId() != null)
						contact.setId(Integer.parseInt(context.getId()));
					if (context.getParameter("company"))
						contact.setCompany((Company)context.getParameter("company"));
					contact.setName((String) context.getParameter("name"));
					contact.setCellphone((String) context
							.getParameter("cellphone"));
					contact.setEmail((String) context.getParameter("email"));
					return new Object[] { contact };
				} else if ("editable".equals(method.getName())
						|| "removable".equals(method.getName())) {
					return new Object[] { Integer.valueOf(((String) context
							.getParameter("id"))) };
				} else if ("view".equals(method.getName())) {
					String id = context.getId();
					return new Object[] { id };
				} else if ("getByContact".equals(method.getName())) {
					return new Object[] { Integer.valueOf(context.getId()) };
				} else if ("remove".equals(method.getName())) {
					return new Object[] { Integer.valueOf(((String) context
							.getParameter("id"))) };
				}

				return null;
			}

		};
	}

}

package it.trace.resources.crm;

import it.trace.entity.Company;
import it.trace.entity.Contact;
import it.trace.manager.ContactManager;
import it.trace.manager.CompanyManager;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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

	private ContactManager contManager = new ContactManager();

	@Inject
	public void setManager(ContactManager manager) {
		this.contManager = manager;
	}

	public List<Contact> list() {
		this.list = contManager.selectAll();
		return list;
	}

	public String editNew() {
		return "success";
	}

	public String create(Contact contact) {
		contManager.insert(contact);
		return "success";
	}

	public Contact view(String id) {
		return contManager.select(Integer.valueOf(id));
	}

	public List<Contact> getByContact(int companyId) {
		this.list = contManager.getContactByCompany(companyId);
		return list;
	}

	// TODO 名字
	public String editable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String update(Contact contact) {
		contManager.update(contact);
		return "success";
	}

	// TODO 名字
	public String removable() {
		// this.contact = manager.select(id);
		return "success";
	}

	public String remove(Integer id) {
		contManager.delete(id);
		return "success";
	}

	public static DataBinder<Contact> createDataBinder() {
		return new DataBinder<Contact>() {

			@Override
			public Object[] bind(Context context, Method method) {

				CompanyManager comManager = new CompanyManager();
				if ("update".equals(method.getName())
						|| "create".equals(method.getName())) {
					Contact contact = new Contact();
					if (context.getId() != null)
						contact.setId(Integer.parseInt(context.getId()));
					if (StringUtils.isNotEmpty((String)context.getParameter("id"))) {
						Company company = comManager.select(Integer.valueOf((String) context
								.getParameter("id")));
						contact.setCompany(company);
					}
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

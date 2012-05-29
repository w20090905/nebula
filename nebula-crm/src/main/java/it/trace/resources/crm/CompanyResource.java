package it.trace.resources.crm;

import it.trace.entity.Company;
import it.trace.entity.Contact;
import it.trace.manager.CompanyManager;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;

import java.lang.reflect.Method;
import java.util.List;

import com.google.inject.Inject;

public class CompanyResource {

	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company Company) {
		this.company = Company;
	}

	private List<Company> list;

	public List<Company> getList() {
		return list;
	}

	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	private CompanyManager manager = new CompanyManager();;

	@Inject
	public void setManager(CompanyManager manager) {
		this.manager = manager;
	}

	public List<Contact> getByContact(int companyId) {
		return manager.select(companyId).getContactList();
	}

	public List<Company> list() {
		this.list = manager.selectAll();
		return list;
	}

	public String editNew() {
		return "success";
	}

	public String create(Company company) {
		manager.insert(company);
		return "success";
	}

	public Company view(String id) {
		return manager.select(Integer.valueOf(id));
	}

	public Company editable(int id) {
		return manager.select(id);
	}

	public String update(Company company) {
		manager.update(company);
		return "success";
	}

	public String removable() {
		// this.company = manager.select(id);
		return "success";
	}

	public void remove(Integer id) {
		manager.delete(id);
	}

	public static DataBinder<Company> createDataBinder() {
		return new DataBinder<Company>() {
			@Override
			public Object[] bind(Context context, Method method) {
				System.out.println("method.getName()" + method.getName());

				if ("update".equals(method.getName())
						|| "create".equals(method.getName())) {
					Company company = new Company();
					if (!"create".equals(method.getName())&& context.getId() != null)
						company.setId(Integer.parseInt(context.getId()));
					company.setName((String) context.getParameter("name"));
					company.setTel((String) context.getParameter("tel"));
					company.setAddress((String) context.getParameter("address"));
					company.setDescription((String) context
							.getParameter("description"));
					return new Object[] { company };
				} else if ("editable".equals(method.getName())) {
					return new Object[] { Long.parseLong((String) context
							.getParameter("id")) };
				} else if ("view".equals(method.getName())) {
					String id = context.getId();
					return new Object[] { id };
				} else if ("remove".equals(method.getName())) {
					return new Object[] { Integer.valueOf(((String) context
							.getParameter("id"))) };
				}

				return null;
			}

		};
	}

}

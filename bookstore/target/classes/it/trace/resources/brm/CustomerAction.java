package it.trace.resources.brm;


import it.trace.entiry.Book;
import it.trace.entiry.Customer;
import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.service.CustomerManager;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;


import com.google.inject.Inject;

public class CustomerAction {

    private Customer customer;
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer Customer) {
        this.customer = customer;
    }

    private List<Customer> list;
    public List<Customer> getList() {
        return list;
    }

    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    private CustomerManager manager;
    @Inject
    public void setManager(CustomerManager manager) {
        this.manager = manager;
    }
    
    public String login(){
    	if(manager.login(this.customer)){
    		//TODO request.setSession(this.customer);
    		return "success";
    	} else {
    		return "input";
    	}
    }

    public String list() {
        this.list = manager.selectAll();
        return "success";
    }

    public String editNew() {
        return "success";
    }

    public String create() {
        manager.insert(this.customer);
        return "success";
    }

    // TODO 名字
    public String editable() {
        this.customer = manager.select(id);
        return "success";
    }

    public String update() {
        manager.update(this.customer);
        return "success";
    }

    // TODO 名字
    public String removable() {
        this.customer = manager.select(id);
        return "success";
    }

    public String remove() {
        manager.delete(id);
        return "success";
    }

    public static DataBinder<Customer> createDataBinder() {
        return new DataBinder<Customer>() {

            @Override
            public Object[] bind(Context context, Method method) {

                if ("update".equals(method.getName()) || "create".equals(method.getName())) {
                    Customer customer = new Customer();
                    if (context.getId() != null)
                    	customer.setId(Long.parseLong(context.getId()));
                    customer.setName((String) context.getParameter("customer.name"));
                    customer.setUserName((String) context.getParameter("customer.userPassword"));
                    customer.setUserPassword((String) context.getParameter("customer.userPassword"));
                    customer.setSex((String) context.getParameter("customer.sex"));
                    customer.setBirthday(new Date(1900,01,01));
                    customer.setLevel(Integer.parseInt((String) context.getParameter("customer.level")));
                    customer.setMemo((String) context.getParameter("customer.memo"));
                    return new Object[] { customer };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }
    
}


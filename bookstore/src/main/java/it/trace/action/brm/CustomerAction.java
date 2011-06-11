package it.trace.action.brm;


import it.trace.entiry.Customer;
import it.trace.mvc.binder.Context;
import it.trace.mvc.binder.DataBinder;
import it.trace.service.CustomerManager;

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

    public static DataBinder<CustomerAction> createDataBinder() {
        return new DataBinder<CustomerAction>() {

            @Override
            public void bind(Context context, CustomerAction instance) {

                if (context.getId() != null && !context.getId().isEmpty()) {
                    instance.setId(Long.parseLong(context.getId()));
                }

                Customer customer = new Customer();
                String v;
                v = context.getParameter("customer.id");
                if (v != null) {
                    customer.setId(Long.parseLong(v));
                }
                v = context.getParameter("customer.name");
                if (v != null) {
                    customer.setName(v);
                }
                v = context.getParameter("customer.password");
                if (v != null) {
                    customer.setUserPassword(v);
                }
                v = context.getParameter("customer.sex");
                if (v != null) {
                    customer.setSex(v);
                }
                v = context.getParameter("customer.birthday");
                if (v != null) {
//TODO                    customer.setBirthday(v);
                }
                v = context.getParameter("customer.level");
                if (v != null) {
                    customer.setLevel(Integer.valueOf(v));
                }
                v = context.getParameter("customer.memo");
                if (v != null) {
                	customer.setMemo(v);
                }
                instance.setCustomer(customer);

            }
        };
    }
}


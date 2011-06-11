package it.trace.action.brm;


import it.trace.entiry.Administrator;
import it.trace.mvc.binder.Context;
import it.trace.mvc.binder.DataBinder;
import it.trace.service.AdministratorManager;

import java.util.List;

import com.google.inject.Inject;

public class AdministratorAction {

    private Administrator administrator;
    public Administrator getAdministrator() {
        return administrator;
    }
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    private List<Administrator> list;
    public List<Administrator> getList() {
        return list;
    }

    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    private AdministratorManager manager;
    @Inject
    public void setManager(AdministratorManager manager) {
        this.manager = manager;
    }
    
    public String login(){
    	if(manager.login(this.administrator)){
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
        manager.insert(this.administrator);
        return "success";
    }

    // TODO 名字
    public String editable() {
        this.administrator = manager.select(id);
        return "success";
    }

    public String update() {
        manager.update(this.administrator);
        return "success";
    }

    // TODO 名字
    public String removable() {
        this.administrator = manager.select(id);
        return "success";
    }

    public String remove() {
        manager.delete(id);
        return "success";
    }

    public static DataBinder<AdministratorAction> createDataBinder() {
        return new DataBinder<AdministratorAction>() {

            @Override
            public void bind(Context context, AdministratorAction instance) {

                if (context.getId() != null && !context.getId().isEmpty()) {
                    instance.setId(Long.parseLong(context.getId()));
                }

                Administrator administrator = new Administrator();
                String v;
                v = context.getParameter("administrator.id");
                if (v != null) {
                    administrator.setId(Long.parseLong(v));
                }
                v = context.getParameter("administrator.name");
                if (v != null) {
                    administrator.setName(v);
                }
                v = context.getParameter("administrator.password");
                if (v != null) {
                    administrator.setPassword(v);
                }
                v = context.getParameter("administrator.role");
                if (v != null) {
                    administrator.setRole(Integer.valueOf(v));
                }
                v = context.getParameter("administrator.memo");
                if (v != null) {
                	administrator.setMemo(v);
                }
                instance.setAdministrator(administrator);

            }
        };
    }
}


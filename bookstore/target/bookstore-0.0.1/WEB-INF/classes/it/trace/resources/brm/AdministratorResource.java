package it.trace.resources.brm;


import it.trace.entiry.Administrator;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.service.AdministratorManager;

import java.lang.reflect.Method;
import java.util.List;

import com.google.inject.Inject;

public class AdministratorResource {

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
    
    public static DataBinder<Administrator> createDataBinder() {
        return new DataBinder<Administrator>() {

        	@Override
            public Object[] bind(Context context, Method method) {

                if ("update".equals(method.getName()) || "create".equals(method.getName())) {
                	Administrator administrator = new Administrator();
                    if (context.getId() != null)
                    	administrator.setId(Long.parseLong(context.getId()));
                    administrator.setName((String) context.getParameter("administrator.name"));
                    administrator.setPassword((String) context.getParameter("administrator.password"));
                    administrator.setRole(Integer.parseInt((String) context.getParameter("administrator.role")));
                    administrator.setMemo((String) context.getParameter("administrator.memo"));
                    return new Object[] { administrator };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }
}


package it.trace.resources.brm;


import it.trace.entiry.Administrator;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.service.AdministratorManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

public class AdministratorResource {
	
	public static final Map<Object, Administrator> admins = new LinkedHashMap<Object, Administrator>();
	static {

		Administrator admin;
		admin = new Administrator();
		admin.setId(1);
		admin.setName("test_admin");
		admin.setRole(1);
		admin.setMemo("test memo");
        admins.put(admin.getId(), admin);
        
    	admin = new Administrator();
		admin.setId(2);
		admin.setName("test_admin2");
		admin.setRole(2);
		admin.setMemo("test memo 2");
        admins.put(admin.getId(), admin);

    }
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

    private static Long id;
    public void setId(Long id) {
        this.id = id;
    }

    private AdministratorManager manager  = new AdministratorManager();
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

    public List<Administrator> list() {
        this.list = manager.selectAll();
        return list;
//    	System.out.println("admin:--------------"+admins.size());
//    	 return new ArrayList<Administrator>(admins.values());
    }

    public String editNew() {
        return "success";
    }

    public void create(Administrator a) {
        manager.insert(a);
    }

    // TODO 名字
    public Administrator editable(long id) {
    	return manager.select(id);
    }

    public String update(Administrator a) {
        manager.update(a);
        return "success";
    }

    // TODO 名字
    public Administrator removable(long id) {
    	return manager.select(id);
    }

    public String remove(long id) {
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
	                    administrator.setGroupId(Long.parseLong((String) context.getParameter("groupId")));
	                    administrator.setName((String) context.getParameter("name"));
	                    administrator.setPassword((String) context.getParameter("password"));
	                    administrator.setRole(Integer.parseInt((String) context.getParameter("role")));
	                    administrator.setMemo((String) context.getParameter("memo"));
	                    return new Object[] { administrator };
                } else if ("editable".equals(method.getName()) || "removable".equals(method.getName()) || "remove".equals(method.getName())) {
                    return new Object[] { Long.parseLong((String) context.getParameter("id")) };
                }

                return null;
            }

        };
    }
}


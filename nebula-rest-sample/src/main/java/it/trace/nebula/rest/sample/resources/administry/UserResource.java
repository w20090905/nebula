package it.trace.nebula.rest.sample.resources.administry;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.sample.entity.User;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserResource {

    public static final Map<Object, User> users = new LinkedHashMap<Object, User>();

    static {

        User user;

        user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        users.put(user.getUsername(), user);

        user = new User();
        user.setUsername("user");
        user.setPassword("user");
        users.put(user.getUsername(), user);

        user = new User();
        user.setUsername("tom");
        user.setPassword("12345");
        users.put(user.getUsername(), user);

    }

    public boolean validate(String username, String password) {

        User u = users.get(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }


    public List<User> list() {
        return new ArrayList<User>(users.values());
    }

    public User editable(String username) {
        return users.get(username);
    }

    public void update(User user) {
        users.put(user.getUsername(), user);
    }

    public void editNew() {
        System.out.println("editNew");
    }

    public void create(User user) {
        users.put(user.getUsername(), user);
    }
    public User removable(String username) {
        return users.get(username);
    }

    public void remove(String username) {
        users.remove(username);
    }

    public static DataBinder<UserResource> createDataBinder() {
        return new DataBinder<UserResource>() {

            @Override
            public Object[] bind(Context context, Method method) {

                if ("validate".equals(method.getName())) {
                    String username = context.getParameter("username");
                    String password = context.getParameter("password");
                    return new Object[] { username, password };
                }

                return null;
            }

        };
    }

}

package it.trace.nebula.rest.sample.resources.admin;

import it.trace.nebula.rest.binder.Context;
import it.trace.nebula.rest.binder.DataBinder;
import it.trace.nebula.rest.sample.entity.Book;
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
        Book book;

        user = new User();
        user.setUsername("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setSex('男');
        user.setEmail("admin@g.cn");
        users.put(user.getUsername(), user);
        book = new Book();
        book.setId(1);
        book.setName("Think in Java");
        book.setDescription("讲JAVA的");
        book.setType(1);
        book.setPublisher("上海教育出版社");
        book.setPrice(66.6F);
        user.setBook(book);

        user = new User();
        user.setUsername("user");
        user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setSex('男');
        user.setEmail("admin@g.cn");
        users.put(user.getUsername(), user);
        book = new Book();
        book.setId(2);
        book.setName("Think in C++");
        book.setDescription("讲C++的");
        book.setType(1);
        book.setPublisher("上海外文出版社");
        book.setPrice(55.5F);
        user.setBook(book);

        user = new User();
        user.setUsername("tom");
        user.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setSex('男');
        user.setEmail("admin@g.cn");
        users.put(user.getUsername(), user);
        book = new Book();
        book.setId(3);
        book.setName("时尚快照");
        book.setDescription("讲时尚的");
        book.setType(2);
        book.setPublisher("上海文艺出版社");
        book.setPrice(44.4F);
        user.setBook(book);

        user = new User();
        user.setUsername("cat");
        user.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setSex('男');
        user.setEmail("admin@g.cn");
        users.put(user.getUsername(), user);
        book = new Book();
        book.setId(4);
        book.setName("中国地图");
        book.setDescription("讲地图的");
        book.setType(3);
        book.setPublisher("上海地理出版社");
        book.setPrice(33.3F);
        user.setBook(book);

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

    public boolean userExist(String username) {
        return users.containsKey(username);
    }

    public List<User> list() {
        return new ArrayList<User>(users.values());
    }

    public User view(String username) {
        return users.get(username);
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
                } else if ("userExist".equals(method.getName())) {
                    String username = context.getParameter("username");
                    return new Object[] { username };
                } else if ("view".equals(method.getName())) {
                    String username = context.getId();
                    return new Object[] { username };
                } else if ("remove".equals(method.getName())) {
                    String username = context.getId();
                    return new Object[] { username };
                } else if ("create".equals(method.getName())) {
                    User user = new User();
                    user.setUsername(context.getParameter("username", String.class));
                    user.setPassword(context.getParameter("password", String.class));
                    user.setFirstName(context.getParameter("firstName", String.class));
                    user.setLastName(context.getParameter("lastName", String.class));
                    user.setSex(context.getParameter("sex", String.class).charAt(0));
                    user.setEmail(context.getParameter("email", String.class));
                    return new Object[] { user };
                } else if ("update".equals(method.getName())) {
                    User user = new User();
                    user.setUsername(context.getId());

                    //                    user.setPassword(context.getParameter("password", String.class));
                    user.setFirstName(context.getParameter("firstName", String.class));
                    user.setLastName(context.getParameter("lastName", String.class));
                    user.setSex(context.getParameter("sex", String.class).charAt(0));
                    user.setEmail(context.getParameter("email", String.class));
                    return new Object[] { user };
                }

                return null;
            }

        };
    }

}

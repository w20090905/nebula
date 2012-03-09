package test.nest.run;

import test.nest.def.List;
import test.nest.def.ListImp;

public class TestList {

    public static class Person {
        String name;
        String sex;
        int height;
        int weight;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Person> users = new ListImp<Person>();

        users.foreach("name");
        // users[age>13]
        users.pick("age>13").get(0);

    }

}

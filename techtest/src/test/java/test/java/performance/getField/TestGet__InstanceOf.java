package test.java.performance.getField;

public class TestGet__InstanceOf implements Runable {

    long max;
    String name,test;

    Person p = new Person();

    @Override
    public void setup() throws Exception {
        p.setName("name");
        p.setSex("sex");
        max = 1000 * 1000 * 10;
    }

    @SuppressWarnings("unused")
	@Override
    public long run() throws Exception {
        boolean is =false;
        for (int i = 0; i < max; i++) {
            // 1
            is = p.name instanceof String;
            is = p.sex instanceof String;
            is = p.name instanceof String;
            is = p.sex instanceof String;
            is = p.name instanceof String;
            is = p.sex instanceof String;
            is = p.name instanceof String;
            is = p.sex instanceof String;
            is = p.name instanceof String;
            is = p.sex instanceof String;
        }

        return max * 10;
    }

    @Override
    public void tearDown() throws Exception {
        // TODO Auto-generated method stub

    }

    public static class Person {
        String name;
        String sex;
        String sex1;
        String sex2;
        String sex3;
        String sex4;
        String sex5;
        String sex6;
        String sex7;
        String sex8;
        String sex9;
        String sex11;
        String sex21;
        String sex31;
        String sex41;
        String sex51;
        String sex61;
        String sex71;
        String sex81;
        String sex91;

        public String getSex1() {
            return sex1;
        }

        public void setSex1(String sex1) {
            this.sex1 = sex1;
        }

        public String getSex2() {
            return sex2;
        }

        public void setSex2(String sex2) {
            this.sex2 = sex2;
        }

        public String getSex3() {
            return sex3;
        }

        public void setSex3(String sex3) {
            this.sex3 = sex3;
        }

        public String getSex4() {
            return sex4;
        }

        public void setSex4(String sex4) {
            this.sex4 = sex4;
        }

        public String getSex5() {
            return sex5;
        }

        public void setSex5(String sex5) {
            this.sex5 = sex5;
        }

        public String getSex6() {
            return sex6;
        }

        public void setSex6(String sex6) {
            this.sex6 = sex6;
        }

        public String getSex7() {
            return sex7;
        }

        public void setSex7(String sex7) {
            this.sex7 = sex7;
        }

        public String getSex8() {
            return sex8;
        }

        public void setSex8(String sex8) {
            this.sex8 = sex8;
        }

        public String getSex9() {
            return sex9;
        }

        public void setSex9(String sex9) {
            this.sex9 = sex9;
        }

        public String getSex11() {
            return sex11;
        }

        public void setSex11(String sex11) {
            this.sex11 = sex11;
        }

        public String getSex21() {
            return sex21;
        }

        public void setSex21(String sex21) {
            this.sex21 = sex21;
        }

        public String getSex31() {
            return sex31;
        }

        public void setSex31(String sex31) {
            this.sex31 = sex31;
        }

        public String getSex41() {
            return sex41;
        }

        public void setSex41(String sex41) {
            this.sex41 = sex41;
        }

        public String getSex51() {
            return sex51;
        }

        public void setSex51(String sex51) {
            this.sex51 = sex51;
        }

        public String getSex61() {
            return sex61;
        }

        public void setSex61(String sex61) {
            this.sex61 = sex61;
        }

        public String getSex71() {
            return sex71;
        }

        public void setSex71(String sex71) {
            this.sex71 = sex71;
        }

        public String getSex81() {
            return sex81;
        }

        public void setSex81(String sex81) {
            this.sex81 = sex81;
        }

        public String getSex91() {
            return sex91;
        }

        public void setSex91(String sex91) {
            this.sex91 = sex91;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

}

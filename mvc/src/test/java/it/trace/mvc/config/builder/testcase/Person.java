package it.trace.mvc.config.builder.testcase;

public class Person {

    @SuppressWarnings("unused")
    private String privateMethod() {
        return null;
    }

    protected String protectedMethod() {
        return null;
    }

    String friendlyMethod() {
        return null;
    }

    public String error() {
        return null;
    }

    public String haveParameter(String a) {
        return null;
    }

    public String getStartWithGet() {
        return null;
    }

    public int notReturnString() {
        return 0;
    }

    public void returnVoid() {
    }

}
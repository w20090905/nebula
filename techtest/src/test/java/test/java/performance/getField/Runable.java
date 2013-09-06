package test.java.performance.getField;

public interface Runable {
    void setup() throws Exception;
    long run() throws Exception;
    void tearDown() throws Exception;
}

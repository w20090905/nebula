package test.java.performance;

public interface Runable {
    void setup() throws Exception;
    long run() throws Exception;
    void tearDown() throws Exception;
}

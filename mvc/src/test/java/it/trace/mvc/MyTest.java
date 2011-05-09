package it.trace.mvc;

import org.junit.Test;

public class MyTest {

    @Test
    public void classLoader() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        Thread.currentThread().getContextClassLoader().getResource("");
    }
}

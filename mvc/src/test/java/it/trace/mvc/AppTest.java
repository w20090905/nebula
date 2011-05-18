package it.trace.mvc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest {

    @BeforeClass
    public static void runBeforeClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void runAfterClass() {
        System.out.println("runAfterClass");
    }

    @Before
    public void runBefore() {
        System.out.println("Befor");
    }

    @After
    public void runAfter() {
        System.out.println("After");
    }

    @Test
    public void normal() {
        Assert.assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void exception() {
        throw new RuntimeException();
    }

    @Test(timeout = 100)
    public void timeout() throws InterruptedException {
        Thread.sleep(50);
    }

    @Ignore("Not Ready to Run")
    @Test
    public void ignore() {
        Assert.fail("Run Ignore");
    }
}

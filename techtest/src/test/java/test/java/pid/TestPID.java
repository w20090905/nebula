package test.java.pid;

import java.lang.management.ManagementFactory;

import junit.framework.TestCase;

public class TestPID extends TestCase {
	public void testPID() {
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
	}
	public static void main(String[] args) {
		try {
			System.out.println(ManagementFactory.getRuntimeMXBean().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
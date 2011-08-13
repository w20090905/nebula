package test.thread;

import java.util.ArrayList;
import java.util.Random;

public class TestThread {
    static long nanoTimeStart;

    static class Sender implements Runnable {
        Holder holder;
        int index;

        Sender(Holder holder, int index) {
            this.holder = holder;
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println(index + " + Thread Sender  START -- at  " + (System.nanoTime() - nanoTimeStart));
            try {
                Request req = new Request(index);
                Response res = holder.request(req);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(index + " + Thread Sender  STOP  -- at  " + (System.nanoTime() - nanoTimeStart));
        }

    }

    static class Holder implements Runnable {
        ArrayList<Request> dd = new ArrayList<Request>();

        Response request(Request req) throws InterruptedException {
            synchronized (req) {
                synchronized (dd) {
                    dd.add(req);
                }
                req.wait();
            }
            return new Response();
        }

        @Override
        public void run() {
            System.out.println("Thread Holder  START -- at  " + (System.nanoTime() - nanoTimeStart));

            try {
                for (;;) {
                    Random r = new Random(1);
                    synchronized (dd) {
                        Thread.sleep(10);
                        if (dd.size() > 0) {
                            int i = r.nextInt(dd.size());
                            Request req = dd.get(i);
                            dd.remove(i);
                            Thread.sleep(r.nextInt(100));
                            synchronized (req) {
                                req.notify();
                            }
                            Thread.sleep(100);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Holder  STOP  -- at  " + (System.nanoTime() - nanoTimeStart));

        }
    }

    static class Request {
        int index;

        public Request(int index) {
            this.index = index;
        }
    }

    static class Response {

    }

    public static void main(String[] args) {
        Holder holder = new Holder();
        Thread tH = new Thread(holder);
        tH.start();
        Random r = new Random(1);
        nanoTimeStart = System.nanoTime();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(r.nextInt(1));
                Thread tS = new Thread(new Sender(holder, i));
                tS.start();
            }
        } catch (InterruptedException e) {
        }

    }

}

package multithreading;

import java.util.concurrent.CountDownLatch;

import static multithreading.Utils.me;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);

        Thread worker = new Worker(latch);
        Thread counter = new Counter(latch, 2);

        worker.start();
        counter.start();
    }

    static class Worker extends Thread {
        private CountDownLatch latch;

        Worker(final CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            me("Worker");

            try {
                latch.await();
            } catch (InterruptedException e) {
            }

            System.out.println(me() + "lock acquired");
        }
    }

    static class Counter extends Thread {
        private CountDownLatch latch;
        private int eventCount;

        Counter(final CountDownLatch latch, final int eventCount) {
            this.latch = latch;
            this.eventCount = eventCount;
        }

        @Override
        public void run() {
            me("Counter");

            for (int i = 0; i < eventCount; i++) {
                Utils.sleep(Utils.random());
                latch.countDown();
            }
        }
    }
}

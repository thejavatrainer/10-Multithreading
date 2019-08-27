package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static multithreading.Utils.me;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Thread[] threads = new Thread[] {
                new Worker(barrier),
                new Worker(barrier),
                new Worker(barrier)
        };

        for (Thread thread : threads) {
            thread.start();
        }

    }

    static class Worker extends Thread {
        private CyclicBarrier barrier;

        Worker(final CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(me() + "working");
            Utils.sleep(Utils.random());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(me() + "continue working");
        }
    }
}

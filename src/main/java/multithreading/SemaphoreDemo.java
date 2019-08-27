package multithreading;

import java.util.concurrent.Semaphore;

import static multithreading.Utils.me;
import static multithreading.Utils.random;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        Thread[] threads = new Thread[] {
                new Worker(semaphore),
                new Worker(semaphore),
                new Worker(semaphore),
                new Worker(semaphore)
        };

        for (Thread thread : threads) {
            thread.start();
        }
    }

    static class Worker extends Thread {
        private Semaphore semaphore;

        Worker(final Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(me() + "acquired lock");

                // do important stuff
                Utils.sleep(random());
                System.out.println(me() + "done with important stuff");

                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

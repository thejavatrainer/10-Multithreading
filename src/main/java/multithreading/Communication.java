package multithreading;

import static multithreading.Utils.me;

public class Communication {
    static class Queue {
        private int value;
        private boolean newValueAvailable;

        synchronized void put(int value) {
            while (newValueAvailable) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // don't care
                }
            }

            this.value = value;
            this.newValueAvailable = true;
            notify();

            System.out.println(me() + "put " + value);
        }

        synchronized int get() {
            while (!newValueAvailable) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // don't care
                }
            }

            int readValue = value;
            newValueAvailable = false;
            notify();
            System.out.println(me() + "got " + readValue);

            return readValue;
        }
    }

    static class Producer implements Runnable {
        private Queue queue;

        Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int i = 0;

            while (true) {
                queue.put(i++);
            }
        }
    }

    static class Consumer implements Runnable {
        private Queue queue;

        Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                queue.get();
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        new Thread(new Producer(queue), "Producer").start();
        new Thread(new Consumer(queue), "Consumer").start();
    }
}

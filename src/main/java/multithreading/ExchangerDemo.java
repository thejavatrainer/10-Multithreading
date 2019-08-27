package multithreading;

import java.util.concurrent.Exchanger;

import static multithreading.Utils.me;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Consumer(exchanger).start();
        new Producer(exchanger).start();
    }

    static class Producer extends Thread {
        private Exchanger<String> exchanger;

        Producer(final Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            me("Producer");

            try {
                Utils.sleep(Utils.random());
                String message = "Hello world";
                System.out.println(me() + "sending " + message);
                exchanger.exchange(message);
            } catch (InterruptedException e) {
                //
            }
        }
    }

    static class Consumer extends Thread {
        private Exchanger<String> exchanger;

        Consumer(final Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            me("Consumer");

            try {
                String message = exchanger.exchange("");
                System.out.println(me() + "received " + message);
            } catch (InterruptedException e) {
                //
            }
        }
    }
}

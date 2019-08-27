package multithreading;

import java.util.concurrent.Phaser;

import static multithreading.Utils.me;
import static multithreading.Utils.random;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        new Thread(new Worker(phaser)).start();
        new Thread(new Worker(phaser)).start();
        new Thread(new Worker(phaser)).start();
    }

    static class Worker extends Thread {
        private Phaser phaser;

        Worker(final Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {
            System.out.println(me() + "Starting phase 1");
            phaser.arriveAndAwaitAdvance();
            Utils.sleep(random());

            System.out.println(me() + "Starting phase 2");
            phaser.arriveAndAwaitAdvance();
            Utils.sleep(random());

            System.out.println(me() + "Starting phase 3");
            phaser.arriveAndDeregister();
            Utils.sleep(random());
        }
    }
}

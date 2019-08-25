package multithreading;

import static multithreading.Utils.me;
import static multithreading.Utils.sleep;

public class Worker implements Runnable {
    private boolean run = true;

    void stop() {
        run = false;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(me() + "running");
            sleep(500);
        }

        System.out.println(me() + "stopping");
    }
}

package multithreading;

import static multithreading.Utils.me;
import static multithreading.Utils.random;

public class WorkerStopper extends Thread {
    private Worker worker;

    WorkerStopper(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        int sleepTime = random();
        System.out.println(me() + "waiting " + sleepTime + " millis to stop worker");
        Utils.sleep(sleepTime);

        worker.stop();
    }
}

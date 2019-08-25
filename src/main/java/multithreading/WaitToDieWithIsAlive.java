package multithreading;

import static multithreading.Utils.me;
import static multithreading.Utils.sleep;

public class WaitToDieWithIsAlive {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();

        new WorkerStopper(worker).start();

        while (workerThread.isAlive()) {
            System.out.println(me() + workerThread.getName() + " still running");
            sleep(200);
        }
    }
}

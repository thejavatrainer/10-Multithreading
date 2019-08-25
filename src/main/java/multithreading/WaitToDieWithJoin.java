package multithreading;

import static multithreading.Utils.me;

public class WaitToDieWithJoin {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();

        new WorkerStopper(worker).start();

        workerThread.join();

        System.out.println(me() + workerThread.getName() + " stopped");
    }
}

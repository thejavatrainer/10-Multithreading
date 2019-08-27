package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService executor;
//        executor = Executors.newFixedThreadPool(5);
        executor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 0; i < 150; i++) {
            executor.submit(() -> {
                System.out.println(Utils.me() + "working");
                Utils.sleep(Utils.random());
                System.out.println(Utils.me() + "done working");
            });
        }

//        executor.shutdown();
//        try {
//            executor.awaitTermination(1, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

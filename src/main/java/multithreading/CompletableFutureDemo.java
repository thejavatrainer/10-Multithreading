package multithreading;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture
                .supplyAsync(() -> 1)
                .thenComposeAsync(x -> CompletableFuture.supplyAsync(() -> x + 1))
                .thenAccept(System.out::println)
                .get();
    }
}

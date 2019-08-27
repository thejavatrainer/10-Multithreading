package multithreading;

import java.time.Instant;
import java.util.Random;

final class Utils {
    private Utils() {
    }

    static String me() {
        return Instant.now() + " " + Thread.currentThread().getName() + ": ";
    }

    static void me(String name) {
        Thread.currentThread().setName(name);
    }

    static void sleep(long millis) {
        try {
            System.out.println(me() + "sleeping for " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // don't care
        }
    }

    static int random() {
        return new Random().nextInt((5000 - 200) + 1) + 200;
    }
}

package multithreading;

import java.util.Random;

final class Utils {
    private Utils() {
    }

    static String me() {
        return Thread.currentThread().getName() + ": ";
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // don't care
        }
    }

    static int random() {
        return new Random().nextInt((5000 - 200) + 1) + 200;
    }
}

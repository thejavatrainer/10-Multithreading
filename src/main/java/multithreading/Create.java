package multithreading;

public class Create {
    static class MyThred extends Thread {
        @Override
        public void run() {
            // do some work
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // do some work
        }
    }

    public static void main(String[] args) {
        Thread myThread = new MyThred();
        Thread myRunnable = new Thread(new MyRunnable());
        Thread lambda = new Thread(() -> {
            // do some work
            });
        }
}

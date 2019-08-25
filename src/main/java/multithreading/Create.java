package multithreading;

public class Create {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });

        Thread theThread = new Thread(() -> System.out.println("Thread"));
        theThread.start();
    }
}

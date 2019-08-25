package multithreading;

import static multithreading.Utils.me;

public class Synchronization {
    static class ValueHolder {
        private int value = 0;

        void add(int value) {
            System.out.println("----" + me() + "Add value: " + value);
            this.value = this.value + value;
        }

        int getValue() {
            return value;
        }
    }

    private void execute() throws InterruptedException {
        ValueHolder value = new ValueHolder();

        Thread thread1 = new Thread(() -> {
            int val = value.getValue() + 3;
            value.add(val);
        }, "Thread1");
        Thread thread2 = new Thread(() -> {
            int val = value.getValue() * 2;
            value.add(val);
        }, "Thread2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("----Value is: " + value.getValue());
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronization sync = new Synchronization();

        for (int i = 0; i < 15; i++) {
            System.out.println("Iteration " + i);
            sync.execute();
        }
    }
}

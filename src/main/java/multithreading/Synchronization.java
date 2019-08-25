package multithreading;

public class Synchronization {
    static class ValueHolder {
        private int value = 0;

        void add(int value) {
            System.out.println("----Add value: " + value);
            this.value = this.value + value;
        }

        int getValue() {
            return value;
        }
    }

    private void execute() throws InterruptedException {
        ValueHolder value = new ValueHolder();

        Thread thread1 = new Thread(() -> value.add(value.getValue() + 3));
        Thread thread2 = new Thread(() -> value.add(value.getValue() * 2));

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

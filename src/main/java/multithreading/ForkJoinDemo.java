package multithreading;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinDemo {
    private static final int size = 10000;
    private static final int[] data = prepareData(size);

    public static void main(String[] args) throws Exception {
        ForkJoinPool executor = new ForkJoinPool();
        ForkJoinTask<Long> task = executor.submit(new SumTask(data));
        System.out.println("Fork/joined sum: " + task.get());

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static int[] prepareData(int size) {
        long sum = 0;
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = Math.abs(random.nextInt());
            sum += array[i];
        }

        System.out.println("Expected sum: " + sum);

        return array;
    }

    static class SumTask extends RecursiveTask<Long> {
        private int[] array;

        SumTask(int[] array) {
            this.array = array;
        }

        @Override
        protected Long compute() {
            if (array.length > 10) {
                return ForkJoinTask.invokeAll(createSubTasks())
                        .stream()
                        .map(ForkJoinTask::join)
                        .reduce(0L, (x, y) -> x + y);
            } else {
                return sum(array);
            }
        }

        private Collection<SumTask> createSubTasks() {
            return Arrays.asList(
                    new SumTask(Arrays.copyOfRange(array, 0, array.length / 2)),
                    new SumTask(Arrays.copyOfRange(array, array.length / 2, array.length)));
        }

        private Long sum(int[] array) {
            return Arrays.stream(array).mapToLong(x -> x).sum();
        }
    }
}

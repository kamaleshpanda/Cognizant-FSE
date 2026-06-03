import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class Ex41_ExecutorServiceCallable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<String>> results = new ArrayList<>();

        // submit 5 callable tasks
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            Callable<String> task = () -> {
                Thread.sleep(1000);
                return "Task " + taskId + " completed by " + Thread.currentThread().getName();
            };
            results.add(executor.submit(task));
        }

        // collect results
        for (Future<String> future : results) {
            System.out.println(future.get());
        }

        executor.shutdown();
        System.out.println("All tasks finished!");
    }
}

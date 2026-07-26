public class Ex40_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting 100,000 virtual threads...");

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[100000];
        for (int i = 0; i < 100000; i++) {
            int threadNum = i;
            threads[i] = Thread.startVirtualThread(() -> {
                if (threadNum % 10000 == 0) {
                    System.out.println("Virtual thread #" + threadNum + " running");
                }
            });
        }

        // wait for all to finish
        for (Thread t : threads) {
            t.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("All done! Time taken: " + (end - start) + " ms");
    }
}

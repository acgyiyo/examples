package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * se usa un ExecutorService para generar un pool de hilos
 */
public class SimpleExecutorService {
    String[] inFiles = {"\\resources\\file1.txt", "\\resources\\file2.txt", "\\resources\\file3.txt", "\\resources\\file4.txt", "\\resources\\file5.txt", "\\resources\\file6.txt"};

    public void initExecutor() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < inFiles.length; i++) {
            SimpleRunnableReader sr = new SimpleRunnableReader(inFiles[i]);
            es.submit(sr);
        }

        try {
            //empieza a cerrar los hilos en el orden que fueron submiteados y no acepta nuevos
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error in executorService-->" + e);
        }
    }
}

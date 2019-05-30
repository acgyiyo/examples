package concurrency;

import java.util.concurrent.*;

/**
 * se usa un ExecutorService para generar un pool de hilos
 */
public class ExecutorWithFuture {
    String[] inFiles = {"\\resources\\file1.txt", "\\resources\\file2.txt", "\\resources\\file3.txt", "\\resources\\file4.txt", "\\resources\\file5.txt", "\\resources\\file6.txt"};

    public void initExecutor() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];
        for (int i = 0; i < inFiles.length; i++) {
            SimpleCallableReader sr = new SimpleCallableReader(inFiles[i]);
            //se obtienen los futures en el array para luego usar sus datos
            results[i] = es.submit(sr);
        }

        int totalLines = 0;
        for (Future<Integer> result : results) {
            try {
                totalLines += result.get();
            } catch (ExecutionException | InterruptedException ee) {
                System.err.println("Error al obtener el recurso del futuro-->>" + ee);
            }
        }
        System.out.println("el total de lineas leidas fue: " + totalLines);

        try {
            //empieza a cerrar los hilos en el orden que fueron submiteados y no acepta nuevos
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error in executorService-->" + e);
        }
    }
}

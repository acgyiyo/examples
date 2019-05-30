package concurrency;

/**
 * ejemplo de un pool de hilos simple con un array
 */
public class SimpleThreadPool {
    String[] inFiles = {"\\resources\\file1.txt","\\resources\\file2.txt","\\resources\\file3.txt","\\resources\\file4.txt","\\resources\\file5.txt","\\resources\\file6.txt"};

    Thread[] threadPool = new Thread[inFiles.length];
    public void initPool() {
        try {
            for (int i = 0; i < inFiles.length; i++) {
                SimpleRunnableReader sr = new SimpleRunnableReader(inFiles[i]);
                threadPool[i] = new Thread(sr);
                threadPool[i].start();
            }

            //esperamos a que todos los hilos terminen antes de finalizar el hilo principal
            for(Thread thread:threadPool){
                thread.join();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

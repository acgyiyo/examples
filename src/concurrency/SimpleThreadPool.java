package concurrency;

import java.io.IOException;

public class SimpleThreadPool {
    String[] inFiles = {"\\resources\\file1.txt","\\resources\\file2.txt","\\resources\\file3.txt","\\resources\\file4.txt","\\resources\\file5.txt","\\resources\\file6.txt"};

    String[] outFiles={};

    public void initPool() {
        try {
            for (int i = 0; i < inFiles.length; i++) {
                SimpleReader sr = new SimpleReader(inFiles[i]);
                Thread thread = new Thread(sr);
                thread.start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

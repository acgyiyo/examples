package concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * se crea un simple clase para ejecutar los hilos de manera asíncrona
 */
public class SimpleRunnableReader implements Runnable {

    private String inFile;

    public SimpleRunnableReader(String inFile){
        this.inFile=inFile;
    }

    public void doRead() throws IOException {
        String line=null;
        int total=0;
        String path = System.getProperty("user.dir") + inFile;
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        while((line=reader.readLine())!=null){
            total+=line.length();
        }
        System.out.println("lineas leidas en archivo: "+inFile+" :: "+total);
    }

    public void run(){
        try {
            doRead();
        }catch (IOException iex){
            System.err.println("Error al iniciar Simple Reader--->"+iex);
        }
    }
}

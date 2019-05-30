package concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/**
 * se crea un una clase de tipo callable para poder realizar retorno de datos en "Futuros"
 */
public class SimpleCallableReader implements Callable<Integer> {

    private String inFile;

    public SimpleCallableReader(String inFile){
        this.inFile=inFile;
    }

    public int doRead() throws IOException {
        String line=null;
        int total=0;
        String path = System.getProperty("user.dir") + inFile;
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        while((line=reader.readLine())!=null){
            total+=line.length();
        }
        System.out.println("lineas leidas en archivo: "+inFile+" :: "+total);

        return total;
    }

    public Integer call() throws IOException{
        return doRead();
    }
}

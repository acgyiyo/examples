package concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleReader implements Runnable {

    private String inFile;

    public SimpleReader(String inFile){
        this.inFile=inFile;
    }

    public void doRead() throws IOException {
        String line=null;
        int total=0;
        BufferedReader reader = Files.newBufferedReader(Paths.get( System.getProperty("user.dir")+ inFile));
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

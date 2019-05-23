import concurrency.SimpleThreadPool;
import nested.Vuelo;

/**
 * @author acgyiyo
 */
public class Pruebas {

    public static void main(String[] args) {
        testSimpleThread();
    }

    public static void testSimpleThread(){
        SimpleThreadPool stp = new SimpleThreadPool();
        stp.initPool();
    }
}

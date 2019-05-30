package concurrency.synchronization;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * con este ejemplo vemos que puede pasar si se usan varios hilos y no se tienen la precaución de tenerlos
 * sincronizados
 */
public class Bank {

    /**
     * con este metodo probamos la sincronización de datos automatica por el 'synchronized'
     */
    public static void processBankAccounts() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        ///si es true el proceso se realiza sincronizado de lo contrario suceden cosas muy raras
        boolean synchro = true;

        for (int i = 0; i < 3; i++) {
            Worker worker = new Worker(account, 'd', synchro, 400);
            Worker worker2 = new Worker(account, 'w', synchro, 100);
            es.submit(worker);
            es.submit(worker2);
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error in bank-->" + e);
        }

        System.out.println("el monto actual es: " + account.getBalance());
    }

    /**
     * aca probamos que podemos no tener metodos 'synchronized' pero aún podemos controlar la sincronización de manera
     * manaul dentro de un bloque sincronizado
     */
    public static void processSynchroBlockBankAccount() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        BankAccount account = new BankAccount(100);
        ///si es true el proceso se realiza sincronizado de lo contrario suceden cosas muy raras
        boolean synchro = true;

        //con esta variable podemos probar la diferencia de tener un código sincronizado manual y uno automatico y ver las
        //fallas de la sincro automatica que se pueden controlar en un bloque sincro manualmente
        boolean block = true;

        Worker[] workers = {new PromoWorker(account, 'd', false, 400, block),
                new PromoWorker(account, 'w', false, 100, block)};

        if (block) {
            for (Worker work : workers) {
                es.submit(work);
            }
//            for (int i = 0; i < 3; i++) {
//                Worker worker = new PromoWorker(account, 'd', false, 400, block);
//                Worker worker2 = new PromoWorker(account, 'w', false, 100, block);
//                es.submit(worker2);
//                es.submit(worker);
//            }
        } else {
            for (int i = 0; i < 3; i++) {
                Worker worker = new PromoWorker(account, 'd', synchro, 400, false);
                Worker worker2 = new PromoWorker(account, 'w', synchro, 100, false);
                es.submit(worker);
                es.submit(worker2);
            }
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error in bank-->" + e);
        }

        System.out.println("el monto actual es: " + account.getBalance());
    }
}

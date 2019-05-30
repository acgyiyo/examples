import concurrency.ExecutorWithFuture;
import concurrency.SimpleExecutorService;
import concurrency.SimpleThreadPool;
import concurrency.synchronization.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author acgyiyo
 */

class Pruebas2 extends Pruebas {
}

public class Pruebas {

    public static void main(String[] args) {
//        simpleThreadExample();
//        simpleExecutorServiceExample();
//     executorWithFuture();
//        synchronizedExecutor();
//        synchronizedBlockExecutor();
        testStream();
    }

    public static void testStream() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(80, "Samuel"));
        people.add(new Person(30, "Karla"));
        people.add(new Person(80, "Sebastian"));
        people.add(new Person(10, "Ximena"));

        Map<Integer, List<Person>> byAge = people.stream()
                .collect(Collectors.groupingBy(a -> a.getAge()));

        System.out.println(byAge);
    }

    public static void synchronizedExecutor() {
        Bank.processBankAccounts();
    }

    public static void synchronizedBlockExecutor() {
        Bank.processSynchroBlockBankAccount();
    }

    public static void executorWithFuture() {
        ExecutorWithFuture ef = new ExecutorWithFuture();
        ef.initExecutor();
        Integer d;
        final Class<?> person;
        final Class<? extends Object> person2;

    }

    public <T extends Object> void metodo() {

    }

    public <T extends Object> void metodo2() {

    }

    public static void simpleThreadExample() {
        SimpleThreadPool stp = new SimpleThreadPool();
        stp.initPool();
    }

    public static void simpleExecutorServiceExample() {
        SimpleExecutorService ses = new SimpleExecutorService();
        ses.initExecutor();
    }
}

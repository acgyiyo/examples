package lambdas;

import Objects.Person;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaExamples {

    /**
     * differences between method reference and simple lambda
     */
    public static void methodReference() {
        //simple(S)
        BinaryOperator<Integer> sumS = (i1, i2) -> Integer.sum(i1, i2);
        Consumer<String> printerS = s -> System.out.println(s);

        //method reference(MR)
        BinaryOperator<Integer> sumMR = Integer::sum;
        Consumer<String> printerMR = System.out::println;
    }

    public static void comparatorExample() {
        //age compator simple
        Comparator<Person> compAge = (p1, p2) -> p1.getAge() - p2.getAge();

        //name compator simpe
        Comparator<Person> compName = (p1, p2) -> p1.getName().compareTo(p2.getName());

        //name compator With comparator class method reference
        Comparator<Person> compName2 = Comparator.comparing(Person::getName);

        //we can concatenate comparators to get a more powerful comparator
        Comparator<Person> compAgeName = compAge.thenComparing(compName);

        Person p = new Person();
        p.initExampleList();
        System.out.println(p.getPeople().stream().max(compAgeName).get());
    }

    public static void predicateExample() {
        Predicate<Person> p1 = p -> p.getAge() > 50;

        Predicate<Person> p2 = p -> p.getName().length() > 5;

        Person p = new Person();
        p.initExampleList();

        p.getPeople().stream().filter(p1).forEach(System.out::println);
        System.out.println("_____________");
        p.getPeople().stream().filter(p1.and(p2)).forEach(System.out::println);
        System.out.println("_____________");
        p.getPeople().stream().filter(p1.or(p2)).forEach(System.out::println);

    }
}

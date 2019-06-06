package Objects;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int age;
    private String name;
    private List<Person> people;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void initExampleList() {
        List<Person> p = new ArrayList<>();
        p.add(new Person(80, "Samuel"));
        p.add(new Person(30, "Karla"));
        p.add(new Person(80, "Angel"));
        p.add(new Person(10, "Ximena"));
        this.people = p;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "{name: " + this.name + "," + " age: " + this.age + "}";
    }
}

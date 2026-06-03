import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// Record definition (Java 16+)
record Person(String name, int age) {}

public class Ex29_Records {
    public static void main(String[] args) {
        Person p1 = new Person("Ravi", 22);
        Person p2 = new Person("Sneha", 17);
        Person p3 = new Person("Amit", 25);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        List<Person> people = Arrays.asList(p1, p2, p3);

        // filter people who are 18 or older
        List<Person> adults = people.stream()
                .filter(p -> p.age() >= 18)
                .collect(Collectors.toList());

        System.out.println("\nAdults only:");
        for (Person p : adults) {
            System.out.println(p.name() + " - " + p.age());
        }
    }
}

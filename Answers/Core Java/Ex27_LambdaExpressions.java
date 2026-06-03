import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");
        names.add("David");

        System.out.println("Before sorting: " + names);

        // sorting using lambda
        Collections.sort(names, (a, b) -> a.compareTo(b));

        System.out.println("After sorting: " + names);
    }
}

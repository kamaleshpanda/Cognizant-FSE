import java.util.HashMap;
import java.util.Scanner;

public class Ex25_HashMapExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> studentMap = new HashMap<>();

        // adding some entries
        studentMap.put(101, "Ravi");
        studentMap.put(102, "Priya");
        studentMap.put(103, "Amit");

        System.out.println("Student Map: " + studentMap);

        System.out.print("\nEnter a student ID to search: ");
        int id = sc.nextInt();

        if (studentMap.containsKey(id)) {
            System.out.println("Student found: " + studentMap.get(id));
        } else {
            System.out.println("No student found with ID " + id);
        }
        sc.close();
    }
}

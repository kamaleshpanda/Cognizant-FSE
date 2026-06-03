import java.util.Scanner;

public class Ex15_StringReversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        StringBuilder sb = new StringBuilder(input);
        String reversed = sb.reverse().toString();

        System.out.println("Reversed: " + reversed);
        sc.close();
    }
}

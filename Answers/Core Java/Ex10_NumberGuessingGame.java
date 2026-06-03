import java.util.Scanner;
import java.util.Random;

public class Ex10_NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int secretNumber = rand.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("I'm thinking of a number between 1 and 100. Try to guess it!");

        while (guess != secretNumber) {
            System.out.print("Your guess: ");
            guess = sc.nextInt();
            attempts++;

            if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Correct! You got it in " + attempts + " attempts!");
            }
        }
        sc.close();
    }
}

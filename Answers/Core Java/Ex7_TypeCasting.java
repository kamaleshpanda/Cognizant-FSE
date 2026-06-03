public class Ex7_TypeCasting {
    public static void main(String[] args) {
        // double to int (narrowing)
        double myDouble = 9.78;
        int myInt = (int) myDouble;
        System.out.println("Double value: " + myDouble);
        System.out.println("After casting to int: " + myInt);

        // int to double (widening)
        int anotherInt = 25;
        double anotherDouble = anotherInt;
        System.out.println("\nInt value: " + anotherInt);
        System.out.println("After casting to double: " + anotherDouble);
    }
}

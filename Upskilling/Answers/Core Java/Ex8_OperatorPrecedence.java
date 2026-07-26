public class Ex8_OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;
        System.out.println("10 + 5 * 2 = " + result1);
        // multiplication happens first so 5*2=10 then 10+10=20

        int result2 = (10 + 5) * 2;
        System.out.println("(10 + 5) * 2 = " + result2);
        // brackets first so 10+5=15 then 15*2=30

        int result3 = 10 / 2 + 3 * 4 - 1;
        System.out.println("10 / 2 + 3 * 4 - 1 = " + result3);
        // 10/2=5, 3*4=12, then 5+12-1=16

        boolean result4 = 5 > 3 && 10 < 20;
        System.out.println("5 > 3 && 10 < 20 = " + result4);
        // both true so result is true
    }
}

public class Ex30_PatternMatchingSwitch {

    static String checkType(Object obj) {
        return switch (obj) {
            case Integer i -> "It's an integer: " + i;
            case String s -> "It's a string: " + s;
            case Double d -> "It's a double: " + d;
            case null -> "It's null";
            default -> "Unknown type";
        };
    }

    public static void main(String[] args) {
        System.out.println(checkType(42));
        System.out.println(checkType("Hello"));
        System.out.println(checkType(3.14));
        System.out.println(checkType(true));
    }
}

/*
 * Exercise 37: Using javap to Inspect Bytecode
 * 
 * Steps:
 * 1. Create a simple class (like this one)
 * 2. Compile it:    javac Ex37_JavapDemo.java
 * 3. Inspect it:    javap -c Ex37_JavapDemo
 * 
 * The javap tool shows the bytecode instructions that the JVM actually runs.
 * You'll see things like:
 *   - aload, invokevirtual, return etc.
 *   - These are low-level JVM instructions
 */

public class Ex37_JavapDemo {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Ex37_JavapDemo demo = new Ex37_JavapDemo();
        int result = demo.add(5, 3);
        System.out.println("Result: " + result);

        System.out.println("\nTo see bytecode, run in terminal:");
        System.out.println("  javac Ex37_JavapDemo.java");
        System.out.println("  javap -c Ex37_JavapDemo");
    }
}

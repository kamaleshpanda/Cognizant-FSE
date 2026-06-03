/*
 * Exercise 38: Decompile a Class File
 * 
 * Steps:
 * 1. Write a simple Java program (like this one)
 * 2. Compile it:  javac Ex38_DecompileDemo.java
 * 3. This creates Ex38_DecompileDemo.class
 * 4. Open the .class file in a decompiler tool like:
 *    - JD-GUI (download from http://java-decompiler.github.io/)
 *    - CFR (java -jar cfr.jar Ex38_DecompileDemo.class)
 * 5. The decompiler will show you Java source code from the .class file
 * 
 * This is useful for understanding what compiled code looks like
 * and for reverse engineering when source is not available.
 */

public class Ex38_DecompileDemo {
    private String message;

    public Ex38_DecompileDemo(String msg) {
        this.message = msg;
    }

    public void showMessage() {
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Ex38_DecompileDemo demo = new Ex38_DecompileDemo("Hello from decompiled code!");
        demo.showMessage();

        System.out.println("\nCompile this file, then open the .class file in JD-GUI or CFR.");
    }
}

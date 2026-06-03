/*
 * Exercise 34: Java Modules
 * 
 * This exercise is about the Java module system.
 * You need to create two modules manually with this folder structure:
 * 
 * project/
 *   com.utils/
 *     module-info.java       -> module com.utils { exports com.utils; }
 *     com/utils/
 *       StringHelper.java
 * 
 *   com.greetings/
 *     module-info.java       -> module com.greetings { requires com.utils; }
 *     com/greetings/
 *       Main.java
 * 
 * --- StringHelper.java ---
 * package com.utils;
 * public class StringHelper {
 *     public static String greet(String name) {
 *         return "Hello, " + name + "!";
 *     }
 * }
 * 
 * --- Main.java ---
 * package com.greetings;
 * import com.utils.StringHelper;
 * public class Main {
 *     public static void main(String[] args) {
 *         System.out.println(StringHelper.greet("World"));
 *     }
 * }
 * 
 * To compile:
 *   javac -d out --module-source-path . -m com.utils,com.greetings
 * 
 * To run:
 *   java --module-path out -m com.greetings/com.greetings.Main
 */

public class Ex34_ModuleExample {
    public static void main(String[] args) {
        System.out.println("See the comments above for module system setup instructions.");
        System.out.println("Java modules need a specific folder structure to work.");
    }
}

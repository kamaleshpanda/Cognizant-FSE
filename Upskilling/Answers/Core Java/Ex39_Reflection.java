import java.lang.reflect.Method;

public class Ex39_Reflection {
    public void sayHello() {
        System.out.println("Hello from reflection!");
    }

    public void sayGoodbye(String name) {
        System.out.println("Goodbye, " + name + "!");
    }

    public static void main(String[] args) throws Exception {
        // load class dynamically
        Class<?> clazz = Class.forName("Ex39_Reflection");

        // print all methods
        System.out.println("Methods in this class:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("  - " + m.getName() + " (params: " + m.getParameterCount() + ")");
        }

        // create instance and invoke method dynamically
        Object obj = clazz.getDeclaredConstructor().newInstance();

        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(obj);

        Method sayGoodbye = clazz.getMethod("sayGoodbye", String.class);
        sayGoodbye.invoke(obj, "World");
    }
}

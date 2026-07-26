# Design Patterns and Principles

## Exercise 1: Singleton Pattern

Ensure Logger class has only one single instance across the app.

```java
class Logger {
    private static Logger instance;

    // Private constructor so nobody can call new Logger()
    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}

public class Main {
    public static void main(String[] args) {
        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();
        l1.log("Testing singleton");
        System.out.println("Are both instances same? " + (l1 == l2));
    }
}
```

---

## Exercise 2: Factory Method Pattern

Creating different types of documents (Word, PDF, Excel) using factory methods.

```java
interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() { System.out.println("Opening Word doc..."); }
}

class PdfDocument implements Document {
    public void open() { System.out.println("Opening PDF doc..."); }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

public class Main {
    public static void main(String[] args) {
        DocumentFactory factory = new PdfFactory();
        Document doc = factory.createDocument();
        doc.open();
    }
}
```

---

## Exercise 3: Builder Pattern

Building a Computer object step-by-step using a nested Builder class.

```java
class Computer {
    String cpu;
    String ram;
    String storage;

    private Computer(Builder b) {
        this.cpu = b.cpu;
        this.ram = b.ram;
        this.storage = b.storage;
    }

    public static class Builder {
        String cpu;
        String ram;
        String storage;

        public Builder setCpu(String cpu) { this.cpu = cpu; return this; }
        public Builder setRam(String ram) { this.ram = ram; return this; }
        public Builder setStorage(String storage) { this.storage = storage; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Computer pc = new Computer.Builder()
            .setCpu("i7")
            .setRam("16GB")
            .setStorage("512GB SSD")
            .build();

        System.out.println("PC built with CPU: " + pc.cpu + ", RAM: " + pc.ram);
    }
}
```

---

## Exercise 4: Adapter Pattern

Connecting different payment gateways to a common interface.

```java
interface PaymentProcessor {
    void pay(double amount);
}

class PayPalGateway {
    public void makePayment(double dollars) {
        System.out.println("Paid $" + dollars + " using PayPal");
    }
}

class PayPalAdapter implements PaymentProcessor {
    PayPalGateway paypal;

    public PayPalAdapter(PayPalGateway paypal) {
        this.paypal = paypal;
    }

    public void pay(double amount) {
        paypal.makePayment(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PayPalAdapter(new PayPalGateway());
        processor.pay(100.0);
    }
}
```

---

## Exercise 5: Decorator Pattern

Dynamically adding extra features (SMS) on top of Email notification.

```java
interface Notifier {
    void send(String msg);
}

class EmailNotifier implements Notifier {
    public void send(String msg) {
        System.out.println("Email: " + msg);
    }
}

abstract class NotifierDecorator implements Notifier {
    Notifier wrapper;
    public NotifierDecorator(Notifier n) { this.wrapper = n; }
    public void send(String msg) { wrapper.send(msg); }
}

class SMSDecorator extends NotifierDecorator {
    public SMSDecorator(Notifier n) { super(n); }
    public void send(String msg) {
        super.send(msg);
        System.out.println("SMS: " + msg);
    }
}

public class Main {
    public static void main(String[] args) {
        Notifier notify = new SMSDecorator(new EmailNotifier());
        notify.send("Hello world!");
    }
}
```

---

## Exercise 6: Proxy Pattern

Lazy loading an image only when display() is called.

```java
interface Image {
    void display();
}

class RealImage implements Image {
    String filename;

    public RealImage(String filename) {
        this.filename = filename;
        System.out.println("Loading " + filename + " from server...");
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    String filename;
    RealImage realImage;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class Main {
    public static void main(String[] args) {
        Image img = new ProxyImage("test.jpg");
        img.display(); // loads and displays
        img.display(); // displays directly
    }
}
```

---

## Exercise 7: Observer Pattern

Notifying mobile app when stock price changes.

```java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double price);
}

class StockMarket {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }
    public void setPrice(double price) {
        for (Observer o : observers) {
            o.update(price);
        }
    }
}

class MobileApp implements Observer {
    public void update(double price) {
        System.out.println("Mobile app stock price alert: $" + price);
    }
}

public class Main {
    public static void main(String[] args) {
        StockMarket sm = new StockMarket();
        sm.addObserver(new MobileApp());
        sm.setPrice(150.50);
    }
}
```

---

## Exercise 8: Strategy Pattern

Selecting credit card vs paypal at runtime.

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPay implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Credit Card");
    }
}

class PayPalPay implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal");
    }
}

class PaymentContext {
    PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy s) { this.strategy = s; }
    public void pay(double amount) { strategy.pay(amount); }
}

public class Main {
    public static void main(String[] args) {
        PaymentContext ctx = new PaymentContext();
        ctx.setStrategy(new CreditCardPay());
        ctx.pay(50.0);
    }
}
```

---

## Exercise 9: Command Pattern

Turning light on/off using command objects.

```java
interface Command {
    void execute();
}

class Light {
    public void turnOn() { System.out.println("Light ON"); }
}

class LightOnCommand implements Command {
    Light light;
    public LightOnCommand(Light l) { this.light = l; }
    public void execute() { light.turnOn(); }
}

class RemoteControl {
    Command cmd;
    public void setCommand(Command c) { this.cmd = c; }
    public void pressButton() { cmd.execute(); }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();
        remote.setCommand(new LightOnCommand(light));
        remote.pressButton();
    }
}
```

---

## Exercise 10: MVC Pattern

Simple Model-View-Controller for Student.

```java
class Student {
    String name;
    int id;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class StudentView {
    public void printDetails(int id, String name) {
        System.out.println("Student ID: " + id + ", Name: " + name);
    }
}

class StudentController {
    Student model;
    StudentView view;

    public StudentController(Student m, StudentView v) {
        this.model = m;
        this.view = v;
    }

    public void updateView() {
        view.printDetails(model.id, model.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(101, "Rahul");
        StudentView v = new StudentView();
        StudentController ctrl = new StudentController(s, v);
        ctrl.updateView();
    }
}
```

---

## Exercise 11: Dependency Injection

Passing repository into service via constructor.

```java
interface CustomerRepository {
    String getCustomer(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public String getCustomer(int id) {
        return "Customer #" + id;
    }
}

class CustomerService {
    CustomerRepository repo;

    // Constructor Injection
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public void displayCustomer(int id) {
        System.out.println("Found: " + repo.getCustomer(id));
    }
}

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);
        service.displayCustomer(1);
    }
}
```

# Data Structures and Algorithms

## Exercise 1: Inventory Management System

Why data structures matter here:
If a warehouse has thousands of products, using a basic list to find items will be slow because you have to check item by item. Using a HashMap lets us find, update, or remove any product instantly by its ID in O(1) time.

### Code

```java
import java.util.HashMap;

class Product {
    String productId;
    String productName;
    int quantity;
    double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: " + productName + " (ID: " + productId + ", Qty: " + quantity + ", Price: $" + price + ")";
    }
}

public class InventoryManagement {
    HashMap<String, Product> inventory = new HashMap<>();

    // Add product
    public void addProduct(Product p) {
        inventory.put(p.productId, p);
        System.out.println("Added: " + p.productName);
    }

    // Update product
    public void updateProduct(String id, int newQty, double newPrice) {
        if (inventory.containsKey(id)) {
            Product p = inventory.get(id);
            p.quantity = newQty;
            p.price = newPrice;
            System.out.println("Updated " + id);
        } else {
            System.out.println("Product not found");
        }
    }

    // Delete product
    public void deleteProduct(String id) {
        if (inventory.containsKey(id)) {
            inventory.remove(id);
            System.out.println("Deleted " + id);
        } else {
            System.out.println("Product not found");
        }
    }

    public static void main(String[] args) {
        InventoryManagement im = new InventoryManagement();
        im.addProduct(new Product("P101", "Laptop", 10, 75000));
        im.addProduct(new Product("P102", "Mouse", 50, 500));
        im.updateProduct("P101", 15, 72000);
        im.deleteProduct("P102");
    }
}
```

### Time Complexity
- Add: O(1) average time
- Update: O(1) average time
- Delete: O(1) average time

---

## Exercise 2: E-commerce Search Function

Big O notation tells us how slow an algorithm gets when the data grows larger.
- Linear Search checks every single product from start to end (O(n) time).
- Binary Search cuts the sorted search range in half each step (O(log n) time), making it much faster for large product lists.

### Code

```java
import java.util.Arrays;
import java.util.Comparator;

class SearchProduct {
    int productId;
    String productName;
    String category;

    public SearchProduct(int id, String name, String category) {
        this.productId = id;
        this.productName = name;
        this.category = category;
    }
}

public class SearchFunction {

    // Linear Search (works on unsorted array)
    public static SearchProduct linearSearch(SearchProduct[] products, int targetId) {
        for (SearchProduct p : products) {
            if (p.productId == targetId) {
                return p;
            }
        }
        return null;
    }

    // Binary Search (needs array sorted by ID)
    public static SearchProduct binarySearch(SearchProduct[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (products[mid].productId == targetId) {
                return products[mid];
            } else if (products[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SearchProduct[] products = {
            new SearchProduct(103, "Shoes", "Fashion"),
            new SearchProduct(101, "Laptop", "Electronics"),
            new SearchProduct(102, "Phone", "Electronics")
        };

        // Linear search
        SearchProduct res1 = linearSearch(products, 101);
        System.out.println("Linear search found: " + (res1 != null ? res1.productName : "Not found"));

        // Binary search needs sorted array first
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));
        SearchProduct res2 = binarySearch(products, 101);
        System.out.println("Binary search found: " + (res2 != null ? res2.productName : "Not found"));
    }
}
```

---

## Exercise 3: Sorting Customer Orders

- Bubble Sort compares adjacent items and swaps them. Simple to write but slow (O(n^2)).
- Quick Sort picks a pivot and splits array into smaller and larger parts (O(n log n) average).

### Code

```java
class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int id, String name, double price) {
        this.orderId = id;
        this.customerName = name;
        this.totalPrice = price;
    }
}

public class OrderSorter {

    // Bubble Sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 250.0),
            new Order(2, "Bob", 99.0),
            new Order(3, "Charlie", 450.0)
        };

        quickSort(orders, 0, orders.length - 1);

        System.out.println("Sorted Orders by Price:");
        for (Order o : orders) {
            System.out.println(o.customerName + " - $" + o.totalPrice);
        }
    }
}
```

---

## Exercise 4: Employee Management System

Using a simple fixed-size Array to store, search, and delete employees.

### Code

```java
class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int id, String name, String pos, double salary) {
        this.employeeId = id;
        this.name = name;
        this.position = pos;
        this.salary = salary;
    }
}

public class EmployeeManager {
    Employee[] employees;
    int count = 0;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
    }

    public void add(Employee e) {
        if (count < employees.length) {
            employees[count++] = e;
        }
    }

    public Employee search(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) return employees[i];
        }
        return null;
    }

    public void displayAll() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i].employeeId + ": " + employees[i].name);
        }
    }

    public void delete(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Deleted employee " + id);
                return;
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManager em = new EmployeeManager(5);
        em.add(new Employee(1, "Alice", "Manager", 70000));
        em.add(new Employee(2, "Bob", "Developer", 50000));
        em.displayAll();
        em.delete(1);
    }
}
```

---

## Exercise 5: Task Management System

Using a custom Singly Linked List to add, search, traverse, and delete tasks.

### Code

```java
class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int id, String name, String status) {
        this.taskId = id;
        this.taskName = name;
        this.status = status;
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task t) {
        this.task = t;
        this.next = null;
    }
}

public class TaskList {
    Node head;

    public void addTask(Task t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    public void printTasks() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.task.taskId + " - " + curr.task.taskName + " (" + curr.task.status + ")");
            curr = curr.next;
        }
    }

    public void deleteTask(int id) {
        if (head == null) return;
        if (head.task.taskId == id) {
            head = head.next;
            return;
        }
        Node curr = head;
        while (curr.next != null && curr.next.task.taskId != id) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
        }
    }

    public static void main(String[] args) {
        TaskList list = new TaskList();
        list.addTask(new Task(101, "Fix login bug", "Pending"));
        list.addTask(new Task(102, "Create database backup", "Done"));
        list.printTasks();
        list.deleteTask(101);
    }
}
```

---

## Exercise 6: Library Management System

Linear Search vs Binary Search on book titles.

### Code

```java
import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int id, String title, String author) {
        this.bookId = id;
        this.title = title;
        this.author = author;
    }
}

public class LibrarySearch {

    public static Book linearSearch(Book[] books, String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) return books[mid];
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Java Programming", "Gosling"),
            new Book(2, "Data Structures", "Lafore"),
            new Book(3, "Algorithms", "Cormen")
        };

        Arrays.sort(books, Comparator.comparing(b -> b.title.toLowerCase()));

        Book found = binarySearch(books, "Data Structures");
        if (found != null) {
            System.out.println("Found book: " + found.title + " by " + found.author);
        }
    }
}
```

---

## Exercise 7: Financial Forecasting

Using simple recursion to calculate future value with growth rate.

### Code

```java
public class FinancialForecasting {

    public static double predictFutureValue(double currentVal, double rate, int years) {
        // Base case: 0 years left
        if (years <= 0) {
            return currentVal;
        }
        // Multiply by (1 + rate) for each year
        return predictFutureValue(currentVal * (1 + rate), rate, years - 1);
    }

    public static void main(String[] args) {
        double presentValue = 10000;
        double growthRate = 0.05; // 5%
        int years = 5;

        double futureVal = predictFutureValue(presentValue, growthRate, years);
        System.out.println("Future Value after " + years + " years: $" + String.format("%.2f", futureVal));
    }
}
```

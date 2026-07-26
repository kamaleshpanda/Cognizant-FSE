public class Ex17_CarClass {
    String make;
    String model;
    int year;

    Ex17_CarClass(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    void displayDetails() {
        System.out.println("Car: " + year + " " + make + " " + model);
    }

    public static void main(String[] args) {
        Ex17_CarClass car1 = new Ex17_CarClass("Toyota", "Corolla", 2020);
        Ex17_CarClass car2 = new Ex17_CarClass("Honda", "Civic", 2022);

        car1.displayDetails();
        car2.displayDetails();
    }
}

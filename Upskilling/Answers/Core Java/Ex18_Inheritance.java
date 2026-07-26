class Animal {
    void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}

public class Ex18_Inheritance {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();

        System.out.print("Animal: ");
        animal.makeSound();

        System.out.print("Dog: ");
        dog.makeSound();
    }
}

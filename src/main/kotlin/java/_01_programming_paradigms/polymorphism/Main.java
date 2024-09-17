package java._01_programming_paradigms.polymorphism;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        var animals = List.of(cat, dog, new Lion(), new Monkey());

        tellAllToSpeak(animals);

    }

    public static void tellAllToSpeak(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.speak();
        }
    }
}

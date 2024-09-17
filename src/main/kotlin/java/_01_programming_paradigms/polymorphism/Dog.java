package java._01_programming_paradigms.polymorphism;

public class Dog implements Animal {

    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

package java._03_staying_immutable.withMethods;

public record Person(String name, int age, String hairColor) {

    // Copy constructor
    public Person(Person p) {
        this(p.name, p.age, p.hairColor);
    }

    // With methods
    public Person withName(String name) {
        return new Person(name, age, hairColor);
    }

    public Person withAge(int age) {
        return new Person(name, age, hairColor);
    }

    public Person withHairColor(String hairColor) {
        return new Person(name, age, hairColor);
    }
}

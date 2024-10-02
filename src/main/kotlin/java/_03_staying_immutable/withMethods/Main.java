package java._03_staying_immutable.withMethods;

public class Main {

    public static void main(String[] args) {
        var person = new Person("John", 30, "brown");
        System.out.println(person);

        var person2 = person.withName("Jane");
        System.out.println(person2);

        var person3 = person2
                        .withAge(25)
                        .withName("Betty")
                        .withHairColor("Gray");

        System.out.println(person3);
    }
}

package java._03_staying_immutable.mutablejava.mutable;

import java._03_staying_immutable.withMethods.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> mailingList = new ArrayList<String>();

    public static void addContact(String email) {
        mailingList.add(email);  // IMPLICIT INPUT (read global) and IMPLICIT OUTPUT (update list in-place)
    }

    // Example of a function that does a read-AND-write operation
    public static String removeNthContact(int n) {
        // This function both modifies the list AND returns a value (the item that was removed from the list)
        return mailingList.remove(n-1);
    }

    public static void dyeHair(Person p, String color) {
        //p.hairColor = color;  // Since Person is a record, it is already immutable and code like this won't work!
    }

    public static void dyeHairByName(List<Person> people, String name, String color) {
        for ( Person p : people ) {
            if ( p.name().equals(name) ) {
                // p.hairColor = color;  // Since Person is a record, it is already immutable and code like this won't work!

                // BUT neither will this, because this just changes the object to which p is pointing and NOT the list:
                // p = dyeHair(p, color);
            }
        }
    }

}

package java._03_staying_immutable.mutablejava.immutable;

import java._03_staying_immutable.withMethods.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> mailingList = new ArrayList<String>();

    public static List<String> addContact(List<String> list, String email) {
        // Use explicit input instead of implicit input to make this function a calculation
        // Use copy-on-write to turn a write into a read and make this function a calculation
//        List<String> newList = new ArrayList<String>(list);  // Make a copy
//        newList.add(email);                                         // Update the copy
//        return newList;                                             // Return the copy

        // OR using our ListUtils:
        return ImmutableListUtils.add(list, email);
    }

    // Avoiding Read-And-Write functions by splitting read from write
    // The original READ is simply getting the nth element
    public static String getNthContact(List<String> list, int n) {
        return list.get(n-1);
    }
    // The original WRITE is the element removal (for which we can use copy-on-write to turn the write into a read)
    public static List<String> removeNthContact(List<String> list, int n) {
        List<String> newList = new ArrayList<String>(list);       // Make a copy
        newList.remove(n-1);                                // Update the copy
        return newList;                                           // Return the copy

        // OR using our ListUtils:
//        return ImmutableListUtils.remove(list, n-1);
    }

    // Avoiding Read-And-Write functions by returning two values
    public static ItemAndList removeNthContact2(List<String> list, int n) {
        List<String> newList = new ArrayList<String>(list);       // Make a copy
        String removedEmail = newList.remove(n-1);          // Update the copy (and store the returned value)

        // Return both the pieces of data
        // (Here, we use a 2-element array to act as a 'pair' of values)
        // return new Object[] { removedEmail, newList };

        // (Here, we use a custom record class to represent the pair)
        return new ItemAndList(removedEmail, newList);
    }

    public record ItemAndList(String item, List<String> list) {}

    public static Person dyeHair(Person p, String color) {
        return new Person(p.name(), p.age(), color); // Make a copy with the new hair color
    }

    public static List<Person> dyeHairByName(List<Person> people, String name, String color) {
        // Here, since we are changing the element of a list we must do copy-on-write on the list
        var newList = new ArrayList<Person>(people);
        for ( int i = 0 ; i < newList.size(); i++ ) {
            Person p = newList.get(i);
            if ( p.name().equals(name) ) {
                // But since we are also changing the item in a specific element of the list we call the copy-on-write dyeHair that gives us a NEW person object
                newList.set(i, dyeHair(p, color));
                // OR using the withHairColor method:
                // newList.set(i, p.withHairColor(color));
            }
        }
        return newList;
    }

}

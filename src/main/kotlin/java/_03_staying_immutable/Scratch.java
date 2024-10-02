package java._03_staying_immutable;

import java.util.HashMap;
import java.util.List;

import static java._03_staying_immutable.mutablejava.immutable.ImmutableListUtils.add;
import static java._03_staying_immutable.mutablejava.immutable.ImmutableListUtils.removeFirst;

public class Scratch {

    private record Course(String name, String code) {}
    private record Person(String name, int age, List<Course> courses) {}
    public static void main(String[] args) {

        int x = 50;

        var L = List.of("a", "b", "c", "d", "e", "f");

        var newL = add(L, "g");
        var newL2 = removeFirst(newL);

        System.out.println(String.join(",", L ));
        System.out.println(String.join(",", newL ));
        System.out.println(String.join(",", newL2 ));

        var box = new Tree<String>("Hello");
        var box2 = new Tree<Integer>(1);
        var box3 = new Tree<Tree<Double>>(new Tree<>(3.14));

        var m = new HashMap<String, Integer>();

//        var courses = new ArrayList<Course>();
//        courses.add(new Course("CS 1331", "Intro to Java"));
//        courses.add(new Course("CS 1332", "Data Structures"));
//
//        var p = new Person("Bob", 42, courses);
//
//        showCourses(p);
//        p.courses().set(0, new Course("CSD215", "Programming Paradigms"));
//        showCourses(p);

    }

    public static void showCourses(Person p ) {
        for ( Course c : p.courses() ) {
            System.out.println(c.name());
        }
    }
}

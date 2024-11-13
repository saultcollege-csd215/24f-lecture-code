package java._05_higher_order_functions.with_hof;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListUtils {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        // This 'looks like' passing a lambda expression to a function (but it's not really)
        list.forEach( item -> System.out.println(item) );
        // This 'looks like' passing a function reference to a function (but it's not really)
        list.forEach(System.out::println);

        // Our withCopy function can also be used to make sequential list operations more efficient
        // Consider the following code.  Each operation involves a copy-on-write:
        list = dropLast(list);
        list = push(list, "four");
        list = push(list, "five");
        list = set(list, 1, "two and a half");

        // Instead, we can do this:
        list = withCopy(list, newList -> {
            // This lambda expression performs all the operations on the copy
            // without need to copy the whole list for each operation
            newList.remove(newList.size() - 1);
            newList.add("four");
            newList.add("five");
            newList.set(1, "two and a half");
        });

    }

    // By encapsulating the copy-on-write pattern for lists in this function, we can avoid code duplication
    public static <T> List<T> withCopy(List<T> list, Consumer<List<T>> operation) {
        List<T> newList = new ArrayList<T>(list);
        operation.accept(newList);
        return newList;
    }

    // Now these functions can be implemented in terms of the withCopy function
    // Only the operation performed on the copy is different (we pass the operation in as a lambda expression)
    public static <T> List<T> set(List<T> list, int index, T value) {
        return withCopy(list, newList -> newList.set(index, value));
    }

    public static <T> List<T> push(List<T> list, T value) {
        return withCopy(list, newList -> newList.add(value));
    }

    public static <T> List<T> dropLast(List<T> list) {
        return withCopy(list, newList -> newList.remove(newList.size() - 1));
    }

    public static <T> List<T> dropFirst(List<T> list) {
        return withCopy(list, newList -> newList.remove(0));
    }
}

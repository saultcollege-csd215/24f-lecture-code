package java._05_higher_order_functions.without_hof;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    // All the functions below have a very similar structure: copy-on-write
    // The only difference is in the operation performed in the 'updated the copy' step
    // Let's use a higher-order function to avoid code duplication!

    public static <T> List<T> set(List<T> list, int index, T value) {
        List<T> newList = new ArrayList<T>(list);
        newList.set(index, value);
        return newList;
    }

    public static <T> List<T> push(List<T> list, T value) {
        List<T> newList = new ArrayList<T>(list);
        newList.add(value);
        return newList;
    }

    public static <T> List<T> dropLast(List<T> list) {
        List<T> newList = new ArrayList<T>(list);
        newList.remove(list.size() - 1);
        return newList;
    }

    public static <T> List<T> dropFirst(List<T> list) {
        List<T> newList = new ArrayList<T>(list);
        newList.remove(0);
        return newList;
    }
}

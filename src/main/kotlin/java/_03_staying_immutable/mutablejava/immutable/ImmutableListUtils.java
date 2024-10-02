package java._03_staying_immutable.mutablejava.immutable;

import java.util.ArrayList;
import java.util.List;

public class ImmutableListUtils {
    public static <T> List<T> add(List<T> list, T item) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.add(item);                      // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> removeFirst(List<T> list) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.remove(0);                  // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> removeLast(List<T> list) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.remove(newList.size()-1);   // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> remove(List<T> list, int idx) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.remove(idx);                    // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> set(List<T> list, int idx, T value) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.set(idx, value);                // Update the copy
        return newList;                         // Return the copy
    }
}

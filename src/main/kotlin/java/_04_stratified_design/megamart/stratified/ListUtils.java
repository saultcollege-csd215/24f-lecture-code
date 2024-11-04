package java._04_stratified_design.megamart.stratified;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<T> add(List<T> list, T item) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.add(item);                      // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> replace(List<T> list, T oldItem, T newItem) {
        var i = list.indexOf(oldItem);         // Find the index of the old item

        if ( i == -1 ) {                        // If the old item is not in the list
            return list;                        // Return the original list
        }

        var copy = new ArrayList<T>(list);      // Copy-on-write
        copy.set(i, newItem);                   // Update the copy at the proper index
        return copy;                            // Return the copy
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

    public static <T> List<T> remove(List<T> list, T item) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.remove(item);                   // Update the copy
        return newList;                         // Return the copy
    }

    public static <T> List<T> set(List<T> list, int idx, T value) {
        var newList = new ArrayList<T>(list);   // Make a copy
        newList.set(idx, value);                // Update the copy
        return newList;                         // Return the copy
    }
}

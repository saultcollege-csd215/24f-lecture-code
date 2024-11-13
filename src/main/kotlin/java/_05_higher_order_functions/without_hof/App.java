package java._05_higher_order_functions.without_hof;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void cook(String s) { System.out.println("Cooked $s"); }
    public static void eat(String s) { System.out.println("Ate $s"); }
    public static void wash(String s) { System.out.println("Washing $s"); }
    public static void dry(String s) { System.out.println("Drying $s"); }
    public static void putAway(String s) { System.out.println("Putting away $s"); }

    // The functions below have the same overall structure:
    // loop over a list of strings
    // We can generalize this structure using a higher-order function
    public static void cookAndEatFoods(List<String> foods) {
        for (var food : foods) {
            cook(food);
            eat(food);
        }
    }

    public static void cleanDishes(List<String> dishes) {
        for ( var d : dishes ) {
            wash(d);
            dry(d);
            putAway(d);
        }
    }

    public static List<Integer> doubleAll(List<Integer> numbers) {
        var result = new ArrayList<Integer>();
        for (var n : numbers) {
            result.add(n * 2);
        }
        return result;
    }

    public static List<Integer> befuddleAll(List<Integer> numbers) {
        var result = new ArrayList<Integer>();
        for (var n : numbers) {
            result.add(n + 4 / (n*n) - 3);
        }
        return result;
    }

    public static List<Integer> squareAll(List<Integer> numbers) {
        var result = new ArrayList<Integer>();
        for (var n : numbers) {
            result.add(n * n);
        }
        return result;
    }

    public static List<String> removeVowelFirstWords(List<String> words) {
        var result = new ArrayList<String>();
        for (var word : words) {
            if (! word.matches("^[aeiouAEIOU].*")) {
                result.add(word);
            }
        }
        return result;
    }

    public static List<String> removeShortWords(List<String> words) {
        var result = new ArrayList<String>();
        for (var word : words) {
            if ( word.length() > 4 ) {
                result.add(word);
            }
        }
        return result;
    }

    public static List<String> removeWordsEndingInY (List<String> words) {
        var result = new ArrayList<String>();
        for (var word: words) {
            if ( ! word.endsWith("y") ) {
                result.add(word);
            }
        }
        return result;
    }

    public static int countWordsStartingWithVowel(List<String> words) {
        var result = 0;
        for (var word : words) {
            if (! word.matches("^[aeiouAEIOU].*") ) {
                result += 1;
            }
        }
        return result;
    }

    public static int countShortWords(List<String> words) {
        var result = 0;
        for (var word : words) {
            if ( word.length() > 4 ) {
                result += 1;
            }
        }
        return result;
    }

    public static int countWordsEndingInY(List<String> words) {
        var result = 0;
        for (var word : words) {
            if (word.endsWith("y")) {
                result += 1;
            }
        }
        return result;
    }

}

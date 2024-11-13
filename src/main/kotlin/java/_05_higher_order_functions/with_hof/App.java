package java._05_higher_order_functions.with_hof;

import java.util.List;

public class App {


    public static void cook(String s) { System.out.println("Cooked $s"); }
    public static void eat(String s) { System.out.println("Ate $s"); }
    public static void wash(String s) { System.out.println("Washing $s"); }
    public static void dry(String s) { System.out.println("Drying $s"); }
    public static void putAway(String s) { System.out.println("Putting away $s"); }

    public static void cookAndEat(String food) {
        cook(food);
        eat(food);
    }

    public static void cleanDish(String dish) {
        wash(dish);
        dry(dish);
        putAway(dish);
    }

    public static void cookAndEatFoods(List<String> foods) {
        // Using a lambda expression
        foods.forEach(food -> {
            cook(food);
            eat(food);
        });

        // Using a function reference
        foods.forEach(App::cookAndEat);

    }

    public static void cleanDishes(List<String> dishes) {
        // Using a lambda expression
        dishes.forEach(dish -> {
            wash(dish);
            dry(dish);
            putAway(dish);
        });

        // Using a function reference
        dishes.forEach(App::cleanDish);

    }

    // Using Stream API to perform various operations (compare with without_hof.App)
    public static List<Integer> doubleAll(List<Integer> numbers) {
        return numbers.stream()   // Get the stream
                .map(n -> n * 2)  // Apply the operation to each element using a map
                .toList();        // Convert the stream back to a list
    }

    public static List<Integer> befuddleAll(List<Integer> numbers) {
        return numbers.stream()                // Get the stream
                .map(n -> n + 4 / (n*n) - 3)   // Apply the operation to each element using a map
                .toList();                     // Convert the stream back to a list
    }

    public static List<Integer> squareAll(List<Integer> numbers) {
        return numbers.stream()   // Get the stream
                .map(n -> n * n)  // Apply the operation to each element using a map
                .toList();        // Convert the stream back to a list
    }

    public static List<String> removeVowelFirstWords(List<String> words) {
        return words.stream()                          // Get the stream
                .filter(word -> ! word.matches("^[aeiouAEIOU].*"))  // Apply the operation to each element using a filter
                .toList();                              // Convert the stream back to a list
    }

    public static List<String> removeShortWords(List<String> words) {
        return words.stream()                          // Get the stream
                .filter(word -> word.length() > 4)     // Apply the operation to each element using a filter
                .toList();                              // Convert the stream back to a list
    }

    public static List<String> removeWordsEndingInY (List<String> words) {
        return words.stream()                          // Get the stream
                .filter(word -> ! word.endsWith("y"))  // Apply the operation to each element using a filter
                .toList();                              // Convert the stream back to a list
    }

    public static int countWordsStartingWithVowel(List<String> words) {
        return (int) words.stream()                          // Get the stream
                .filter(word -> word.matches("^[aeiouAEIOU].*"))  // Apply the operation to each element using a filter
                .count();                                    // Get the count
    }

    public static int countWordsEndingInY(List<String> words) {
        return (int) words.stream()                          // Get the stream
                .filter(word -> word.endsWith("y"))  // Apply the operation to each element using a filter
                .count();                                    // Get the count
    }

    public static int countWordsLongerThan4(List<String> words) {
        return (int) words.stream()                          // Get the stream
                .filter(word -> word.length() > 4)  // Apply the operation to each element using a filter
                .count();                                    // Get the count
    }
}

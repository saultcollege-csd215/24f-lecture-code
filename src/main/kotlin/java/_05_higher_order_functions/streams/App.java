package java._05_higher_order_functions.streams;

import java.util.List;

public class App {

    private static boolean isPrime(int n) {
        if (n < 2) { return false; }
        for (int i = 2; i < n ; i++) {
            if (n % i == 0) { return false; }
        }
        return true;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        // Map examples
        var doubled = numbers.stream().map(n -> n*2).toList();       // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40]
        var squared = numbers.stream().map(n -> n*n).toList();       // [1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400]
        var isEven = numbers.stream().map(n -> n % 2 == 0).toList(); // [false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true]

        // Filter examples
        var evens = numbers.stream().filter(n -> n % 2 == 0).toList();   // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
        var odds = numbers.stream().filter(n -> n % 2 != 0).toList();    // [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
        var primes = numbers.stream().filter(App::isPrime);              // [2, 3, 5, 7, 11, 13, 17, 19]

        // Reduce examples
        var sum = numbers.stream().reduce(0, Integer::sum);                                           // 210
        var product = numbers.stream().reduce(1, (total, next) -> total * next);                      // 2432902008176640000
        var max = numbers.stream().reduce(Integer.MIN_VALUE, (total, next) -> next > total ? next : total);  // 20
        // OR
        max = numbers.stream().max(Integer::compareTo).get();                                                // 20

        // FlatMap examples
        record Person(String name, List<Integer> luckyNumbers) {}
        var people = List.of(
                new Person("Alice", List.of(1, 2, 3)),
                new Person("Bob", List.of(4, 5, 6)),
                new Person("Carol", List.of(7, 8, 9))
        );

        // Sort people by name
        var sortedPeople = people.stream().sorted((p1, p2) -> p1.name().compareTo(p2.name())).toList();

        // Sort people by the second number
        var sortedPeople2 = people.stream().sorted((p1, p2) -> p1.luckyNumbers().get(1) - p2.luckyNumbers().get(1)).toList();

        var alice = people.stream().reduce(null, (found, next) -> next.name() == "Alice" ? next : null);  // Person(name=Alice, luckyNumbers=[1, 2, 3])
        var allLuckyNumbers = people.stream().flatMap(n -> n.luckyNumbers().stream()).toList(); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // Chained examples
        var doubledEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .toList();  // [4, 8, 12, 16, 20, 24, 28, 32, 36, 40]

        var sumOfLuckyNumbers = people.stream()
                .flatMap(n -> n.luckyNumbers().stream())
                .reduce(0, Integer::sum); // 45

        var largestEvenLuckyNumber = people.stream()
                .flatMap(n -> n.luckyNumbers().stream())
                .filter(n -> n % 2 == 0)
                .reduce(Integer.MIN_VALUE, (total, next) -> next > total ? next : total); // 8

        // All / Any examples
        var allEven = numbers.stream().allMatch(n -> n % 2 == 0);       // false
        var evensAreEven = evens.stream().allMatch(n -> n % 2 == 0);    // true
        var anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);       // true
        var any10 = numbers.stream().anyMatch(n -> n == 10);            // true
        var any100 = numbers.stream().anyMatch(n -> n == 100);          // false
        var allLessThan20 = numbers.stream().allMatch(n -> n < 20);     // true

    }
}

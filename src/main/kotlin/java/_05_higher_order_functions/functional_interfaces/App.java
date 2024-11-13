package java._05_higher_order_functions.functional_interfaces;

import java.util.List;
import java.util.function.*;

public class App {

    public static void main(String[] args) {

        // Lambda expression syntax:
        // (parameter list) -> { body }
        Function<Double, Double> f = (x) -> {
            // Lambda body can be any number of lines, just like a regular function
            // Here, it's just one line, but it could be more
            return x + 1;
        };
        // If there is only one parameter, the parentheses can be omitted:
        Function<Double, Double> f2 = x -> { return x + 1; };
        // If the body is a single return statement, the curly braces can be omitted:
        Function<Double, Double> f3 = x -> x + 1;


        // Examples of various common functional interfaces
        Consumer<String> printIt = s -> System.out.println(s);
        Supplier<Integer> oneTo100 = () -> (int) (Math.random() * 100) + 1;
        Function<String, Integer> len = s -> s.length();
        BiFunction<Double, Double, Double> sum = (a, b) -> a + b;
        Predicate<String> isLongish = s -> s.length() > 42;


        List<String> list = List.of("one", "two", "three");

        // Using a custom implementation of the Consumer interface
        list.forEach(new ConsolePrinter<String>());

        // Using an anonymous class
        // The curly braces syntax here effectively creates a new class that implements the Consumer interface
        // AND instantiates an instance of that class
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // Using a lambda expression
        // The lambda expression syntax here effectively creates a new class that implements the Consumer interface
        // AND uses the lambda expression as the implementation of the ONE method in the functional interface
        list.forEach( item -> System.out.println(item) );

        // Using a function reference
        // As long as the referred function meets the interface requirements, (in this case, the Consumer interface)
        // it can be used as a function reference
        // In this case, our consumer above was ultimately just call println, but since println meets the Consumer
        // interface requirements, we can use it as a function reference directly!
        list.forEach( System.out::println );
    }


}

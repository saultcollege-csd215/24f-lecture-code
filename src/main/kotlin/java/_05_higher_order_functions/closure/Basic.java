package java._05_higher_order_functions.closure;

import java.util.function.Consumer;

public class Basic {

    public static void higherOrderFunc(Consumer<String> consumer) {
        // This is where the lambda expression is finally called
        // Even though x is not in scope here, it is still accessible to the body of the lambda expression
        // when it is called here due to closure
        consumer.accept("Hello");
    }

    public static void main(String[] args) {

        int x = 1;  // This variable is defined outside the scope of the lambda expression below
                    // But it is still accessible inside the lambda expression

        higherOrderFunc(s -> System.out.println(x + ": " + s));
    }
}

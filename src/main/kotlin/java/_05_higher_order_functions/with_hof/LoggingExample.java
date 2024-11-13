package java._05_higher_order_functions.with_hof;

public class LoggingExample {

    public static void log(String s) {
        System.out.println(s);
    }

    public static void justCallIt(Runnable action) {
        action.run();
    }

    // In JavaScript it would look more like this:
    // function justCallIt(action) {
    //     action();
    // }


    // This function takes a runnable (a function that takes no arguments and returns nothing)
    // and runs it inside a try-catch block that logs errors
    public static void withLogging(Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            log("Exception: " + e.getMessage());
        }
    }

    // This function takes a runnable action and returns a new
    // action that when run will run the original action with logging
    public static Runnable makeLoggingFunction(Runnable action) {
        // Here, we return a function that when called will call the original function and log any exceptions
        return () -> {
            try {
                action.run();
            } catch (Exception e) {
                log("Exception: " + e.getMessage());
            }
        };

        // But by extracting the try/catch block into a separate function as in withLogging above, we can reuse it
        // here and in other locations too if needed
//        return () -> withLogging(action);

    }


    public static void aFuncThatNeedsLogging() {
        System.out.println("Doing something");
        throw new RuntimeException("Something went wrong");
    }

    public static int anotherFuncThatNeedsLogging(int n) {
        var r = n * 3;

        if (n < 0) {
            throw new RuntimeException("n must be positive");
        }

        return r;
    }

    public static void main(String[] args) {

        System.out.println("Calling justCallIt()...");

        // Since justCallIt() takes a Runnable, we can pass it a lambda expression
        justCallIt(LoggingExample::aFuncThatNeedsLogging);

        // Since aFuncThatNeedsLogging() takes no parameters and returns nothing,
        // i.e., it matches the signature of Runnable's abstract method, we can use a function reference directly
        withLogging(LoggingExample::aFuncThatNeedsLogging);

        withLogging(() -> {
            var n = 42;
            LoggingExample.anotherFuncThatNeedsLogging(n);

        });

        // Since anotherFuncThatNeedsLogging() does NOT match the expected interface, we need to use a lambda expression
        // We can rely on closure to capture the value of n
        var n = -1;
        withLogging(() -> {
            var x = anotherFuncThatNeedsLogging(n);
            System.out.println("x = " + x);
        });

        // With makeLoggingFunction, we can build a new function out of an existing function...
        var aFuncWithLogging = makeLoggingFunction(LoggingExample::aFuncThatNeedsLogging);
        // and then use it wherever we need the original function's behavior plus logging
        aFuncWithLogging.run();

    }
}

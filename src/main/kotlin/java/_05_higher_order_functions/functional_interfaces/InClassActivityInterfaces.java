package java._05_higher_order_functions.functional_interfaces;

import java.util.List;

public class InClassActivityInterfaces {

    public static void main(String[] args) {

        var concreteExampleFunctionalInteface = new ExampleFunctionalInterface() {
            @Override
            public void doSomething() {
                System.out.println("Doing something");
            }
        };

    }

    @FunctionalInterface
    public interface Adder {
        int add(int a, int b);
    }

    // This...
//    public class ConcreteAdder implements Adder {
//        @Override
//        public int add(int a, int b) {
//            return a + b;
//        }
//    }
//    Adder adder = new ConcreteAdder();
    // ...is equivalent to this:
    Adder adder = (a, b) -> a + b;

    @FunctionalInterface
    public interface Converter {
        String apply(int i);
    }

    Converter c1 = i -> Integer.toString(i);
    Converter c2 = i -> "Hello, world";
    Converter c3 = i -> {
        if (i % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    };

    @FunctionalInterface
    public interface Converter2 {
        int convert(String s);
    }

    Converter2 c4 = s -> Integer.parseInt(s);
    Converter2 c5 = s -> s.length();
    Converter2 c6 = s -> {
        if (s.equals("Even")) {
            return 0;
        } else {
            return 1;
        }
    };

    @FunctionalInterface
    public interface Averager {
        double calcAverage(List<Integer> list);
    }

    Averager a1 = list -> {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum / list.size();
    };
    Averager a2 = list -> 0.0;
    Averager a3 = list -> list.size();
}

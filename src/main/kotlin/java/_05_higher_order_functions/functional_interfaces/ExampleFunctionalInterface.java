package java._05_higher_order_functions.functional_interfaces;

// The @FunctionalInterface annotation is optional, but it is a good idea to use it
// because it will cause a compile-time error if you try to add more than one abstract method
@FunctionalInterface
public interface ExampleFunctionalInterface {
    void doSomething();  // This is the only abstract method in this interface
                         // therefore, this is a functional interface

    // Even though this interface has multiple methods, it is still a functional interface
    // because only ONE of them is abstract (has no default implementation)
    default void doSomethingElse() {
        System.out.println("Doing something else");
    }

}

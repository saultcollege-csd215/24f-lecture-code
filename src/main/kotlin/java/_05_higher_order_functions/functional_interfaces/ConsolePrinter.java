package java._05_higher_order_functions.functional_interfaces;

// This class implements the Consumer functional interface
// I.e., it 'consumes' one item at a time (via the accept method)
// and does something with it (here, prints it to the console)
class ConsolePrinter<T> implements java.util.function.Consumer<T> {
    @Override
    public void accept(T s) {
        System.out.println(s);
    }
}
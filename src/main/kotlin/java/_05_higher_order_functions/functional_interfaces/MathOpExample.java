package java._05_higher_order_functions.functional_interfaces;

public class MathOpExample {

    @FunctionalInterface
    public interface MathOp {
        double operate(double a, double b);
    }

    public static double doMath(double a, double b, MathOp op) {
        return op.operate(a, b);
    }

    public static void main(String[] args) {

        // Any of these work, and do the same thing:

        // 1) Storing a lambda expression in a variable
//        MathOp add = (a, b) -> a + b;
//        System.out.println(doMath(5, 3, add));

        // 2) Storing a reference in a variable
//        MathOp add = Double::sum;
//        System.out.println(doMath(5, 3, add));

        // 3) Storing the object created by an anonymous class in a variable
//        MathOp add = new MathOp() {
//            @Override
//            public double operate(double a, double b) {
//                return a + b;
//            }
//        };
//        System.out.println(doMath(5, 3, add));

        // 4) Using a lambda expression directly
//        System.out.println(doMath(5, 3, (a, b) -> a + b));

        // 5) Using a method reference directly
//        System.out.println(doMath(5, 3, Double::sum));

        // 6) Using an anonymous class directly
//        System.out.println(doMath(5, 3, new MathOp() {
//            @Override
//            public double operate(double a, double b) {
//                return a + b;
//            }
//        }));



    }

}

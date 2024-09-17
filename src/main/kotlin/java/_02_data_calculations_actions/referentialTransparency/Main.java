package java._02_data_calculations_actions.referentialTransparency;

public class Main {

    public static void main(String[] args) {

        System.out.println(square(hypotenuse(square(3), square(4))));
    }

    public static double hypotenuse(double a, double b) {
        var h = Math.sqrt(a * a + b * b);
        return h;
    }

    public static double square(double x) {
        return x * x;
    }
}

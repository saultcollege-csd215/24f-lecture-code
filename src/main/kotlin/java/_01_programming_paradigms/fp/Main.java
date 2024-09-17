package java._01_programming_paradigms.fp;

public class Main {

    public static void main(String[] args) {

        anImpureFunction();

        String s = aPureFunction();
        String s2 = anotherPureFunction();
        anotherImpureFunction(s);
        anotherImpureFunction(s2);
    }

    public static String aPureFunction() {
        return "Hello, world";
    }
    public static String anotherPureFunction() {
        return "Hello, class";
    }

    public static void anImpureFunction() {
        System.out.println("Hello, world");
    }

    public static void anotherImpureFunction(String s) {
        System.out.println(s);
    }
}

package java._06_other_fp_concepts.adts.interfaces_classes;

import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Shape> L = List.of(
                new Circle(new Point(0, 0), 5),
                new Rectangle(new Point(0, 0), new Point(5, 5)),
                new Rectangle(new Point(0, 0), new Point(10, 10))
        );

        // Polymorphism is achieved by implementing the same interface.
        // The interface defines the common behavior.
        // The classes define the specific behavior.
        // At run time, the JVM will call the appropriate specific method.
        L.forEach(s -> {
            System.out.printf("Area: %f%n", s.area());
            System.out.printf("Perimeter: %f%n", s.perimeter());
            System.out.printf("Vertices: %d%n", s.vertices());
            System.out.printf("Edges: %d%n", s.edges());
        });
    }
}

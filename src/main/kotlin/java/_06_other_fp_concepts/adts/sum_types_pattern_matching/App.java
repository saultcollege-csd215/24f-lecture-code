package java._06_other_fp_concepts.adts.sum_types_pattern_matching;

import java.util.List;

import static java._06_other_fp_concepts.adts.sum_types_pattern_matching.Shape.*;

public class App {

    public static void main(String[] args) {

        List<Shape> L = List.of(
                new Circle(new Point(0, 0), 5),
                new Rectangle(new Point(0, 0), new Point(5, 5)),
                new Rectangle(new Point(0, 0), new Point(10, 10))
        );

        // Polymorphism is achieved via pattern matching in the various functions.
        // The functions define the common interface.
        // The switch expressions within each function define the specific implementation of the behaviour
        // for each of the data types defined in the sealed interface.
        // At compile time the compiler will check that all the cases are covered, reducing the risk of runtime errors.
        L.forEach(s -> {
            System.out.printf("Area: %f%n", area(s));
            System.out.printf("Perimeter: %f%n", perimeter(s));
            System.out.printf("Vertices: %d%n", vertices(s));
            System.out.printf("Edges: %d%n", edges(s));
        });
    }
}

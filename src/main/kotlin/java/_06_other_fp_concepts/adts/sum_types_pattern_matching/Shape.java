package java._06_other_fp_concepts.adts.sum_types_pattern_matching;

// Using a sealed interface and pattern matching, the interface defines the set of TYPES rather than the set of BEHAVIOURS
// (Note here that the interface is sealed, and it contains a fixed set of record classes within it.)
// The behaviours are defined as static methods within the interface, and pattern matching is used to implement them
// With pattern matching, the compiler can check that all the cases are covered, reducing the risk of runtime errors
// This approach works well when the set of TYPES is well known in advance, but the set of BEHAVIOURS is more likely to change
// Adding a new behaviour is a simple matter of adding a new static method to the interface
// But adding a new type requires changing each of the static methods that pattern match on the type

public sealed interface Shape {

    record Circle(Point center, double radius) implements Shape { }
    record Rectangle(Point topLeft, Point botRight) implements Shape {
        public double width() { return botRight.x() - topLeft.x(); }
        public double height() { return botRight.y() - topLeft.y(); }
    }

    static double area(Shape s) {
        return switch (s) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
        };
    }

    static double perimeter(Shape s) {
        return switch (s) {
            case Circle c -> 2 * Math.PI * c.radius();
            case Rectangle r -> 2 * (r.width() + r.height());
        };
    }

    static int vertices(Shape s) {
        return switch (s) {
            case Circle c -> 0;
            case Rectangle r -> 4;
        };
    }

    static int edges(Shape s) {
        return switch (s) {
            case Circle c -> 0;
            case Rectangle r -> 4;
        };
    }
}
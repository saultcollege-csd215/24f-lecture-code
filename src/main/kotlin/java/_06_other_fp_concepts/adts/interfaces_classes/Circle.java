package java._06_other_fp_concepts.adts.interfaces_classes;

public record Circle(Point center, double radius) implements Shape {

    @Override
    public double area() { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }

    @Override
    public int vertices() { return 0; }

    @Override
    public int edges() { return 0; }
}

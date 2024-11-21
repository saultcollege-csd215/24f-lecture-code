package java._06_other_fp_concepts.adts.interfaces_classes;

public record Rectangle(Point topLeft, Point botRight) implements Shape {

    public double width() { return botRight.x() - topLeft.x(); }
    public double height() { return botRight.y() - topLeft.y(); }

    @Override
    public double area() { return width() * height(); }

    @Override
    public double perimeter() { return 2 * (width() + height()); }

    @Override
    public int vertices() { return 4; }

    @Override
    public int edges() { return 4; }
}

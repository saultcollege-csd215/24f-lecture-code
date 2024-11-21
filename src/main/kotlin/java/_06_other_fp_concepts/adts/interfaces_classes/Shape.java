package java._06_other_fp_concepts.adts.interfaces_classes;

// Using the usual Interfaces & classes approach, the interface defines the behaviour of the data type
// And the classes implement the interface
// This approach works well when the set of BEHAVIOURS is well known in advance, but the set of TYPES is more likely to change
// Adding a new type is a simple matter of adding a new class that implements the interface
// But adding a new behaviour requires changing the interface and all the classes that implement it
public interface Shape {
    double area();
    double perimeter();
    int vertices();
    int edges();
}

package java._03_staying_immutable.generic;

import java.util.ArrayList;

class Grid<T> {

    private int rows;
    private int cols;

    private ArrayList<T> items;

    public Grid(int rows, int cols, T initialValue) {
        items = new ArrayList<T>(rows * cols);
    }

    public T get(int row, int col) {
        return items.get(row * cols + col);
    }

    public void set(int row, int col, T value) {
        items.set(rows * cols + col, value);
    }

    @Override
    public String toString() {
        return "A grid with " + rows + " rows and " + cols + " columns";
    }
}
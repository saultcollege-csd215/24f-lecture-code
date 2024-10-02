package _03_staying_immutable.generic

class Grid<T>(val rows: Int, val cols: Int, initialValue: T) {

    private val items = MutableList<T>(size = rows * cols) {
        initialValue
    }

    fun get(row: Int, col: Int): T {
        return items[row * cols + col]
    }

    fun set(row: Int, col: Int, value: T) {
        items[rows * cols + col] = value
    }

    override fun toString(): String {
        return "A grid with $rows rows and $cols columns"
    }
}
package _01_programming_paradigms.pureFunctions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun repeat() {
        assertEquals("Hello, World!Hello, World!", repeat("Hello, World!", 2))
        assertEquals("", repeat("Hello, World!", 0))
        assertEquals("", repeat("", 12))
    }

    @Test
    fun sum() {
    }

    @Test
    fun isEven() {
    }
}
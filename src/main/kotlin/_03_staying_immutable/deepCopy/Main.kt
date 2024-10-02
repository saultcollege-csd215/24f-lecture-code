package _03_staying_immutable.deepCopy

import _03_staying_immutable.megaMart.CartItem

fun main() {

    val L1 = listOf(CartItem("A", 1.0), CartItem("B", 2.0))

    val L1b = L1 // aliasing assignment (copy of reference)
    val L2 = shallowCopy(L1)
    val L3 = stillAShallowCopy(L1)
    val L4 = deepCopy(L1)

    // Only the deep copy contains data that is not shared with the original list
    // (i.e. both the top-level list, and each of the list's items have been fully copied
    // and are not simply references to the original list or items)
    println("L1b reference equality: ${L1 === L1b}; CartItem reference equality: ${L1[0] === L1b[0]}")
    println("L2 reference equality: ${L1 === L2}; CartItem reference equality: ${L1[0] === L2[0]}")
    println("L3 reference equality: ${L1 === L3}; CartItem reference equality: ${L1[0] === L3[0]}")
    println("L4 reference equality: ${L1 === L4}; CartItem reference equality: ${L1[0] === L4[0]}")
}

fun shallowCopy(original: List<CartItem>) = original.toList()

fun stillAShallowCopy(original: List<CartItem>): List<CartItem> {
    val copy = mutableListOf<CartItem>()
    for ( item in original ) {
        // Since item is a reference type here (a CartItem)
        // passing item ends up just making a copy of the *reference* (the address)
        // and not an actual copy of the data in the reference
        copy.add(item)
    }
    return copy;
}

fun deepCopy(original: List<CartItem>): List<CartItem> {
    val copy = mutableListOf<CartItem>()
    for ( item in original ) {
        // Here, we make a new copy of the item DATA
        // and not just a copy of there REFERENCE
        copy.add(item.copy())
    }
    return copy
}
// OR, just...
// = original.map { it.copy() }
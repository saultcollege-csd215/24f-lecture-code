package _04_stratified_design.megamart.stratified.cart


data class CartItem(val name: String, val price: Double)

fun addItem(cart: List<CartItem>, item: CartItem) =
    cart + item

// NOTE: Compare this method with the removeItemByName in the 'needswork' package.
// For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
fun removeItemByName(cart: List<CartItem>, name: String): List<CartItem> {
    val item = getItemByName(cart, name) ?: return cart

    return cart - item
}

// NOTE: Compare this method with the setPriceByName in the 'needswork' package.
// For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
fun setPriceByName(cart: List<CartItem>, name: String, price: Double): List<CartItem> {

    val item = getItemByName(cart, name) ?: return cart

    val updatedItem = item.copy(price = price)

    return cart.map { if (it.name == name) updatedItem else it }

}

fun deepCopyCart(cart: List<CartItem>) = cart.map { it.copy() }

fun calcTotal(cart: List<CartItem>) = cart.sumOf { it.price }

fun isNameInCart(cart: List<CartItem>, name: String): Boolean {

    // At first, we might write this function like this:
//    for ( item in cart ) {
//        if ( item.name == name ) {
//            return true;
//        }
//    }
//    return false

    // But after writing getItemByName, we can recognize that lookup-by-name pattern again
    // and reuse it to perform the necessary calculation here!
    return getItemByName(cart, name) != null

    // FYI, using Kotlin's 'any' would also be quite simple here.
    // any returns true if any of the items in the list are true for the given condition.
    // return cart.any { it.name == name }
}

// This method encapsulates an operation that is used in many places; now we can reuse it simply by calling the method!
fun getItemByName(cart: List<CartItem>, name: String) = cart.find { it.name == name }

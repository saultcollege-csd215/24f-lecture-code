package _03_staying_immutable.megaMart

/****************************
 * Most of the code below is as it was in the previous unit: _02_data_calculations_actions.megaMart.functional.Main
 *
 * New code that demonstrates points where there may be unsafe code is labeled as UNSAFE
 */


data class CartItem(val name: String, val price: Double)


fun addItemToCart(cart: List<CartItem>, name: String, price: Double): List<CartItem> {
    var updatedCart = addItem(cart, CartItem(name, price))

    val total = calcTotal(updatedCart)
    setDOMCartTotal(total)              // Update the DOM is an action
    updateDOMShippingIcons(updatedCart) // This too is still an action, but it now uses an explicit input rather than reading a global
    updateDOMTax(total)

    /*** UNSAFE:
     * The blackFridayPromotion function is outside our control,
     * and it might change mutate the given data, which we are trying to keep immutable.
     * We need to do some defensive copying!
     *
     */// REPLACE THIS...
    //blackFridayPromotion(updatedCart)
    /*** SAFE
     *
     */// WITH THIS...
    updatedCart = safeBlackFridayPromotion(updatedCart)

    return updatedCart

}

/*** SAFE
 * This function wraps a call to an unsafe function using deep copies
 * so that data interacting with the unsafe function can remain immutable.
 * I.e., the function is a calculation because it has no implicit inputs or outputs
 */
fun safeBlackFridayPromotion(cart: List<CartItem>): List<CartItem> {
    // Deep copy the given cart so that we don't mutate the original data
    var deepCopy = cart.map { it.copy() }
    // The external function expects a mutable list
    deepCopy = deepCopy.toMutableList()
    // This function makes in-place changes to the copy
    EXTERNAL_UNSAFE_blackFridayPromotion(deepCopy)
    // Return a deep copy of the modified data so there is no chance of
    // the external library having a reference to our returned data
    return deepCopy.map { it.copy() }
}

/*** UNSAFE
 * This function is meant to represent an external function from a library
 * that we do not control, and that might mutate the given data
 */
fun EXTERNAL_UNSAFE_blackFridayPromotion(cart: MutableList<CartItem>) {
    for (i in 0 until cart.size) {
        cart[i] = cart[i].copy(price=cart[i].price * 0.5)
    }
}

// This new function is a calculation, and thus easily testable and reusable
// (We now use addItem in a couple places: addItemToCart, and in updateShippingIcons to test for free shipping)
fun addItem(cart: List<CartItem>, item: CartItem) =
    cart + item  // Kotlin's + operator respects data immutability
// A new list is created with the new item added

/*** NEW since unit 02 ***/
/*** UNSAFE
 * This function is an action because it updates the given list in-place (an IMPLICIT OUTPUT)
 */// REPLACE THIS...
fun removeItemByName(cart: MutableList<CartItem>, name: String) {
    for ( item in cart ) {
        if (item.name == name) {
            cart.remove(item)
            return
        }
    }
}
/*** SAFE
 * This function is an action because it updates the given list in-place (an IMPLICIT OUTPUT)
 */// WITH THIS...
fun removeItemByName(cart: List<CartItem>, name: String): List<CartItem> {
    // Here we create a new list with the item removed...
    val newCart = mutableListOf<CartItem>()
    for ( item in cart ) {
        if (item.name != name) {
            newCart.add(item)
        }
    }
    return newCart
    // OR, just...
    // return cart.filter { it.name != name }
}

fun updateDOMShippingIcons(cart: List<CartItem>) {
    val buyButtons = getDOMBuyButtons() // Accessing the DOM is an action
    for (button in buyButtons) {
        if (getsFreeShipping(addItem(cart, button.item))) {
            println("Free shipping icon shown on ${button.item.name}")
        } else {
            println("Free shipping icon hidden on ${button.item.name}")
        }
    }
}

fun setDOMCartTotal(total: Double) {
    // Update the total in the DOM (an action)
    // e.g. document.getElementById("cart-total").innerText = total
    // To keep things simple here, we'll just print the change
    println("Updated cart total to $total")
}

fun updateDOMTax(total: Double) {
    // val tax = shoppingCartTotal * 0.13  // Reading a global variable is an action
    // By passing in the total as an explicit input, we can now calculate the tax without relying on a global variable
    println("Updated tax to ${calcTax(total)}")
}

// These are business rules encapsulated as calculations.
// It is often useful to place business rule calculations like this into pure functions
// so they may be easily tested and reused.
fun calcTax(total: Double) = total * 0.13
fun getsFreeShipping(cart: List<CartItem>) = calcTotal(cart) > 20.0
fun calcTotal(cart: List<CartItem>) = cart.sumOf { it.price }
// The sumOf is a reduce function that iterates over the list and sums the result of the lambda function for each item
// The above one-liner is equivalent to:
// fun calcTotal(cart: List<CartItem>): Double {
//     var total = 0.0
//     for (item in cart) {
//         total += item.price
//     }
//     return total
// }




data class BuyButton(val item: CartItem)
fun getDOMBuyButtons() =
// NOTE: This is just a simulation of something like
// document.getElementsByClassName("buy-button")

    // To keep things simple here, we just simulate with a list of 3 buttons
    listOf(
        BuyButton(CartItem("Item 1", 10.0)),
        BuyButton(CartItem("Item 2", 15.0)),
        BuyButton(CartItem("Item 3", 5.0))
    )
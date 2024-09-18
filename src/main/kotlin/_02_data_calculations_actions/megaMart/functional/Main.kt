package _02_data_calculations_actions.megaMart.functional

data class CartItem(val name: String, val price: Double)


fun addItemToCart(cart: List<CartItem>, name: String, price: Double): List<CartItem> {
    // Instead of updating a global variable, we now pass in the cart as an explicit input
    // and return a new cart with the new item added
    val updatedCart = addItem(cart, CartItem(name, price))

    calcCartTotal(updatedCart)  // This is still an action, but it now uses an explicit input rather than reading a global

    return updatedCart

}

// This new function is a calculation, and thus easily testable and reusable
// (We now use addItem in a couple places: addItemToCart, and in updateShippingIcons to test for free shipping)
fun addItem(cart: List<CartItem>, item: CartItem) =
    cart + item  // Kotlin's + operator respects data immutability
                 // A new list is created with the new item added

fun calcCartTotal(cart: List<CartItem>) {
    val total = calcTotal(cart)
    setDOMCartTotal(total)       // Update the DOM is an action
    updateDOMShippingIcons(cart) // This too is still an action, but it now uses an explicit input rather than reading a global
    updateDOMTax(total)
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
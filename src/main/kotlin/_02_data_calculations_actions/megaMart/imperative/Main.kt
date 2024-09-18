package _02_data_calculations_actions.megaMart.imperative

/**
 * NOTE: This is a simplified example to illustrate the process of identifying and separating calculations from actions.
 * It is not a complete program
 */

data class CartItem(val name: String, val price: Double)

var shoppingCart = mutableListOf<CartItem>()
var shoppingCartTotal = 0.0

fun addItemToCart(name: String, price: Double) {
    shoppingCart.add(CartItem(name, price))
    calcCartTotal()
}

fun calcCartTotal() {
    shoppingCartTotal = 0.0
    for (item in shoppingCart) {
        shoppingCartTotal += item.price  // Updating a global variable is an action
    }

    setDOMCartTotal(shoppingCartTotal)  // Updating the DOM is an action
    updateDOMShippingIcons()
    updateDOMTax()
}

fun updateDOMShippingIcons() {
    val buyButtons = getDOMBuyButtons()  // Accessing the DOM is an action

    for (button in buyButtons) {
        // There is a business rule embedded here (carts >= 20 get free shipping)
        // This code also requires the shoppingCartTotal to be initialized before testing
        if (button.item.price + shoppingCartTotal > 20.0) {
            // Can't test this code without a mock DOM
            // Update the DOM to show the free shipping icon (an action)
            // To keep things simple here, we'll just print the change
            println("Free shipping icon shown on ${button.item.name}")
        } else {
            // Update the DOM to hide the free shipping icon (an action)
            // To keep things simple here, we'll just print the change
            println("Free shipping icon hidden on ${button.item.name}")
        }
    }
}

fun updateDOMTax() {
    val tax = shoppingCartTotal * 0.08  // Reading a global variable is an action

    // Update the tax in the DOM (an action)
    // e.g. document.getElementById("tax").innerText = tax
    // To keep things simple here, we'll just print the change
    println("Updated tax to $tax")
}

fun setDOMCartTotal(total: Double) {
    // Update the total in the DOM (an action)
    // e.g. document.getElementById("cart-total").innerText = total
    // To keep things simple here, we'll just print the change
    println("Updated cart total to $total")
}

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
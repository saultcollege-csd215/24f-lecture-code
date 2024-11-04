package _04_stratified_design.megamart

/****************************
 * Most of the code below is as it was in the previous unit: _03_staying_immutable.megaMart
 *
 * Here, we highlight how the code can be thought of in terms of 'layers'
 * and suggest some improvements we could make
 */


/*************************************
 * LAYER: DOM operations
 */

fun addItemToCart(cart: List<CartItem>, name: String, price: Double): List<CartItem> {
    var updatedCart = addItem(cart, CartItem(name, price))

    val total = calcTotal(updatedCart)
    setDOMCartTotal(total)              // Update the DOM is an action
    updateDOMShippingIcons(updatedCart) // This too is still an action, but it now uses an explicit input rather than reading a global
    updateDOMTax(total)

    return updatedCart
}

fun deleteHandler(cart: List<CartItem>, name: String) {
    val updatedCart = removeItemByName(cart, name)

    // TODO: Both this function and addItemToCart have the same update logic.
    // This could be extracted into a separate function
    val total = calcTotal(updatedCart)
    setDOMCartTotal(total)
    updateDOMShippingIcons(updatedCart)
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

/************************************
 * LAYER: Marketing
 */
fun getsFreeShipping(cart: List<CartItem>) = calcTotal(cart) > 20.0

fun addFreeTieClip(cart: List<CartItem>): List<CartItem> {

    // TODO: After adding the 'isInCart' function to our 'cart operations' layer, this code can be simplified!
    var hasTie = false
    var hasTieClip = false

    // TODO: Note this pattern of looping through the cart items and finding an item by name; it shows up in several places.
    //      This is an opportunity for encapsulation and generalization!
    for (item in cart) {
        if (item.name == "tie") {
            hasTie = true
        }
        if (item.name == "tie clip") {
            hasTieClip = true
        }
    }

    if (hasTie && !hasTieClip) {
        return addItem(cart, CartItem("tie clip", 0.0))
    }
    return cart
}

/*************************************
 * LAYER: Business logic (cart)
 */

data class CartItem(val name: String, val price: Double)

fun addItem(cart: List<CartItem>, item: CartItem) =
    cart + item

// TODO: This function could be simplified if we had a getItemByName function in the 'cart' layer
fun removeItemByName(cart: List<CartItem>, name: String): List<CartItem> {
    val updatedCart = mutableListOf<CartItem>()
    // TODO: This lookup-by-name pattern shows up in multiple places. It could be extracted into a function
    for (item in cart) {
        if (item.name != name) {
            updatedCart.add(item)
        }
    }
    return updatedCart
}

fun setPriceByName(cart: List<CartItem>, name: String, price: Double): List<CartItem> {
    val updatedCart = mutableListOf<CartItem>()
    // TODO: And here it is again in a similar form, but slightly different than the above examples
    for (item in cart) {
        if (item.name == name) {
            updatedCart.add(CartItem(name, price))
        } else {
            updatedCart.add(item)
        }
    }
    return updatedCart
}

fun deepCopyCart(cart: List<CartItem>) = cart.map { it.copy() }

fun calcTotal(cart: List<CartItem>) = cart.sumOf { it.price }

// TODO: If we had a 'isNameInCart' function in this  layer, some of our code in higher layers can be simplified!

// TODO: If we had a 'getItemByName' function, some of our code above could be simplified!

// TODO: If we had a 'updateItem' function in this layer, the setPriceByName function can be simplified!


/*************************************
 * LAYER: Business logic (general)
 */
fun calcTax(total: Double) = total * 0.13


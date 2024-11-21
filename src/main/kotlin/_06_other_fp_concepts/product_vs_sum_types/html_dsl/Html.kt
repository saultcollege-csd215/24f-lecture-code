package _06_other_fp_concepts.product_vs_sum_types.html_dsl

/**
 * This file demonstrates the beginnings of a DSL for creating HTML documents.
 *
 * It makes use of the following class structure:
 * NOTE: this is obviously not a complete implementation of the entire HTML spec,
 * but it gives you a sense of how you might structure a DSL for creating HTML documents.
 *
 * sealed interface Node
 *      - data class Text(content)                    // Represents text content
 *      - data class Comment(content)                 // Represents an HTML comment
 *      - sealed class Element(tagName)               // Represents any actual HTML element
 *          - sealed class BlockElement(tagName)      // Represents a block-level HTML elements
 *              - class Html
 *              - class Head
 *              - class Body
 *              - class Title
 *              - class H1
 *              - class P
 *              - class Div
 *          - sealed class InlineElement(tagName)     // Represents an inline HTML elements
 *              - class A
 *              - class Span
 */

fun main() {

    // With our DSL, we have a concise way of creating HTML documents
    // AND if the DSL is implemented correctly, the compiler can help us ensure that the HTML is valid
    // For example, we simply cannot add any element other than head or body to the html element.
    // The compiler will also warn us if we forget to close a tag.
    // The DSL itself ensures that tags are properly nested according to the structure specified by our code here.

    // html is function that takes a lambda as an argument and applies all code in the lambda to a new Html object
    val webpage = html {
        // Inside this lambda 'this' is a new Html element (see the use of 'apply' in the html function)

        // We can set attributes on Elements using the square bracket syntax
        // because we have defined the 'set' operator on the Element class
        this["lang"] = "en"

        // The comment method is a convenience method for adding comments to the HTML
        // It is defined in the Element class
        comment("This is a comment")

        // The 'head' and 'body' methods are defined on the Html class
        // so we can call them from inside here because 'this' is an Html object here
        head {
            // Inside this lambda, 'this' is now a Head object (see the Html.head function)
            // The 'title' method is only defined in the Head class
            title { text("HTML encoding with Kotlin") }
        }
        body {
            // This won't work because 'title' is not a method on Body objects:
            // title { text( "NOPE") }

            // This does, though: 'div' is a method in Body
            // It happens to take an optional 'className' argument that allows us to set the class attribute
            div(className="container") {
                div {
                    h1(style="color: red") { text("Hello, world!") }
                    p { text("Welcome to a ${ span { text("super") } } web page") }
                }
                div {
                    p {
                        text("A ${ a(href="https://www.example.com", linkText="link") }, and another link: ${ a("https://www.example.com") } ")
                    }
                }
            }
        }
    }
    println(webpage)
}

// The 'entry point' of our DSL:
// The contents parameter must be a function that takes has no arguments (parameterless) and returns nothing (void),
// but it must also assume that 'this' inside the function is an Html object, aka Html must be the 'receiver'
// The syntax Html.() -> Unit is how you  say "a parameterless void function with an Html object as receiver"
// Note that the body of the function creates a new Html object, then applies the contents lambda to it
// The nature of the apply function is that it sets the receiver of the lambda to the object it is called on.
// I.e., inside the contents lambda here, 'this' will be the new Html object, which is why the lambda may
// contain calls to methods available to Html objects:
// html {
//     comment("This is an HTML comment")   // comment is a method on Element, which Html is a subclass of
//     body {                               // body is a method on Html
//         text("Hello, world!")            // Here, we're in a new lambda, where 'this' is a Body object
//     }
// }
fun html(contents: Html.() -> Unit): Html = Html().apply(contents)

// The root of our DSL's data structure
sealed interface Node

// Represents text content of elements
data class Text(val content: String): Node {
    override fun toString() = content
}

// Represents HTML comments
data class Comment(val content: String): Node {
    override fun toString() = "<!-- $content -->"
}

// Represents any HTML element
sealed class Element(private val tagName: String): Node {
    protected val children: MutableList<Node> = mutableListOf()   // The children of this element
    protected val attributes: Attrs = mutableMapOf()              // This element's attributes

    protected fun addChild(node: Node) {
        children.add(node)
    }

    // Any element may have a text or comment node as a child
    // These functions are part of the DSL that allows us to do things like this:
    // html {
    //      text("Some text")
    //      comment("An HTML comment")
    // }
    fun text(t: String) = addChild(Text(t))
    fun comment(c: String) = addChild(Comment(c))

    // Allow for element attributes to be accessed like a map
    // E.g.  element["href"] = "http://example.com"
    // In Kotlin, this is done by defining the 'set' operator (note the OPERATOR keyword here)
    operator fun set(key: String, value: String) {
        if ( value.isEmpty() ) {
            attributes.remove(key)
        } else {
            attributes[key] = value
        }
    }

    /**
     * @return true if this element has only one child and that child is a Text node
     */
    fun hasOnlyOneTextNode() = children.size == 1 && children[0] is Text
}

sealed class BlockElement(private val tagName: String) : Element(tagName) {
    /**
     * @return a string representation of this element and its children, with nice indentation
     */
    override fun toString(): String {
        val sb = StringBuilder()      // A StringBuilder is more efficient than concatenating strings
        sb.append("<$tagName")
        sb.append(attributes.toHtmlAttrs())  // See the Attr helpers below
        if (children.isEmpty()) {
            sb.append(" />\n")
        } else {
            // Put the whole element on one line if it only contains a text node
            if ( this.hasOnlyOneTextNode() ) {
                sb.append(">" + children[0].toString() + "</$tagName>")
            } else {
                // Otherwise, put each child on its own line with proper indentation
                sb.append(">\n")
                for (child in children) {
                    val indentedChildStr = "  " + child.toString().replace("\n", "\n  ")
                    sb.append("$indentedChildStr\n")
                }
                sb.append("</$tagName>")
            }
        }
        return sb.toString()
    }
}

sealed class InlineElement(private val tagName: String): Element(tagName) {
    /**
     * @return a string representation of this element and its children
     */
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("<$tagName")
        sb.append(attributes.toHtmlAttrs())
        if (children.isEmpty()) {
            sb.append(" />")
        } else {
            // Inline elements are rendered on a single line, so no fanciness around indentation required here.
            sb.append(">")
            for (child in children) {
                sb.append(child.toString())
            }
            sb.append("</$tagName>")
        }
        return sb.toString()
    }
}

class Html: BlockElement("html") {
    // The 'head' and 'body' methods are defined on the Html class
    // Meaning that they are only available to Html objects, and only these elements
    // can be added to Html elements. Arranging our DSL in this way allows us to enforce
    // which elements may be nested inside which other elements.
    //
    // The 'contents' parameter here works as it does above in the html function
    // (See the documentation there for how they syntax works here.)
    // One difference between the html function and the head/body methods is the object being created.
    // In the html function, a new Html object is created.  Here a Head or Body object is being created.
    // The apply function then applies the contents lambda to that new object.
    // Another difference to note is that unlike the html function above,
    // we are also using the 'also' method to add the new Head/Body object
    // as a child of 'this' (which is an Html object here because we are in the Html class)
    fun head(contents: Head.() -> Unit) = Head().apply(contents).also { addChild(it) }
    fun body(contents: Body.() -> Unit) = Body().apply(contents).also { addChild(it) }
}

class Head: BlockElement("head") {
    fun title(contents: Title.() -> Unit) = Title().apply(contents).also { addChild(it) }
}

class Body : BlockElement("body") {
    // Including an optional parameter for className here allows the DSL to have a simple way
    // to set the class attribute on a div element. This could be done with any attribute on all
    // elements, and is another way that the DSL can help enforce validity (only allowing certain
    // attributes on certain elements).
    fun div(className: String="", contents: Div.() -> Unit) = Div().apply {
        this["class"] = className
        contents()
    }.also { addChild(it) }
}

class Title : BlockElement("title")
class H1 : BlockElement("h1")
class P : BlockElement("p") {
    fun span(contents: Span.() -> Unit) = Span().apply(contents)

    // A special case for <a> elements that makes it simpler to create links than using the usual lambda syntax
    fun a(href: String, linkText: String="") = A().apply {
        this["href"] = href
        text(linkText.ifEmpty { href })
    }
}
class Div : BlockElement("div") {
    fun h1(style: String="", contents: H1.() -> Unit) = H1().apply {
        this["style"] = style
        contents()
    }.also { addChild(it) }
    fun p(contents: P.() -> Unit) = P().apply(contents).also { addChild(it) }

    // Note that divs are allowed in both Body and Div elements, so we have this same
    // function in both classes
    fun div(className: String="", contents: Div.() -> Unit) = Div().apply {
        this["class"] = className
        contents()
    }.also { addChild(it) }
}
class A : InlineElement("a")
class Span : InlineElement("span")

// Some helpers to deal with HTML attributes:
// A typealias that allows us to say 'Attrs' instead of MutableMap<String, String>
typealias Attrs = MutableMap<String, String>
// An extension method on Strings to escape special characters in attributes
fun String.attrEscape() = this.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;")
// An extension method on our Attrs type to convert the data to an HTML attribute string
fun Attrs.toHtmlAttrs(): String {
    if (isEmpty()) {
        return ""
    }

    return " " + map { (name, value) -> "${name.attrEscape()}=\"${value.attrEscape()}\"" }.joinToString(" ")
}

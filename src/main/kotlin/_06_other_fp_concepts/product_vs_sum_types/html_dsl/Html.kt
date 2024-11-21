package _06_other_fp_concepts.product_vs_sum_types.html_dsl

typealias Attrs = MutableMap<String, String>

fun String.attrEscape() = this.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;")
fun Attrs.toHtmlAttrs(): String {
    if (isEmpty()) {
        return ""
    }

    return " " + map { (name, value) -> "${name.attrEscape()}=\"${value.attrEscape()}\"" }.joinToString(" ")
}

sealed interface Node {
    fun toString(indent: Int = 0) = when (this) {
        is Text -> " ".repeat(indent) + content
        is Comment -> " ".repeat(indent) + "<!-- $content -->"
        is BlockElement -> toHtmlString(indent)
        is InlineElement -> (this as InlineElement).toString()
    }
}

data class Text(val content: String): Node {
    override fun toString() = content
}
data class Comment(val content: String): Node

sealed class Element(private val tagName: String): Node {
    protected val children: MutableList<Node> = mutableListOf()
    protected val attributes: Attrs = mutableMapOf()

    open fun addChild(node: Node) {
        children.add(node)
    }

    fun text(t: String) = addChild(Text(t))
    fun comment(c: String) = addChild(Comment(c))

    // Allow for element attributes to be accessed like a map
    // E.g.  element["href"] = "http://example.com"
    fun get(key: String): String? = attributes[key]
    operator fun set(key: String, value: String) { attributes[key] = value }

}

sealed class BlockElement(private val tagName: String) : Element(tagName) {
    override fun toString() = toHtmlString(0)
    fun toHtmlString(indent: Int): String {
        val sb = StringBuilder()
        val i = " ".repeat(indent)
        sb.append("$i<$tagName")
        sb.append(attributes.toHtmlAttrs())
        if (children.isEmpty()) {
            sb.append(" />\n")
        } else {
            if ( children.size == 1 && children[0] is Text) {
                sb.append(">" + children[0].toString() + "</$tagName>")
            } else {
                sb.append(">\n")
                for (child in children) {
                    sb.append(child.toString(indent + 2) + "\n")
                }
                sb.append("$i</$tagName>")
            }
        }
        return sb.toString()
    }
}

sealed class InlineElement(private val tagName: String): Element(tagName) {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("<$tagName")
        sb.append(attributes.toHtmlAttrs())
        if (children.isEmpty()) {
            sb.append(" />")
        } else {
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
    fun head(contents: Head.() -> Unit) = Head().apply(contents).also { addChild(it) }
    fun body(contents: Body.() -> Unit) = Body().apply(contents).also { addChild(it) }
}

class Head: BlockElement("head") {
    fun title(contents: Title.() -> Unit) = Title().apply(contents).also { addChild(it) }
}

class Body : BlockElement("body") {
    fun h1(style: String="", contents: H1.() -> Unit) = H1().apply(contents).also { addChild(it) }
    fun p(contents: P.() -> Unit) = P().apply(contents).also { addChild(it) }
}

class Title : BlockElement("title")
class H1 : BlockElement("h1")
class P : BlockElement("p") {
    fun span(contents: Span.() -> Unit) = Span().apply(contents)
    fun a(href: String, linkText: String="") = A().apply {
        this["href"] = href
        text(linkText.ifEmpty { href })
    }
}
class A(href: String="") : InlineElement("a")
class Span : InlineElement("span")


fun html(contents: Html.() -> Unit): Html = Html().apply(contents)


fun main() {
    val webpage = html {
        this["lang"] = "en"
        comment("This is a comment")
        head {
            title { text("HTML encoding with Kotlin") }
        }
        body {
            h1(style="color: red") { text("Hello, world!") }
            p { text("Welcome to a ${ span { text("super") } } web page") }
            p {
                text("A ${ a(href="https://www.example.com", linkText="link") }, and another link: ${ a("https://www.example.com") } ")
            }
        }
    }
    println(webpage)
}
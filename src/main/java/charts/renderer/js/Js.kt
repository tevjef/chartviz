package charts.renderer.js

interface JsValue {
    fun asJsValue(): JsValue
    fun asString(): String {
        return asJsValue().toString()
    }
}

interface JsLiteral : JsValue, JsVariableDeclarationValue

class JsString(private val string: String) : JsLiteral {
    override fun asJsValue(): JsValue = this
    override fun toString(): String = "'$string'"
}

class JsNumber(private val number: Float) : JsLiteral {
    override fun asJsValue(): JsValue = this
    override fun toString(): String = number.toString()
}

class JsIdentifier(val id: String) : JsValue {
    override fun asJsValue(): JsValue = this
    override fun toString(): String = id
}

class JsProperty(private val key: JsIdentifier, private val value: JsValue) : JsValue {
    override fun asJsValue(): JsValue = this
    override fun toString(): String {
        return "$key: $value"
    }
}

open class JsArray(private vararg val values: JsValue) : JsLiteral, JsVariableDeclarationValue {
    override fun asJsValue(): JsValue = this
    override fun toString(): String {
        return "[${values.joinToString(", ")}]"
    }
}

open class JsObject(private vararg val values: JsProperty) : JsLiteral, JsVariableDeclarationValue  {
    override fun asJsValue(): JsValue = this
    override fun toString(): String {
        return "{${values.joinToString(", ")}}"
    }
}

interface JsVariableDeclarationValue : JsValue

class JsVariableDeclaration(val id: JsIdentifier, val init: JsVariableDeclarationValue): JsValue {
    override fun asJsValue(): JsValue = this
    override fun toString(): String {
        return "var $id = ${init.asJsValue().asString()};"
    }
}
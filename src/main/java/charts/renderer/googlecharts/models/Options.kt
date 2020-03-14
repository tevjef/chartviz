package charts.renderer.googlecharts.models

import charts.renderer.js.*

class Options(private val chart: Chart, val bars: String) : JsValue {
    class Chart(private val title: String, private val subtitle: String) : JsObject() {
        override fun asJsValue(): JsValue {
            return JsProperty(
                    JsIdentifier("chart"), JsObject(
                    JsProperty(JsIdentifier("title"), JsString(title)),
                    JsProperty(JsIdentifier("subtitle"), JsString(subtitle))
            )
            )
        }
    }

    override fun asJsValue(): JsValue {
        return JsVariableDeclaration(JsIdentifier("options"),
                JsObject(
                        JsProperty(
                                JsIdentifier("bars"), JsString(bars)
                        ),
                        chart.asJsValue() as JsProperty
                )
        )
    }

    override fun toString(): String {
        return asJsValue().toString()
    }
}

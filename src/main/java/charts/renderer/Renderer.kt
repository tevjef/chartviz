package charts.renderer

import charts.data.BarChart

interface Renderer {

    fun render(chart: BarChart): String
}

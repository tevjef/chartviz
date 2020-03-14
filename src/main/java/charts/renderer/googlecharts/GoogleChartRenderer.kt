package charts.renderer.googlecharts

import charts.data.BarChart
import charts.data.BarDataSet
import charts.renderer.Renderer
import charts.renderer.googlecharts.models.Options
import charts.renderer.js.JsArray
import charts.renderer.js.JsNumber
import charts.renderer.js.JsString

class GoogleChartRenderer : Renderer {

    fun generateHtml(options: Options, data: JsArray): String {

        return """
        <html>
        <head>
          <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

          <script type="text/javascript">
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawMaterial);
            
            function drawMaterial() {
                  var data = google.visualization.arrayToDataTable($data);
            
                  $options
                  
                  var materialChart = new google.charts.Bar(document.getElementById('chart_div'));
                  materialChart.draw(data, options);
          }
          </script>

        <div id='chart_div'></div>
        </head>
        </html>
    """.trimIndent()
    }

    override fun render(chart: BarChart): String {
        val options = Options(Options.Chart("Some chart title", "subtitle"), bars = "horizontal")
        return generateHtml(options, chart.render())
    }

    private fun BarChart.render(): JsArray {
        return JsArray(
                JsArray(JsString("X Axis"), *this.data.first().stackLabels.map { JsString(it) }.toTypedArray()),
                *this.data.map { it.render() }.toTypedArray())
    }

    private fun BarDataSet.render(): JsArray {
        return JsArray(JsString(name), *entries.map { JsNumber(it.y)}.toTypedArray())
    }
}

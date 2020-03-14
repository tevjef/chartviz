package charts.data

data class BarDataSet(val entries: List<BarEntry>, val name: String) {
    var stackLabels: List<String> = emptyList()
}

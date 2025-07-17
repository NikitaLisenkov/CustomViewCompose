package com.example.customviewcompose.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.customviewcompose.data.Bar
import kotlin.math.roundToInt


private const val MIN_VISIBLE_BARS_COUNT = 20

@Composable
fun BarsScreen() {

    val viewModel: BarsViewModel = viewModel()
    val screenState = viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = screenState.value) {

        is ScreenState.Content -> {
            Terminal(bars = currentState.bars)
        }

        is ScreenState.Error -> {}
        is ScreenState.Loading -> {}
    }

}


@Composable
fun Terminal(bars: List<Bar>) {

    var scrolledBy by remember {
        mutableStateOf(0f)
    }

    var visibleBarsCount by remember {
        mutableStateOf(100)
    }

    var terminalWidth by remember {
        mutableStateOf(0f)
    }

    val barWidth by remember {
        derivedStateOf {
            terminalWidth / visibleBarsCount
        }
    }

    val visibleBars by remember {
        derivedStateOf {
            val startIndex = (scrolledBy / barWidth).roundToInt().coerceAtLeast(0)
            val endIndex = (startIndex + visibleBarsCount).coerceAtMost(bars.size)
            bars.subList(fromIndex = startIndex, toIndex = endIndex)
        }
    }


    val transformableState = TransformableState { zoomChange, panChange, _ ->
        visibleBarsCount = (visibleBarsCount / zoomChange)
            .roundToInt()
            .coerceIn(MIN_VISIBLE_BARS_COUNT, bars.size)

        scrolledBy = (scrolledBy + panChange.x)
            .coerceAtLeast(0f)
            .coerceAtMost(bars.size * barWidth - terminalWidth)

    }
    /**
     * Нам нужно каждую свечку распологать справа от предыдущей, свеча с индексом 0 будет раположена в левой части экрана,
     * свеча с индексом 1 правее и т.д. Для рассчета отступа мы взяли ширину экрана и разделили на кол-во свечей. После этого
     * индекс свечи умножаем на ее ширину и получаем необходимый отступ
     * Нижняя часть свечи - минимальная цена, верняя часть - максимальная цена
     * Для этого мы от мин цены отнимаем минимальную цену за всю историю
     * Для того, чтобы график занимал весь экран, нужно рассчитать кол-во пикселей на 1 доллар: мы получили весь
     * диапазон цен - от макс цены отняли мин, чтобы было понятно сколько "долларов" отобразить на всем экране, всю высоту экрана
     * разделили на полученное кол-во. Затем мы берем цену, умножаем ее на кол-во пикселей на 1 пункт и из высоты экрана вычитаем полученное кол-во
     */
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .transformable(transformableState)
    ) {
        terminalWidth = size.width
        val maxPrice = visibleBars.maxOf { it.high }
        val minPrice = visibleBars.minOf { it.low }
        val pxPerPoint = size.height / (maxPrice - minPrice)

        translate(left = scrolledBy) {
            bars.forEachIndexed { index, bar ->
                val offsetX = size.width - index * barWidth
                drawLine(
                    color = Color.White,
                    start = Offset(offsetX, size.height - ((bar.low - minPrice) * pxPerPoint)),
                    end = Offset(offsetX, size.height - ((bar.high - minPrice) * pxPerPoint)),
                    strokeWidth = 1f
                )
                drawLine(
                    color = if (bar.open < bar.close) Color.Green else Color.Red,
                    start = Offset(offsetX, size.height - ((bar.open - minPrice) * pxPerPoint)),
                    end = Offset(offsetX, size.height - ((bar.close - minPrice) * pxPerPoint)),
                    strokeWidth = barWidth / 2
                )
            }
        }
    }
}
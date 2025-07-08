package com.example.customviewcompose.presentation.samples

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

private fun DrawScope.oleg() {
    drawCircle(
        center = Offset(200f, 200f),
        color = Color.White,
        radius = 38.dp.toPx(),
        style = Stroke(width = 3f)
    )
    drawLine(
        color = Color.White,
        start = Offset(330f, 300f),
        end = Offset(400f, 120f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(400f, 120f),
        end = Offset(500f, 300f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(560f, 100f),
        end = Offset(560f, 300f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(560f, 100f),
        end = Offset(700f, 100f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(560f, 200f),
        end = Offset(700f, 200f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(560f, 300f),
        end = Offset(700f, 300f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(800f, 100f),
        end = Offset(800f, 300f),
        strokeWidth = 1.4.dp.toPx()
    )
    drawLine(
        color = Color.White,
        start = Offset(800f, 100f),
        end = Offset(930f, 100f),
        strokeWidth = 1.4.dp.toPx()
    )
}
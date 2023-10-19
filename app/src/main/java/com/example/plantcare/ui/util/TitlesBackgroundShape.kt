package com.example.plantcare.ui.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TitlesBackgroundShape(
    private val width: Float,
    private val height: Float): Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density,
        ) = Outline.Generic(Path().apply {
            reset()
            moveTo(0f, 0f)

            cubicTo(0f, 1f, width/2, -70f, width*3.1f, height)
            cubicTo(width*3.5f, height, width/2, height*3.5f, 0f, height*2.1f)

            close()
        })
}


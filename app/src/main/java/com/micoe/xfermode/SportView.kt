package com.micoe.xfermode

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

private val RADIUS = 150.dp
private val RING_WIDTH = 20.dp

class SportView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val bounds = Rect()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 80.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制环
        paint.style = Paint.Style.STROKE
        paint.color = Color.GRAY
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        //绘制进度条
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        paint.style = Paint.Style.FILL
        paint.getTextBounds("zhaofei", 0, "zhaofei".length, bounds)
        canvas.drawText("zhaofei", width/2f, height / 2f - (bounds.top + bounds.bottom) / 2, paint)
    }
}
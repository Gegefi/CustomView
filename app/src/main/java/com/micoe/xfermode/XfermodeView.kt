package com.micoe.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 20f.dp
private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

class XfermodeView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(
        150f.dp,
        50f.dp,
        300f.dp,
        200f.dp
    )

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds,null)
        paint.color = Color.parseColor("#34567822")
        paint.color = Color.RED
        canvas.drawOval(200f.dp, 50f.dp, 300f.dp, 150f.dp, paint)
        paint.xfermode = XFERMODE
        paint.color = Color.BLUE
        canvas.drawRect(150f.dp, 100f.dp, 250f.dp, 200f.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }

    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options();
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.goddess, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.drawable.goddess, options)
    }
}
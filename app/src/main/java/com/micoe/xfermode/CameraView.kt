package com.micoe.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 100.dp

class CameraView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(BITMAP_SIZE)
    private val camera = Camera()
    private val clipped = Path().apply {
        addOval(
            BITMAP_PADDING,
            BITMAP_PADDING,
            BITMAP_PADDING + BITMAP_SIZE,
            BITMAP_PADDING + BITMAP_SIZE, Path.Direction.CCW
        )
    }

    init {
        camera.rotateX(40f)
        camera.setLocation(0f, 0f, -5 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        //范围裁切
//        canvas.clipRect(
//            BITMAP_PADDING,
//            BITMAP_PADDING,
//            BITMAP_PADDING + BITMAP_SIZE / 2,
//            BITMAP_PADDING + BITMAP_SIZE / 2
//        )
//        canvas.clipPath(clipped)

        //上半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.clipRect(-BITMAP_SIZE / 2, -BITMAP_SIZE / 2, BITMAP_SIZE / 2, 0f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        //几何变换
        //下半部分
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE / 2, 0f, BITMAP_SIZE / 2, BITMAP_SIZE / 2)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()
    }

    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.goddess, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.drawable.goddess, options)
    }
}
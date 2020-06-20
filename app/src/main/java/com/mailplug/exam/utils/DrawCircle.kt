package com.mailplug.exam.utils

import android.view.View
import android.widget.ImageView
import com.mailplug.exam.Appbase.Companion.appContext
import com.mailplug.exam.R

class DrawCircle {

    fun makeCircle(imageView : ImageView, color: String) {
        imageView.visibility = View.VISIBLE
//        val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        canvas.drawColor(Color.WHITE)
//        val paint = Paint()
//        paint.color = Color.RED
//        val rect = RectF()
//        rect.set(160.toFloat(), 140.toFloat(), 360.toFloat(), 640.toFloat())
//        canvas.drawArc(rect, 0.toFloat(), 360.toFloat(), true, paint)

//        imageView.setImageBitmap(bitmap)
        when (color) {
            "y" -> imageView.setImageDrawable(appContext.getDrawable(R.drawable.circle_yellow))
            "b" -> imageView.setImageDrawable(appContext.getDrawable(R.drawable.circle_blue))
            "r" -> imageView.setImageDrawable(appContext.getDrawable(R.drawable.circle_red))
        }
    }
}
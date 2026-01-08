package com.example.ui.ext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.use
import androidx.core.graphics.drawable.DrawableCompat

internal fun View.visible() {
  this.visibility = View.VISIBLE
}

internal fun View.gone() {
  this.visibility = View.GONE
}

internal fun View.dpToPx(dp: Int): Float {
  val resources = context.resources
  val metrics = resources.displayMetrics
  return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

internal inline fun View.readAttributes(
  attrs: AttributeSet?,
  styleableArray: IntArray,
  block: (TypedArray) -> Unit
) {
  val typedArray = context.theme.obtainStyledAttributes(attrs, styleableArray, 0, 0)
  typedArray.use(block)
}

internal fun <T : View> T.showIfWithBlock(predicate: Boolean, block: T.() -> Unit) {
  if (predicate) {
    visible()
    block()
  } else {
    gone()
  }
}

internal fun Context.getDrawableCompat(@DrawableRes id: Int) =
  AppCompatResources.getDrawable(this, id)

internal fun Drawable.tint(colorInt: Int): Drawable {
  return DrawableCompat.wrap(this).apply {
    DrawableCompat.setTint(this, colorInt)
  }
}

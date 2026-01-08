package com.example.ui.icon

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.ui.ext.getDrawableCompat
import com.example.ui.ext.tint
import com.nutapos.nutatest.core.ui.R


object IconManager {

    fun getIconDrawable(context: Context, icon: Icon, tintColorToken: Int): Drawable {
        val drawable = context.getDrawableCompat(icon.getDrawableResId())
            ?: requireNotNull(context.getDrawableCompat(R.drawable.ui_icon_placeholder))
        drawable.mutate()
        return drawable.tint(tintColorToken)
    }
}
package com.example.ui.text

import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat

const val SUPPORT_FONT_SCALING_DEFAULT = true

fun TextView.setStyle(
    @StyleRes style: Int,
    useLineSpacing: Boolean = false,
) {
    setStyle(
        style = style,
        useLineSpacing = useLineSpacing,
        supportFontScaling = SUPPORT_FONT_SCALING_DEFAULT,
    )
}

fun TextView.setStyle(
    @StyleRes style: Int,
    useLineSpacing: Boolean = false,
    supportFontScaling: Boolean,
) {
    TextViewCompat.setTextAppearance(this, style)
    if (!supportFontScaling) {
        val fontScale = resources.configuration.fontScale
        val scaledTextSize = textSize
        val newTextSize = scaledTextSize / fontScale
        setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize)
    }
    // Duplicate of TextViewCompat.setLineHeight but with float values to be more accurate
    if (useLineSpacing) {
        val attrs = intArrayOf(android.R.attr.lineSpacingExtra)
        val typedArray = context.theme.obtainStyledAttributes(style, attrs)
        val styleLineSpacing = typedArray.getDimension(0, lineSpacingExtra)
        setLineSpacing(styleLineSpacing, 1f)
        typedArray.recycle()
    }
}
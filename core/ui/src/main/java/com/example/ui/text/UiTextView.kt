package com.example.ui.text

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.nutapos.nutatest.core.ui.R

class UiTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    var textStyle: TypographyStyle = TypographyStyle.BODY_SMALL_REGULAR_DEFAULT
        set(value) {
            field = value
            try {
                setStyle(value.style, true, supportFontScaling)
            } catch (exception: Exception) {}
        }

    var supportFontScaling: Boolean = false
        set(value) {
            field = value
            textStyle = textStyle
        }

    private var spacingAdd = 0f

    init {
        attrs?.let { attributeSet ->
          context.withStyledAttributes(
            attributeSet,
            R.styleable.UiTextView,
            0,
            0,
          ) {
            supportFontScaling =
              getBoolean(R.styleable.UiTextView_supportFontScaling, SUPPORT_FONT_SCALING_DEFAULT)
            val styleOrdinal = getInt(
              R.styleable.UiTextView_textStyle,
              TypographyStyle.BODY_SMALL_REGULAR_DEFAULT.ordinal
            )
            textStyle = typographyStyleValues[styleOrdinal]
          }
        }
    }

    override fun setLineSpacing(add: Float, mult: Float) {
        super.setLineSpacing(add, mult)
        spacingAdd = add
    }

    companion object {
        private val typographyStyleValues = TypographyStyle.values()
    }
}

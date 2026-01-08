package com.example.ui.icon

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
import com.example.ui.ext.dpToPx
import com.example.ui.ext.tint
import com.example.ui.ext.readAttributes
import com.nutapos.nutatest.core.ui.R


class UiIconView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
  AppCompatImageView(context, attrs) {

  enum class IconSize(val id: Int) {
    REGULAR(0),
    LARGE(1)
  }

  init {
    readAttributes(attrs, R.styleable.UiIconView) { typedArray ->
      val iconNameIndex = typedArray.getInt(R.styleable.UiIconView_ui_icon_name, -1)
      val tintColor = typedArray.getColor(
        R.styleable.UiIconView_ui_icon_color_token,
        ContextCompat.getColor(context, R.color.ui_icon_dynamic_default)
      )
      val iconSize = typedArray.getInt(
        R.styleable.UiIconView_ui_icon_size,
        IconSize.REGULAR.id
      )

      if (iconSize == IconSize.LARGE.id) {
        doOnPreDraw {
          updateLayoutParams {
            width = dpToPx(ICON_SIZE_LARGE).toInt()
            height = dpToPx(ICON_SIZE_LARGE).toInt()
          }
        }
      }

      if (iconNameIndex != -1) {
        val iconName = Icon.values()[iconNameIndex]
        setIcon(iconName, tintColor)
      }
    }
  }

  fun setIcon(iconName: Icon, tintColorToken: Int) {
    setImageDrawable(IconManager.getIconDrawable(context, iconName, tintColorToken))
  }

  fun setTint(tintColorToken: Int) {
    drawable.mutate().tint(tintColorToken)
  }

  companion object {
    private const val ICON_SIZE_LARGE = 24
  }
}
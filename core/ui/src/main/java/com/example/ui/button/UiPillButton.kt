package com.example.ui.button

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.ui.ext.dpToPx
import com.example.ui.icon.Icon
import com.example.ui.icon.IconManager
import com.nutapos.nutatest.core.ui.R

class UiPillButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.style.Widget_AppCompat_Button
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        initDefaultStyle()

        renderButtonView(
            bgResoource = R.drawable.bg_btn_pill_secondary,
            selectorTextColor = R.color.selector_btn_txt_secondary
        )

        val customAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.UiPillButton, 0, 0)

        with(customAttributes) {
            val iconName = getInt(R.styleable.UiPillButton_ui_icon_name, -1)
            if (iconName != -1) {
                val icon = Icon.values()[iconName]
                setIcon(icon)
            }
        }

        setTextSize()
    }

    private fun renderButtonView(
        bgResoource: Int,
        selectorTextColor: Int
    ) {
        setBackgroundResource(bgResoource)
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                selectorTextColor
            )
        )
    }

    private fun setTextSize() {
        setPadding(
            context.resources.getDimensionPixelSize(R.dimen.spacing_x12),
            context.resources.getDimensionPixelSize(R.dimen.spacing_x8),
            context.resources.getDimensionPixelSize(R.dimen.spacing_x12),
            context.resources.getDimensionPixelSize(R.dimen.spacing_x8)
        )
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
    }

    private fun initDefaultStyle() {
        isClickable = true
        gravity = Gravity.CENTER
    }

    override fun setEnabled(enabled: Boolean) {
        setTintState(enabled)
        super.setEnabled(enabled)
    }

    private fun setTintState(enabled: Boolean) {
        if (enabled) {
            compoundDrawables.forEach {
                it?.setTint(ContextCompat.getColor(context, R.color.success_6_color))
            }
        } else {
            compoundDrawables.forEach {
                it?.setTint(ContextCompat.getColor(context, R.color.success_3_color))
            }
        }
    }

    fun setIcon(icon: Icon) {
        val drawable = IconManager.getIconDrawable(context,
            icon,
            ContextCompat.getColor(context, R.color.success_6_color)
        )
        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        compoundDrawablePadding = dpToPx(4).toInt()
    }

}
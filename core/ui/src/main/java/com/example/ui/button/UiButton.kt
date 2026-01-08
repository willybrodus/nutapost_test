package com.example.ui.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.ui.ext.gone
import com.example.ui.icon.IconData
import com.example.ui.icon.IconManager
import com.example.ui.ext.visible
import com.nutapos.nutatest.core.ui.R

@SuppressLint("CustomViewStyleable")
class UiButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.style.Widget_AppCompat_Button
) : AppCompatButton(context, attrs, defStyleAttr) {

    enum class ButtonType(val id: Int) {
        PRIMARY(0),
        SECONDARY(1),
        TERTIARY(2),
        PRIMARY_FULL_WIDTH(3),
        PRIMARY_DESTRUCTIVE(4)
    }

    enum class ButtonSize(val id: Int) {
        SMALL(0),
        MEDIUM(1),
        LARGE(2)
    }

    enum class IconPosition {
        LEFT,
        RIGHT
    }

    private var progressDrawable: Drawable? = null
    private var buttonSize: Int = ButtonSize.MEDIUM.id

    private var isLoading: Boolean = false
        set(value) {
            if (isLoading == value) return
            field = value

            val (startDrawable, _, endDrawable, bottomDrawable) = compoundDrawablesRelative
            if (value) {
                textSize = 0F
                setCompoundDrawablesRelative(
                    startDrawable,
                    progressDrawable,
                    endDrawable,
                    bottomDrawable
                )
                (progressDrawable as? Animatable)?.start()
            } else {
                setTextSize()
                setCompoundDrawablesRelative(
                    startDrawable,
                    null,
                    endDrawable,
                    bottomDrawable
                )
                (progressDrawable as? Animatable)?.stop()
            }
        }

    override fun onDetachedFromWindow() {
        (progressDrawable as? Animatable)?.stop()
        super.onDetachedFromWindow()
    }

    init {
        initDefaultStyle()

        val customAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.UiButton, 0, 0)

        with(customAttributes) {
            when (getInt(R.styleable.UiButton_btnType, ButtonType.PRIMARY.id)) {
                ButtonType.PRIMARY.id ->
                    renderButtonView(
                        bgResoource = R.drawable.bg_btn_primary,
                        selectorTextColor = R.color.selector_btn_txt_primary,
                        loadingColor = R.color.btn_text_primary_default
                    )

                ButtonType.SECONDARY.id ->
                    renderButtonView(
                        bgResoource = R.drawable.bg_btn_secondary,
                        selectorTextColor = R.color.selector_btn_txt_secondary,
                        loadingColor = R.color.btn_text_tertiary_default
                    )

                ButtonType.TERTIARY.id ->
                    renderButtonView(
                        bgResoource = R.drawable.bg_btn_tertiary,
                        selectorTextColor = R.color.selector_btn_txt_tertiary,
                        loadingColor = R.color.btn_text_secondary_default
                    )

                ButtonType.PRIMARY_FULL_WIDTH.id ->
                    renderButtonView(
                        bgResoource = R.drawable.bg_btn_primary_full_width,
                        selectorTextColor = R.color.selector_btn_txt_primary,
                        loadingColor = R.color.btn_text_primary_default
                    )
                ButtonType.PRIMARY_DESTRUCTIVE.id ->
                    renderButtonView(
                        bgResoource = R.drawable.bg_btn_primary_destructive,
                        selectorTextColor = R.color.selector_btn_txt_primary_destructive,
                        loadingColor = R.color.btn_text_primary_destructive_default
                    )
            }

            buttonSize = getInt(R.styleable.UiButton_btnSize, ButtonSize.MEDIUM.id)
            setTextSize()

            if (getBoolean(R.styleable.UiButton_isLoading, false)) {
                showLoader()
            }

            recycle()
        }
    }

    private fun renderButtonView(
        bgResoource: Int,
        selectorTextColor: Int,
        loadingColor: Int
    ) {
        setBackgroundResource(bgResoource)
        setTextAppearance(R.style.ButtonTitleSmallBold)
        setTextColor(
            ContextCompat.getColorStateList(
                context,
                selectorTextColor
            )
        )
        progressDrawable = ProgressBar(context).indeterminateDrawable.apply {
            setBounds(0, 0, 24.dpToPx(), 24.dpToPx())
            setTint(ContextCompat.getColor(context, loadingColor))
        }
    }

    private fun setTextSize() {
        when (buttonSize) {
            ButtonSize.SMALL.id -> {
                setPadding(
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x12),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x8),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x12),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x8)
                )
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            }

            ButtonSize.MEDIUM.id -> {
                setPadding(
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x16),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x12),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x16),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x12)
                )
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            }

            ButtonSize.LARGE.id -> {
                setPadding(
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x20),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x16),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x20),
                    context.resources.getDimensionPixelSize(R.dimen.spacing_x16)
                )
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            }
        }
    }

    private fun initDefaultStyle() {
        isClickable = true
        gravity = Gravity.CENTER
    }

    fun showLoader() {
        (text as? TextView)?.gone()
        isLoading = true
    }

    fun hideLoader() {
        (text as? TextView)?.visible()
        isLoading = false
    }

    fun setIcon(iconData: IconData, iconPosition: IconPosition) {
        val drawable =
            IconManager.getIconDrawable(context, iconData.iconName, iconData.iconColorToken)

        if (iconPosition == IconPosition.RIGHT) {
            setCompoundDrawablesRelative(
                null, null, drawable, null
            )
        } else {
            setCompoundDrawablesRelative(
                drawable, null, null, null
            )
        }
    }

    private fun Int.dpToPx(): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }
}
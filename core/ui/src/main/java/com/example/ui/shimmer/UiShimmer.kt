package com.example.ui.shimmer

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.example.ui.ext.ui_static_white
import com.example.ui.ext.text_2_color
import com.example.ui.ext.readAttributes
import com.example.ui.ext.getStringViaResources
import com.nutapos.nutatest.core.ui.R


class UiShimmer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var animator: ValueAnimator? = null
    private var gradientPaint: Paint? = null
    private var gradientColors: IntArray? = null
    private val centerColor = ui_static_white
    private val edgeColor = text_2_color
    private val animationDuration = 1200L

    private var contentViewBitmap: Bitmap? = null
    private var contentViewPaint: Paint? = null
    private var contentViewRes: Int = -1
    private var contentView: View? = null
    private var shimmerBitmap: Bitmap? = null

    private var shimmerAccessibilityDescription: String? = null
        set(value) {
            field = value
            contentDescription = value
                ?: resources.getString(R.string.accessibilityButtonLoadingText)
        }

    init {
        readAttributes(attrs, R.styleable.UiShimmer) { typedArray ->
            if (attrs != null) {
                contentViewRes = typedArray.getResourceId(R.styleable.UiShimmer_ui_layout, -1)
                if (contentViewRes == -1) {
                    throw IllegalAccessException("Cannot inflate shimmer view without a layout attribute")
                }

                shimmerAccessibilityDescription = typedArray.getStringViaResources(R.styleable.UiShimmer_ui_accessibility_description)
            }
        }

        isFocusable = true
        initContentViewPaint()
        initGradientPaint()
        initAnimation()
    }

    private fun initAnimation() {
        animator = ValueAnimator.ofFloat(-1f, 2f).apply {
            duration = animationDuration
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                val f = it.animatedValue as Float
                updateGradient(width.toFloat(), f)
                invalidate()
            }
        }
    }

    private fun initGradientPaint() {
        gradientPaint = Paint()
        gradientPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        gradientPaint!!.isAntiAlias = true
        gradientColors = intArrayOf(edgeColor, centerColor, edgeColor)
    }

    private fun initContentViewPaint() {
        contentViewPaint = Paint()
        contentViewPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
    }
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (contentView == null) {
            contentView = initContentView(widthMeasureSpec, heightMeasureSpec)
        }

        val wms = MeasureSpec.makeMeasureSpec(contentView!!.width, MeasureSpec.EXACTLY)
        val hms = MeasureSpec.makeMeasureSpec(contentView!!.height, MeasureSpec.EXACTLY)
        super.onMeasure(wms, hms)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateGradient(w.toFloat(), -1f)
        if (h <= 0 || w <= 0) {
            contentViewBitmap = null
            animator!!.cancel()
        }
    }

    private fun updateGradient(w: Float, animatedValue: Float) {
        if (width == 0 && height == 0) {
            return
        } else if(width == 0 || height == 0) {
            return
        }
        val left = w * animatedValue
        val linearGradient = LinearGradient(left, 0f, left + w, 0f, requireNotNull(gradientColors), floatArrayOf(0f, .5f, 1f), Shader.TileMode.CLAMP)
        gradientPaint!!.shader = linearGradient
        if (shimmerBitmap == null) {
            shimmerBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }
        shimmerBitmap?.let { shimmerBitmap ->
            val canvas = Canvas(shimmerBitmap)
            contentViewBitmap?.let {
                canvas.drawBitmap(it, 0F, 0F, contentViewPaint)
                canvas.drawRect(0F, 0F, it.width.toFloat(), it.height.toFloat(), requireNotNull(gradientPaint))
            }
        }
    }

    private fun initContentView(widthMeasureSpec: Int, heightMeasureSpec: Int): View {
        // Inflate content view and call measure and layout manually to get dimensions
        val contentView = LayoutInflater.from(context).inflate(contentViewRes, parent as ViewGroup, false)
        contentView.measure(widthMeasureSpec, heightMeasureSpec)
        contentView.layout(0, 0, contentView.measuredWidth, contentView.measuredHeight)

        if(contentView.measuredWidth != 0 && contentView.measuredHeight != 0) {
            contentView.background = null

            contentViewBitmap = try {
                Bitmap.createBitmap(contentView.measuredWidth, contentView.measuredHeight, Bitmap.Config.ALPHA_8)
            } catch (e: OutOfMemoryError) {
                System.gc()
                Bitmap.createBitmap(contentView.measuredWidth / 2, contentView.measuredHeight / 2, Bitmap.Config.ALPHA_8)
            }

            contentViewBitmap?.let { contentViewBitmap ->
                val canvas = Canvas(contentViewBitmap)
                contentView.draw(canvas)
            }
        }

        return contentView
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shimmerBitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        when (visibility) {
            VISIBLE -> startAnimationIfNotAlreadyRunning()
            INVISIBLE, GONE -> cancelAnimation()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimationIfNotAlreadyRunning()
    }

    private fun cancelAnimation() {
        animator?.duration = 0
        animator?.cancel()
    }

    private fun startAnimationIfNotAlreadyRunning() {
        if (isShown && animator != null && !animator!!.isRunning) {
            animator!!.duration = animationDuration
            animator!!.start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel()
    }
}
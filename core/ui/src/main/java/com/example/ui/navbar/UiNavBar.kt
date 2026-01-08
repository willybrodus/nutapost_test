package com.example.ui.navbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.ui.ext.readAttributes
import com.example.ui.ext.getStringViaResources
import com.example.ui.ext.gone
import com.nutapos.nutatest.core.ui.R
import com.nutapos.nutatest.core.ui.databinding.UiNavBarBinding

class UiNavBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : LinearLayout(context, attrs) {

    private val binding = UiNavBarBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        readAttributes(attrs, R.styleable.UiNavBar) { typedArray ->
            val title = typedArray.getStringViaResources(R.styleable.UiNavBar_ui_title).orEmpty()
            binding.tvTitle.text = title
        }

        addView(binding.root)
    }

    fun showNavigationIcon(onBackPressed: () -> Unit) {
        binding.iconBack.setOnClickListener {
            onBackPressed.invoke()
        }
    }

    fun hideNavigationIcon() {
        binding.iconBack.gone()
    }
}
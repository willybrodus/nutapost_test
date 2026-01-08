package com.example.ui.icon

import com.nutapos.nutatest.core.ui.R


enum class Icon {
    NAVIGATION_BACK {
        override fun getDrawableResId(): Int {
            return R.drawable.ic_navigation_16_back
        }
    },
    NAVIGATION_CANCEL {
        override fun getDrawableResId(): Int {
            return R.drawable.ic_navigation_16_cancel
        }
    };

    abstract fun getDrawableResId(): Int
}
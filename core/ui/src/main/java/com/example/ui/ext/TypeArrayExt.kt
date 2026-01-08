package com.example.ui.ext

import android.content.res.TypedArray
import androidx.annotation.StyleableRes


internal fun TypedArray.getStringViaResources(@StyleableRes id: Int): String? {
    val textResourceId = getResourceId(id, 0)
    return if (textResourceId != 0) {
        resources.getString(textResourceId)
    } else {
        getString(id)
    }
}
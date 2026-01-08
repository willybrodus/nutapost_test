package com.example.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nutapos.nutatest.core.ui.R
import com.nutapos.nutatest.core.ui.databinding.UiBottomsheetBinding

class UiBottomSheet(
    val context: Context,
    val contentView: View,
    private val isCancelable: Boolean = true
) {

    private var isUserAction = false

    private val flashBottomsheetBinding = UiBottomsheetBinding.inflate(
        LayoutInflater.from(context), null, false
    )

    private val bottomSheetDialog =
        BottomSheetDialog(context, R.style.BottomSheetDialog)

    fun show(
        onBottomSheetShown: (() -> Unit)? = null,
        onBottomSheetDismissed: ((isUserAction: Boolean) -> Unit)? = null
    ) {
        flashBottomsheetBinding.apply {
            clContent.removeAllViews()
            clClose.isVisible = isCancelable
            clClose.setOnClickListener {
                dismiss(isUserAction = true)
            }
            clContent.addView(contentView)
        }

        bottomSheetDialog.apply {
            setCancelable(isCancelable)
            setContentView(flashBottomsheetBinding.root)
            setOnShowListener {
                onBottomSheetShown?.invoke()
            }
            setOnDismissListener {
                onBottomSheetDismissed?.invoke(isUserAction)
            }

            if (isShowing) return
            show()
        }
    }

    fun dismiss(isUserAction: Boolean = false) {
        this.isUserAction = isUserAction
        bottomSheetDialog.dismiss()
    }

    fun isShowing() = bottomSheetDialog.isShowing
}
package com.nutapos.nutatest.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ui.dialog.UiBottomSheet
import com.nutapos.nutatest.core.ui.databinding.SampleDialogBinding

class SampleDialog(
    viewContainer: ViewGroup
) {
    private val contentView = SampleDialogBinding.inflate(
        LayoutInflater.from(viewContainer.context),
        viewContainer,
        false
    )

    private val dialogCard: UiBottomSheet =
      UiBottomSheet(viewContainer.context, contentView.root)


    init {
        setActionButton()
    }

    fun show(showListener: () -> Unit = {}) {
        if (dialogCard.isShowing()) return
        dialogCard.show(showListener)
    }

    fun setDescription(description: String) {
        contentView.description.text = description
    }

    private fun setActionButton() {
        contentView.actionButton.setOnClickListener {
            dialogCard.dismiss()
        }
    }
}

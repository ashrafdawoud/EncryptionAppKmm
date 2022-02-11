package com.example.food1fork.android.Presentation.Componnents

import androidx.compose.runtime.Composable
import com.BFCAI.encryptionapp.android.Presentation.Component.CustomSnackBar
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue


@Composable
fun ProcessDialogQueue(
    queue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessage: () -> Unit
) {
    queue?.peek()?.let { dialogInfo ->
        dialogInfo.onDismiss?.let { ondismiss ->
            if (dialogInfo.uiComponentType == UIComponentType.Dialog)
                GenericDialog(
                    ondesmiss = ondismiss,
                    title = dialogInfo.title,
                    description = dialogInfo.description,
                    positiveAction = dialogInfo.positiveAction,
                    negativeAction = dialogInfo.negativeAction,
                    onRemoveHeadMessage = onRemoveHeadMessage
                )
            else
                CustomSnackBar()
            queue.poll()
        }
    }
}
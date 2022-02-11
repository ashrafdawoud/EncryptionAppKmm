package com.example.food1fork.android.Presentation.Componnents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food2fork.Food2ForkKmm.Domain.Model.NegativeAction
import com.example.food2fork.Food2ForkKmm.Domain.Model.PositiveAction

@Composable
fun GenericDialog(
    modifier:Modifier=Modifier,
    ondesmiss:()->Unit?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadMessage:()->Unit
) {
    AlertDialog(
        onDismissRequest = {
            ondesmiss?.invoke()
            onRemoveHeadMessage()
        },
        text = {
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ){
               if (negativeAction!=null){
                   Button(
                       modifier = Modifier.padding(end = 8.dp),
                       onClick = {
                           negativeAction.onNegativeAction()
                           onRemoveHeadMessage()
                       }
                   ) {
                       Text(text = negativeAction.negativeBtnTxt)
                   }
               }
                if (positiveAction!=null){
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            positiveAction.onPositiveAction()
                            onRemoveHeadMessage()
                        }
                    ) {
                        Text(text = positiveAction.positiveBtnTxt)
                    }
                }
            }
        }
    )
}
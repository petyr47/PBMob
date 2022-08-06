package com.aneke.peter.pbmob.ui

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.aneke.peter.pbmob.data.Hit

@Composable
fun DecisionDialog(hit : Hit, showDialogState : MutableState<Boolean>, confirmAction : (Hit) -> Unit) {
    if (showDialogState.value){
        AlertDialog(
            title = {
                Text(text = "Confirm Action")
            },
            text = {
                Text(text = "Are you sure you want to see more details for this image?")
            },
            onDismissRequest = {
                showDialogState.value = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialogState.value = false
                    confirmAction.invoke(hit)
                })
                { Text(text = "YES") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialogState.value = false
                })
                { Text(text = "NO") }
            },
            shape = CutCornerShape(16.dp)
        )
    }

}
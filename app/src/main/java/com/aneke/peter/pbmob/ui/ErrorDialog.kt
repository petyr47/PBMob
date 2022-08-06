package com.aneke.peter.pbmob.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDialog(showDialogState : MutableState<Boolean>, message : String) {
    if (showDialogState.value){
        AlertDialog(
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = message)
            },
            onDismissRequest = {
                showDialogState.value = false
            },
            confirmButton = {
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialogState.value = false
                })
                { Text(text = "OK") }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
}
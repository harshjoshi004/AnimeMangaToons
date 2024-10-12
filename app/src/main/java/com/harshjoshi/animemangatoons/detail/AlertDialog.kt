package com.harshjoshi.animemangatoons.detail

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun RatingDialogue(dialogOpen: MutableState<Boolean>, confirmFun:(Float)->Unit){
    var str by remember {
        mutableStateOf("")
    }
    if(dialogOpen.value){
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value = false
            },
            confirmButton = {
                Button(onClick = {
                    if(str.isNotEmpty()) {
                        confirmFun(str.toFloat())
                        dialogOpen.value = false
                    }
                }
                ) {
                    Text(text = "Give Rating")
                }
            },
            dismissButton = {
                ElevatedButton(onClick = { dialogOpen.value = false }
                ) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "Give Rating")
            },
            text = {
                Column(
                    Modifier
                        .wrapContentHeight()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.size(16.dp))
                    OutlinedTextField(
                        value = str,
                        onValueChange = {it-> str = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(text = "Rating")}
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            )
        )
    }
}
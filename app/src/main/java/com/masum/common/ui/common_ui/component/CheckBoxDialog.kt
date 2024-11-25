package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.masum.common.ui.theme.ComposeCommonUiTheme
import com.masum.common.ui.theme.*

@Composable
fun CheckBoxAlertDialog(
    setShowDialog: (Boolean) -> Unit,
    listOfOptions: List<String>,
    selectedOptions: MutableState<Set<String>>
) {
    ComposeCommonUiTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Box() {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CheckboxWithMultiOption(
                            options = listOfOptions,
                            selectedOptions = selectedOptions
                        )
                        Spacer24DPH()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextView15_W600(
                                value = "Ok",
                                color = primary,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable {
                                        setShowDialog(false)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowCheckBoxDialog() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val openDialog = remember { mutableStateOf(false) }
        val selectedOptions = remember { mutableStateOf(setOf<String>()) }
        val listOfOptions = listOf("Option 1", "Option 2", "Option 3")

        Button(
            shape = RoundedCornerShape(5.dp),
            onClick = {
                openDialog.value = true
            }) {
            Text(
                text = "Open Dialog",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        if (openDialog.value) {
            CheckBoxAlertDialog(
                setShowDialog = { openDialog.value = it },
                listOfOptions = listOfOptions,
                selectedOptions = selectedOptions
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckBoxAlertDialogPreview() {
    ComposeCommonUiTheme {
        ShowCheckBoxDialog()
    }
}
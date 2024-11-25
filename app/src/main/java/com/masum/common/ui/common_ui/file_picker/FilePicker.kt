package com.masum.common.ui.common_ui.file_picker

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilePicker(
    openFilePicker: MutableState<Boolean>,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current

    var selectedFileUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                try {
                    // remember the permission until device reboots
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (e: Exception) {
                    Log.e("PersistableUriPermission", "No persistable permission grants found: $e")
                }
            }
            selectedFileUri = uri
        }
    )

    LaunchedEffect(key1 = openFilePicker.value) {
        if (openFilePicker.value) {
            launcher.launch(arrayOf("application/pdf"))
            openFilePicker.value = false
        }
    }

    LaunchedEffect(key1 = selectedFileUri) {
        selectedFileUri?.let {
            onValueChange(selectedFileUri.toString())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FilePickerPreview(){
    val fileUriStr = remember { mutableStateOf("") }
    val openFilePicker = remember { mutableStateOf(false) }

    Column {
        FilePicker(
            openFilePicker = openFilePicker,
            onValueChange = { data ->
                fileUriStr.value = data
            }
        )

        Button(onClick = {
            openFilePicker.value = true
        }) {
            Text(text = "Select Document")
        }

        Text(text = "Document Path: ${fileUriStr.value}")
    }
}
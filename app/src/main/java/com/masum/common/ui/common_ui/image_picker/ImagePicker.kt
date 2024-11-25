package com.masum.common.ui.common_ui.image_picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masum.common.R
import com.masum.common.ui.common_ui.component.Spacer8DPH
import com.masum.common.ui.common_ui.component.TextView16_W600
import com.masum.common.ui.common_ui.component.showToastMessage
import com.masum.common.ui.theme.ComposeCommonUiTheme
import com.masum.common.ui.theme.*


@Composable
fun ImagePickerDialog() {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier
        .size(100.dp)
        .clickable {
            openDialog.value = true
        }) {
        TextView16_W600(value = "Click Me")
    }
    if (openDialog.value) {
        ImagePickerAlertDialog(setShowDialog = { openDialog.value = it }, selectCamera = {
            showToastMessage(context, "Camera Click")
        }, selectGallery = { showToastMessage(context, "Gallery Click") })
    }
}


@Composable
fun ImagePickerAlertDialog(
    setShowDialog: (Boolean) -> Unit,
    selectCamera: () -> Unit,
    selectGallery: () -> Unit,
) {
    val context = LocalContext.current
    ComposeCommonUiTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp), color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {

                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(0.6F),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(painter = painterResource(id = R.drawable.camera),
                                    contentDescription = "",
                                    tint = primary,
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp)
                                        .clickable {
                                            selectCamera()
                                            setShowDialog(false)
                                        }

                                )
                                Spacer8DPH()
                                TextView16_W600(
                                    value = "Camera",
                                    color = text_heading,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Column(
                                modifier = Modifier.weight(0.6F),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(painter = painterResource(id = R.drawable.image),
                                    contentDescription = "",
                                    tint =primary,
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp)
                                        .clickable {
                                            selectGallery()
                                            setShowDialog(false)
                                        })
                                Spacer8DPH()
                                TextView16_W600(
                                    value = "Gallery",
                                    color = text_heading,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImagePickerPreview() {
    ComposeCommonUiTheme {

        ImagePickerDialog()
    }
}
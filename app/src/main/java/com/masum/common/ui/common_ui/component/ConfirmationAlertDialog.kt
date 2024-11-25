package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.masum.common.R
import com.masum.common.ui.theme.ComposeCommonUiTheme
import com.masum.common.ui.theme.*


@Composable
fun ConfirmationAlertDialog(
    heading: String,
    description: String,
    icon: Int? = null,
    setShowDialog: (Boolean) -> Unit,
    onConfirmClick: () -> Unit
) {
    val context = LocalContext.current
    ComposeCommonUiTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.background
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
                        icon?.let {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(72.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Spacer24DPH()
                        }

                        TextView16_W600(
                            value = heading,
                            color = text_heading,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer4DPH()

                        TextView14_W400(
                            value = description,
                            color = text_color,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer24DPH()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextView14_W500(
                                value = context.resources.getString(R.string.cancel),
                                color = text_heading,
                                modifier = Modifier.clickable {
                                    setShowDialog(false)
                                }
                            )
                            Spacer16DPW()
                            TextView14_W500(
                                value = context.resources.getString(R.string.confirm),
                                color = primary_dark,
                                modifier = Modifier.clickable {
                                    onConfirmClick()
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
fun SuccessAlertDialog(
    description: String? = "Your Data has been submitted Successfully.",
    setShowDialog: (Boolean) -> Unit,
    navController: NavController,
    isCancelable: Boolean = true,
    onOkClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Dialog(onDismissRequest = { if (isCancelable) setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background
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
                    Icon(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(72.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer24DPH()

                    TextView16_W600(
                        value = context.resources.getString(R.string.dummy_text),
                        color = text_heading,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer8DPH()

                    TextView14_W400(
                        value = description ?: "",
                        color = text_color,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer24DPH()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        TextView14_W500(
                            value = context.resources.getString(R.string.dummy_text),
                            color = primary_dark,
                            modifier = Modifier.clickable {
                                if (onOkClick != null) {
                                    onOkClick()
                                }
                                setShowDialog(false)
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageAlertDialog(
    heading: String? = "",
    description: String? = "",
    drawableIcon: Int = R.drawable.placeholder,
    setShowDialog: (Boolean) -> Unit,
    onOkClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background
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
                    Icon(
                        painter = painterResource(id = drawableIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(72.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer24DPH()

                    TextView16_W600(
                        value = heading ?: "",
                        color = text_heading,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer8DPH()

                    TextView14_W400(
                        value = description ?: "",
                        color = text_color,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer24DPH()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        TextView14_W500(
                            value = context.resources.getString(R.string.dummy_text),
                            color = primary_dark,
                            modifier = Modifier.clickable {
                                if (onOkClick != null) {
                                    onOkClick()
                                }
                                setShowDialog(false)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessAlertDialogPreview() {
    val context = LocalContext.current
    ComposeCommonUiTheme {
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = gray_background
        ) {
            //SuccessAlertDialog(setShowDialog = { }, navController = NavController(context))
            ConfirmationAlertDialog(heading = "Clock-In", description = "Are you sure you want to clock in", setShowDialog = { }, onConfirmClick = {})
        }
    }
}
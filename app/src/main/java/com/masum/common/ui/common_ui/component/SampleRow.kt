package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.*

import com.masum.common.utils.ImageUtils
import com.masum.common.R

@Composable
fun SampleRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    titleTextColor: Color = text_dark,
    valueTextColor: Color = text_color,
    rowIndex: Int = 1
) {
    Row(
        modifier = Modifier
            .background(if (rowIndex % 2 == 0) grayRowColor else Color.Transparent)
            .then(modifier)
    ) {
        TextView16_W600(value = title, color = titleTextColor, modifier = Modifier.weight(1f))
        TextView16_W400(value = value, color = valueTextColor, modifier = Modifier.weight(1f))
    }
}

@Composable
fun SampleColum(
    title: String,
    value: String,
    titleTextColor: Color = text_dark,
    valueTextColor: Color = text_color,
) {
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        TextView16_W600(value = title, color = titleTextColor)
        Spacer4DPH()
        TextView16_W400(value = value, color = valueTextColor)
        Spacer8DPH()

    }
}

@Composable
fun SampleImageView(
    title: String, answer: String, titleTextColor: Color = text_dark,
) {

    Column {
        TextView16_W600(value = title, color = titleTextColor)
        Spacer4DPH()
        RectangleImageWithLabel(
            modifier = Modifier
                .height(160.dp),
            title = title,
            imageBitmap = ImageUtils.decodeStringToImage(answer),
            imageUri = answer, onClick = {}

        )
        Spacer8DPH()
    }
}

@Composable
fun SampleRowWithIcon(
    title: String = "View",
    value: String,
    titleIcon: Int = R.drawable.visibility,
    valueIcon: Int = R.drawable.placeholder,
    titleTextColor: Color = MaterialTheme.colorScheme.tertiary,
    valueTextColor: Color = MaterialTheme.colorScheme.tertiary,
    onTitleClick: (() -> Unit)? = null,
    onValueClick: (() -> Unit)? = null
) {
    Row {
        Row(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier
                .wrapContentWidth()
                .clickable {
                    if (onTitleClick != null) {
                        onTitleClick()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = titleIcon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
                Spacer4DPW()
                TextView12_W500(value = title, color = titleTextColor)
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            if (value.isNotEmpty() || value == "Invoice") {
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        if (onValueClick != null) {
                            onValueClick()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = valueIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer4DPW()
                    TextView12_W500(
                        value = value,
                        color = valueTextColor,
                        textAlign = TextAlign.End
                    )
                }
            }

        }
    }
}

@Composable
fun SampleRowWithIcon3(
    firstIconTitle: String = "View",
    secondIconTitle: String = "",
    thirdIconTitle: String = "",
    firstIcon: Int = R.drawable.visibility,
    secondIcon: Int = R.drawable.placeholder,
    thirdIcon: Int = R.drawable.placeholder,
    onFirstIconClick: (() -> Unit)? = null,
    onSecondIconClick: (() -> Unit)? = null,
    onThirdIconClick: (() -> Unit)? = null
) {
    Row {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Row(modifier = Modifier
                .wrapContentWidth()
                .clickable {
                    if (onFirstIconClick != null) {
                        onFirstIconClick()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = firstIcon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
                Spacer4DPW()
                TextView12_W500(value = firstIconTitle)
            }
        }
        if (secondIconTitle != "") {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        if (onSecondIconClick != null) {
                            onSecondIconClick()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = secondIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer4DPW()
                    TextView12_W500(value = secondIconTitle, textAlign = TextAlign.End)
                }
            }
        }
        if (thirdIconTitle != "") {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        if (onThirdIconClick != null) {
                            onThirdIconClick()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = thirdIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer4DPW()
                    TextView12_W500(value = thirdIconTitle, textAlign = TextAlign.End)
                }
            }
        }
    }
}

@Composable
fun SampleRowForComposable(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), verticalAlignment = Alignment.CenterVertically
        ) {
            leftContent()
        }
        Spacer8DPW()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), verticalAlignment = Alignment.CenterVertically
        ) {
            rightContent()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SampleRowPreviews() {
    ComposeCommonUiTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            SampleRow(title = "Season Name", value = "Rabi-22/23")
            SampleColum(title = "Season Name", value = "Rabi-22/23")
            Spacer16DPH()

            SampleRowWithIcon(title = "View", value = "Confirmation")
            Spacer16DPH()

            SampleRowWithIcon3(
                firstIconTitle = "View",
                secondIconTitle = "Edit",
                thirdIconTitle = "Delete"
            )
            Spacer16DPH()

            SampleRowForComposable(
                leftContent = {
                    TextView12_W500(value = "title")
                },
                rightContent = {
                    TextView12_W500(value = "value")
                }
            )
            Spacer16DPH()

            SampleRowForComposable(
                leftContent = {
                    SpinnerWithLabel(
                        title = "Product Category",
                        list = listOf(),
                        currentValue = remember { mutableStateOf("") },
                        placeholder = "Select Product Category"
                    )
                },
                rightContent = {
                    SpinnerWithLabel_Search(
                        title = "Product Name",
                        list = listOf(),
                        currentValue = remember { mutableStateOf("") },
                        placeholder = "Select Product Name"
                    )
                }
            )
            Spacer16DPH()
        }
    }
}
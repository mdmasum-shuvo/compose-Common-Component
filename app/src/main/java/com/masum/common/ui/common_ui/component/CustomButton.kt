package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.masum.common.R
import com.masum.common.ui.theme.*


@Composable
fun CustomShapeButton(
    modifier: Modifier = Modifier,
    title: String,
    imageId: Int = R.drawable.cloud_upload,
    textColor: Color = light_background,
    buttonShape: Shape = RoundedCornerShape(5.dp),
    shadowBorderRadius: Dp = 5.dp,
    backgroundColor: Color = primary,
    iconPositionLeft: Boolean = true,
    isIconVisible: Boolean = true,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .drawColoredShadow(
                    borderRadius = shadowBorderRadius,
                    shadowRadius = 5.dp,
                    offsetY = 3.dp
                )
                .clip(buttonShape)
                .clickable { onClick() }
                .background(/*gradientColor()*/ backgroundColor)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconPositionLeft) {
                if (isIconVisible) {
                    Icon(
                        painterResource(id = imageId), contentDescription = "Submit",
                        tint = textColor
                    )
                    Spacer8DPW()
                }

                TextView15_W600(modifier = Modifier.padding(end = if (isIconVisible) 10.dp else 0.dp), value = title, color = textColor)
            } else {
                TextView15_W600(modifier = Modifier.padding(start = if (isIconVisible) 10.dp else 0.dp), value = title, color = textColor)

                if (isIconVisible) {
                    Spacer8DPW()
                    Icon(
                        painterResource(id = imageId), contentDescription = "Submit",
                        tint = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun CircleShapeButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = light_background,
    buttonShape: Shape = CircleShape,
    backgroundColor: Color = primary,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .drawColoredShadow(borderRadius = 50.dp, shadowRadius = 5.dp, offsetY = 3.dp)
                .height(48.dp)
                .fillMaxWidth()
                .clip(buttonShape)
                .clickable { onClick() }
                .background(/*gradientColor()*/ backgroundColor)
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextView16_W700(value = title, color = textColor)
        }
    }
}

@Composable
fun CircleShapeButtonLanguage(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = light_background,
    buttonShape: Shape = CircleShape,
    backgroundColor: Color = primary,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .clip(buttonShape)
                .clickable { onClick() }
                .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextView12_W400(value = title, color = textColor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomButtonPreview() {

    ComposeCommonUiTheme {
        Column (modifier = Modifier.padding(16.dp)) {
            CustomShapeButton(
                modifier = Modifier
                    .border(width = 1.dp, color = primary, shape = RoundedCornerShape(5.dp))
                    .width(150.dp)
                    .height(40.dp),
                title = "Add",
                imageId = R.drawable.cloud_upload,
                onClick = { }
            )
            Spacer12DPH()

            CustomShapeButton(
                modifier = Modifier
                    .width(64.dp)
                    .height(34.dp),
                title = "Add",
                buttonShape = RoundedCornerShape(5.dp),
                isIconVisible = false,
                onClick = { }
            )
            Spacer12DPH()

            CustomShapeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(150.dp)
                    .height(40.dp),
                title = "Submit",
                imageId = R.drawable.cloud_upload,
                onClick = { }
            )
            Spacer12DPH()

            CircleShapeButton(
                title = "Sign In",
                onClick = { }
            )
        }
    }
}
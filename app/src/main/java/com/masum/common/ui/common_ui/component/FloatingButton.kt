package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.masum.common.R

import com.masum.common.ui.theme.drawColoredShadow
import com.masum.common.ui.theme.*

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    drawableId: ImageVector =  Icons.Filled.AddCircle,
    shape: Shape = RoundedCornerShape(5.dp),
    shadowBorderRadius: Dp = 5.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Card(
            //border = BorderStroke(2.dp, primary),
            shape = shape,
            modifier = modifier
                .size(40.dp)
                .drawColoredShadow(
                    borderRadius = shadowBorderRadius,
                    shadowRadius = 0.dp,
                    offsetY = 0.dp
                )
                .align(Alignment.BottomEnd)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier
                    .background(gradientColor() /*primary*/)
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = drawableId,
                    contentDescription = "contentDescription",
                    modifier = Modifier.size(24.dp),
                    tint = white_color
                )
            }
        }
    }

}

@Composable
fun FloatingButtonWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    endDP: Int = 16,
    imageId: Int = R.drawable.edit_document,
    shape: Shape = RoundedCornerShape(5.dp),
    shadowBorderRadius: Dp = 5.dp,
    textColor: Color = light_background,
    backgroundColor: Color = primary,
    iconPositionLeft: Boolean = true,
    isIconVisible: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = endDP.dp, bottom = 32.dp)
    ) {
        Card(
            //border = BorderStroke(2.dp, primary),
            shape = shape,
            modifier = modifier
                .drawColoredShadow(
                    borderRadius = shadowBorderRadius,
                    shadowRadius = 5.dp,
                    offsetY = 10.dp
                )
                .align(Alignment.BottomEnd)
                .clickable {
                    onClick()
                }
        ) {
            Row(
                modifier = modifier
                    .background(gradientColor() /*backgroundColor*/)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                if (iconPositionLeft) {
                    if (isIconVisible) {
                        androidx.compose.material3.Icon(
                            painterResource(id = imageId), contentDescription = "Search",
                            tint = light_background
                        )
                        Spacer8DPW()
                    }

                    TextView15_W600(value = title, color = textColor)
                } else {
                    TextView15_W600(value = title, color = textColor)

                    if (isIconVisible) {
                        Spacer8DPW()
                        androidx.compose.material3.Icon(
                            painterResource(id = imageId), contentDescription = "Search",
                            tint = light_background
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFloatingButton() {
    ComposeCommonUiTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            /*FloatingButtonRectangle(
                modifier = Modifier.height(50.dp).width(150.dp),
                title = "Add New",
                iconPositionLeft = true,
                isIconVisible = true,
                onClick = { }
            )*/

            FloatingActionButton(

            ) { }

            //FloatingActionButton {}
        }
    }
}
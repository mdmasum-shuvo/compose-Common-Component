package com.masum.common.ui.common_ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.R
import com.masum.common.ui.theme.*


@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    header: String, // header
    color: Color = primary, // color
    isExpanded: Boolean = false,
    isIndicatorLine: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(isExpanded) } // Expand State
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 90f else 270f,
        label = "Rotation state of expand icon button",
    )
    val strokeState by animateDpAsState(
        targetValue = if (expanded) 2.dp else 1.dp,
        label = "Stroke width",
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize() // edit animation here
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // control the header alignment over here.
        ) {
            TextView15_W600(value = header, color = color)

            val paddingValues = if (expanded) PaddingValues(start = 16.dp) else PaddingValues(start = 16.dp)
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(rotationState)
                    .clickable { expanded = !expanded },
                painter = painterResource(id = R.drawable.arrow_right),
                tint = color, // Icon Color
                contentDescription = "Drop Down Arrow"
            )
        }
        if (expanded) {
            content()
        }
        if (isIndicatorLine) Divider(color = color)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpandableCardViewPreview() {
    ComposeCommonUiTheme {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            val name = remember { mutableStateOf("") }
            ExpandableCard(
                header = "Comments (2)",
                color = green_light,
                content = {
                    BasicTextField(inputValue = name, isPrefix = true, placeholder = "01XXXXXXXXX")
                    BasicTextField(inputValue = name, isPrefix = true, placeholder = "01XXXXXXXXX")
                }
            )
        }
    }
}
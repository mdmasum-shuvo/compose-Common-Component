package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.drawColoredShadow
import com.masum.common.ui.theme.*


@Composable
fun TextChip(
    modifier: Modifier = Modifier,
    text: String,
    id:String? = null,
    selectedIndex: MutableState<Int>,
    index: Int = 0,
    onSelect: (String) -> Unit,
    shape: Shape = RoundedCornerShape(5.dp),
    shadowBorderRadius: Dp = 5.dp,
    /*selectedColor: Color = primary,*/
    selectedColor: Brush = gradientColor()
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = if (index == 0) 0.dp else 8.dp)
            .drawColoredShadow(
                borderRadius = shadowBorderRadius,
                shadowRadius = 5.dp,
                offsetY = 10.dp,
                alpha = if (selectedIndex.value == index) 0.2f else 0f
            )
            .border(
                width = if (selectedIndex.value == index) 0.dp else 1.dp,
                brush = if (selectedIndex.value == index) gradientUnspecifiedColor() else selectedColor,
                shape = shape
            )
            .background(
                /*color = if (selectedIndex.value == index) selectedColor else Transparent,*/
                brush = if (selectedIndex.value == index) selectedColor else Brush.horizontalGradient(
                    listOf(
                        White, White
                    )
                ),
                shape = shape
            )
            .clip(shape = shape)
            .selectable(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = if (selectedIndex.value != index) index else -1
                    id?.let { onSelect(it) }
                }
            )
    ) {
        TextView15_W500(
            value = text, color = if (selectedIndex.value == index) White else text_color,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChipPreview() {
    val selectedIndex = remember { mutableStateOf(0) }

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = selectedIndex.value, block = {
        if (selectedIndex.value >= 0)
            listState.animateScrollToItem(index = if (selectedIndex.value <= 0) selectedIndex.value else if (selectedIndex.value == 1000) selectedIndex.value - 1000 else selectedIndex.value - 1)
    })
    val dataList = listOf<Int>(0, 1, 2, 3, 4)

    ComposeCommonUiTheme {
        Surface(color = gray_background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row() {
                    LazyRow(state = listState) {
                        itemsIndexed(dataList) { index, item ->
                            TextChip(
                                text = "Farmer Details",
                                selectedIndex = selectedIndex,
                                index = index,
                                onSelect = {}
                            )
                        }
                    }
                }
            }
        }
    }
}

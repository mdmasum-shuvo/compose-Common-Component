package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.masum.common.ui.theme.*


@Composable
fun DropdownItem(
    list: List<String>?,
    currentValue: MutableState<String>,
    color: Color = MaterialTheme.colorScheme.tertiary,
    placeholder: String = "",
    isBorderEnable: Boolean = true,onValueChanged: (() -> Unit)?=null
) {

    val expanded = remember { mutableStateOf(false) }

    /*if (placeholder.isNotEmpty() && currentValue.value == "")
        currentValue.value = placeholder*/

    Surface(modifier = Modifier.fillMaxWidth(), color = Color.Unspecified) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isBorderEnable) 42.dp else 30.dp)
                    .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                    .border(
                        width = if (isBorderEnable) 1.dp else 0.dp,
                        color = if (isBorderEnable && expanded.value) primary
                        else if (isBorderEnable && !expanded.value) light_gray else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .background(
                        color = if (isBorderEnable) Color.White else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded.value = !expanded.value
                        }
                        .align(Alignment.Center)
                        .padding(
                            horizontal = if (isBorderEnable) 12.dp else 0.dp,
                            vertical = if (isBorderEnable) 10.dp else 0.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextView15_W400(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        value = if (placeholder.isNotEmpty() && currentValue.value == "") placeholder else currentValue.value,
                        color = if (placeholder.isNotEmpty() && currentValue.value == "") text_gray else color
                    )

                    Icon(
                        imageVector = Icons.Filled.ExpandMore,
                        contentDescription = null,
                        tint = primary,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    // center the dropdown list using offset
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        offset = DpOffset(x = 66.dp, y = if (isBorderEnable) 10.dp else 0.dp)
                    ) {
                        list?.forEach {
                            DropdownMenuItem(modifier = Modifier.background(light_background),
                                onClick = {
                                    currentValue.value = it
                                    expanded.value = false
                                    onValueChanged!!()
                                }) {
                                TextView15_W400(value = it)
                            }
                            HorizontalDivider(color = gray_bg)
                        }
                    }
                }
            }
            if(!isBorderEnable) HorizontalDivider(color = gray_bg)
        }
    }
}

@Composable
fun DropdownItemWithEditText(
    list: List<String>?,
    currentValue: MutableState<String>,
    color: Color = MaterialTheme.colorScheme.tertiary,
    placeholder: String = "",
    isBorderEnable: Boolean = true
) {

    val expanded = remember { mutableStateOf(false) }

    val keyboard = LocalSoftwareKeyboardController.current

    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    // Clearing focus on keyboard hide
    LaunchedEffect(key1 = isKeyboardVisible) {
        if (!isKeyboardVisible) expanded.value = false
    }

    Surface(modifier = Modifier.fillMaxWidth(), color = Color.Unspecified) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isBorderEnable) 42.dp else 30.dp)
                    .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                    .border(
                        width = if (isBorderEnable) 1.dp else 0.dp,
                        color = if (isBorderEnable && expanded.value) primary
                        else if (isBorderEnable && !expanded.value) light_gray else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .background(
                        color = if (isBorderEnable) Color.White else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded.value = !expanded.value
                        }
                        .align(Alignment.Center)
                        .padding(
                            horizontal = if (isBorderEnable) 12.dp else 0.dp,
                            vertical = if (isBorderEnable) 10.dp else 0.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        inputValue = currentValue,
                        placeholder = placeholder,
                        color = if (placeholder.isNotEmpty() && currentValue.value == "") text_gray else color,
                        isBorderEnable = false,
                        isVerticalPadding = false,
                        isIndicatorLine = false,
                        onValueChanged = {
                            expanded.value = true
                        },
                        onClick = { isClicked ->
                            if (isClicked) expanded.value = true
                        }
                    )

                    Icon(
                        imageVector = Icons.Filled.ExpandMore,
                        contentDescription = null,
                        tint = primary,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    // center the dropdown list using offset
                    list?.filter { s -> s.lowercase().contains(currentValue.value.lowercase()) }
                        .let { filteredList ->
                            if (filteredList?.isNotEmpty() == true && isKeyboardVisible) {
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false },
                                    properties = PopupProperties(focusable = false),
                                    offset = DpOffset(
                                        x = 66.dp,
                                        y = if (isBorderEnable) 10.dp else 0.dp
                                    )
                                ) {
                                    filteredList.forEach {
                                        DropdownMenuItem(modifier = Modifier.background(
                                            light_background
                                        ),
                                            onClick = {
                                                currentValue.value = it
                                                expanded.value = false
                                                keyboard?.hide()
                                            }) {
                                            TextView15_W400(value = it)
                                        }
                                    }
                                }
                            }
                        }
                }
            }
            if(!isBorderEnable) HorizontalDivider(color = gray_bg)
        }
    }
}

@Composable
fun DropdownItemWithSearch(
    list: List<String>?,
    currentValue: MutableState<String>,
    color: Color = MaterialTheme.colorScheme.tertiary,
    placeholder: String = "",
    isBorderEnable: Boolean = true,
    onValueChanged: (() -> Unit)?=null
) {

    val expanded = remember { mutableStateOf(false) }

    if (expanded.value) {
        if (list != null) {
            SearchListAlertDialog(
                setShowDialog = { expanded.value = it },
                list = list,
                selectedValue = currentValue,
                placeholder = placeholder,
                onValueChanged = {
                    onValueChanged!!()
                }
            )
        }
    }

    Surface(modifier = Modifier.fillMaxWidth(), color = Color.Unspecified) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isBorderEnable) 42.dp else 30.dp)
                    .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                    .border(
                        width = if (isBorderEnable) 1.dp else 0.dp,
                        color = if (isBorderEnable && expanded.value) primary
                        else if (isBorderEnable && !expanded.value) light_gray else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .background(
                        color = if (isBorderEnable) Color.White else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded.value = !expanded.value
                        }
                        .align(Alignment.Center)
                        .padding(
                            horizontal = if (isBorderEnable) 12.dp else 0.dp,
                            vertical = if (isBorderEnable) 10.dp else 0.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextView15_W400(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        value = if (placeholder.isNotEmpty() && currentValue.value == "") placeholder else currentValue.value,
                        color = if (placeholder.isNotEmpty() && currentValue.value == "") text_gray else color
                    )

                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = primary,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                }
            }
            if(!isBorderEnable) HorizontalDivider(color = gray_bg)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DropDownPreview() {

    ComposeCommonUiTheme {
        val list = listOf(
            "Contract Procurement",
            "Contract Procurement",
            "Contract Procurement",
            "Contract Procurement",
            "Contract Procurement"
        )
        val currentValue = remember { mutableStateOf(list[0]) }
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            DropdownItem(list = list, currentValue = currentValue, placeholder = "Select")
            //Divider(color = gray_bg)
            //DropdownItemWithEditText(list = list, currentValue = currentValue, placeholder = "Select")
        }
    }
}

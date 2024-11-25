package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckBoxField(
    listOfOptions: List<String>?,
    selectedOptions: MutableState<Set<String>>,
    color: Color = MaterialTheme.colorScheme.tertiary,
    placeholder: String = "",
    isBorderEnable: Boolean = true
) {
    val expanded = remember { mutableStateOf(false) }

    if (expanded.value) {
        if (listOfOptions != null) {
            CheckBoxAlertDialog(
                setShowDialog = { expanded.value = it },
                listOfOptions = listOfOptions,
                selectedOptions = selectedOptions
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Unspecified
    ) {

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
                            .weight(5f)
                            .basicMarquee(iterations = Int.MAX_VALUE, animationMode = MarqueeAnimationMode.Immediately),
                        value = if (placeholder.isNotEmpty() && selectedOptions.value.isNullOrEmpty()) placeholder else selectedOptions.value.sorted().joinToString(", "),
                        color = if (placeholder.isNotEmpty() && selectedOptions.value.isNullOrEmpty()) text_gray else color
                    )

                    Icon(
                        imageVector = Icons.Filled.ExpandMore,
                        contentDescription = null,
                        tint = primary,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                }
            }
            if (!isBorderEnable) HorizontalDivider(color = gray_bg)
        }
    }
}

@Composable
fun CheckboxWithMultiOption(
    options: List<String>,
    selectedOptions: MutableState<Set<String>>,
) {
    Column {
        options.forEach { option ->
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedOptions.value.contains(option),
                    onCheckedChange = { selected ->
                        val currentSelected = selectedOptions.value.toMutableSet()
                        if (selected) {
                            currentSelected.add(option)
                        } else {
                            currentSelected.remove(option)
                        }
                        selectedOptions.value = currentSelected
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = primary,
                        checkmarkColor = Color.White
                    )
                )
                Spacer8DPW()
                TextView15_W400(value = option)
            }
        }
    }
}
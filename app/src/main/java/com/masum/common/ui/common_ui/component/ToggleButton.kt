package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.*


@Composable
fun ToggleButton(
    states: List<String>,
    selectedOption: MutableState<String>
) {
    val onSelectionChange = { text: String ->
        selectedOption.value = text
    }

    Surface(
        shape = RoundedCornerShape(24.dp),
        elevation = 4.dp,
        modifier = Modifier.wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            states.forEach { text->
                Text(
                    text = text,
                    color = if (text == selectedOption.value) Color.White else Color.Black,
                    modifier = Modifier
                        .clickable(
                            // This is to disable the ripple effect
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onSelectionChange(text)
                        }
                        .padding(2.dp)
                        .clip(shape = RoundedCornerShape(24.dp))
                        .background(if (text == selectedOption.value) primary else Color.Unspecified)
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToggleButtonPreview() {
    ComposeCommonUiTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val states = listOf(
                "State 1",
                "State 2",
                "State 3",
            )
            val selectedOption = remember {
                mutableStateOf(states[1])
            }
            ToggleButton(
                states = states,
                selectedOption = selectedOption
            )
        }
    }
}
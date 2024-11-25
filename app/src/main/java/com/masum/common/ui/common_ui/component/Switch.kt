package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithText(
    modifier: Modifier,
    checkedText: MutableState<String>,
    checked: MutableState<Boolean>,
    onCheckedChange: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextView13_W500(value = if (checked.value) checkedText.value else "", color = Color.White, textAlign = TextAlign.Center)
        Spacer8DPW()
        Switch(
            modifier = modifier,
            checked = checked.value,
            onCheckedChange = {
                onCheckedChange()
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Transparent,
                checkedBorderColor = Color.White,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Transparent,
                uncheckedBorderColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun SwitchPreview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SwitchWithText(
            modifier = Modifier.scale(0.7f),
            checkedText = remember { mutableStateOf("Clock-In\n08:00 AM") },
            checked = remember { mutableStateOf(true) }
        )
    }
}

package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.*

import com.masum.common.utils.DateTimeUtils

@Composable
fun TimeFieldWithIcon(
    title: String,
    time: MutableState<String>,
    isRequired: Boolean = false,
    color: Color = MaterialTheme.colorScheme.tertiary,
    placeholder: String = "Select A Time",
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "Please select a time",
    isBorderEnable: Boolean = true,
    isRepeatable: Boolean = false,
    onValueChanged: (() -> Unit)?=null,
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }

) {

    val context = LocalContext.current
    val timePickerDialog = rememberSaveable { mutableStateOf(false) }

    DateTimeUtils.TimePickerDialog(
        selectedTime = time,
        showDialog = timePickerDialog,
        context = context,onValueChanged={
            onValueChanged!!()
        }
    )

    Column {
        Row {
            TextView15_W600(value = title)
            if (isRequired) {
                Spacer4DPW()
                TextView15_W600(value = "*", color = red)
            }
        }
        Spacer4DPH()
        Row {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isBorderEnable) 42.dp else 30.dp)
                    .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                    .clickable { timePickerDialog.value = true }
                    .border(
                        width = if (isBorderEnable) 1.dp else 0.dp,
                        color = if (isBorderEnable && timePickerDialog.value) primary
                        else if (isBorderEnable && !timePickerDialog.value) light_gray else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .background(
                        color = if (isBorderEnable) Color.White else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .padding(
                        horizontal = if (isBorderEnable) 12.dp else 0.dp,
                        vertical = if (isBorderEnable) 10.dp else 0.dp
                    )
                    .weight(if (isRepeatable) 0.9f else 1f)
                , Arrangement.SpaceBetween
            ) {
                TextView15_W400(
                    value = if (placeholder.isNotEmpty() && time.value == "") placeholder else time.value,
                    color = if (placeholder.isNotEmpty() && time.value == "") text_gray else color
                )

                Icon(
                    Icons.Filled.AccessTime,
                    "contentDescription",
                    tint = primary,
                    //modifier = Modifier.padding(bottom = 4.dp)
                )

            }
            if (isRepeatable)
                FloatingActionButton(modifier = Modifier.weight(.1f)) {

                }
        }
        if(!isBorderEnable) HorizontalDivider(color = gray_bg)

        // Error Message
        if (isRequired && isErrorEnabled.value && time.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}


@Preview
@Composable
fun PreviewTimeField() {
    ComposeCommonUiTheme {
        Surface(color = gray_background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                TimeFieldWithIcon(
                    title = "TimeField With Icon",
                    time = remember { mutableStateOf("") }
                )
                Spacer12DPH()
            }
        }
    }
}
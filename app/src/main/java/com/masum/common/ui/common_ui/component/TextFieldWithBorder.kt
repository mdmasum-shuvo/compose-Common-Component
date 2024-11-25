package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.R
import com.masum.common.ui.theme.*


@Composable
fun TextFieldWithBorder(
    modifier: Modifier = Modifier,
    startIcon: Int? = null,
    endIcon: Int? = null,
    isKeyboardShown: Boolean = false,
    inputValue: MutableState<String>,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    maxLine: Int = 1,
    placeholder: String = "",
    color: Color = text_gray,
    isPrefix: Boolean = false,
    readOnly: Boolean = false,
    isIndicatorLine: Boolean = false,
    isLeadingIcon: Boolean = false,
    leadingIcon: ImageVector? = null,
    onIconClick: () -> Unit = {}
) {
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Card(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(width = 1.dp, color = if (isKeyboardVisible) primary else gray_bg)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (startIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onIconClick() },
                    painter = painterResource(id = startIcon),
                    tint = Color.Unspecified, // Icon Color
                    contentDescription = "Start Icon"
                )
                Spacer8DPW()
            }
            BasicTextField(
                isKeyboardShown = isKeyboardShown,
                modifier = modifier.weight(1f),
                inputValue = inputValue,
                inputType = inputType,
                imeAction = imeAction,
                maxLine = maxLine,
                placeholder = placeholder,
                color = color,
                isPrefix = isPrefix,
                readOnly = readOnly,
                isBorderEnable = false,
                isIndicatorLine = isIndicatorLine,
                isLeadingIcon = isLeadingIcon,
                leadingIcon = leadingIcon
            )
            if (endIcon != null) {
                Spacer8DPW()
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onIconClick() },
                    painter = painterResource(id = endIcon),
                    tint = Color.Unspecified, // Icon Color
                    contentDescription = "End Icon"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TextFieldWithBorderPreview() {
    val input = remember {
        mutableStateOf("")
    }
    ComposeCommonUiTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            TextFieldWithBorder(inputValue = input, placeholder = "User ID")
            Spacer16DPH()

            TextFieldWithBorder(inputValue = input, startIcon = R.drawable.visibility, placeholder = "Password")
            Spacer16DPH()

            TextFieldWithBorder(inputValue = input, endIcon = R.drawable.visibility, placeholder = "Password")
            Spacer16DPH()
        }
    }
}
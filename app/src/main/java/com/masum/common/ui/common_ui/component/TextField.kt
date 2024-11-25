package com.masum.common.ui.common_ui.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masum.common.ui.theme.*

import kotlinx.coroutines.delay

@Composable
fun BasicTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(

        fontWeight = FontWeight.W400,
        fontSize = 15.sp
    ),
    isKeyboardShown: Boolean = false,
    inputValue: MutableState<String>,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    maxLine: Int = 1,
    placeholder: String = "",
    color: Color = text_gray,
    isPrefix: Boolean = false,
    readOnly: Boolean = false,
    isBorderEnable: Boolean = true,
    isVerticalPadding: Boolean = true,
    isIndicatorLine: Boolean = false,
    isLeadingIcon: Boolean = false,
    leadingIcon: ImageVector? = null,
    onValueChanged: ((String) -> Unit)? = null,
    onClick: ((Boolean) -> Unit)? = null,
) {

    val addSpace = "          "
    val showKeyboard = remember { mutableStateOf(isKeyboardShown) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    val interactionSource = remember { MutableInteractionSource() }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val indicatorColor = if (isFocused) primary else gray_bg

    if (onClick != null) {
        onClick(isFocused)
    }

    val focusManager = LocalFocusManager.current

    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    // Clearing focus on keyboard hide
    LaunchedEffect(key1 = isKeyboardVisible) {
        if (!isKeyboardVisible) focusManager.clearFocus()
    }

    // selection cursor handle color changing
    val customTextSelectionColors = TextSelectionColors(
        handleColor = primary,
        backgroundColor = primary_light,
    )
    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
    ) {
        androidx.compose.foundation.text.BasicTextField(
            value = inputValue.value,
            readOnly = readOnly,
            onValueChange = { newText ->
                if (inputType == KeyboardType.Number) {
                    if(newText.isEmpty()){
                        inputValue.value = newText
                        if (onValueChanged != null) {
                            onValueChanged(newText)
                        }
                    }
                   else if (!newText.contains(",") && newText.first() != '.') {
                        inputValue.value = newText
                        if (onValueChanged != null) {
                            onValueChanged(newText)
                        }
                    }
                } else {
                    inputValue.value = newText
                    if (onValueChanged != null) {
                        onValueChanged(newText)
                    }
                }

            },

            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = inputType
            ),
            cursorBrush = SolidColor(primary),
            decorationBox = { innerTextField ->
                if (inputValue.value.isEmpty()) {
                    Row {
                        TextView15_W400(
                            value = if (isPrefix) addSpace + placeholder else placeholder,
                            color = color,
                            modifier = Modifier.weight(4f)
                        )
                        if (isLeadingIcon) {
                            Icon(
                                imageVector = leadingIcon!!, contentDescription = "",
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
                innerTextField()
            },
            singleLine = false,
            maxLines = maxLine,
            visualTransformation = if (isPrefix) PrefixTransformation(prefix = "+880 ") else if (inputType == KeyboardType.Password || inputType == KeyboardType.NumberPassword) PasswordVisualTransformation() else VisualTransformation.None,
            interactionSource = interactionSource,
            textStyle = textStyle,
            modifier = modifier
                .border(
                    width = if (isBorderEnable) 1.dp else 0.dp,
                    color = if (isBorderEnable && isFocused) primary
                    else if (isBorderEnable && !isFocused) light_gray else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .background(
                    color = if (readOnly) grayRowColor else if (isBorderEnable) Color.White else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .drawCustomIndicatorLine(
                    indicatorBorder = if (isIndicatorLine) BorderStroke(
                        1.dp,
                        indicatorColor
                    ) else BorderStroke(0.dp, indicatorColor),
                    indicatorPadding = 0.dp
                )
                .height(if (isBorderEnable) 42.dp else 30.dp)
                .fillMaxWidth()
                .padding(
                    horizontal = if (isBorderEnable) 12.dp else 0.dp,
                    vertical = if (isBorderEnable) 10.dp else if (isVerticalPadding) 5.dp else 0.dp
                ),
        )
    }

    LaunchedEffect(focusRequester) {
        if (showKeyboard.value) {
            focusRequester.requestFocus()
            delay(100) // Make sure you have delay here
            keyboard?.show()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun previewBasicTextField() {
    val name = remember { mutableStateOf("") }

    ComposeCommonUiTheme {
        Surface(color = gray_background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BasicTextField(inputValue = name, isPrefix = true, placeholder = "01XXXXXXXXX")
            }
            Log.d("TAG", "previewBasicTextField: ${name.value}")
        }
    }
}

fun Modifier.drawCustomIndicatorLine(
    indicatorBorder: BorderStroke,
    indicatorPadding: Dp = 0.dp
): Modifier {

    val strokeWidthDp = indicatorBorder.width
    return drawWithContent {
        drawContent()
        if (strokeWidthDp == Dp.Hairline) return@drawWithContent
        val strokeWidth = strokeWidthDp.value * density
        val y = size.height - strokeWidth / 2
        drawLine(
            indicatorBorder.brush,
            Offset((indicatorPadding).toPx(), y),
            Offset(size.width - indicatorPadding.toPx(), y),
            strokeWidth
        )
    }
}

class PrefixTransformation(val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return prefixFilter(text, prefix)
    }
}

fun prefixFilter(number: AnnotatedString, prefix: String): TransformedText {

    var out = prefix + number.text
    val prefixOffset = prefix.length

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < prefixOffset) return 0
            return offset - prefixOffset
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

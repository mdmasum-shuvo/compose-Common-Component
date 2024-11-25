package com.masum.common.ui.common_ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.SpanStyle
import com.masum.common.ui.theme.*


@Composable
fun TextView15_W600(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
    style: TextStyle = TextStyle()
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 15.sp
        ).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView15_W600WithRequired(
    modifier: Modifier = Modifier,
    value: String?,
    isRequired: Boolean = false,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
    style: TextStyle = TextStyle()
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(value?:"")
            if (isRequired) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = red)) {
                    append("*")
                }
            }

        },
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 15.sp
        ).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

// End Q-Map

@Composable
fun TextView16_W600(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
    style: TextStyle = TextStyle()
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        ).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}
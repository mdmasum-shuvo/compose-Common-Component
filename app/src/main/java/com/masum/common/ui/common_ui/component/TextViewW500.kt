package com.masum.common.ui.common_ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TextView11_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 11.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView12_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 12.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView13_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 13.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView15_W500(
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
            fontSize = 15.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView16_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 16.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView24_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 24.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

// End Q-Map

@Composable
fun TextView14_W500(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 14.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView18_W600(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 18.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration,
        maxLines = 1
    )
}

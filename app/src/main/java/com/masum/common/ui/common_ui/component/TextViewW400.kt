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
fun TextView13_W400(
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

            fontWeight = FontWeight.W400,
            fontSize = 13.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView15_W400(
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
            fontWeight = FontWeight.W400,
            fontSize = 15.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView20_W400(
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
            fontWeight = FontWeight.W400,
            fontSize = 20.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView20_W600(
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
            fontSize = 20.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

// End Q-Map

@Composable
fun TextView16_W400(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 16.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView14_W400(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView12_W400(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Visible,
) {
    Text(
        modifier = modifier,
        text = value,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 12.sp),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}
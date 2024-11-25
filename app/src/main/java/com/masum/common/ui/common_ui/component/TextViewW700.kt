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
fun TextView20_W700(
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
            fontWeight = FontWeight.W700,
            fontSize = 20.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

// End Q-Map

@Composable
fun TextView12_W700(
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
            fontWeight = FontWeight.W700,
            fontSize = 12.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}

@Composable
fun TextView16_W700(
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
            fontWeight = FontWeight.W700,
            fontSize = 16.sp).plus(style),
        textAlign = textAlign,
        overflow = overflow,
        textDecoration = textDecoration
    )
}
package com.masum.common.ui.common_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun RichTextHeader() {
    Text(

        text = buildAnnotatedString {
            append("Hello ")
            append("Bold Text ")

        }
    )
}


package com.masum.common.ui.common_ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.masum.common.ui.theme.ComposeCommonUiTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GradientBackgroundTopBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GradientBackgroundRoundedTopBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            /*.offset(y = (-400).dp)
            .fillMaxWidth()
            .fillMaxHeight(0.4f)*/
            .clip(RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50))
            .background(brush = gradientColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GradientToolbarPreview() {
    ComposeCommonUiTheme {
        GradientBackgroundRoundedTopBar(modifier = Modifier.fillMaxHeight(0.3f))
    }
}
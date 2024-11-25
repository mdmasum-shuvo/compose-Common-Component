package com.masum.common.ui.common_ui.component

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.masum.common.R
import com.masum.common.ui.theme.*

@Composable
fun gradientColor(): Brush {
    return Brush.horizontalGradient(
        colors = listOf(primary, primary)
    )
}

@Composable
fun gradientUnspecifiedColor(): Brush {
    return Brush.horizontalGradient(
        colors = listOf(Color.Unspecified, Color.Unspecified)
    )
}

fun gradientStatusBar(activity: Activity) {
    val window: Window = activity.window
    val background = ContextCompat.getDrawable(activity, R.drawable.gradient_horizontal)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    window.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
    //window.navigationBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
    window.setBackgroundDrawable(background)
}
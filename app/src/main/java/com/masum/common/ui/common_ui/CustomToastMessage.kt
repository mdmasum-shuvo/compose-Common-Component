package com.masum.common.ui.common_ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.masum.common.ui.common_ui.component.TextView16_W500
import com.masum.common.ui.theme.*
import kotlinx.coroutines.delay


@Composable
fun CustomToast(
    message: String,
    containerColor: Color = primary_dark,
    contentColor: Color = light_background,
    showToast: Boolean,
    onDismiss: () -> Unit
) {
    val alpha by animateFloatAsState(
        targetValue = if (showToast) 1f else 0f,
        animationSpec = tween(durationMillis = 500), label = "" // Animation duration (500ms)
    )

    if (alpha > 0f) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(containerColor.copy(alpha = alpha), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                TextView16_W500(value = message, color = contentColor.copy(alpha = alpha))
            }
        }

        // Dismiss the toast after a delay
        LaunchedEffect(Unit) {
            delay(3000) // Show for 3 seconds
            onDismiss()
        }

    }
}
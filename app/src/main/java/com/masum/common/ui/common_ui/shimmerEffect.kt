package com.masum.common.ui.common_ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.masum.common.ui.common_ui.component.NetworkImage
import com.masum.common.ui.common_ui.component.Spacer16DPH
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.masum.common.ui.theme.*

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFF2F2F2),
                Color(0xFFB8B5B5),
                Color(0xFFF2F2F2),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

fun Modifier.customShimmer(
    duration: Int = 500
): Modifier = composed {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = createCustomTheme(duration),
    )
    shimmer(customShimmer = shimmer)
}

private fun createCustomTheme(duration: Int) = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = duration,
            delayMillis = 500,
            easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    rotation = 5f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 1.0f),
        Color.Unspecified.copy(alpha = 0.2f),
        Color.Unspecified.copy(alpha = 1.0f),
    ),
    shaderColorStops = null,
    shimmerWidth = 200.dp,
)

@Composable
fun ShimmerListItemWithImage() {
    Column {
        Card(
            modifier = Modifier.padding(bottom = 2.dp),
            shape = RoundedCornerShape(5.dp),
            CardDefaults.cardColors(
                containerColor = white_color // Set the card's background color
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(modifier = Modifier.background(color = light_background)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NetworkImage(
                        imgUrl = "",
                        modifier = Modifier
                            .customShimmer()
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 90.dp)
                                .shimmerEffect()

                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 36.dp)
                                .shimmerEffect()

                        )
                    }
                }

            }

        }
        Spacer16DPH()
    }
}
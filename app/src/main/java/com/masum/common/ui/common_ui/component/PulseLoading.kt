package com.masum.common.ui.common_ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masum.common.ui.theme.*

@Composable
fun PulseLoading(
    durationMillis: Int = 1000,
    maxPulseSize: Float = 300f,
    minPulseSize: Float = 50f,
    pulseColor: Color = Color(234, 240, 246),

    ) {

    Dialog(onDismissRequest = {}) {
/*        val infiniteTransition = rememberInfiniteTransition()
        val size = infiniteTransition.animateFloat(
            initialValue = minPulseSize,
            targetValue = maxPulseSize,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
        val alpha = infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )*/
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                backgroundColor = pulseColor,
                elevation = 4.dp
            ) {}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(minPulseSize.dp)
                    .align(Alignment.Center),
            ) {
                // ImageNormal(modifier = Modifier.size(72.dp))
                CircularProgressIndicator()

            }
        }


    }


}

@Composable
fun Loading(

) {
    Dialog(onDismissRequest = {}) {
        CircularProgressIndicator(color = brand_color)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun load() {
    PulseLoading()
}
package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.masum.common.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    imgUrl: String? = "",
    placeHolder: Int = R.drawable.placeholder
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imgUrl)
            .memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED)
            .decoderFactory(SvgDecoder.Factory()).crossfade(true).build(),
        contentDescription = null,
        placeholder = painterResource(id = placeHolder),
        error = painterResource(id = placeHolder),
        contentScale = contentScale,
        modifier = modifier,
    )

}

@Composable
fun ImageNormal(
    modifier: Modifier = Modifier.fillMaxSize(),
    drawableId: Int = R.drawable.placeholder,
    contentScale: ContentScale = ContentScale.None
) {
    Image(
        painterResource(drawableId),
        contentDescription = "logo",
        contentScale = contentScale,
        modifier = modifier,
    )
}

@Composable
fun ZoomableNetworkImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    imgUrl: String? = "",
    placeHolder: Int = R.drawable.placeholder,
    minZoom: Float = 0.5f, // adding min zoom limits to 50%
    maxZoom: Float = 3f, // adding max zoom limits to 200%
    isRotateEnable: Boolean = false,
    isPanningEnable: Boolean = false
) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(0f) }
    val offset = remember { mutableStateOf(Offset.Zero) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imgUrl)
            .memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED)
            .decoderFactory(SvgDecoder.Factory()).crossfade(true).build(),
        contentDescription = null,
        placeholder = painterResource(id = placeHolder),
        error = painterResource(id = placeHolder),
        contentScale = contentScale,
        modifier = modifier
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    offset.value += if (isPanningEnable) pan * 1.5f else Offset.Zero
                    scale.value *= zoom
                    rotationState.value += if (isRotateEnable) rotation else 0f
                }
            }
            .graphicsLayer(
                scaleX = maxOf(minZoom, minOf(maxZoom, scale.value)),
                scaleY = maxOf(minZoom, minOf(maxZoom, scale.value)),
                translationX = if (scale.value > 1f) offset.value.x else 0f,
                translationY = if (scale.value > 1f) offset.value.y else 0f,
                rotationZ = rotationState.value
            )
    )
}
package com.masum.common.ui.common_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.masum.common.R


@Composable
fun NetworkImageFit(
    modifier: Modifier = Modifier,
    imgUrl: String? = "",
    placeHolder: Int = R.drawable.placeholder,
    error: Int = R.drawable.placeholder
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(), contentDescription = null,
        placeholder = painterResource(id = placeHolder),
        error = painterResource(id = error),
        contentScale = ContentScale.Fit,
        modifier = modifier
    )

}
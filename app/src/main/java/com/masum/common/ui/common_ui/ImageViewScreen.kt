package com.masum.common.ui.common_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.masum.common.R
import com.masum.common.ui.common_ui.component.ZoomableNetworkImage
import com.masum.common.ui.theme.*


@Composable
fun ImageViewScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val heading = remember {
        mutableStateOf(
            navController.previousBackStackEntry?.savedStateHandle?.get("Heading") ?: ""
        )
    }
    val imageUrl = remember {
        mutableStateOf(
            navController.previousBackStackEntry?.savedStateHandle?.get("url") ?: ""
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = gray_background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ZoomableNetworkImage(
                        modifier = Modifier.fillMaxSize(),
                        imgUrl = imageUrl.value,
                        placeHolder = R.drawable.placeholder,
                        contentScale = ContentScale.Fit,
                        minZoom = 1f,
                        maxZoom = 3f,
                        isPanningEnable = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImageViewScreenPreview() {
    val context = LocalContext.current
    ImageViewScreen(navController = NavController(context))
}
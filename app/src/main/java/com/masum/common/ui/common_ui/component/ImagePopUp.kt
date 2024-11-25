package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masum.common.ui.theme.ComposeCommonUiTheme

@Composable
fun ImagePopUpDialog(
    imageUrl: String? = null,
    setShowDialog: (Boolean) -> Unit
) {
    val context = LocalContext.current
    ComposeCommonUiTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ZoomableNetworkImage(
                        imgUrl = imageUrl,
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
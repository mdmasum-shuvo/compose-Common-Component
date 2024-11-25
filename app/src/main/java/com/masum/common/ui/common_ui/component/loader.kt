package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masum.common.ui.theme.*

@Composable
fun PulseLoading(title: String = "") {

    var context= LocalContext.current
    Dialog(onDismissRequest = {}) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center),
                backgroundColor = light_blue,
                elevation = 8.dp
            ) {}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .align(Alignment.Center),
            ) {
                // ImageNormal(modifier = Modifier.size(72.dp))
                CircularProgressIndicator(color = green_light)
                Spacer16DPH()
                TextView12_W700(value = if (title!="") title else "Please Wait")
            }
        }


    }


}

@Preview
@Composable
fun PrviewLoader() {
    ComposeCommonUiTheme {
        PulseLoading()
    }

}
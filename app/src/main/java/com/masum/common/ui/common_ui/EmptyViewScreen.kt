package com.masum.common.ui.common_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.masum.common.ui.common_ui.component.ImageNormal
import com.masum.common.ui.common_ui.component.Spacer16DPH
import com.masum.common.ui.common_ui.component.Spacer24DPH
import com.masum.common.ui.common_ui.component.Spacer80DPH
import com.masum.common.ui.common_ui.component.TextView14_W400
import com.masum.common.ui.common_ui.component.TextView16_W600
import com.masum.common.R
import com.masum.common.ui.theme.primary


@Composable
fun EmptyViewScreen(
    modifier: Modifier = Modifier,
    title: String? = "Sorry! No Data Found",
    description: String? = "No information available on this page\n" +
            "or technical issue prevents data display.",
    image: Int = R.drawable.no_data
) {
    val context= LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Spacer80DPH()
        ImageNormal(drawableId = image, modifier = Modifier.height(150.dp), contentScale = ContentScale.Fit)
        Spacer16DPH()
        TextView16_W600(value =context.resources.getString(R.string.no_data_title), color = primary)
        Spacer24DPH()
        TextView14_W400(value =context.resources.getString(R.string.no_data_des), textAlign = TextAlign.Center)
    }

}
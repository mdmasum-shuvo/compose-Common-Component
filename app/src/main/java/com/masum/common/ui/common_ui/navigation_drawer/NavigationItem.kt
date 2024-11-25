package com.masum.common.ui.common_ui.navigation_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.ui.common_ui.component.TextView14_W400
import com.masum.common.ui.theme.*


@Composable
fun NavigationItem(
    title: String? = "",
    isStartIcon : Boolean = false,
    startIcon: ImageVector = Icons.Default.ExpandMore,
    endIcon: ImageVector = Icons.Default.ExpandMore,
    backgroundColor:Color = MaterialTheme.colorScheme.background,
    textColor:Color = MaterialTheme.colorScheme.onBackground,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(38.dp)
            .clickable {
                onItemClick()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isStartIcon){
                    Icon(
                        startIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    )
                }

                TextView14_W400(
                    value = title!!,
                    textAlign = TextAlign.Center,
                    color = textColor
                )
            }
            Icon(
                endIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(end = 10.dp)
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavigationItemPreview() {
    ComposeCommonUiTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            NavigationItem(
                title = "IC Operations",
                backgroundColor = green_color,
                textColor = MaterialTheme.colorScheme.background,
                onItemClick = { }
            )
        }
    }
}
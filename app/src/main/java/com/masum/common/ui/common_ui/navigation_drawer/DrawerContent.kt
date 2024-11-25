package com.masum.common.ui.common_ui.navigation_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.masum.common.R
import com.masum.common.ui.common_ui.component.ImageNormal
import com.masum.common.ui.common_ui.component.Spacer12DPH
import com.masum.common.ui.common_ui.component.Spacer24DPH
import com.masum.common.ui.common_ui.component.Spacer8DPW
import com.masum.common.ui.common_ui.component.TextView16_W400
import com.masum.common.ui.common_ui.component.TextView16_W500
import com.masum.common.ui.common_ui.component.ToggleButton
import com.masum.common.ui.theme.*

import kotlinx.coroutines.launch

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    states: List<String> = listOf(),
    selectedOption: MutableState<String> = mutableStateOf("")
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageNormal(
            modifier = Modifier.height(50.dp).width(200.dp)
                .align(Alignment.CenterHorizontally),
            drawableId = R.drawable.app_icon,
            contentScale = ContentScale.FillBounds
        )
        Spacer24DPH()
        TextView16_W500(value = context.resources.getString(R.string.app_name), color = white_color)

        Spacer12DPH()
        ToggleButton(
            states = states,
            selectedOption = selectedOption
        )
        Spacer24DPH()
    }
}

@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    drawerItemList: List<DrawerItemData>,
    navController: NavHostController?,
    closeNavDrawer: () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(.9f)
                .verticalScroll(rememberScrollState())
        ) {
            drawerItemList.forEachIndexed { index, item ->
                DrawerMenuItem(
                    text = item.title,
                    imageId = item.imageId,
                    onItemClick = {
                        if (item.routeName.isNotEmpty()) {
                            navController?.navigate(item.routeName) {

                            }
                        }
                        item.onItemClick?.invoke()
                        closeNavDrawer()
                    }
                )
                Spacer24DPH()
            }
        }

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            TextView16_W400(
                value = "Designed & Developed by\n Appinion BD Limited.",
                color = text_color
            )
        }
    }
}

@Composable
fun DrawerMenuItem(
    text: String,
    imageId: Int,
    isSelected: Boolean = false,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) primary else Color.Unspecified,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onItemClick() }
            .padding(if (isSelected) 8.dp else 0.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(id = imageId),
            contentDescription = null,
            tint = if (isSelected) white_color else primary
        )
        Spacer8DPW()
        TextView16_W500(value = text, color = if (isSelected) white_color else text_dark)
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController?,
    scaffoldState: ScaffoldState,
    drawerItemList: List<DrawerItemData>,
    states: List<String>,
    selectedOption: MutableState<String>
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        DrawerHeader(modifier = Modifier.weight(.2f), states = states, selectedOption = selectedOption)
        DrawerBody(
            modifier = Modifier.weight(.8f),
            drawerItemList = drawerItemList,
            navController = navController,
            closeNavDrawer = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        )
    }
}



data class DrawerItemData(
    val title: String,
    val imageId: Int,
    val routeName: String,
    val onItemClick: (() -> Unit)? = null
)
package com.masum.common.ui.common_ui.navigation_drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.masum.common.R
import com.masum.common.ui.common_ui.component.TextView16_W600
import com.masum.common.ui.theme.*

import kotlinx.coroutines.launch

@Composable
fun NavTopBar(
    navController: NavController,
    onNavigationIconClick: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

  /*  if (currentRoute == null || currentRoute == Routing.LoginScreen.routeName) {
        return
    }*/

    TopAppBar(
        title = {
            TextView16_W600(
                value = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.background,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(end = 50.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        backgroundColor = green_color
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NavigationDrawerPreview() {
    val navController = rememberNavController()
    val navigationDrawer = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = navigationDrawer.value,
        topBar = {
            if (navigationDrawer.value) {
                NavTopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            }
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(navController = navController, drawerItemList = listOf(), closeNavDrawer = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    ) {

    }
}
package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.masum.common.ui.theme.*


@Composable
fun SearchListAlertDialog(
    setShowDialog: (Boolean) -> Unit,
    list: List<String>,
    selectedValue: MutableState<String>,
    placeholder: String = "",
    onValueChanged: (() -> Unit)?=null
) {
    val inputValue = remember { mutableStateOf("") }
    var filteredList = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(key1 = true) {
        list.forEach { filteredList.add(it) }
    }

    LaunchedEffect(key1 = inputValue.value) {
        var searchedList = onSearchTextChange(list, inputValue.value)
        filteredList.clear()
        searchedList.forEach { filteredList.add(it) }
    }

    ComposeCommonUiTheme {
        Dialog(onDismissRequest = { setShowDialog(false) }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Box() {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier
                                    //.padding(horizontal = 8.dp)
                                    .fillMaxWidth()
                                    .height(48.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiary
                                )
                                Spacer4DPW()

                                BasicTextField(
                                    inputValue = inputValue,
                                    placeholder = placeholder,
                                    isBorderEnable = false,
                                    isIndicatorLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(5f)
                                )

                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiary,
                                    modifier = Modifier.clickable {
                                        if (inputValue.value == "")
                                            setShowDialog(false)
                                        else
                                            inputValue.value = ""
                                    }
                                )
                            }
                        }
                        Spacer24DPH()
                        LazyColumn {
                            itemsIndexed(items = filteredList) { index, data ->
                                TextView15_W400(
                                    value = data,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(vertical = 8.dp)
                                        .clickable {
                                            selectedValue.value = data
                                            setShowDialog(false)
                                            onValueChanged!!()
                                        }
                                )
                                HorizontalDivider(color = gray_bg)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun onSearchTextChange(list: List<String>, query: String): List<String> {
    val filteredList: ArrayList<String> = ArrayList()
    list.filter { s -> s.lowercase().contains(query.lowercase()) }.forEach {
        filteredList.add(it)
    }
    return filteredList
}

@Composable
fun ShowDialog() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val openDialog = remember { mutableStateOf(false) }
        val selectedValue = remember { mutableStateOf("") }
        val list = listOf(
            "Contract Procurement",
            "Extra Procurement",
            "Pre Procurement",
            "Post Procurement",
            "No Procurement"
        )

        Button(
            shape = RoundedCornerShape(5.dp),
            onClick = {
                openDialog.value = true
            }) {
            Text(
                text = "Open Dialog",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        if (openDialog.value) {
            SearchListAlertDialog(
                setShowDialog = { openDialog.value = it },
                list = list,
                selectedValue = selectedValue
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchListAlertDialogPreview() {
    ComposeCommonUiTheme {
        ShowDialog()
    }
}
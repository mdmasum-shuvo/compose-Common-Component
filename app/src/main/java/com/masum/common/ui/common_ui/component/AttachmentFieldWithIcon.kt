package com.masum.common.ui.common_ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masum.common.ui.common_ui.file_picker.FilePicker
import com.masum.common.utils.FileUtils
import com.masum.common.ui.theme.ComposeCommonUiTheme
import com.masum.common.ui.theme.gray_background
import com.masum.common.ui.theme.gray_bg
import com.masum.common.ui.theme.light_gray
import com.masum.common.ui.theme.primary
import com.masum.common.ui.theme.red
import com.masum.common.ui.theme.text_err
import com.masum.common.ui.theme.text_gray
import com.masum.common.ui.theme.white_color


@Composable
fun AttachmentFieldWithIcon(
    title: String,
    isRequired: Boolean = false,
    isRepeatable: Boolean = false,
    placeholder: String = "Choose file",
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "Please choose file",
    isBorderEnable: Boolean = true,
    onValueChanged: ((String) -> Unit)? = {},
    onAddFieldValueChanged: ((SnapshotStateList<MutableState<String>>) -> Unit)? = null,
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }
) {
    val context = LocalContext.current

    val openFilePicker = rememberSaveable { mutableStateOf(false) }
    val registerIndex = rememberSaveable { mutableIntStateOf(-1) }
    val fileUriStr = rememberSaveable { mutableStateOf("") }

    val fields = remember { mutableStateListOf<MutableState<String>>() }

    FilePicker(
        openFilePicker = openFilePicker,
        onValueChange = { data ->
            if (registerIndex.value == -1) {
                fileUriStr.value = data
                onValueChanged!!(fileUriStr.value)
            } else {
                fields[registerIndex.value].value = data
                onAddFieldValueChanged!!(fields)
            }
        }
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                TextView15_W600(value = title)
                if (isRequired) {
                    Spacer4DPW()
                    TextView15_W600(value = "*", color = red)
                }
            }

        }
        Spacer4DPH()
        Row {
            Row(
                modifier = Modifier
                    .weight(if (isRepeatable) 0.9f else 1f)
                    .fillMaxWidth()
                    //.height(if (isBorderEnable) 42.dp else 30.dp)
                    .wrapContentHeight()
                    .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                    .clickable {
                        // Add file picker logic here
                    }
                    .border(
                        width = if (isBorderEnable) 1.dp else 0.dp,
                        color = if (isBorderEnable) light_gray else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .background(
                        color = if (isBorderEnable) Color.White else Color.Unspecified,
                        shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                    )
                    .padding(
                        horizontal = if (isBorderEnable) 12.dp else 0.dp,
                        vertical = if (isBorderEnable) 10.dp else 0.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (placeholder.isNotEmpty() && fileUriStr.value.isEmpty()) {
                    TextView15_W400(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        value = placeholder,
                        color = text_gray
                    )
                } else {
                    FileUtils.getFileName(context, Uri.parse(fileUriStr.value))?.let {
                        FileItem(
                            name = it,
                            onRemoveClick = {
                                // Remove file logic here
                                fileUriStr.value = ""
                            }
                        )
                    }
                }


                Icon(
                    Icons.Filled.AttachFile,
                    "contentDescription",
                    tint = primary,
                    modifier = Modifier.clickable {
                        registerIndex.value = -1
                        openFilePicker.value = true
                    }
                )

            }
            if (isRepeatable)
                FloatingActionButton(modifier = Modifier.weight(.1f)) {
                    fields.add(mutableStateOf(""))
                }
        }

        if (isRepeatable) {
            fields.forEachIndexed { index, s ->
                Spacer8DPH()
                Row {
                    Row(
                        modifier = Modifier
                            .weight(if (isRepeatable) 0.9f else 1f)
                            .fillMaxWidth()
                            //.height(if (isBorderEnable) 42.dp else 30.dp)
                            .wrapContentHeight()
                            .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                            .clickable {
                                // Add file picker logic here
                            }
                            .border(
                                width = if (isBorderEnable) 1.dp else 0.dp,
                                color = if (isBorderEnable) light_gray else Color.Unspecified,
                                shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                            )
                            .background(
                                color = if (isBorderEnable) Color.White else Color.Unspecified,
                                shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                            )
                            .padding(
                                horizontal = if (isBorderEnable) 12.dp else 0.dp,
                                vertical = if (isBorderEnable) 10.dp else 0.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (placeholder.isNotEmpty() && fields[index].value.isEmpty()) {
                            TextView15_W400(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                value = placeholder,
                                color = text_gray
                            )
                        } else {
                            FileUtils.getFileName(context, Uri.parse(fields[index].value))?.let {
                                FileItem(
                                    name = it,
                                    onRemoveClick = {
                                        // Remove file logic here
                                        fields[index].value = ""
                                    }
                                )
                            }
                        }


                        Icon(
                            Icons.Filled.AttachFile,
                            "contentDescription",
                            tint = primary,
                            modifier = Modifier.clickable {
                                registerIndex.intValue = index
                                openFilePicker.value = true
                            }
                        )

                    }
                    FloatingActionButton(
                        modifier = Modifier.weight(.1f),
                        drawableId = Icons.Filled.Close
                    ) {
                        fields.removeAt(index)
                        onAddFieldValueChanged!!(fields)
                    }

                }
            }

        }
        if (!isBorderEnable) HorizontalDivider(color = gray_bg)

        // Error Message
        if (isRequired && isErrorEnabled.value && fileUriStr.value.isEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
            validationCheck!!(false, errorMessage)
        } else if (isRequired && fileUriStr.value.isEmpty()) {
            validationCheck!!(false, errorMessage)
        } else {
            validationCheck!!(true, errorMessage)
        }

        Spacer12DPH()

    }
}

@Composable
fun FileItem(
    name: String,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(24.dp)
            .background(color = primary, shape = RoundedCornerShape(5.dp))
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.AutoMirrored.Filled.InsertDriveFile,
            "contentDescription",
            tint = white_color,
            modifier = Modifier.padding(end = 2.dp)
        )

        TextView15_W400(
            modifier = Modifier
                .width(100.dp)
                .basicMarquee(Int.MAX_VALUE),
            value = name,
            color = white_color
        )

        Icon(
            Icons.Filled.Close,
            "contentDescription",
            tint = white_color,
            modifier = Modifier
                .padding(start = 2.dp)
                .clickable {
                    onRemoveClick()
                }
        )
    }
}


@Preview
@Composable
fun PreviewAttachmentField() {
    ComposeCommonUiTheme {
        Surface(color = gray_background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AttachmentFieldWithIcon(
                    title = "Attachment With Icon"
                )
                Spacer12DPH()
            }
        }
    }
}
package com.masum.common.ui.common_ui.component

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.masum.common.ui.common_ui.image_picker.CameraAndGalleryImagePicker
import com.masum.common.utils.ImageUtils
import com.masum.common.R
import com.masum.common.ui.theme.*

@Composable
fun TextFieldWithLabel(
    title: String,
    modifier: Modifier = Modifier,
    inputValue: MutableState<String>,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    maxLine: Int = 1,
    placeholder: String = "",
    isRequired: Boolean = false,
    isValid: Boolean = true,
    readOnly: Boolean = false,
    isRepeatable: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    validationMessage: String = "This field can't be empty",
    adMoreItem: List<String?>? = null,
    onValueChanged: (() -> Unit)? = {},
    onAddFieldValueChanged: ((SnapshotStateList<MutableState<String>>) -> Unit)? = {},
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }
) {
    val fields = remember { mutableStateListOf<MutableState<String>>() }
    LaunchedEffect(adMoreItem) {
        if (!adMoreItem.isNullOrEmpty()) {
            fields.clear()
            fields.addAll(adMoreItem.map { mutableStateOf(it ?: "") })
        } else {
            fields.clear()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row {
            TextView15_W600WithRequired(value = title, isRequired = isRequired)
        }
        Spacer4DPH()
        Row(

        ) {
            BasicTextField(
                modifier = modifier.weight(if (isRepeatable) 0.9f else 1f),
                inputValue = inputValue,
                inputType = inputType,

                imeAction = imeAction,
                placeholder = placeholder,
                maxLine = maxLine,
                readOnly = readOnly, onValueChanged = {
                    onValueChanged!!()
                }
            )

            if (isRepeatable)
                FloatingActionButton(modifier = Modifier.weight(.1f)) {
                    fields.add(mutableStateOf(""))
                }
        }

        if (isRepeatable) {
            fields.forEachIndexed { index, s ->
                Spacer8DPH()
                Row(

                ) {
                    BasicTextField(
                        modifier = modifier.weight(0.9f),
                        inputValue = fields[index],
                        inputType = inputType,
                        imeAction = imeAction,
                        placeholder = placeholder,
                        maxLine = maxLine,
                        readOnly = readOnly, onValueChanged = {
                            // onValueChanged!!()
                            onAddFieldValueChanged!!(fields)
                        }
                    )
                    FloatingActionButton(
                        modifier = Modifier.weight(.1f),
                        drawableId = Icons.Filled.Close
                    ) {
                        fields.removeAt(index)
                        onAddFieldValueChanged!!(fields)

                        //onAddMoreClick!!()

                    }
                }
            }

        }

        // Error Message
        if (isRequired && isErrorEnabled.value && inputValue.value.isNullOrEmpty()) {

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        } else if (isRequired && inputValue.value.isNullOrEmpty()) {
            validationCheck!!(false, errorMessage)
        } else {
            if (!isValid) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = text_err
                    )
                    Spacer4DPW()
                    TextView15_W400(value = validationMessage, color = text_err)
                }
                validationCheck!!(false, validationMessage)
            } else {
                validationCheck!!(true, "")
            }
        }
    }
}

@Composable
fun SpinnerWithLabel(
    title: String?,
    modifier: Modifier = Modifier,
    list: List<String>?,
    currentValue: MutableState<String>,
    placeholder: String = "",
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    onValueChanged: (() -> Unit)? = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row {
            TextView15_W600(value = title ?: "")
            if (isRequired) {
                Spacer4DPW()
                TextView15_W600(value = "*", color = red)
            }
        }
        Spacer4DPH()
        Row {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                //if (!list.isNullOrEmpty())
                DropdownItem(
                    list = list,
                    currentValue = currentValue,
                    placeholder = placeholder, onValueChanged = {
                        onValueChanged!!()
                    }
                )
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && currentValue.value.isEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}

@Composable
fun SpinnerWithLabel_Search(
    title: String?,
    modifier: Modifier = Modifier,
    list: List<String>?,
    currentValue: MutableState<String>,
    placeholder: String = "",
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    onValueChanged: (() -> Unit)? = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row {
            TextView15_W600(value = title ?: "")
            if (isRequired) {
                Spacer4DPW()
                TextView15_W600(value = "*", color = red)
            }
        }
        Spacer4DPH()
        Row {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                //if (!list.isNullOrEmpty())
                DropdownItemWithSearch(
                    list = list,
                    currentValue = currentValue,
                    placeholder = placeholder,
                    onValueChanged = {
                        onValueChanged!!()
                    }
                )
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && currentValue.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}

@Composable
fun SpinnerWithLabel_EditText(
    title: String?,
    modifier: Modifier = Modifier,
    list: List<String>?,
    currentValue: MutableState<String>,
    placeholder: String = "",
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty"
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row {
            TextView15_W600(value = title ?: "")
            if (isRequired) {
                Spacer4DPW()
                TextView15_W600(value = "*", color = red)
            }
        }
        Spacer4DPH()
        Row {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                //if (!list.isNullOrEmpty())
                DropdownItemWithEditText(
                    list = list,
                    currentValue = currentValue,
                    placeholder = placeholder
                )
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && currentValue.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}


@Composable
fun RectangleImageWithLabel(
    title: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = "",
    placeholderId: Int = R.drawable.placeholder,
    isRequired: Boolean = false,
    imageUri: String? = "",
    imageBitmap: Bitmap? = null,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "Please select an image",
    isBorderEnable: Boolean = true,
    expanded: MutableState<Boolean> = mutableStateOf(false),
    onClick: (() -> Unit)? = null,

    ) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                .border(
                    width = if (isBorderEnable) 1.dp else 0.dp,
                    color = if (isBorderEnable && expanded.value) primary
                    else if (isBorderEnable && !expanded.value) light_gray else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .background(
                    color = if (isBorderEnable) Color.White else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .clickable { onClick!!() }
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!imageUri.equals("")) {
                AsyncImage(
                    model = Uri.parse(imageUri),
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.Fit
                )
            } else if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.Fit
                )
            } else if (!imageUrl.equals("")) {
                NetworkImage(
                    modifier = modifier,
                    imgUrl = imageUrl,
                    placeHolder = placeholderId,
                    contentScale = ContentScale.Fit
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.add_photo),
                    contentDescription = "",
                    tint = primary,
                    modifier = Modifier.size(40.dp)
                )
                Spacer16DPH()
                TextView13_W400(
                    value = "Please click here to add photos from camera or gallery",
                    color = text_color,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && imageUrl.equals("") && imageUri.equals("") && imageBitmap == null) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}


@Composable
fun PhotoField(
    title: String,
    isRequired: Boolean = false,
    isValid: Boolean = true,
    isRepeatable: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    validationMessage: String = "This field can't be empty",
    adMoreItem: List<String?>? = null,
    onValueChanged: ((String) -> Unit)? = {},
    onAddFieldValueChanged: ((SnapshotStateList<MutableState<String>>) -> Unit)? = {},
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }
) {
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val registerIndex = rememberSaveable { mutableIntStateOf(-1) }
    val imageInputCurrVal = rememberSaveable { mutableStateOf("") }

    val fields = remember { mutableStateListOf<MutableState<String>>() }
    LaunchedEffect(adMoreItem) {
        if (!adMoreItem.isNullOrEmpty()) {
            fields.clear()
            fields.addAll(adMoreItem.map { mutableStateOf(it ?: "") })
        } else {
            fields.clear()
        }
    }

    CameraAndGalleryImagePicker(
        openDialog = openDialog
    ) { data ->
        if (registerIndex.value == -1) {
            imageInputCurrVal.value = data
            onValueChanged!!(imageInputCurrVal.value)
        } else {
            fields[registerIndex.value].value = data
            onAddFieldValueChanged!!(fields)
        }
        //  question.answer = imageInputCurrVal.value
    }

    Column {
        TextView15_W600WithRequired(value = title, isRequired = isRequired)
        Spacer4DPH()
        Row {
            RectangleImageWithLabel(
                modifier = Modifier.weight(if (isRepeatable) 0.9f else 1f),
                title = title,
                imageUri = imageInputCurrVal.value,
                imageBitmap = ImageUtils.decodeStringToImage(imageInputCurrVal.value),
                imageUrl = imageInputCurrVal.value,
                isRequired = isRequired,
                onClick = {
                    registerIndex.value = -1
                    openDialog.value = true
                }
            )
            if (isRepeatable)
                FloatingActionButton(modifier = Modifier.weight(.1f)) {
                    fields.add(mutableStateOf(""))
                }
        }

        if (isRepeatable) {
            fields.forEachIndexed { index, s ->
                Spacer8DPH()
                Row(

                ) {
                    RectangleImageWithLabel(
                        modifier = Modifier.weight(0.9f),
                        title = title,
                        imageUri = fields[index].value,
                        imageBitmap = ImageUtils.decodeStringToImage(fields[index].value),
                        isRequired = isRequired,
                        onClick = {
                            openDialog.value = true
                            registerIndex.intValue = index
                        }
                    )
                    FloatingActionButton(
                        modifier = Modifier.weight(.1f),
                        drawableId = Icons.Filled.Close
                    ) {
                        fields.removeAt(index)
                        onAddFieldValueChanged!!(fields)
                        //onAddMoreClick!!()
                    }
                }
            }

        }


        if (isRequired && isErrorEnabled.value && imageInputCurrVal.value.equals("") && imageInputCurrVal.value.equals(
                ""
            )
        ) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
            validationCheck!!(false, errorMessage)
        } else if (isRequired&& imageInputCurrVal.value.equals("") && imageInputCurrVal.value.equals(
                ""
            )) {
            validationCheck!!(false, errorMessage)
        } else {
            validationCheck!!(true, errorMessage)
        }

    }
    Spacer12DPH()

}

@Composable
fun CheckBoxWithLabel(
    title: String?,
    modifier: Modifier = Modifier,
    listOfOptions: List<String>?,
    selectedOptions: MutableState<Set<String>>,
    placeholder: String = "",
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty"
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        TextView15_W600WithRequired(value = title ?: "", isRequired = isRequired)

        Spacer4DPH()
        Row {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                //if (!list.isNullOrEmpty())
                CheckBoxField(
                    listOfOptions = listOfOptions,
                    selectedOptions = selectedOptions,
                    placeholder = placeholder
                )
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && selectedOptions.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
        }
    }
}

@Composable
fun CheckBoxListWithLabel(
    title: String?,
    modifier: Modifier = Modifier,
    listOfOptions: List<String>?,
    selectedOptions: MutableState<Set<String>>,
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    onValueChanged: (() -> Unit)? = {},
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        TextView15_W600WithRequired(value = title, isRequired = isRequired)

        Spacer12DPH()
        Column {
            listOfOptions?.forEach { option ->
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        modifier = Modifier.size(20.dp),
                        checked = selectedOptions.value.contains(option),
                        onCheckedChange = { selected ->
                            val currentSelected = selectedOptions.value.toMutableSet()
                            if (selected) {
                                currentSelected.add(option)
                            } else {
                                currentSelected.remove(option)
                            }
                            selectedOptions.value = currentSelected
                            onValueChanged!!()

                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = primary,
                            checkmarkColor = Color.White
                        )
                    )
                    Spacer8DPW()
                    TextView15_W400(value = option)
                }
                Spacer12DPH()
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && selectedOptions.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
            validationCheck!!(false, errorMessage)
        } else if (isRequired && selectedOptions.value.isNullOrEmpty()) {
            validationCheck!!(false, errorMessage)
        } else {
            validationCheck!!(true, errorMessage)
        }
    }
}

@Composable
fun RadioButtonWithLabel(
    title: String?,
    modifier: Modifier = Modifier,
    listOfOptions: List<String>?,
    selectedOption: MutableState<String>,
    isRequired: Boolean = false,
    isErrorEnabled: MutableState<Boolean> = mutableStateOf(false),
    errorMessage: String = "This field can't be empty",
    onValueChanged: (() -> Unit)? = {},
    validationCheck: ((Boolean, String) -> Unit)? = { v, m -> }

) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        TextView15_W600WithRequired(value = title, isRequired = isRequired)

        Spacer12DPH()
        Column {
            listOfOptions?.forEach { option ->
                Row(
                    Modifier
                        .selectable(
                            selected = (option == selectedOption.value),
                            onClick = {
                                selectedOption.value = option
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        modifier = Modifier.size(20.dp),
                        selected = (option == selectedOption.value),
                        onClick = {
                            selectedOption.value = option
                            onValueChanged!!()

                        },
                        colors = RadioButtonDefaults.colors(selectedColor = primary)
                    )
                    Spacer8DPW()
                    TextView15_W400(value = option)
                }
                Spacer12DPH()
            }
        }

        // Error Message
        if (isRequired && isErrorEnabled.value && selectedOption.value.isNullOrEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = text_err
                )
                Spacer4DPW()
                TextView15_W400(value = errorMessage, color = text_err)
            }
            validationCheck!!(false, errorMessage)
        } else if (isRequired && selectedOption.value.isNullOrEmpty()) {
            validationCheck!!(false, errorMessage)
        } else {
            validationCheck!!(true, errorMessage)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewInputFields() {
    val text = remember { mutableStateOf("") }

    ComposeCommonUiTheme {
        Surface(color = gray_background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                TextFieldWithLabel(
                    title = "TextField With Label",
                    inputValue = text,
                    placeholder = "Enter user id", onValueChanged = {

                    }
                )
                Spacer12DPH()

                SpinnerWithLabel(
                    title = "Spinner With Label",
                    list = listOf("Contract", "Extra", "Own"),
                    currentValue = remember { mutableStateOf("") },
                    placeholder = "Select From List"
                )
                Spacer12DPH()

                SpinnerWithLabel_EditText(
                    title = "Spinner With Label EditText",
                    list = listOf("Contract", "Extra", "Own"),
                    currentValue = remember { mutableStateOf("") },
                    placeholder = "Select From List"
                )
                Spacer12DPH()

                SpinnerWithLabel_Search(
                    title = "Spinner With Label Search",
                    list = listOf("Contract", "Extra", "Own"),
                    currentValue = remember { mutableStateOf("") },
                    placeholder = "Select From List"
                )
                Spacer12DPH()

                DateFieldWithIcon(
                    title = "DateField With Icon",
                    date = remember { mutableStateOf("") })
                Spacer12DPH()

                TimeFieldWithIcon(
                    title = "TimeField With Icon",
                    time = remember { mutableStateOf("") })
                Spacer12DPH()

                CheckBoxWithLabel(
                    title = "CheckBox With Label",
                    listOfOptions = listOf("Contract", "Extra", "Own"),
                    selectedOptions = remember { mutableStateOf(setOf()) },
                    placeholder = "Select From List"
                )
                Spacer12DPH()

                CheckBoxListWithLabel(
                    title = "CheckBox List With Label",
                    listOfOptions = listOf("Contract", "Extra", "Own"),
                    selectedOptions = remember { mutableStateOf(setOf()) }
                )
                Spacer12DPH()

                RadioButtonWithLabel(
                    title = "RadioButton With Label",
                    listOfOptions = listOf("Contract", "Extra", "Own"),
                    selectedOption = remember { mutableStateOf("Contract") }
                )
                Spacer12DPH()

                LocationFieldWithIcon(
                    title = "LocationField With Icon",
                    latitude = remember { mutableStateOf("") },
                    longitude = remember { mutableStateOf("") }
                )
                Spacer12DPH()

                RectangleImageWithLabel(
                    title = "Rectangle Image With Label"
                )
                Spacer12DPH()

                AttachmentFieldWithIcon(
                    title = "Attachment With Icon",
                    /*fileList = remember {
                        mutableStateListOf<String>(
                            "test.pdf",
                            "test2.pdf",
                            "test3.pdf",
                            "test4.pdf"
                        )
                    }*/
                )
                Spacer12DPH()

                TextFieldWithLabel(
                    title = "TextField With Label Longer",
                    modifier = Modifier.height(300.dp),
                    imeAction = ImeAction.Default,
                    inputValue = text,
                    placeholder = "Type Text (0/300)", onValueChanged = {

                    }
                )
                Spacer12DPH()
            }
        }
    }
}
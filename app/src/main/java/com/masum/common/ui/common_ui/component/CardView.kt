package com.masum.common.ui.common_ui.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.masum.common.utils.AppUtils
import com.masum.common.utils.AppUtils.showToast
import com.masum.common.R

import com.masum.common.ui.theme.*
import java.io.Serializable

@Composable
fun Vertical_TitleValue_CardView(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    titleColor: Color = text_green,
    valueColor: Color = text_dark,
    shadowColor: Color = shadow_color_blue,
    shadowBorderRadius: Dp = 5.dp
) {
    Card(
        modifier = modifier
            .drawColoredShadow(
                color = shadowColor,
                borderRadius = shadowBorderRadius,
                shadowRadius = 5.dp
            )
            .padding(bottom = 2.dp),
        colors = cardColor(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextView13_W500(value = title, color = titleColor)
            Spacer4DPH()
            TextView15_W500(value = value, color = valueColor)
        }
    }
}

@Composable
fun CardView_List(
    modifier: Modifier = Modifier,
    title: String,
    type: String? = null,
    subTitle: String? = null,
    description: String,
    instruction: String? = null,
    instructionColor: Color = text_color,
    imageUrl: String? = null,
    firstIcon: Int = R.drawable.placeholder_solid,
    endIcon: Int = R.drawable.visibility,
    isSelected: Boolean? = null,
    isImageVisible: Boolean = true,
    isEndIconVisible: Boolean = true,
    isDraft: Boolean = false,
    indicatorColor: Color = primary_dark,
    onCardClick: () -> Unit = {},
    onIconClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        CardDefaults.cardColors(
            containerColor =if(!isDraft) indicatorColor else light_background// Set the card's background color
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Row {
            Spacer4DPW()
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { onCardClick() }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isImageVisible) {
                    if (!imageUrl.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .aspectRatio(1f)
                                .background(circleColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            NetworkImage(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(50.dp)
                                    .clip(CircleShape),
                                imgUrl = imageUrl,
                                placeHolder = R.drawable.placeholder_solid,
                                contentScale = ContentScale.Fit
                            )
                        }

                    } else {
                        Icon(
                            painter = painterResource(id = firstIcon),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(56.dp)
                        )
                    }
                    Spacer8DPW()
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextView16_W600(
                            value = title,
                            color = text_dark,
                            modifier = Modifier
                                .weight(1f)
                                .basicMarquee(
                                    iterations = Int.MAX_VALUE,
                                    animationMode = MarqueeAnimationMode.Immediately
                                )
                        )
                        if (isSelected != null) {
                            Icon(
                                painter = painterResource(id = if (isSelected)  R.drawable.check_circle else R.drawable.uncheck_circle),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable(
                                        // This is to disable the ripple effect
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) { onIconClick() }
                            )
                        }
                        if (endIcon == R.drawable.arrow_right) {
                            Icon(
                                painter = painterResource(id = endIcon),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable(
                                        // This is to disable the ripple effect
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) { onIconClick() }
                            )
                        }
                    }
                    Spacer4DPH()
                    if (!type.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier
                                .background(color = primary, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp)
                        ) {
                            TextView13_W400(
                                value = type,
                                color = Color.White
                            )
                        }
                        Spacer4DPH()
                    }
                    if (subTitle != null) {
                        TextView14_W400(
                            value = subTitle,
                            color =if (isDraft) light_background else text_color,
                            modifier = Modifier.background(if (isDraft) indicatorColor else light_background, shape =  RoundedCornerShape(5.dp)).padding(if (isDraft)4.dp else 0.dp)
                        )
                        Spacer4DPH()
                    }
                    TextView12_W400(value = description, color = text_gray)
                    if (instruction != null) {
                        Spacer4DPH()
                        TextView12_W400(value = instruction, color = instructionColor)
                    }
                }
                if (isEndIconVisible && endIcon != R.drawable.arrow_right) {
                    Spacer8DPW()
                    Icon(
                        painter = painterResource(id = endIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                // This is to disable the ripple effect
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onIconClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun CardView_ActivityList(
    modifier: Modifier = Modifier,
    title: String,
    category: String,
    householdCode: String,
    beneficiaryCode: String,
    branch: String,
    submittedOn: String,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable { onCardClick() }
                .padding(12.dp)
        ) {
            TextView16_W500(value = title, color = text_dark)
            Spacer4DPH()

            Row {
                TextView12_W400(value = category, color = text_color, modifier = Modifier.weight(1f))
                TextView12_W400(value = householdCode, color = text_color, modifier = Modifier.weight(1f))
            }
            Spacer4DPH()

            Row {
                TextView12_W400(value = beneficiaryCode, color = text_color, modifier = Modifier.weight(1f))
                TextView12_W400(value = branch, color = text_color, modifier = Modifier.weight(1f))
            }
            Spacer4DPH()

            TextView12_W400(value = submittedOn, color = text_gray)
        }
    }
}

@Composable
fun CardView_UserList(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    description: String,
    typeList: List<String> = listOf(),
    endIcon: Int = R.drawable.arrow_right,
    endIconSize: Dp = 20.dp,
    isEndIconVisible: Boolean = true,
    onCardClick: () -> Unit,
    onEndIconClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable { onCardClick() }
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextView15_W600(value = title, color = text_dark, modifier = Modifier.weight(1f))
                if (isEndIconVisible) {
                    Icon(
                        painter = painterResource(id = endIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(endIconSize)
                            .clickable(
                                // This is to disable the ripple effect
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onEndIconClick()
                            }
                    )
                }
            }
            Spacer7DPH()

            if (typeList.isNotEmpty()) {
                Horizontal_TextList(textList = typeList)
                Spacer7DPH()
            }

            if (subTitle != null) {
                TextView13_W500(value = subTitle, color = text_color)
                Spacer7DPH()
            }
            TextView11_W500(value = description, color = text_color)
        }
    }
}

@Composable
fun CardView_UserDesc(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    description: String,
    typeList: List<String> = listOf(),
    imageUrl: String? = null,
    placeHolder: Int = R.drawable.placeholder_user
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(primary)
            .padding(16.dp)
    ) {
        NetworkImage(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            imgUrl = imageUrl,
            placeHolder = placeHolder,
            contentScale = ContentScale.Crop
        )
        Spacer8DPW()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            TextView16_W600(value = title, color = Color.White)

            if (typeList.isNotEmpty()) {
                Spacer7DPH()
                Horizontal_TextList(textList = typeList, isTextGradient = true)
            }

            if (!subTitle.isNullOrEmpty()) {
                Spacer7DPH()
                TextView13_W500(value = subTitle, color = Color.White)
            }

            if (!description.isNullOrEmpty()) {
                Spacer7DPH()
                TextView11_W500(value = description, color = Color.White)
            }
        }
    }
}

data class SampleDetailsData(
    var title: String? = null,
    var value: String? = null,
) : Serializable

@Composable
fun Details_CardView(
    modifier: Modifier = Modifier,
    heading: String? = null,
    detailsDataList: List<SampleDetailsData>,
) {
    var latCurrVal: Double? = null
    var lonCurrVal: Double? = null

    Card(
        modifier = Modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            if (!heading.isNullOrEmpty()) {
                TextView16_W700(
                    value = heading, color = green_light, modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(brush = gradientColor())
                )
                Spacer4DPH()
            }

            detailsDataList.forEachIndexed { index, item ->
                if (item.title?.equals("Contact No.") == true || item.title?.equals("Phone") == true) {
                    Row {
                        TextView16_W500(
                            value = item.title!!,
                            color = text_dark,
                            modifier = Modifier.weight(1f)
                        )
                        ContactNoWithGradient(value = item.value!!, modifier = Modifier.weight(1f))
                    }
                } else if (item.title?.equals("Farm Type") == true) {
                    Row {
                        val typeList = AppUtils.splitString(item.value, "|") ?: listOf()
                        TextView16_W400(
                            value = item.title ?: "",
                            color = text_dark,
                            modifier = Modifier.weight(1f)
                        )
                        Horizontal_TextList(textList = typeList, modifier = Modifier.weight(1f))
                    }
                } else if (item.title?.lowercase().equals("latitude")) {
                    latCurrVal = item.value?.toDoubleOrNull()
                } else if (item.title?.lowercase().equals("longitude")) {
                    lonCurrVal = item.value?.toDoubleOrNull()
                } else {
                    SampleRow(
                        title = item.title ?: "",
                        value = item.value ?: ""
                    )
                }
                if (index != (detailsDataList.size - 1)) Spacer4DPH()
            }

            if (latCurrVal != null && lonCurrVal != null && latCurrVal != 0.0 && lonCurrVal != 0.0) {
                Spacer4DPH()

                Card(shape = RoundedCornerShape(5.dp)) {
                    GoogleMapView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        latitude = latCurrVal ?: 0.0,
                        longitude = lonCurrVal ?: 0.0,
                        zoomLevel = 15f
                    )
                }
            }
        }
    }
}

@Composable
fun Details_CardView_GrayRow(
    modifier: Modifier = Modifier,
    heading: String? = null,
    detailsDataList: List<SampleDetailsData>
) {
    Card(
        modifier = Modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .background(gradientColor())
                .padding(8.dp)
        ) {
            if (!heading.isNullOrEmpty()) {
                TextView13_W500(
                    value = heading,
                    color = white_color,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            detailsDataList.forEachIndexed { index, item ->
                if (item.title?.equals("Contact No.") == true || item.title?.equals("Phone") == true) {
                    Row(
                        modifier = Modifier
                            .background(if (index % 2 == 0) grayRowColor else Color.Transparent)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        TextView13_W500(
                            value = item.title!!,
                            color = text_dark,
                            modifier = Modifier.weight(1f)
                        )
                        ContactNoWithGradient(value = item.value!!, modifier = Modifier.weight(1f))
                    }
                } else if (item.title?.equals("Reason for Change Feed") == true || item.title?.equals(
                        "Found Any Disease"
                    ) == true
                ) {
                    Row(
                        modifier = Modifier
                            .background(if (index % 2 == 0) grayRowColor else Color.Transparent)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        val typeList = AppUtils.splitString(item.value, ",") ?: listOf()
                        TextView13_W500(
                            value = item.title!!,
                            color = text_dark,
                            modifier = Modifier.weight(1f)
                        )
                        Horizontal_TextList(textList = typeList, modifier = Modifier.weight(1f))
                    }
                } else {
                    SampleRow(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        title = item.title!!,
                        value = item.value!!,
                        rowIndex = index
                    )
                }
            }
        }
    }
}

@Composable
fun Details_CardView_ImageView(
    modifier: Modifier = Modifier,
    heading: String? = null,
    imageUrl: String? = null,
    onImageClick: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        ImagePopUpDialog(
            imageUrl = imageUrl,
            setShowDialog = { openDialog.value = it }
        )
    }

    Card(
        modifier = Modifier.padding(bottom = 2.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .background(gradientColor())
                .padding(8.dp)
        ) {
            if (!heading.isNullOrEmpty()) {
                TextView13_W500(
                    value = heading,
                    color = white_color,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        imageUrl?.let {
            Column(
                modifier = modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NetworkImage(
                    modifier = Modifier
                        .clickable {
                            onImageClick()
                            //openDialog.value = true
                        },
                    contentScale = ContentScale.Fit,
                    imgUrl = imageUrl
                )
            }
        }
    }
}

@Composable
fun CustomProgressBar(
    progress: Int = 0,
    width: Dp
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(8.dp)
                .background(light_gray)
                .width(width)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(8.dp)
                    .background(gradientColor())
                    .width((width) * progress / 100)
            )
            //TextView11_W500(value = "$progress %", color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ContactNoWithGradient(
    modifier: Modifier = Modifier,
    value: String,
) {
    val context = LocalContext.current

    Row(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(gradientColor(), shape = RoundedCornerShape(4.dp))
                .clickable {
                    phoneCallIntent(context, value)
                }
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(13.dp)
            )
            Spacer4DPW()
            TextView13_W400(value = value, color = Color.White)
        }
    }
}

fun phoneCallIntent(context: Context, phoneNumber: String) {
    val uri = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_DIAL, uri)

    try {
        context.startActivity(intent)
    } catch (s: SecurityException) {
        context.showToast("An error occurred")
    }
}

@Composable
fun Horizontal_TextList(
    modifier: Modifier = Modifier,
    textList: List<String> = listOf(),
    isTextGradient: Boolean = false,
    isLeadingIconVisible: Boolean = false,
    leadingIcon: Int = R.drawable.description,
    onItemClick: () -> Unit = {}
) {
    // This helps to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        textList.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .background(
                        brush = if (!isTextGradient) gradientColor() else gradientUnspecifiedColor(),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        color = if (isTextGradient) Color.White else Color.Unspecified,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable(
                        // This is to disable the ripple effect
                        indication = null,
                        interactionSource = interactionSource
                    ) { onItemClick() }
                    .padding(horizontal = 4.dp)
            ) {
                if (isLeadingIconVisible) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = "",
                        tint = if (!isTextGradient) Color.White else Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer4DPW()
                }

                TextView13_W400(
                    value = item,
                    color = Color.White,
                    style = TextStyle(brush = if (isTextGradient) gradientColor() else null)
                )
            }
            Spacer4DPW()
        }
    }
}

@Composable
fun Vertical_TextList(
    modifier: Modifier = Modifier,
    textList: List<String> = listOf(),
    isTextGradient: Boolean = false,
    isLeadingIconVisible: Boolean = false,
    leadingIcon: Int = R.drawable.description,
    onItemClick: (Int) -> Unit = {}
) {
    // This helps to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        textList.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .background(
                        brush = if (!isTextGradient) gradientColor() else gradientUnspecifiedColor(),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        color = if (isTextGradient) Color.White else Color.Unspecified,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable(
                        // This is to disable the ripple effect
                        indication = null,
                        interactionSource = interactionSource
                    ) { onItemClick(index) }
                    .padding(horizontal = 4.dp)
            ) {
                if (isLeadingIconVisible) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = "",
                        tint = if (!isTextGradient) Color.White else Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer4DPW()
                }

                TextView13_W400(
                    value = item,
                    color = Color.White,
                    style = TextStyle(brush = if (isTextGradient) gradientColor() else null)
                )
            }
            Spacer4DPW()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardViewPreviews() {
    ComposeCommonUiTheme {
        val sampleInfoList: List<SampleDetailsData> = listOf(
            SampleDetailsData("Farm ID", "123456"),
            SampleDetailsData("Contact No.", "012 7890 3456"),
            SampleDetailsData("Region", "Mymensingh"),
            SampleDetailsData("Zone", "Brahmaputra"),
            SampleDetailsData("Address", "Enayetpur , Chauhali, Sirajganj-6751"),
            SampleDetailsData("MEO", "Asif Raihan"),
            SampleDetailsData("Agent", "ABC Traders")
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = gray_background
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                CardView_List(
                    title = "General Updates",
                    firstIcon = R.drawable.clipboard_icon,
                    description = "Submitted 19 OCT 2023, 10:10 AM",
                    onIconClick = { }
                )
                Spacer16DPH()

                CardView_List(
                    title = "Mohammad Nayem Islam",
                    subTitle = "123456, Brahmaputra, Mymensingh",
                    description = "Last update 19 OCT 2023, 10:10 AM",
                    endIcon = R.drawable.location,
                    onIconClick = { }
                )
                Spacer16DPH()

                CardView_UserList(
                    title = "Abul Kashem Poultry Farm",
                    subTitle = "Borobajar, Rahitpur,  Keranigonj, Dhaka",
                    description = "Last updated on 19 OCT 2023, 10:10 AM",
                    typeList = listOf("Poultry", "Fish", "Cattle"),
                    onCardClick = { }
                )
                Spacer16DPH()

                CardView_UserDesc(
                    title = "Abul Kashem Poultry Farm",
                    subTitle = "Borobajar, Rahitpur,  Keranigonj, Dhaka",
                    description = "Last updated on 19 OCT 2023, 10:10 AM",
                    typeList = listOf("Poultry", "Fish", "Cattle")
                )
                Spacer16DPH()

                Details_CardView(
                    heading = "Official Info",
                    detailsDataList = sampleInfoList
                )
                Spacer16DPH()

                Details_CardView_GrayRow(
                    heading = "Nayem Feed Shop",
                    detailsDataList = sampleInfoList
                )
                Spacer16DPH()
            }
        }
    }
}
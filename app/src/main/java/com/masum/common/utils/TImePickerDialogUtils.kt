package com.masum.common.utils

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import com.masum.common.utils.DateTimeUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TImePickerDialogUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun timePickerDialog(context: Context, time: MutableState<String>, showDialog: MutableState<Boolean>,){
        val mCalendar = Calendar.getInstance()
        val sdf = SimpleDateFormat(DateTimeUtils.HOUR_12_FORMAT)
        try {
            mCalendar.time = sdf.parse(time.value)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val mHour = mCalendar[Calendar.HOUR_OF_DAY]
        val mMinute = mCalendar[Calendar.MINUTE]
        // Creating a TimePicker dialod
        val mTimePickerDialog = TimePickerDialog(
            context,
            { _, mHour: Int, mMinute: Int ->
                //time.value = DateTimeUtils.appointmentTimeFormat("$mHour:$mMinute")
            }, mHour, mMinute, false
        )

        if (showDialog.value) {
            mTimePickerDialog.show()
            showDialog.value = false
        }
    }
}
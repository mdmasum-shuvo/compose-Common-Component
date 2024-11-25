package com.masum.common.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masum.common.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

data class TipsDate(
    var monthName: String = "",
    var dayNumber: String = "",
    val isLocked: Boolean = true,
)

object DateTimeUtils {

    const val HOUR_12_FORMAT = "hh:mm a"
    const val HOUR_24_FORMAT = "HH:mm:ss"
    const val DATE_TIME_Y_M_D_24 = "yyyy-MM-dd HH:mm:ss"
    const val D_M_Y = "dd-MM-yyyy"
    const val DATE_TIME_DD_MM_YYYYY_24 = "dd-MM-yyyy HH:mm:ss"
    const val DATE_TIME_YYYY_MM_DD_24 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_TIME_DD_MM_YYYY_12 = "dd MMMM yyyy, hh:mm aa"
    const val DATE_TIME_YYYYY_MM_DD_T_24_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"

    const val Y_M_D = "yyyy-MM-dd"
    const val DATE_MMMM_DD_YYYY = "MMM dd,yyyy"
    const val DD_MMMM_YYYY_STRING = "dd MMMM yyyy"
    const val DD_MMMM_COMA_YYYY_STRING = "dd MMMM, yyyy"
    const val DD_MMM_YYYY_STRING = "dd MMM yyyy"
    const val DD_MMM_YYYY = "dd-MMM-yyyy"
    const val DD_MMM_STRING = "dd MMMM"
    const val DATE_DD_MM_YYYY = "dd/MM/yyyy"
    const val DATE_T_ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_TIME_IN_WORD = "MMM dd,yyyy hh:mm a"
    const val DATE_TIME_IN_WORD_GAPE = "dd MMM,yyyy hh:mm a"
    const val FORMAT_TIME_AM_PM = "hh:mm aa"
    private var calendar: Calendar? = null
    var currentPosition = 0
    const val ONE_DAY_IN_MILLIS: Long = 86400000

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateTime(format: String): String {
        val formatter = SimpleDateFormat(format)
        val date = Date()
        return formatter.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateTimeBn(format: String): String {
        val formatter = SimpleDateFormat(format, Locale("bn"))
        val date = Date()
        return formatter.format(date)
    }

    private var _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date


    @Composable
    fun TimePickerDialog(
        selectedTime: MutableState<String>,
        showDialog: MutableState<Boolean>,
        context: Context,
        onValueChanged: (() -> Unit)? = {}
    ) {
        val c = Calendar.getInstance()
        val mHour = c[Calendar.HOUR_OF_DAY]
        val mMinute = c[Calendar.MINUTE]

        val timePickerDialog = TimePickerDialog(
            context, R.style.DialogTheme, TimePickerDialog.OnTimeSetListener
            { timePicker: TimePicker, mHour: Int, mMinute: Int ->

                val formattedTime: String = when {
                    mHour == 0 -> {
                        if (mMinute < 10) {
                            "${mHour + 12}:0${mMinute} am"
                        } else {
                            "${mHour + 12}:${mMinute} am"
                        }
                    }

                    mHour > 12 -> {
                        if (mMinute < 10) {
                            "${mHour - 12}:0${mMinute} pm"
                        } else {
                            "${mHour - 12}:${mMinute} pm"
                        }
                    }

                    mHour == 12 -> {
                        if (mMinute < 10) {
                            "${mHour}:0${mMinute} pm"
                        } else {
                            "${mHour}:${mMinute} pm"
                        }
                    }

                    else -> {
                        if (mMinute < 10) {
                            "${mHour}:${mMinute} am"
                        } else {
                            "${mHour}:${mMinute} am"
                        }
                    }
                }

                selectedTime.value = formattedTime

                showDialog.value = false
                onValueChanged!!()
            }, mHour, mMinute, false
        )
        //datePickerDialog.setCancelable(false)
        timePickerDialog.setOnDismissListener { showDialog.value = false }

        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel") { dialog, which ->
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                showDialog.value = false
            }
        }

        if (showDialog.value) {
            timePickerDialog.show()
        }
    }

    @Composable
    fun DatePickerDialog(
        selectedDate: MutableState<String>,
        showDialog: MutableState<Boolean>,
        context: Context,
        maxDate: String? = null,
        minDate: String? = null,
        maxDayNum: Int? = null,
        minDayNum: Int? = null, onValueChange: () -> Unit?
    ) {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat(DD_MMM_YYYY_STRING)
        try {
            c.time = sdf.parse(selectedDate.value)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val mHour = c[Calendar.HOUR_OF_DAY]
        val mMinute = c[Calendar.MINUTE]
        val datePickerDialog = DatePickerDialog(
            context, R.style.DialogTheme, DatePickerDialog.OnDateSetListener
            { datePicker: DatePicker, year: Int, month: Int, day: Int ->

                var mm = month
                mm += 1
                val month: StringBuilder = java.lang.StringBuilder()
                val date: StringBuilder = java.lang.StringBuilder()
                if (mm < 10) {
                    month.append(0)
                    month.append(mm)
                } else {
                    month.append(mm)
                }

                if (day < 10) {
                    date.append(0)
                    date.append(day)
                } else {
                    date.append(day)
                }

                var dateStr1 = "$date/$month/$year"
                selectedDate.value = "$year-$month-$date"


                selectedDate.value = changeDateFormat(
                    DATE_DD_MM_YYYY,
                    DD_MMM_YYYY_STRING,
                    dateStr1
                )!!

                showDialog.value = false
                onValueChange()

            }, year, month, day
        )
        //datePickerDialog.setCancelable(false)
        datePickerDialog.setOnDismissListener { showDialog.value = false }

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel") { dialog, which ->
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                showDialog.value = false
            }
        }

        // when given number of days
        if (maxDayNum != null) {
            datePickerDialog.datePicker.maxDate =
                getCurrTimeInMillis()
        }

        if (minDayNum != null) {
            datePickerDialog.datePicker.minDate =
                getCurrTimeInMillis() - (ONE_DAY_IN_MILLIS * minDayNum)
        }

        // when given date string
        if (maxDate != null) {
            datePickerDialog.datePicker.maxDate = dateToMillisecond(maxDate)!!.toLong()
        }

        if (minDate != null) {
            datePickerDialog.datePicker.minDate = dateToMillisecond(minDate)!!.toLong()
        }

        if (showDialog.value) {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val today = LocalDate.now()
                val nextMonth = today.minusDays(280)
                datePickerDialog.updateDate( nextMonth.year, nextMonth.monthValue-1, nextMonth.dayOfMonth)

            }*/
            datePickerDialog.show()
            //showDialog.value = false
        }
    }

    private fun dateToMillisecond(dateStr: String?): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            dateFormat.parse(dateStr).time.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
            "0"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(currentFormat: String, newFormat: String, date: String): String? {
        val datePickerFormat = SimpleDateFormat(currentFormat, Locale.getDefault())
        val myFormat = SimpleDateFormat(newFormat)
        return try {
            val dateFromUser = datePickerFormat.parse(date)
            myFormat.format(dateFromUser!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeNetworkToViewDateFormat(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(DATE_TIME_Y_M_D_24, Locale.getDefault())
        val myFormat = SimpleDateFormat(DD_MMM_YYYY)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeNetworkToFormat_DD_MMM_YYYY(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(Y_M_D, Locale.getDefault())
        val myFormat = SimpleDateFormat(DD_MMM_YYYY_STRING)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeNetworkToFormat_HOUR_12_FORMAT(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(DATE_TIME_YYYY_MM_DD_24, Locale.getDefault())
        val myFormat = SimpleDateFormat(HOUR_12_FORMAT)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeNetworkZoneToFormat_HOUR_12_FORMAT(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(DATE_TIME_YYYYY_MM_DD_T_24_Z, Locale.getDefault())
        val myFormat = SimpleDateFormat(HOUR_12_FORMAT)
        return try {
            val dateFromUser =
                date?.let { datePickerFormat.parse(it.replace("Z", getZoneOffset() ?: "")) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getZoneOffset(): String? {
        return try {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
            val currentLocalTime = calendar.time
            val zone: DateFormat = SimpleDateFormat("Z")
            val zoneOffset = zone.format(currentLocalTime)
            zoneOffset
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeToNetworkFormat_D_M_Y(date: String?): String? {

        val datePickerFormat = SimpleDateFormat(DD_MMM_YYYY_STRING, Locale.getDefault())
        val myFormat = SimpleDateFormat(D_M_Y)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun dateToMillis(date: String, dateFormat: String): Long {
        val datePickerFormat = SimpleDateFormat(dateFormat)

        val start =datePickerFormat.parse(date)
        // Convert to milliseconds
        val startMillis = start?.time
        return startMillis ?: 0
    }

    fun convertMillisTodays(millis:Long):Long{
        val millisInADay = 24 * 60 * 60 * 1000 // Milliseconds in a day
        val daysDiff = millis / millisInADay
        return daysDiff
    }

    @SuppressLint("SimpleDateFormat")
    fun changeToNetworkFormat_Y_M_D(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(DD_MMM_YYYY_STRING, Locale.getDefault())
        val myFormat = SimpleDateFormat(Y_M_D)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun changeNetworkToFormat_DATE_TIME_DD_MM_YYYYY_12(date: String?): String? {
        val datePickerFormat = SimpleDateFormat(DATE_TIME_YYYY_MM_DD_24, Locale.getDefault())
        val myFormat = SimpleDateFormat(DATE_TIME_DD_MM_YYYY_12)
        return try {
            val dateFromUser = date?.let { datePickerFormat.parse(it) }
            dateFromUser?.let { myFormat.format(it) }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getCurrTimeInMillis(): Long {
        /*Calendar cal = Calendar.getInstance();
        long msec = cal.getTimeInMillis();*/
        val mSec = System.currentTimeMillis()
        return mSec
    }

    fun millisToFormattedDateTime(milliSeconds: Long, dateFormat: String): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun getCurrentMonthStartEndDates(dateFormat: String): Pair<String, String> {
        // Get the current date
        val currentDate = LocalDate.now()

        // Get the first day of the current month (start date)
        val startOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth())

        // Get the last day of the current month (end date)
        val endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth())

        // Format the dates to your preferred format (e.g., yyyy-MM-dd)
        val formatter = DateTimeFormatter.ofPattern(dateFormat)

        return Pair(startOfMonth.format(formatter), endOfMonth.format(formatter))
    }

    fun getDateRangeForLastDays(days: Int, dateFormat: String): Pair<String, String> {
        val currentDate = LocalDate.now()

        // Calculate the start date by subtracting the specified number of days
        val startDate = currentDate.minusDays(days.toLong())

        // Format both the start and end dates to the desired format (e.g., yyyy-MM-dd)
        val formatter = DateTimeFormatter.ofPattern(dateFormat)

        return Pair(startDate.format(formatter), currentDate.format(formatter))
    }

    fun timeDifferenceInHours(startDateInMillis: Long): Long {

        // SimpleDateFormat converts the
        // string format to date object
        val sdf = SimpleDateFormat(DATE_TIME_DD_MM_YYYYY_24)
        val start_date: String =
            millisToFormattedDateTime(
                startDateInMillis,
                DATE_TIME_DD_MM_YYYYY_24
            )
        val end_date: String = getCurrentDateTime(DATE_TIME_DD_MM_YYYYY_24)

        // Try Block
        return try {
            // parse method is used to parse
            // the text from a string to
            // produce the date
            val d1 = sdf.parse(start_date)
            val d2 = sdf.parse(end_date)

            // Calculate time difference
            // in milliseconds
            val difference_In_Time = d2.time - d1.time

            // Calculate time difference in hours
            ((difference_In_Time
                    / (1000 * 60 * 60))
                    % 24)
        } // Catch the Exception
        catch (e: ParseException) {
            e.printStackTrace()
            0
        }
    }

    fun isBetweenTimeSlot(startHour: Int, endHour: Int): Boolean {
        val slotHours = (endHour - startHour) % 24
        val cal = Calendar.getInstance()
        // set calendar to TODAY 21:00:00.000
        cal[Calendar.HOUR_OF_DAY] = startHour
        val startHourMilli = cal.timeInMillis

        // add 14 hours = TOMORROW 07:00:00.000
        cal.add(Calendar.HOUR_OF_DAY, slotHours)
        val endHourMilli = cal.timeInMillis
        val currentMilli: Long = getCurrTimeInMillis()

        // checking if the current time is between time slot
        return currentMilli in startHourMilli..endHourMilli
    }

    fun findTimeDifference(startDateInMillis: Long): String? {

        // SimpleDateFormat converts the
        // string format to date object
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val start_date: String = millisToFormattedDateTime(startDateInMillis, "dd-MM-yyyy HH:mm:ss")
        val end_date: String = getCurrentDateTime("dd-MM-yyyy HH:mm:ss")
        var timeDifference = ""

        // Try Block
        return try {
            // parse method is used to parse
            // the text from a string to
            // produce the date
            val d1 = sdf.parse(start_date)
            val d2 = sdf.parse(end_date)

            // Calculate time difference
            // in milliseconds
            val difference_In_Time = d2.time - d1.time

            // Calculate time difference in
            // seconds, minutes, hours, years,
            // and days
            val difference_In_Seconds = ((difference_In_Time / 1000) % 60)
            val difference_In_Minutes = ((difference_In_Time / (1000 * 60)) % 60)
            val difference_In_Hours = ((difference_In_Time / (1000 * 60 * 60)) % 24)
            val difference_In_Years = (difference_In_Time / (1000L * 60 * 60 * 24 * 365))
            val difference_In_Days = ((difference_In_Time / (1000 * 60 * 60 * 24)) % 365)

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds
            if (difference_In_Years > 0) {
                timeDifference = "$timeDifference$difference_In_Years years, "
            }
            if (difference_In_Days > 0) {
                timeDifference = "$timeDifference$difference_In_Days days, "
            }
            if (difference_In_Hours > 0) {
                timeDifference = "$timeDifference$difference_In_Hours hours, "
            }
            if (difference_In_Minutes > 0) {
                timeDifference = "$timeDifference$difference_In_Minutes minutes, "
            }
            timeDifference = if (difference_In_Seconds > 0) {
                "$timeDifference$difference_In_Seconds seconds ago"
            } else {
                timeDifference + "0 seconds ago"
            }
            timeDifference
        } // Catch the Exception
        catch (e: ParseException) {
            e.printStackTrace()
            timeDifference
        }
    }
}




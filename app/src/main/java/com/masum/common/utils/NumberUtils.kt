package com.masum.common.utils

import android.util.Patterns
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

object NumberUtils {
    private val banglaDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
    private val englishDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')


    open fun isValidPhoneNumber(s: String?): Boolean {
        if (s == null || s == "") return false
        val PHONE_PATTERN = "^(013|014|015|016|017|018|019)\\d{8}$"

        val pattern: Pattern = Pattern.compile(PHONE_PATTERN)
        val matcher: Matcher = pattern.matcher(s)
        return matcher.matches()
    }

    fun roundValue(value: Double): String {
        var number = value
        if (value < 0) {
            number = value * (-1)
        }
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(number)
    }

    fun getDigitBanglaFromEnglish(number: String?): String {
        if (number == null) return ""
        val builder = StringBuilder()
        try {
            for (i in number.indices) {
                if (Character.isDigit(number[i])) {
                    if (number[i].code - 48 <= 9) {
                        builder.append(banglaDigits[number[i].code - 48])
                    } else {
                        builder.append(number[i])
                    }
                } else {
                    builder.append(number[i])
                }
            }
        } catch (e: Exception) {
            //logger.debug("getDigitBanglaFromEnglish: ",e);
            return ""
        }
        return builder.toString()
    }


    fun getDigitEnglishFromBangla(number: String?): String {
        val banglaToEnglishDigitsMap: HashMap<String, String> =
            HashMap<String, String>() //define empty hashmap
        banglaToEnglishDigitsMap["০"] = "0"
        banglaToEnglishDigitsMap["১"] = "1"
        banglaToEnglishDigitsMap["২"] = "2"
        banglaToEnglishDigitsMap["৩"] = "3"
        banglaToEnglishDigitsMap["৪"] = "4"
        banglaToEnglishDigitsMap["৫"] = "5"
        banglaToEnglishDigitsMap["৬"] = "6"
        banglaToEnglishDigitsMap["৭"] = "7"
        banglaToEnglishDigitsMap["৮"] = "8"
        banglaToEnglishDigitsMap["৯"] = "9"
        if (number == null) return ""
        val builder = java.lang.StringBuilder()
        try {
            for (i in number.indices) {
                if (banglaToEnglishDigitsMap.containsKey(number[i].toString())) {
                    builder.append(banglaToEnglishDigitsMap[number[i].toString()])
                } else {
                    builder.append(number[i])
                }
            }
        } catch (e: java.lang.Exception) {
            // logger.debug("getDigitBanglaFromEnglish: ",e);
            return ""
        }
        return builder.toString()
    }


    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
package com.masum.common.utils

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.runtime.MutableState
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Date

object AppUtils {
    var RUNNING_WEEK = ""

    fun Context.showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()


    fun getDeviceInfo(): String {
        val infoBuffer = StringBuffer()
        infoBuffer.append(
            """
        Model :${Build.MODEL}
        
        """.trimIndent()
        ) //The end-user-visible name for the end product.


        infoBuffer.append(
            """
        Manufacturer: ${Build.MANUFACTURER}
        
        """.trimIndent()
        ) //The manufacturer of the product/hardware.


        infoBuffer.append(
            """
        Brand: ${Build.BRAND}
        
        """.trimIndent()
        ) //The consumer-visible brand with which the product/hardware will be associated, if any.

        return infoBuffer.toString()
    }

    fun getRealPathFromURI(contentURI: Uri, context: Context): String? {
        val result: String?
        val cursor = context.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    fun getVolume(context: Context, volumeLevel: MutableState<Int>) {
        val am: AudioManager? = context.getSystemService(AUDIO_SERVICE) as AudioManager?
        volumeLevel.value = am!!.getStreamVolume(AudioManager.STREAM_MUSIC)

    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    fun encodeUrl(it: String): String {
        val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
        return encodedUrl ?: ""
    }

    fun decodeUrlString(it: String): String {
        val encodedUrl = it.replace("+", " ")
        return encodedUrl ?: ""
    }

    fun splitString(mainString: String?, separator: String): List<String>? {
        return mainString?.split(separator)?.toTypedArray()?.toList()
    }

    fun splitStringDot(mainString: String?): Array<String>? {
        return mainString?.split(".")?.toTypedArray()
    }

    fun splitStringUrl(mainString: String?): Array<String>? {
        return mainString?.split("/")?.toTypedArray()
    }

    fun splitStringHeight(mainString: String?): Array<String>? {
        return mainString?.split("’")?.toTypedArray()
    }

    fun Context.createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            externalCacheDir /* directory */
        )
        return image
    }



}
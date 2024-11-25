package com.masum.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


object ImageUtils {

    fun decodeStringToImage(imageString: String?): Bitmap? {
        if (!imageString.isNullOrEmpty()) {
            try {
                val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                return decodedImage
            } catch (e: IllegalArgumentException){
                return null
            }
        }
        return null
    }

    fun encodeImageToString(context: Context, imageUri: Uri?): String? {
        var bitmap:Bitmap? = null
        try {
            val parcelFileDescriptor = context.contentResolver.openFileDescriptor(imageUri!!, "r")
            val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()

            // initialize byte stream
            val stream = ByteArrayOutputStream()
            // compress Bitmap
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            // Initialize byte array
            val bytes = stream.toByteArray()
            // get base64 encoded string
            val imageString = Base64.encodeToString(bytes, Base64.DEFAULT)

            return imageString
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun uriToFile(context: Context, uri: String): File {
        val filePath = FileUtils.getFile(context, Uri.parse(uri))
        return File(filePath.absolutePath)
    }
}
package com.masum.common.utils

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


object FileUtils {
    /**
     * Get a file from a Uri.
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     */
    @SuppressLint("Range")
    @Throws(Exception::class)
    fun getFileFromUri(context: Context, uri: Uri): File {
        var path: String? = null


        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (DocumentsContract.isDocumentUri(context, uri)) { // TODO: 2015. 11. 17. KITKAT


                // ExternalStorageProvider


                if (isExternalStorageDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    val type = split[0]


                    if ("primary".equals(type, ignoreCase = true)) {
                        path = Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) { // DownloadsProvider

                    val id = DocumentsContract.getDocumentId(uri)
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), id.toLong()
                    )

                    path = getDataColumn(context, contentUri, null, null)
                } else if (isMediaDocument(uri)) { // MediaProvider


                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    val type = split[0]

                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }

                    val selection = "_id=?"
                    val selectionArgs = arrayOf(
                        split[1]
                    )

                    path = getDataColumn(context, contentUri, selection, selectionArgs)
                    Log.e("", "")
                } else if (isGoogleDrive(uri)) { // Google Drive
                    val TAG = "isGoogleDrive"
                    path = TAG
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    val acc = split[0]
                    val doc = split[1]

                    /*
                    * @details google drive document data. - acc , docId.
                    * */
                    return saveFileIntoExternalStorageByUri(context, uri)
                } // MediaStore (and general)
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                path = getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                path = uri.path
            }

            return File(path)
        } else {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            return File(cursor!!.getString(cursor.getColumnIndex("_data")))
        }
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is GoogleDrive.
     */
    fun isGoogleDrive(uri: Uri): Boolean {
        return uri.authority.equals("com.google.android.apps.docs.storage", ignoreCase = true)
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = MediaStore.Images.Media.DATA
        val projection = arrayOf(
            column
        )

        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }


    fun makeEmptyFileIntoExternalStorageWithTitle(title: String?): File {
        val root = Environment.getExternalStorageDirectory().absolutePath
        return File(root, title)
    }


    @SuppressLint("Range")
    fun getFileName(context: Context, uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }


    @Throws(Exception::class)
    fun saveBitmapFileIntoExternalStorageWithTitle(bitmap: Bitmap, title: String) {
        val fileOutputStream = FileOutputStream(
            makeEmptyFileIntoExternalStorageWithTitle(
                "$title.png"
            )
        )
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.close()
    }


    @Throws(Exception::class)
    fun saveFileIntoExternalStorageByUri(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val originalSize = inputStream!!.available()

        var bis: BufferedInputStream? = null
        var bos: BufferedOutputStream? = null
        val fileName = getFileName(context, uri)
        val file = makeEmptyFileIntoExternalStorageWithTitle(fileName)
        bis = BufferedInputStream(inputStream)
        bos = BufferedOutputStream(
            FileOutputStream(
                file, false
            )
        )

        val buf = ByteArray(originalSize)
        bis.read(buf)
        do {
            bos.write(buf)
        } while (bis.read(buf) != -1)

        bos.flush()
        bos.close()
        bis.close()

        return file
    }

    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File {
        val destinationFilename =
            File(context.filesDir.path + File.separatorChar + queryName(context, uri))
        try {
            context.contentResolver.openInputStream(uri).use { ins ->
                createFileFromStream(
                    ins!!, destinationFilename
                )
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
        return destinationFilename
    }

    fun createFileFromStream(ins: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while ((ins.read(buffer).also { length = it }) > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
    }

    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor = checkNotNull(
            context.contentResolver.query(uri, null, null, null, null)
        )
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }
}
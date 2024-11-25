package com.masum.common.ui.common_ui.image_picker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.masum.common.utils.AppUtils.createImageFile
import java.util.Objects

@Composable
fun CameraAndGalleryImagePicker(
    openDialog: MutableState<Boolean> = mutableStateOf(false),onValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    // For Image Picker
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                try {
                    // remember the permission until device reboots
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (e: Exception) {
                    Log.e("PersistableUriPermission", "No persistable permission grants found: $e")
                }
            }
            selectedImageUri = uri
        }
    )

    // For Camera Start
    var uri  by remember {
        mutableStateOf<Uri?>(null)
    }
    Log.e("camera", "Uri open $uri")


    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it){
                Log.e("camera", "Uri take $uri")
                selectedImageUri = uri
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        Log.e("camera", "Uri launch $uri")
        if (it) {
            uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                "${context.packageName}.provider", context.createImageFile()
            )
            cameraLauncher.launch(uri!!)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    if (openDialog.value) {
        ImagePickerAlertDialog(setShowDialog = { openDialog.value = it },
            selectCamera = {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    uri = FileProvider.getUriForFile(
                        Objects.requireNonNull(context),
                        "${context.packageName}.provider", context.createImageFile()
                    )
                    cameraLauncher.launch(uri!!)
                } else {
                    // Request a permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            selectGallery = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            })
    }
    // For Camera End

    LaunchedEffect(key1 = selectedImageUri) {
        selectedImageUri?.let {
            onValueChange(selectedImageUri.toString())
        }
    }
}
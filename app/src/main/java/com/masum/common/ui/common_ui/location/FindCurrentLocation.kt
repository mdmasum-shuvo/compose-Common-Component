package com.masum.common.ui.common_ui.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FindCurrentLocation(currentLatLng1:MutableState<LatAndLong>){
    val context= LocalContext.current
    val isGpsLocationOn = remember {
        mutableStateOf(false)
    }
    var currentLatLng = remember {
        mutableStateOf(LatAndLong())
    }
    if (isGpsLocationOn.value)
        currentLatLng = getUserLocation(context)

    currentLatLng1.value = currentLatLng.value

    val settingResultRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            Log.d("location", "Accepted")
            isGpsLocationOn.value = true
        } else {
            isGpsLocationOn.value = false
            Log.d("location", "Denied")
        }
    }

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    if (permissionState.allPermissionsGranted) {
        // Permissions are granted, proceed to fetch location
        LaunchedEffect(key1 = isGpsLocationOn.value, block = {
            checkDeviceLocationSettingsStart(context, { intentSenderRequest ->
                settingResultRequest.launch(intentSenderRequest)
            }, {
                isGpsLocationOn.value = true
                Log.e("location", "location enable")
            })

        })
    } else {
        // Request permissions if not granted
        LaunchedEffect(permissionState) {
            permissionState.launchMultiplePermissionRequest()
        }
    }
}

private fun checkDeviceLocationSettingsStart(
    context: Context,
    onDisabled: (IntentSenderRequest) -> Unit,
    onEnabled: () -> Unit
) {
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
        .setMinUpdateIntervalMillis(1000)
        .setMaxUpdateDelayMillis(1000)
        .build()

    val client: SettingsClient = LocationServices.getSettingsClient(context)
    val builder: LocationSettingsRequest.Builder = LocationSettingsRequest
        .Builder()
        .addLocationRequest(locationRequest)

    val gpsSettingTask: Task<LocationSettingsResponse> =
        client.checkLocationSettings(builder.build())

    gpsSettingTask.addOnSuccessListener {
        onEnabled()
    }
    gpsSettingTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest
                    .Builder(exception.resolution)
                    .build()
                onDisabled(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                // ignore here
            }
        }
    }

}

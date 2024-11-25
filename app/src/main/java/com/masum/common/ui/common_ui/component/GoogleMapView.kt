package com.masum.common.ui.common_ui.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.masum.common.R

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    markerTitle: String? = null,
    markerSnippet: String? = null,
    markerIcon: Int = R.drawable.location_marker,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    zoomLevel: Float = 10f,
    isShowInfoWindow: Boolean = false
) {
    // Set properties using MapProperties which you can use to recompose the map
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 20f, minZoomPreference = 5f)
        )
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(compassEnabled = false, mapToolbarEnabled = true, zoomControlsEnabled = false)
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(23.8103, 90.4125), 10f)  // Center point of Dhaka for initial MapView
    }

    Box(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = modifier,
            //contentPadding = PaddingValues(bottom = Dp.Infinity), // For Hiding Google Logo on Map
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ) {
            if (latitude != 0.0 && longitude != 0.0) {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), zoomLevel))
                GoogleMarkers(
                    title = markerTitle,
                    snippet = markerSnippet,
                    markerIcon = markerIcon,
                    latitude = latitude,
                    longitude = longitude,
                    isShowInfoWindow = isShowInfoWindow
                )
            }
        }
    }
}

@Composable
fun GoogleMarkers(
    title: String?,
    snippet: String?,
    markerIcon: Int = R.drawable.location_marker,
    markerIconBitmap: BitmapDescriptor? = null,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    isShowInfoWindow: Boolean = false
) {
    val  context = LocalContext.current

    val markerState = rememberMarkerState(position = LatLng(latitude, longitude))
    // Update the marker position
    LaunchedEffect(key1 = latitude, key2 = longitude) {
        val old = markerState.position
        if (old.latitude != latitude || old.longitude != longitude) {
            markerState.position = LatLng(latitude, longitude)
        }
    }
    if (isShowInfoWindow) markerState.showInfoWindow() // Keeps open the info window

    Marker(
        state = markerState,
        title = title,
        snippet = snippet,
        //icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        icon = markerIconBitmap ?: BitmapFromVector(context, markerIcon)
    )
}

fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    // below line is use to generate a drawable.
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

    // below line is use to set bounds to our vector drawable.
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    // below line is use to create a bitmap for our drawable which we have added.
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // below line is use to add bitmap in our canvas.
    val canvas = Canvas(bitmap)

    // below line is use to draw our vector drawable in canvas.
    vectorDrawable.draw(canvas)

    // after generating our bitmap we are returning our bitmap.
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

@Preview(showSystemUi = true)
@Composable
private fun GoogleMapViewPreview() {
    GoogleMapView(modifier = Modifier.size(100.dp))
}
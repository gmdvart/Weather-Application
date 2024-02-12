package com.example.weatherapplication.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.weatherapplication.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessToFineLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessToCoarseLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasAccessToFineLocation || !hasAccessToCoarseLocation || !isGpsEnabled) return null

        return suspendCancellableCoroutine { continuation ->
            locationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) continuation.resume(result)
                    else continuation.resume(null)

                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener { continuation.resume(it) }
                addOnFailureListener { continuation.resume(null) }
                addOnCanceledListener { continuation.cancel() }
            }
        }
    }
}